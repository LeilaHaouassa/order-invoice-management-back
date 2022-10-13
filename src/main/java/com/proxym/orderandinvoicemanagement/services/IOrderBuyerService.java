package com.proxym.orderandinvoicemanagement.services;

import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderCancellationDTO;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderChangeDTO;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderDTO;
import com.proxym.orderandinvoicemanagement.exception.IllegalOperationException;
import com.proxym.orderandinvoicemanagement.model.orderEntities.Order;

import java.util.Set;

public interface IOrderBuyerService {
    //TODO For testing, To be deleted in the end
    Set<OrderDTO> getAll();

    Set<OrderDTO> getAllSentOrders(String technicalId);

    OrderChangeDTO changeOrder(String buyerPartyId,OrderChangeDTO orderChangeDTO) throws IllegalArgumentException, IllegalOperationException;

    OrderCancellationDTO cancelOrder(String buyerPartyId,OrderCancellationDTO orderCancellationDTO) throws IllegalArgumentException, IllegalOperationException;

    OrderDTO placeOrder(String buyerPartyId,OrderDTO order) throws IllegalArgumentException;

    void acceptOrder(String orderTechnicalId) throws IllegalOperationException;






}
