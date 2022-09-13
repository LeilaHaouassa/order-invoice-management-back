package com.proxym.orderandinvoicemanagement.model.orderEntities;

import com.proxym.orderandinvoicemanagement.model.baseEntities.DateType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.IdentifierType;
import com.proxym.orderandinvoicemanagement.model.communEntities.CustomerParty;
import com.proxym.orderandinvoicemanagement.model.communEntities.MonetaryTotal;
import com.proxym.orderandinvoicemanagement.model.communEntities.SupplierParty;

import java.util.HashSet;
import java.util.Set;

public class OrderChange {

    private IdentifierType id;

    //Required
    //"The date, assigned by the sender, on which this document was issued.",
    private DateType issueDate ;


    //Required
    //"The Order Change Sequence Number assigned by the Buyer to ensure the
    // proper sequencing of changes."
    private IdentifierType sequenceNumberID;



    //Required
    //"A reference to the Order being changed.
    private Set<OrderReference> orderReference = new HashSet<>();


    //Required
    //"The buyer."
    private CustomerParty buyerCustomerParty;

    //Required
    //"The seller."
    private SupplierParty sellerSupplierParty;

    //Required
    //"A line associated with a line in the Catalogue and specifying a kind of
    // item being ordered."
    private Set<OrderLine> orderLine = new HashSet<>();

    //"The amount of change to the total cost of the order anticipated by the buyer."
    private MonetaryTotal anticipatedMonetaryTotal;

}

