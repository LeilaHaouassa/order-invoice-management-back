package com.proxym.orderandinvoicemanagement.services.Implementations;

import com.proxym.orderandinvoicemanagement.dto.commun.CustomerPartyDTO;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.*;
import com.proxym.orderandinvoicemanagement.exception.IllegalOperationException;
import com.proxym.orderandinvoicemanagement.model.baseEntities.IndicatorType;
import com.proxym.orderandinvoicemanagement.model.orderEntities.*;
import com.proxym.orderandinvoicemanagement.repositories.OrderRepository;
import com.proxym.orderandinvoicemanagement.repositories.OrderResponseRepository;
import com.proxym.orderandinvoicemanagement.repositories.OrderResponseSimpleRepository;
import com.proxym.orderandinvoicemanagement.services.IOrderSellerService;
import com.proxym.orderandinvoicemanagement.services.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class OrderSellerServiceImpl implements IOrderSellerService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private OrderResponseRepository orderResponseRepository;
    @Autowired
    private OrderResponseSimpleRepository orderResponseSimpleRepository;



    @Override
    public Set<OrderDTO> getAllReceivedOrders(String sellerPartyId) {
        Set<Order> orders = orderRepository.findAllBySellerSupplierParty_Party_TechnicalId(sellerPartyId);
        return orderService.convertSetToDTO(orders);
    }

    @Override
    public OrderDTO rejectOrder(String sellerPartyId, OrderResponseSimpleDTO orderResponseSimpleDTO) throws IllegalArgumentException, IllegalOperationException {
        orderResponseSimpleDTO.setActionType(ActionType.REJECT);
        OrderReferenceDTO referenceDTO = orderResponseSimpleDTO.getOrderReference();
        Order order = getOrderIfOperationIsLegal(referenceDTO.getTechnicalId());
        orderResponseSimpleDTO.setOrderReference(orderService.assignRestOfOrderRefData(referenceDTO,order));
        CustomerPartyDTO customerPartyDTO = modelMapper.map(order.getBuyerCustomerParty(),CustomerPartyDTO.class);
        orderResponseSimpleDTO.setBuyerCustomerParty(customerPartyDTO);
        order.setStatus(OrderStatus.REJECTED);
        orderResponseSimpleDTO.setAcceptedIndicator(new IndicatorType(false));
        orderResponseSimpleDTO.setIssueDate(orderService.getCurrentDate());
        orderResponseSimpleDTO.setIssueTime(orderService.getCurrentTime());
        orderResponseSimpleDTO = orderService.assignSellerToOrderResponseSimpleDTOByPartyId(sellerPartyId,orderResponseSimpleDTO);
        OrderResponseSimple orderResponseSimple = modelMapper.map(orderResponseSimpleDTO,OrderResponseSimple.class);
        try {
            orderResponseSimple =orderResponseSimpleRepository.insert(orderResponseSimple);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new IllegalArgumentException("Invalid Input: Id of order response is already taken. Please provide new value.",e);
        }
        order.addToHistoryStack(orderResponseSimple);
        orderRepository.save(order);
        return modelMapper.map(order,OrderDTO.class);
    }

    @Override
    public Set<OrderDTO> addDetail(String sellerPartyId, OrderResponseDTO orderResponseDTO) throws IllegalArgumentException, IllegalOperationException {
        orderResponseDTO.setActionType(ActionType.NEGOTIATE);
        Set<OrderReferenceDTO> orderReferences = orderResponseDTO.getOrderReference();
        Set<Order> orders = new HashSet<>();
        orderReferences.forEach((ref) -> {
            Order order = getOrderIfOperationIsLegal(ref.getTechnicalId());
            orderReferences.remove(ref);
            ref = orderService.assignRestOfOrderRefData(ref, order);
            orderReferences.add(ref);
            orders.add(order);
        });
        orderResponseDTO.setOrderReference(orderReferences);
        CustomerPartyDTO customerPartyDTO = modelMapper.map(orders.stream().findFirst().orElse(new Order()).getBuyerCustomerParty(),CustomerPartyDTO.class);
        orderResponseDTO.setBuyerCustomerParty(customerPartyDTO);
        orderResponseDTO.setIssueDate(orderService.getCurrentDate());
        orderResponseDTO.setIssueTime(orderService.getCurrentTime());
        orderResponseDTO = orderService.assignSellerToOrderResponseDTOByPartyId(sellerPartyId,orderResponseDTO);
        OrderResponse orderResponse = modelMapper.map(orderResponseDTO,OrderResponse.class);
        try {
            orderResponse = orderResponseRepository.insert(orderResponse);
        } catch (Exception e){
            throw new IllegalArgumentException("Invalid Input: Id of order response is already taken. Please provide new value.",e);
        }
        OrderResponse finalOrderResponse = orderResponse;
        orders.forEach(order -> {
            order.setStatus(OrderStatus.NEGOTIATING);
            order.addToHistoryStack(finalOrderResponse);
            order.setIdOfDocumentForOrderChange(finalOrderResponse.getTechnicalId());
            orderRepository.save(order);
        });
        return orderService.convertSetToDTO(orders);
    }


    @Override
    public OrderDTO acceptOrderResponseRequired(String sellerPartyId, OrderResponseSimpleDTO orderResponseSimpleDTO) {
        orderResponseSimpleDTO.setActionType(ActionType.CONFIRM);
        OrderReferenceDTO referenceDTO = orderResponseSimpleDTO.getOrderReference();
        Order order = getOrderIfOperationIsLegal(referenceDTO.getTechnicalId());
        orderResponseSimpleDTO.setOrderReference(orderService.assignRestOfOrderRefData(referenceDTO,order));
        CustomerPartyDTO customerPartyDTO = modelMapper.map(order.getBuyerCustomerParty(),CustomerPartyDTO.class);
        orderResponseSimpleDTO.setBuyerCustomerParty(customerPartyDTO);
        order.setStatus(OrderStatus.CONFIRMED);
        orderResponseSimpleDTO.setAcceptedIndicator(new IndicatorType(true));
        orderResponseSimpleDTO.setIssueTime(orderService.getCurrentTime());
        orderResponseSimpleDTO.setIssueDate(orderService.getCurrentDate());
        orderResponseSimpleDTO = orderService.assignSellerToOrderResponseSimpleDTOByPartyId(sellerPartyId,orderResponseSimpleDTO);
        OrderResponseSimple orderResponseSimple = modelMapper.map(orderResponseSimpleDTO,OrderResponseSimple.class);
        try {
            orderResponseSimple = orderResponseSimpleRepository.insert(orderResponseSimple);
        }catch (Exception e){
            throw new IllegalArgumentException("Invalid Input: Id of order response is already taken. Please provide new value.",e);
        }
        order.addToHistoryStack(orderResponseSimple);
        orderRepository.save(order);
        return modelMapper.map(order,OrderDTO.class);
    }

    @Override
    public OrderDTO acceptOrderWithoutResponse(String orderTechnicalId) {
        Order order = getOrderIfOperationIsLegal(orderTechnicalId);
        order.setStatus(OrderStatus.ACCEPTED);
        orderRepository.save(order);
        return  modelMapper.map(order,OrderDTO.class);
    }

    @Override
    public Order getOrderIfOperationIsLegal(String technicalId) throws IllegalOperationException {
        OrderDTO orderDTO = orderService.getOrderByTechnicalId(technicalId);
        Order order = modelMapper.map(orderDTO,Order.class);
        if( !order.getStatus().equals(OrderStatus.PENDING) && !order.getStatus().equals(OrderStatus.CHANGED) ){
            throw new IllegalOperationException("Illegal Operation Attempted: No action is allowed unless the order is created or changed.");
        }
        return order;
    }

    @Override
    public Object getDocumentForNegotiation(String orderTechnicalId) {
        OrderDTO orderDTO = orderService.getOrderByTechnicalId(orderTechnicalId);
        String documentId = orderDTO.getIdOfDocumentToNegotiateOver();
        if( orderDTO.getTechnicalId().equals(documentId)){
            return orderDTO;
        }else{
           List<Object> stack = orderDTO.getHistoryStack();
            for (int i = stack.size() - 1; i >= 0; i--)
            {
                if( stack.get(i) instanceof OrderChangeDTO){
                    if (((OrderChangeDTO)stack.get(i)).getTechnicalId().equals(documentId)){
                        return stack.get(i);
                    }
                }
            }
        }
        return null;
    }

}
