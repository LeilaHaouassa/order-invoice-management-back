package com.proxym.orderandinvoicemanagement.services;

import com.proxym.orderandinvoicemanagement.dto.orderRelated.*;
import com.proxym.orderandinvoicemanagement.exception.IllegalOperationException;
import com.proxym.orderandinvoicemanagement.exception.ResourceNotFoundException;
import com.proxym.orderandinvoicemanagement.model.baseEntities.DateType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.IdentifierType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.TimeType;
import com.proxym.orderandinvoicemanagement.model.orderEntities.Order;
import com.proxym.orderandinvoicemanagement.model.orderEntities.OrderResponse;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface IOrderService {

    Set<OrderDTO> convertSetToDTO(Set<Order> orders);

    TimeType getCurrentTime();

    DateType getCurrentDate();

    List<Object> getHistory(String technicalId);

    OrderReferenceDTO assignRestOfOrderRefData(OrderReferenceDTO ref, Order order) ;

    OrderDTO getOrderByTechnicalId(String technicalId) throws ResourceNotFoundException;

    OrderDTO assignBuyerToOrderDTOByPartyId(String buyerPartyId, OrderDTO orderDTO);

    OrderChangeDTO assignBuyerToOrderChangeDTOByPartyId(String buyerPartyId, OrderChangeDTO orderChangeDTO);

    OrderCancellationDTO assignBuyerToOrderCancellationDTOByPartyId(String buyerPartyId, OrderCancellationDTO orderCancellationDTO);

    OrderResponseSimpleDTO assignSellerToOrderResponseSimpleDTOByPartyId(String sellerPartyId, OrderResponseSimpleDTO orderResponseSimpleDTO);

    OrderResponseDTO assignSellerToOrderResponseDTOByPartyId(String sellerPartyId, OrderResponseDTO orderResponseDTO);
}
