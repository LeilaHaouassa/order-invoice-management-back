package com.proxym.orderandinvoicemanagement.dto.orderRelated;

import com.proxym.orderandinvoicemanagement.dto.commun.CustomerPartyDTO;
import com.proxym.orderandinvoicemanagement.dto.commun.SupplierPartyDTO;
import com.proxym.orderandinvoicemanagement.model.baseEntities.*;
import com.proxym.orderandinvoicemanagement.model.orderEntities.ActionType;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class OrderResponseSimpleDTO {
    private String technicalId;
    private IdentifierType id;
    private DateType issueDate ;
    private TimeType issueTime ;
    private IndicatorType acceptedIndicator;
    private OrderReferenceDTO orderReference;
    private Set<TextType> rejectionNote = new HashSet<>();
    private CustomerPartyDTO buyerCustomerParty;
    private SupplierPartyDTO sellerSupplierParty;
    private ActionType actionType;
}
