package com.proxym.orderandinvoicemanagement.dto.orderRelated;

import com.proxym.orderandinvoicemanagement.dto.commun.CustomerPartyDTO;
import com.proxym.orderandinvoicemanagement.dto.commun.SupplierPartyDTO;
import com.proxym.orderandinvoicemanagement.model.baseEntities.DateType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.IdentifierType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.TimeType;
import com.proxym.orderandinvoicemanagement.model.communEntities.MonetaryTotal;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class OrderChangeDTO {
    private String technicalId;
    private IdentifierType id;
    private DateType issueDate ;
    private TimeType issueTime ;
    private IdentifierType sequenceNumberID;
    private OrderReferenceDTO orderReference;
    private CustomerPartyDTO buyerCustomerParty;
    private SupplierPartyDTO sellerSupplierParty;
    private Set<OrderLineDTO> orderLine = new HashSet<>();
    private MonetaryTotal anticipatedMonetaryTotal;
}
