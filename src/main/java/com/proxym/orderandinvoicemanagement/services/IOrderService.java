package com.proxym.orderandinvoicemanagement.services;

import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderDTO;
import com.proxym.orderandinvoicemanagement.exception.IllegalOperationException;
import com.proxym.orderandinvoicemanagement.exception.ResourceNotFoundException;
import com.proxym.orderandinvoicemanagement.model.baseEntities.DateType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.IdentifierType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.TimeType;
import com.proxym.orderandinvoicemanagement.model.orderEntities.Order;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface IOrderService {

    Set<OrderDTO> convertSetToDTO(Set<Order> orders);

    Set<OrderDTO> convertListToDTO(List<Order> list);

    TimeType getCurrentTime();

    DateType getCurrentDate();

    Order saveOrder(Order order);

    Order getOrderByTechnicalId(String technicalId) throws ResourceNotFoundException;

    Order getOrderIfOperationIsLegal(String technicalId) throws IllegalOperationException;

}
