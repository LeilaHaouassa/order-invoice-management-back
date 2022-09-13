package com.proxym.orderandinvoicemanagement.model.orderEntities;

import com.proxym.orderandinvoicemanagement.model.baseEntities.*;
import com.proxym.orderandinvoicemanagement.model.communEntities.CustomerParty;
import com.proxym.orderandinvoicemanagement.model.communEntities.MonetaryTotal;
import com.proxym.orderandinvoicemanagement.model.communEntities.SupplierParty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

//"A document used to order goods and services."
@Document
public class Order {

    //Required
    //"An identifier for this document, assigned by the sender."
    @Id
    private IdentifierType id;

    //Required
    //"The date, assigned by the sender, on which this document was issued.",
    private DateType issueDate ;


    //"The time, assigned by the sender, on which this document was issued.",
    private TimeType issueTime ;


    //"Free-form text pertinent to this document, conveying
    // information that is not contained explicitly in other structures."
    private Set<TextType> note = new HashSet<>();

    //"The buyer."  //Required
    private CustomerParty buyerCustomerParty;

    //"The seller."  //Required
    private SupplierParty sellerSupplierParty;

    //Required
    //"A line associated with a line in the Catalogue and specifying a kind of
    // item being ordered."
    private Set<OrderLine> orderLine = new HashSet<>();

    //"The total amount for the Order anticipated by the buyer."
    private MonetaryTotal anticipatedMonetaryTotal;


}

    //"The time, assigned by the sender, at which this document was issued."
    //private TimeType issueTime ;