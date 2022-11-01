package com.proxym.orderandinvoicemanagement.services.Implementations;

import com.proxym.orderandinvoicemanagement.dto.commun.CustomerPartyDTO;
import com.proxym.orderandinvoicemanagement.dto.commun.PartyDTO;
import com.proxym.orderandinvoicemanagement.dto.commun.PartyRefDTO;
import com.proxym.orderandinvoicemanagement.dto.commun.SupplierPartyDTO;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.*;
import com.proxym.orderandinvoicemanagement.exception.IllegalOperationException;
import com.proxym.orderandinvoicemanagement.exception.ResourceNotFoundException;
import com.proxym.orderandinvoicemanagement.model.baseEntities.DateType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.TimeType;
import com.proxym.orderandinvoicemanagement.model.orderEntities.Order;
import com.proxym.orderandinvoicemanagement.model.orderEntities.OrderStatus;
import com.proxym.orderandinvoicemanagement.repositories.OrderRepository;
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

    public TimeType getCurrentTime() {
        return TimeType.builder().timeContent(LocalTime.now().toString()).build();
    }

    public DateType getCurrentDate() {
        return DateType.builder().dateContent(LocalDate.now().toString()).build();
    }

    @Override
    public List<Object> getHistory(String technicalId) {
        Order order = orderRepository.findByTechnicalId(technicalId).orElse(null);
        if (order == null) {
            throw new ResourceNotFoundException("Retrieving Failed: Order with Id " + technicalId + " not found.");
        }
        return order.getHistoryStack();
    }

    @Override
    public OrderReferenceDTO assignRestOfOrderRefData(OrderReferenceDTO referenceDTO, Order order) {
        referenceDTO.setIssueDate(order.getIssueDate());
        referenceDTO.setIssueTime(order.getIssueTime());
        referenceDTO.setId(order.getId());
        return referenceDTO;
    }


    @Override
    public OrderDTO getOrderByTechnicalId(String technicalId) {
        Order order = orderRepository.findByTechnicalId(technicalId).orElse(null);
        if (order == null) {
            throw new ResourceNotFoundException("Retrieving Failed: Order with Id " + technicalId + " not found.");
        }
        return modelMapper.map(order,OrderDTO.class);
    }


    @Override
    public OrderDTO assignBuyerToOrderDTOByPartyId(String buyerPartyId, OrderDTO orderDTO) {
        PartyDTO buyerParty = partyService.getPartyById(buyerPartyId);
        PartyRefDTO buyerPartyRef = modelMapper.map(buyerParty, PartyRefDTO.class);
        orderDTO.setBuyerCustomerParty( CustomerPartyDTO.builder().party(buyerPartyRef).build());
        return orderDTO;
    }

    @Override
    public OrderChangeDTO assignBuyerToOrderChangeDTOByPartyId(String buyerPartyId, OrderChangeDTO orderChangeDTO) {
        PartyDTO buyerParty = partyService.getPartyById(buyerPartyId);
        PartyRefDTO buyerPartyRef = modelMapper.map(buyerParty, PartyRefDTO.class);
        orderChangeDTO.setBuyerCustomerParty( CustomerPartyDTO.builder().party(buyerPartyRef).build());
        return orderChangeDTO;
    }

    @Override
    public OrderCancellationDTO assignBuyerToOrderCancellationDTOByPartyId(String buyerPartyId, OrderCancellationDTO orderCancellationDTO) {
        PartyDTO buyerParty = partyService.getPartyById(buyerPartyId);
        PartyRefDTO buyerPartyRef = modelMapper.map(buyerParty, PartyRefDTO.class);
        orderCancellationDTO.setBuyerCustomerParty( CustomerPartyDTO.builder().party(buyerPartyRef).build());
        return orderCancellationDTO;
    }

    @Override
    public OrderResponseSimpleDTO assignSellerToOrderResponseSimpleDTOByPartyId(String sellerPartyId, OrderResponseSimpleDTO orderResponseSimpleDTO) {
        PartyDTO sellerParty = partyService.getPartyById(sellerPartyId);
        PartyRefDTO sellerPartyRef = modelMapper.map(sellerParty, PartyRefDTO.class);
        orderResponseSimpleDTO.setSellerSupplierParty( SupplierPartyDTO.builder().party(sellerPartyRef).build());
        return orderResponseSimpleDTO;
    }

    @Override
    public OrderResponseDTO assignSellerToOrderResponseDTOByPartyId(String sellerPartyId, OrderResponseDTO orderResponseDTO) {
        PartyDTO sellerParty = partyService.getPartyById(sellerPartyId);
        PartyRefDTO sellerPartyRef = modelMapper.map(sellerParty, PartyRefDTO.class);
        orderResponseDTO.setSellerSupplierParty( SupplierPartyDTO.builder().party(sellerPartyRef).build());
        return orderResponseDTO;
    }


}
