package com.proxym.orderandinvoicemanagement.services.Implementations;

import com.proxym.orderandinvoicemanagement.dto.orderRelated.*;
import com.proxym.orderandinvoicemanagement.exception.ResourceNotFoundException;
import com.proxym.orderandinvoicemanagement.model.communEntities.Party.Party;
import com.proxym.orderandinvoicemanagement.model.communEntities.Party.PartyRef;
import com.proxym.orderandinvoicemanagement.model.orderEntities.*;
import com.proxym.orderandinvoicemanagement.repositories.PartyRepository;
import com.proxym.orderandinvoicemanagement.services.IPartyCustomMappingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartyCustomMappingServiceImpl implements IPartyCustomMappingService {

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Order mappingForOrder(OrderDTO orderDTO, Order order) {
        String customerPartyTechnicalID = orderDTO.getBuyerCustomerParty().getParty().getTechnicalId();
        String supplierPartyTechnicalID = orderDTO.getSellerSupplierParty().getParty().getTechnicalId();
        Party customer = partyRepository.findPartyByTechnicalId(customerPartyTechnicalID).orElse(null);
        Party supplier = partyRepository.findPartyByTechnicalId(supplierPartyTechnicalID).orElse(null);
        if( supplier == null) {
            throw new ResourceNotFoundException("Can't find selected supplier");
        }
        order.getBuyerCustomerParty().setParty(modelMapper.map(customer, PartyRef.class));
        order.getSellerSupplierParty().setParty(modelMapper.map(supplier,PartyRef.class));
        return order;
    }

    @Override
    public OrderCancellation mappingForOrderCancellation(OrderCancellationDTO orderCancellationDTO, OrderCancellation orderCancellation) {
        String customerPartyTechnicalID = orderCancellationDTO.getBuyerCustomerParty().getParty().getTechnicalId();
        String supplierPartyTechnicalID = orderCancellationDTO.getSellerSupplierParty().getParty().getTechnicalId();
        Party customer = partyRepository.findPartyByTechnicalId(customerPartyTechnicalID).orElse(null);
        Party supplier = partyRepository.findPartyByTechnicalId(supplierPartyTechnicalID).orElse(null);
        if( supplier == null) {
            throw new ResourceNotFoundException("Can't find selected supplier");
        }
        orderCancellation.getBuyerCustomerParty().setParty(modelMapper.map(customer, PartyRef.class));
        orderCancellation.getSellerSupplierParty().setParty(modelMapper.map(supplier,PartyRef.class));
        return orderCancellation;
    }

    @Override
    public OrderChange mappingForOrderChange(OrderChangeDTO orderChangeDTO, OrderChange orderChange) {
        String customerPartyTechnicalID = orderChangeDTO.getBuyerCustomerParty().getParty().getTechnicalId();
        String supplierPartyTechnicalID = orderChangeDTO.getSellerSupplierParty().getParty().getTechnicalId();
        Party customer = partyRepository.findPartyByTechnicalId(customerPartyTechnicalID).orElse(null);
        Party supplier = partyRepository.findPartyByTechnicalId(supplierPartyTechnicalID).orElse(null);
        if( supplier == null) {
            throw new ResourceNotFoundException("Can't find selected supplier");
        }
        orderChange.getBuyerCustomerParty().setParty(modelMapper.map(customer, PartyRef.class));
        orderChange.getSellerSupplierParty().setParty(modelMapper.map(supplier,PartyRef.class));
        return orderChange;
    }

    @Override
    public OrderResponse mappingForOrderResponse(OrderResponseDTO orderResponseDTO, OrderResponse orderResponse) {
        String customerPartyTechnicalID = orderResponseDTO.getBuyerCustomerParty().getParty().getTechnicalId();
        String supplierPartyTechnicalID = orderResponseDTO.getSellerSupplierParty().getParty().getTechnicalId();
        Party customer = partyRepository.findPartyByTechnicalId(customerPartyTechnicalID).orElse(null);
        Party supplier = partyRepository.findPartyByTechnicalId(supplierPartyTechnicalID).orElse(null);
        if( supplier == null) {
            throw new ResourceNotFoundException("Can't find selected supplier");
        }
        orderResponse.getBuyerCustomerParty().setParty(modelMapper.map(customer, PartyRef.class));
        orderResponse.getSellerSupplierParty().setParty(modelMapper.map(supplier,PartyRef.class));
        return orderResponse;
    }

    @Override
    public OrderResponseSimple mappingForOrderResponseSimple(OrderResponseSimpleDTO orderResponseSimpleDTO, OrderResponseSimple orderResponseSimple) {
        String customerPartyTechnicalID = orderResponseSimpleDTO.getBuyerCustomerParty().getParty().getTechnicalId();
        String supplierPartyTechnicalID = orderResponseSimpleDTO.getSellerSupplierParty().getParty().getTechnicalId();
        Party customer = partyRepository.findPartyByTechnicalId(customerPartyTechnicalID).orElse(null);
        Party supplier = partyRepository.findPartyByTechnicalId(supplierPartyTechnicalID).orElse(null);
        if( supplier == null) {
            throw new ResourceNotFoundException("Can't find selected supplier");
        }
        orderResponseSimple.getBuyerCustomerParty().setParty(modelMapper.map(customer, PartyRef.class));
        orderResponseSimple.getSellerSupplierParty().setParty(modelMapper.map(supplier,PartyRef.class));
        return orderResponseSimple;
    }
}
