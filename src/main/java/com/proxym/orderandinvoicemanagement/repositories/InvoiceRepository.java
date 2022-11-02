package com.proxym.orderandinvoicemanagement.repositories;

import com.proxym.orderandinvoicemanagement.model.invoiceEntities.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.Set;

public interface InvoiceRepository extends MongoRepository<Invoice,String> {

    Optional<Invoice> findByTechnicalId(String technicalId);

    Set<Invoice> findAllByBuyerCustomerParty_Party_TechnicalId(String technicalId);

    Set<Invoice> findAllBySellerSupplierParty_Party_TechnicalId(String technicalId);

}
