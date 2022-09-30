package com.proxym.orderandinvoicemanagement.services;

import com.proxym.orderandinvoicemanagement.dto.orderRelated.*;
import com.proxym.orderandinvoicemanagement.model.orderEntities.*;

public interface IPartyCustomMappingService {

    Order mappingForOrder(OrderDTO orderDTO,Order order );

    OrderCancellation mappingForOrderCancellation(OrderCancellationDTO orderCancellationDTO, OrderCancellation orderCancellation );

    OrderChange mappingForOrderChange(OrderChangeDTO orderChangeDTO, OrderChange orderChange );

    OrderResponse mappingForOrderResponse(OrderResponseDTO orderResponseDTO, OrderResponse orderResponse );

    OrderResponseSimple mappingForOrderResponseSimple(OrderResponseSimpleDTO orderResponseSimpleDTO, OrderResponseSimple orderResponseSimple );
}
