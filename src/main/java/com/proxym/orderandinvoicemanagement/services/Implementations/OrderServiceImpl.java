package com.proxym.orderandinvoicemanagement.services.Implementations;

import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderDTO;
import com.proxym.orderandinvoicemanagement.exception.IllegalOperationException;
import com.proxym.orderandinvoicemanagement.exception.ResourceNotFoundException;
import com.proxym.orderandinvoicemanagement.model.baseEntities.DateType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.TimeType;
import com.proxym.orderandinvoicemanagement.model.orderEntities.Order;
import com.proxym.orderandinvoicemanagement.model.orderEntities.OrderStatus;
import com.proxym.orderandinvoicemanagement.repositories.OrderRepository;
import com.proxym.orderandinvoicemanagement.services.IOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    public ModelMapper modelMapper;
    @Autowired
    public OrderRepository orderRepository;

    @Override
    public Set<OrderDTO> convertSetToDTO(Set<Order> orders) {
        return orders.stream().map(order -> modelMapper.map(order, OrderDTO.class)).collect(Collectors.toSet());
    }

    @Override
    public Set<OrderDTO> convertListToDTO(List<Order> list) {
        return list.stream().map(order -> modelMapper.map(order, OrderDTO.class)).collect(Collectors.toSet());
    }

    public TimeType getCurrentTime() {
        return TimeType.builder().timeContent(LocalTime.now().toString()).build();
    }

    public DateType getCurrentDate() {
        return DateType.builder().dateContent(LocalDate.now().toString()).build();
    }

    @Override
    public Order saveOrder(Order order) {
        if (order != null){
            return orderRepository.insert(order);
        }
        return null;
    }

    @Override
    public Order getOrderByTechnicalId(String technicalId) {
        Order order = orderRepository.findByTechnicalId(technicalId).orElse(null);
        if (order == null) {
            throw new ResourceNotFoundException("Retrieving Failed: Order with Id " + technicalId + " not found.");
        }
        return order;
//        return modelMapper.map(order,OrderDTO.class);
    }
    @Override
    public Order getOrderIfOperationIsLegal(String technicalId) throws IllegalOperationException{
        Order order = getOrderByTechnicalId(technicalId);
        if(order.getStatus().equals(OrderStatus.ACCEPTED) || order.getStatus().equals(OrderStatus.REJECTED) || order.getStatus().equals(OrderStatus.CANCELLED)){
            throw new IllegalOperationException("Illegal Operation Attempted: No action is allowed after having the order accepted, rejected or canceled.");
        }
        return order;
    }


}
