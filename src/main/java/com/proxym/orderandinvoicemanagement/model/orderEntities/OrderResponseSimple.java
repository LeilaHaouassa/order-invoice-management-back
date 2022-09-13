package com.proxym.orderandinvoicemanagement.model.orderEntities;


import com.proxym.orderandinvoicemanagement.model.baseEntities.DateType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.IdentifierType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.IndicatorType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.TextType;
import com.proxym.orderandinvoicemanagement.model.communEntities.CustomerParty;
import com.proxym.orderandinvoicemanagement.model.communEntities.SupplierParty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.HashSet;
import java.util.Set;

//A document used to indicate simple
// acceptance or rejection of an entire Order."

public class OrderResponseSimple {

    //Required

    private IdentifierType id;

    //Required
    //"The date, assigned by the sender, on which this document was issued.",
    private DateType issueDate ;

    //Required
    //"Indicates whether the Order is accepted (true) or rejected (false)."
    private IndicatorType acceptedIndicator;

    //Required
    //"A reference to the Order being responded to.
    private Set<OrderReference> orderReference = new HashSet<>();

    //"The reason for rejection if the order was not accepted."
    private Set<TextType> rejectionNote = new HashSet<>();

    //Required
    //"The buyer."
    private CustomerParty buyerCustomerParty;

    //Required
    //"The seller."
    private SupplierParty sellerSupplierParty;


}
