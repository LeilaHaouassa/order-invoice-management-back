package com.proxym.orderandinvoicemanagement.repositories;

import com.proxym.orderandinvoicemanagement.model.baseEntities.TextType;
import com.proxym.orderandinvoicemanagement.model.communEntities.Item.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface ItemRepository extends MongoRepository<Item,String> {
    Optional<Item> getByName(TextType name);
}
