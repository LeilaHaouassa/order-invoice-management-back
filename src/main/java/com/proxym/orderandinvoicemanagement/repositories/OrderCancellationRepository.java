package com.proxym.orderandinvoicemanagement.repositories;

import com.proxym.orderandinvoicemanagement.model.orderEntities.OrderCancellation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderCancellationRepository extends MongoRepository<OrderCancellation,String> {
}
