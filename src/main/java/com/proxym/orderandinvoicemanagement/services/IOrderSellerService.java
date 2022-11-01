package com.proxym.orderandinvoicemanagement.services;

import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderDTO;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderResponseDTO;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderResponseSimpleDTO;
import com.proxym.orderandinvoicemanagement.exception.IllegalOperationException;
import com.proxym.orderandinvoicemanagement.model.communEntities.Party.Party;
import com.proxym.orderandinvoicemanagement.model.orderEntities.Order;

import java.util.Set;

public interface IOrderSellerService {

    Set<OrderDTO> getAllReceivedOrders(String sellerPartyId);

    OrderDTO rejectOrder(String sellerPartyId, OrderResponseSimpleDTO orderResponseSimpleDTO) throws IllegalArgumentException, IllegalOperationException;

    Set<OrderDTO> addDetail(String sellerPartyId, OrderResponseDTO orderResponseDTO) throws IllegalArgumentException, IllegalOperationException;

    OrderDTO acceptOrderResponseRequired(String sellerPartyId,OrderResponseSimpleDTO orderResponseSimpleDTO);

    OrderDTO acceptOrderWithoutResponse(String orderTechnicalId);

    Order getOrderIfOperationIsLegal(String technicalId) throws IllegalOperationException;

    Object getDocumentForNegotiation(String orderTechnicalId);
}
