package com.proxym.orderandinvoicemanagement.repositories;

import com.proxym.orderandinvoicemanagement.model.baseEntities.IdentifierType;
import com.proxym.orderandinvoicemanagement.model.communEntities.Party;
import com.proxym.orderandinvoicemanagement.model.orderEntities.Order;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.Set;

public interface OrderRepository extends MongoRepository<Order,String> {

    Optional<Order> findByTechnicalId(String technicalId);

    Set<Order> findAllByBuyerCustomerParty_Party(Party party);

    Set<Order> findAllBySellerSupplierParty_Party(Party party);
}
