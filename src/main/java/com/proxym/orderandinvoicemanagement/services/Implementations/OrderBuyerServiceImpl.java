package com.proxym.orderandinvoicemanagement.services.Implementations;

import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderCancellationDTO;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderChangeDTO;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderDTO;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderReferenceDTO;
import com.proxym.orderandinvoicemanagement.exception.IllegalOperationException;
import com.proxym.orderandinvoicemanagement.model.communEntities.Party;
import com.proxym.orderandinvoicemanagement.model.orderEntities.Order;
import com.proxym.orderandinvoicemanagement.model.orderEntities.OrderCancellation;
import com.proxym.orderandinvoicemanagement.model.orderEntities.OrderChange;
import com.proxym.orderandinvoicemanagement.model.orderEntities.OrderStatus;
import com.proxym.orderandinvoicemanagement.repositories.OrderCancellationRepository;
import com.proxym.orderandinvoicemanagement.repositories.OrderChangeRepository;
import com.proxym.orderandinvoicemanagement.repositories.OrderRepository;
import com.proxym.orderandinvoicemanagement.services.IOrderBuyerService;
import com.proxym.orderandinvoicemanagement.services.IOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderBuyerServiceImpl implements IOrderBuyerService {

    @Autowired
    public ModelMapper modelMapper;
    @Autowired
    public OrderRepository orderRepository;
    @Autowired
    public IOrderService orderService;
    @Autowired
    public OrderCancellationRepository orderCancellationRepository;
    @Autowired
    public OrderChangeRepository orderChangeRepository;

    @Override
    public Set<OrderDTO> getAll() {
        List<Order> orders = orderRepository.findAll();
        return orderService.convertListToDTO(orders);
    }

    @Override
    public Set<OrderDTO> getAllSentOrders(Party party) {
        Set<Order> orders = orderRepository.findAllByBuyerCustomerParty_Party(party);
        return orderService.convertSetToDTO(orders);
    }

    @Override
    public OrderChangeDTO changeOrder(OrderChangeDTO orderChangeDTO) throws IllegalArgumentException, IllegalOperationException {
        Order order = orderService.getOrderIfOperationIsLegal(orderChangeDTO.getOrderReference().getTechnicalId());
        order.setStatus(OrderStatus.NEGOTIATING);
        orderChangeDTO.setIssueDate(orderService.getCurrentDate());
        orderChangeDTO.setIssueTime(orderService.getCurrentTime());
        OrderChange orderChange = modelMapper.map(orderChangeDTO,OrderChange.class);
        try {
            orderChange = orderChangeRepository.insert(orderChange);
        }catch (Exception exception){
            throw new IllegalArgumentException("Invalid Input: Id of order change is already taken. Please provide new value.", exception);
        }
        order.addToHistoryStack(orderChange);
        orderRepository.save(order);
        return modelMapper.map(orderChange,OrderChangeDTO.class);
    }

    @Override
    public OrderCancellationDTO cancelOrder(OrderCancellationDTO orderCancellationDTO) throws IllegalArgumentException, IllegalOperationException {
        Set<OrderReferenceDTO> orderReferences = orderCancellationDTO.getOrderReference();
        Set<Order> orders = new HashSet<>();
        orderReferences.forEach((ref) -> {
            Order order = orderService.getOrderIfOperationIsLegal(ref.getTechnicalId());
            orders.add(order);
        });
        orderCancellationDTO.setIssueDate(orderService.getCurrentDate());
        orderCancellationDTO.setIssueTime(orderService.getCurrentTime());

        OrderCancellation orderCancellation = modelMapper.map(orderCancellationDTO,OrderCancellation.class);
        try {
            orderCancellation = orderCancellationRepository.insert(orderCancellation);
        } catch (Exception exception) {
            throw new IllegalArgumentException("Invalid Input: Id of order cancellation is already taken. Please provide new value.", exception);
        }
        OrderCancellation finalOrderCancellation = orderCancellation;
        orders.forEach(order -> {
            order.setStatus(OrderStatus.CANCELLED);
            order.addToHistoryStack(finalOrderCancellation);
            orderRepository.save(order);
        });
        return modelMapper.map(finalOrderCancellation,OrderCancellationDTO.class);
    }


    @Override
    public OrderDTO placeOrder(OrderDTO orderDTO) throws IllegalArgumentException {
        orderDTO.setIssueDate(orderService.getCurrentDate());
        orderDTO.setIssueTime(orderService.getCurrentTime());
        orderDTO.setStatus(OrderStatus.PENDING);
        Order order = modelMapper.map(orderDTO,Order.class);
        try {
            order = orderRepository.insert(order);
        } catch (Exception exception) {
            throw new IllegalArgumentException("Invalid Input: Id of order is already taken. Please provide new value for it.", exception);
        }
        return modelMapper.map(order,OrderDTO.class);
    }

    @Override
    public void acceptOrder(String technicalId) throws IllegalOperationException {
        Order order = orderService.getOrderIfOperationIsLegal(technicalId);
        order.setStatus(OrderStatus.ACCEPTED);
        orderRepository.save(order);
    }


}
