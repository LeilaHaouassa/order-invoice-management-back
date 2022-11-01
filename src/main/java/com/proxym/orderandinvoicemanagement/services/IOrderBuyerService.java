package com.proxym.orderandinvoicemanagement.services;

import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderCancellationDTO;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderChangeDTO;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderDTO;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderResponseDTO;
import com.proxym.orderandinvoicemanagement.exception.IllegalOperationException;
import com.proxym.orderandinvoicemanagement.model.orderEntities.Order;

import java.util.Set;

public interface IOrderBuyerService {

    Set<OrderDTO> getAllSentOrders(String buyerPartyId);

    OrderDTO changeOrder(String buyerPartyId,OrderChangeDTO orderChangeDTO) throws IllegalArgumentException, IllegalOperationException;

    Set<OrderDTO> cancelOrder(String buyerPartyId,OrderCancellationDTO orderCancellationDTO) throws IllegalArgumentException, IllegalOperationException;

    OrderDTO placeOrder(String buyerPartyId,OrderDTO order) throws IllegalArgumentException;

    OrderDTO acceptOrder(String orderTechnicalId) throws IllegalOperationException;

    Order getOrderIfOperationIsLegal(String technicalId) throws IllegalOperationException;

    Order getOrderIfChangeIsLegal(String technicalId) throws IllegalOperationException;

    OrderResponseDTO getDocumentForOrderChange(String orderTechnicalId);
}
