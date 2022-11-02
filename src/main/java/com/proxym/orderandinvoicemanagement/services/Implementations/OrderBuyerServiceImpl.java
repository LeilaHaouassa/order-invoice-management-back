package com.proxym.orderandinvoicemanagement.services.Implementations;

import com.proxym.orderandinvoicemanagement.dto.commun.CustomerPartyDTO;
import com.proxym.orderandinvoicemanagement.dto.commun.PartyDTO;
import com.proxym.orderandinvoicemanagement.dto.commun.PartyRefDTO;
import com.proxym.orderandinvoicemanagement.dto.commun.SupplierPartyDTO;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.*;
import com.proxym.orderandinvoicemanagement.exception.IllegalOperationException;
import com.proxym.orderandinvoicemanagement.exception.ResourceNotFoundException;
import com.proxym.orderandinvoicemanagement.model.orderEntities.*;
import com.proxym.orderandinvoicemanagement.repositories.OrderCancellationRepository;
import com.proxym.orderandinvoicemanagement.repositories.OrderChangeRepository;
import com.proxym.orderandinvoicemanagement.repositories.OrderRepository;
import com.proxym.orderandinvoicemanagement.services.IOrderBuyerService;
import com.proxym.orderandinvoicemanagement.services.IOrderService;
import com.proxym.orderandinvoicemanagement.services.IPartyService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Slf4j
@Service
public class OrderBuyerServiceImpl implements IOrderBuyerService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private OrderCancellationRepository orderCancellationRepository;
    @Autowired
    private OrderChangeRepository orderChangeRepository;

    private boolean checkIfOperationIsLegal(OrderStatus status){
        return status.equals(OrderStatus.NEGOTIATING) || status.equals(OrderStatus.CONFIRMED) ;
    }

    private boolean checkIfChangeIsLegal(OrderStatus status){
        return checkIfOperationIsLegal(status) || status.equals(OrderStatus.ACCEPTED) ;
    }


    @Override
    public Set<OrderDTO> getAllSentOrders(String buyerPartyId) {
        Set<Order> orders = orderRepository.findAllByBuyerCustomerParty_Party_TechnicalId(buyerPartyId);
        return orderService.convertSetToDTO(orders);
    }

    @Override
    public OrderDTO changeOrder(String buyerPartyId,OrderChangeDTO orderChangeDTO) throws IllegalArgumentException, IllegalOperationException {
        orderChangeDTO.setActionType(ActionType.CHANGE);
        OrderReferenceDTO referenceDTO = orderChangeDTO.getOrderReference();
        Order order = getOrderIfChangeIsLegal(referenceDTO.getTechnicalId());
        orderChangeDTO.setOrderReference(orderService.assignRestOfOrderRefData(referenceDTO,order));
        order.setStatus(OrderStatus.CHANGED);
        orderChangeDTO.setIssueDate(orderService.getCurrentDate());
        orderChangeDTO.setIssueTime(orderService.getCurrentTime());
        orderChangeDTO = orderService.assignBuyerToOrderChangeDTOByPartyId(buyerPartyId,orderChangeDTO);
        SupplierPartyDTO supplierPartyDTO = modelMapper.map(order.getSellerSupplierParty(),SupplierPartyDTO.class);
        orderChangeDTO.setSellerSupplierParty(supplierPartyDTO);
        OrderChange orderChange = modelMapper.map(orderChangeDTO,OrderChange.class);
        try {
            orderChange = orderChangeRepository.insert(orderChange);
        }catch (Exception exception){
            throw new IllegalArgumentException("Invalid Input: Id of order change is already taken. Please provide new value.", exception);
        }
        order.addToHistoryStack(orderChange);
        order.setIdOfDocumentToNegotiateOver(orderChange.getTechnicalId());
        order.setIdOfDocumentForInvoice(orderChange.getTechnicalId());
        order = orderRepository.save(order);
        return modelMapper.map(order,OrderDTO.class);
    }

    @Override
    public Set<OrderDTO> cancelOrder(String buyerPartyId,OrderCancellationDTO orderCancellationDTO) throws IllegalArgumentException, IllegalOperationException {
        orderCancellationDTO.setActionType(ActionType.CANCEL);
        Set<OrderReferenceDTO> orderReferences = orderCancellationDTO.getOrderReference();
        Set<Order> orders = new HashSet<>();
        orderReferences.forEach((ref) -> {
            Order order = getOrderIfOperationIsLegal(ref.getTechnicalId());
            orderReferences.remove(ref);
            ref = orderService.assignRestOfOrderRefData(ref, order);
            orderReferences.add(ref);
            orders.add(order);
        });
        orderCancellationDTO.setOrderReference(orderReferences);
        SupplierPartyDTO supplierPartyDTO = modelMapper.map(orders.stream().findFirst().orElse(new Order()).getBuyerCustomerParty(),SupplierPartyDTO.class);
        orderCancellationDTO.setSellerSupplierParty(supplierPartyDTO);
        orderCancellationDTO.setIssueDate(orderService.getCurrentDate());
        orderCancellationDTO.setIssueTime(orderService.getCurrentTime());
        orderCancellationDTO = orderService.assignBuyerToOrderCancellationDTOByPartyId(buyerPartyId,orderCancellationDTO);
        log.info(orderCancellationDTO.toString());
        OrderCancellation orderCancellation = modelMapper.map(orderCancellationDTO,OrderCancellation.class);
        try {
            orderCancellation = orderCancellationRepository.insert(orderCancellation);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new IllegalArgumentException("Invalid Input: Id of order cancellation is already taken. Please provide new value.", exception);
        }
        OrderCancellation finalOrderCancellation = orderCancellation;
        orders.forEach(order -> {
            order.setStatus(OrderStatus.CANCELLED);
            order.addToHistoryStack(finalOrderCancellation);
            orderRepository.save(order);
        });
        return orderService.convertSetToDTO(orders);
    }

    @Override
    public OrderDTO placeOrder(String buyerPartyId,OrderDTO orderDTO) throws IllegalArgumentException, ResourceNotFoundException {
        orderDTO.setActionType(ActionType.SEND);
        orderDTO = orderService.assignBuyerToOrderDTOByPartyId(buyerPartyId,orderDTO);
        orderDTO.setIssueDate(orderService.getCurrentDate());
        orderDTO.setIssueTime(orderService.getCurrentTime());
        orderDTO.setStatus(OrderStatus.PENDING);
        log.info(orderDTO.toString());
        Order order = modelMapper.map(orderDTO,Order.class);
        log.info(order.toString());

        try {
            order = orderRepository.insert(order);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new IllegalArgumentException("Invalid Input: Id of order is already taken. Please provide new value for it.", exception);
        }
        order.setIdOfDocumentToNegotiateOver(order.getTechnicalId());
        order.setIdOfDocumentForInvoice((order.getTechnicalId()));
        order = orderRepository.save(order);
        return modelMapper.map(order,OrderDTO.class);
    }

    @Override
    public OrderDTO acceptOrder(String orderTechnicalId) throws IllegalOperationException {
        Order order = getOrderIfOperationIsLegal(orderTechnicalId);
        order.setStatus(OrderStatus.ACCEPTED);
        orderRepository.save(order);
        return  modelMapper.map(order,OrderDTO.class);
    }

    @Override
    public Order getOrderIfOperationIsLegal(String technicalId) throws IllegalOperationException {
        OrderDTO orderDTO = orderService.getOrderByTechnicalId(technicalId);
        Order order = modelMapper.map(orderDTO,Order.class);
        if(!checkIfOperationIsLegal(order.getStatus())){
            throw new IllegalOperationException("Illegal Operation Attempted: No action is allowed unless the order is confirmed or negotiated.");
        }
        return order;
    }

    @Override
    public Order getOrderIfChangeIsLegal(String technicalId) throws IllegalOperationException {
        OrderDTO orderDTO = orderService.getOrderByTechnicalId(technicalId);
        Order order = modelMapper.map(orderDTO,Order.class);
        if(!checkIfChangeIsLegal(order.getStatus())){
            throw new IllegalOperationException("Illegal Operation Attempted: No action is allowed is the order accepted, confirmed or negotiated.");
        }
        return order;
    }

    @Override
    public OrderResponseDTO getDocumentForOrderChange(String orderTechnicalId) {
        OrderDTO orderDTO = orderService.getOrderByTechnicalId(orderTechnicalId);
        String documentId = orderDTO.getIdOfDocumentForOrderChange();
        List<Object> stack = orderDTO.getHistoryStack();
        OrderResponse orderResponse = null;
        for (int i = stack.size() - 1; i >= 0; i--)
        {
            if( stack.get(i) instanceof OrderResponse) {
                if (((OrderResponse) stack.get(i)).getTechnicalId().equals(documentId)) {
                    orderResponse = (OrderResponse) stack.get(i);
                }
            }
        }
        return modelMapper.map(orderResponse,OrderResponseDTO.class);
    }


}
