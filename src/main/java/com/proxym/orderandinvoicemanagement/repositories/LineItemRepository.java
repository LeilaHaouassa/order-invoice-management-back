package com.proxym.orderandinvoicemanagement.repositories;

import com.proxym.orderandinvoicemanagement.model.communEntities.LineItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LineItemRepository extends MongoRepository<LineItem,String> {
}
