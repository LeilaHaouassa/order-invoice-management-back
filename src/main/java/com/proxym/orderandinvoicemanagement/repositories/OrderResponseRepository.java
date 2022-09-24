package com.proxym.orderandinvoicemanagement.repositories;

import com.proxym.orderandinvoicemanagement.model.orderEntities.OrderResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderResponseRepository extends MongoRepository<OrderResponse,String> {
}
