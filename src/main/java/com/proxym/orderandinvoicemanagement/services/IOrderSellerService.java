package com.proxym.orderandinvoicemanagement.services;

import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderDTO;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderResponseDTO;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderResponseSimpleDTO;
import com.proxym.orderandinvoicemanagement.exception.IllegalOperationException;
import com.proxym.orderandinvoicemanagement.model.communEntities.Party;
import com.proxym.orderandinvoicemanagement.model.config.Settings;

import java.util.Set;

public interface IOrderSellerService {

    Set<OrderDTO> getAllReceivedOrders(Party party);

    OrderResponseSimpleDTO rejectOrder(OrderResponseSimpleDTO orderResponseSimpleDTO) throws IllegalArgumentException, IllegalOperationException;

    OrderResponseDTO addDetail(OrderResponseDTO orderResponseDTO) throws IllegalArgumentException, IllegalOperationException;

    OrderResponseSimpleDTO acceptOrderResponseRequired(OrderResponseSimpleDTO orderResponseSimpleDTO);

    void acceptOrderWithoutResponse(String technicalId);
}
