package com.proxym.orderandinvoicemanagement.dto.orderRelated;

import com.proxym.orderandinvoicemanagement.dto.commun.CustomerPartyDTO;
import com.proxym.orderandinvoicemanagement.dto.commun.MonetaryTotalDTO;
import com.proxym.orderandinvoicemanagement.dto.commun.SupplierPartyDTO;
import com.proxym.orderandinvoicemanagement.model.baseEntities.DateType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.IdentifierType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.TextType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.TimeType;
import com.proxym.orderandinvoicemanagement.model.orderEntities.OrderStatus;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class OrderDTO {
    private String technicalId;
    private IdentifierType id;
    private DateType issueDate ;
    private TimeType issueTime ;
    private OrderStatus status;
//    private Set<TextType> note = new HashSet<>();
    private CustomerPartyDTO buyerCustomerParty;
    private SupplierPartyDTO sellerSupplierParty;
    private OrderLineDTO orderLine;
    private MonetaryTotalDTO anticipatedMonetaryTotal;
}
