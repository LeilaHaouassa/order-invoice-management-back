package com.proxym.orderandinvoicemanagement.services.Implementations;

import com.proxym.orderandinvoicemanagement.dto.commun.PartyDTO;
import com.proxym.orderandinvoicemanagement.dto.commun.PartyRefDTO;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.*;
import com.proxym.orderandinvoicemanagement.exception.IllegalOperationException;
import com.proxym.orderandinvoicemanagement.exception.ResourceNotFoundException;
import com.proxym.orderandinvoicemanagement.model.baseEntities.DateType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.TimeType;
import com.proxym.orderandinvoicemanagement.model.communEntities.Party.Party;
import com.proxym.orderandinvoicemanagement.model.communEntities.Party.PartyRef;
import com.proxym.orderandinvoicemanagement.model.orderEntities.Order;
import com.proxym.orderandinvoicemanagement.model.orderEntities.OrderStatus;
import com.proxym.orderandinvoicemanagement.repositories.OrderRepository;
import com.proxym.orderandinvoicemanagement.repositories.PartyRepository;
import com.proxym.orderandinvoicemanagement.services.IOrderService;
import com.proxym.orderandinvoicemanagement.services.IPartyService;
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
    private ModelMapper modelMapper;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private IPartyService partyService;

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
    }
    @Override
    public Order getOrderIfOperationIsLegal(String technicalId) throws IllegalOperationException{
        Order order = getOrderByTechnicalId(technicalId);
        if(order.getStatus().equals(OrderStatus.ACCEPTED) || order.getStatus().equals(OrderStatus.REJECTED) || order.getStatus().equals(OrderStatus.CANCELLED)){
            throw new IllegalOperationException("Illegal Operation Attempted: No action is allowed after having the order accepted, rejected or canceled.");
        }
        return order;
    }

    @Override
    public OrderDTO assignBuyerToOrderDTOByPartyId(String buyerPartyId, OrderDTO orderDTO) {
        PartyDTO buyerParty = partyService.getPartyById(buyerPartyId);
        PartyRefDTO buyerPartyRef = modelMapper.map(buyerParty, PartyRefDTO.class);
        orderDTO.getBuyerCustomerParty().setParty(buyerPartyRef);
        return orderDTO;
    }

    @Override
    public OrderChangeDTO assignBuyerToOrderChangeDTOByPartyId(String buyerPartyId, OrderChangeDTO orderChangeDTO) {
        PartyDTO buyerParty = partyService.getPartyById(buyerPartyId);
        PartyRefDTO buyerPartyRef = modelMapper.map(buyerParty, PartyRefDTO.class);
        orderChangeDTO.getBuyerCustomerParty().setParty(buyerPartyRef);
        return orderChangeDTO;
    }

    @Override
    public OrderCancellationDTO assignBuyerToOrderCancellationDTOByPartyId(String buyerPartyId, OrderCancellationDTO orderCancellationDTO) {
        PartyDTO buyerParty = partyService.getPartyById(buyerPartyId);
        PartyRefDTO buyerPartyRef = modelMapper.map(buyerParty, PartyRefDTO.class);
        orderCancellationDTO.getBuyerCustomerParty().setParty(buyerPartyRef);
        return orderCancellationDTO;
    }

    @Override
    public OrderResponseSimpleDTO assignBuyerToOrderResponseSimpleDTOByPartyId(String buyerPartyId, OrderResponseSimpleDTO orderResponseSimpleDTO) {
        PartyDTO buyerParty = partyService.getPartyById(buyerPartyId);
        PartyRefDTO buyerPartyRef = modelMapper.map(buyerParty, PartyRefDTO.class);
        orderResponseSimpleDTO.getBuyerCustomerParty().setParty(buyerPartyRef);
        return orderResponseSimpleDTO;
    }

    @Override
    public OrderResponseDTO assignBuyerToOrderResponseDTOByPartyId(String buyerPartyId, OrderResponseDTO orderResponseDTO) {
        PartyDTO buyerParty = partyService.getPartyById(buyerPartyId);
        PartyRefDTO buyerPartyRef = modelMapper.map(buyerParty, PartyRefDTO.class);
        orderResponseDTO.getBuyerCustomerParty().setParty(buyerPartyRef);
        return orderResponseDTO;
    }


}
