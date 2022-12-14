package com.proxym.orderandinvoicemanagement.model.orderEntities;

import com.proxym.orderandinvoicemanagement.model.baseEntities.DateType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.IdentifierType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.TextType;
import com.proxym.orderandinvoicemanagement.model.communEntities.CustomerParty;
import com.proxym.orderandinvoicemanagement.model.communEntities.SupplierParty;

import java.util.HashSet;
import java.util.Set;

//"A document used to cancel an entire Order."
public class OrderCancellation {
    //Required
    private IdentifierType id;

    //Required
    //"The date, assigned by the sender, on which this document was issued."
    private DateType issueDate ;

    //Required
    //"The general reason for cancellation of the referenced order."
    private Set<TextType> cancellationNote = new HashSet<>();

    //Required
    //"A reference to the Order being cancelled. While multiple
    // references are allowed, it is considered better practice to
    // cancel only one Order in each Order Cancellation document."
    private Set<OrderReference> orderReference = new HashSet<>();

    //Required
    //"The buyer."
    private CustomerParty buyerCustomerParty;

    //Required
    //"The seller."
    private SupplierParty sellerSupplierParty;
}
