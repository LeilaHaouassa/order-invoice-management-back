package com.proxym.orderandinvoicemanagement.repositories;

import com.proxym.orderandinvoicemanagement.model.orderEntities.OrderChange;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderChangeRepository extends MongoRepository<OrderChange,String> {
}
