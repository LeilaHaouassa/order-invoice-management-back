package com.proxym.orderandinvoicemanagement.dto.invoiceRelated;

import com.proxym.orderandinvoicemanagement.dto.commun.CustomerPartyDTO;
import com.proxym.orderandinvoicemanagement.dto.commun.MonetaryTotalDTO;
import com.proxym.orderandinvoicemanagement.dto.commun.SupplierPartyDTO;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderReferenceDTO;
import com.proxym.orderandinvoicemanagement.model.baseEntities.*;
import com.proxym.orderandinvoicemanagement.model.communEntities.CustomerParty;
import com.proxym.orderandinvoicemanagement.model.communEntities.SupplierParty;
import lombok.Data;


import java.util.HashSet;
import java.util.Set;

@Data
public class InvoiceDTO {
    private String technicalId;
    private IdentifierType id;
    private DateType issueDate ;
    private TimeType issueTime ;
    private OrderReferenceDTO orderReference;
    private CustomerPartyDTO accountingCustomerParty;
    private SupplierPartyDTO accountingSupplierParty;
    private CustomerPartyDTO buyerCustomerParty;
    private SupplierPartyDTO sellerSupplierParty;
    private MonetaryTotalDTO legalMonetaryTotal;
    private Set<InvoiceLineDTO> invoiceLine = new HashSet<>();
}
