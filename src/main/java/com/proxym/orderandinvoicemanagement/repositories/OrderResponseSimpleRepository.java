package com.proxym.orderandinvoicemanagement.repositories;

import com.proxym.orderandinvoicemanagement.model.orderEntities.OrderResponseSimple;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderResponseSimpleRepository extends MongoRepository<OrderResponseSimple,String> {
}
