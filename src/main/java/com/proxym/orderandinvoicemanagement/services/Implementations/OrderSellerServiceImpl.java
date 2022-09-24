package com.proxym.orderandinvoicemanagement.services.Implementations;

import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderDTO;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderReferenceDTO;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderResponseDTO;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderResponseSimpleDTO;
import com.proxym.orderandinvoicemanagement.exception.IllegalOperationException;
import com.proxym.orderandinvoicemanagement.model.baseEntities.IndicatorType;
import com.proxym.orderandinvoicemanagement.model.communEntities.Party;
import com.proxym.orderandinvoicemanagement.model.config.Settings;
import com.proxym.orderandinvoicemanagement.model.orderEntities.Order;
import com.proxym.orderandinvoicemanagement.model.orderEntities.OrderResponse;
import com.proxym.orderandinvoicemanagement.model.orderEntities.OrderResponseSimple;
import com.proxym.orderandinvoicemanagement.model.orderEntities.OrderStatus;
import com.proxym.orderandinvoicemanagement.repositories.OrderRepository;
import com.proxym.orderandinvoicemanagement.repositories.OrderResponseRepository;
import com.proxym.orderandinvoicemanagement.repositories.OrderResponseSimpleRepository;
import com.proxym.orderandinvoicemanagement.services.IOrderSellerService;
import com.proxym.orderandinvoicemanagement.services.IOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderSellerServiceImpl implements IOrderSellerService {

    @Autowired
    public ModelMapper modelMapper;
    @Autowired
    public OrderRepository orderRepository;
    @Autowired
    public IOrderService orderService;
    @Autowired
    public OrderResponseRepository orderResponseRepository;
    @Autowired
    public OrderResponseSimpleRepository orderResponseSimpleRepository;


    @Override
    public Set<OrderDTO> getAllReceivedOrders(Party party) {
        Set<Order> orders =orderRepository.findAllBySellerSupplierParty_Party(party);
        return orderService.convertSetToDTO(orders);
    }

    @Override
    public OrderResponseSimpleDTO rejectOrder(OrderResponseSimpleDTO orderResponseSimpleDTO) throws IllegalArgumentException, IllegalOperationException {
        Order order = orderService.getOrderIfOperationIsLegal(orderResponseSimpleDTO.getOrderReference().getTechnicalId());
        order.setStatus(OrderStatus.REJECTED);
        orderResponseSimpleDTO.setAcceptedIndicator(new IndicatorType(true));
        orderResponseSimpleDTO.setIssueDate(orderService.getCurrentDate());
        orderResponseSimpleDTO.setIssueTime(orderService.getCurrentTime());
        OrderResponseSimple orderResponseSimple = modelMapper.map(orderResponseSimpleDTO,OrderResponseSimple.class);
        try {
            orderResponseSimple =orderResponseSimpleRepository.insert(orderResponseSimple);
        }catch (Exception e){
            throw new IllegalArgumentException("Invalid Input: Id of order response is already taken. Please provide new value.",e);
        }
        order.addToHistoryStack(orderResponseSimple);

        return modelMapper.map(orderResponseSimple,OrderResponseSimpleDTO.class);
    }

    @Override
    public OrderResponseDTO addDetail(OrderResponseDTO orderResponseDTO) throws IllegalArgumentException, IllegalOperationException {
        Set<OrderReferenceDTO> orderReferences = orderResponseDTO.getOrderReference();
        Set<Order> orders = new HashSet<>();
        orderReferences.forEach((ref) -> {
            Order order = orderService.getOrderIfOperationIsLegal(ref.getTechnicalId());
            orders.add(order);
        });
        orderResponseDTO.setIssueDate(orderService.getCurrentDate());
        orderResponseDTO.setIssueTime(orderService.getCurrentTime());
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
            orderRepository.save(order);
        });
        return modelMapper.map(finalOrderResponse,OrderResponseDTO.class);
    }


    @Override
    public OrderResponseSimpleDTO acceptOrderResponseRequired(OrderResponseSimpleDTO orderResponseSimpleDTO) {
        Order order = orderService.getOrderIfOperationIsLegal(orderResponseSimpleDTO.getTechnicalId());
        order.setStatus(OrderStatus.ACCEPTED);
        orderResponseSimpleDTO.setAcceptedIndicator(new IndicatorType(true));
        orderResponseSimpleDTO.setIssueTime(orderService.getCurrentTime());
        orderResponseSimpleDTO.setIssueDate(orderService.getCurrentDate());
        OrderResponseSimple orderResponseSimple = modelMapper.map(orderResponseSimpleDTO,OrderResponseSimple.class);
        try {
            orderResponseSimple = orderResponseSimpleRepository.insert(orderResponseSimple);
        }catch (Exception e){
            throw new IllegalArgumentException("Invalid Input: Id of order response is already taken. Please provide new value.",e);
        }
        order.addToHistoryStack(orderResponseSimple);
        orderRepository.save(order);
        return modelMapper.map(orderResponseSimple,OrderResponseSimpleDTO.class);
    }

    @Override
    public void acceptOrderWithoutResponse(String technicalId) {
        Order order = orderService.getOrderIfOperationIsLegal(technicalId);
        order.setStatus(OrderStatus.ACCEPTED);
        orderRepository.save(order);
    }
}
