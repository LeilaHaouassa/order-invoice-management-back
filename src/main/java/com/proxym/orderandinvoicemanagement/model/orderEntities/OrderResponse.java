package com.proxym.orderandinvoicemanagement.model.orderEntities;


import com.proxym.orderandinvoicemanagement.model.baseEntities.DateType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.IdentifierType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.TextType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.TimeType;
import com.proxym.orderandinvoicemanagement.model.communEntities.CustomerParty;
import com.proxym.orderandinvoicemanagement.model.communEntities.MonetaryTotal;
import com.proxym.orderandinvoicemanagement.model.communEntities.SupplierParty;

import java.util.HashSet;
import java.util.Set;

//"A document used to indicate detailed acceptance
// or rejection of an Order or to make a counter-offer."
public class OrderResponse {
    //Required
    private IdentifierType id;

    //Required
    //"The date, assigned by the sender, on which this document was issued.",
    private DateType issueDate ;


    //"Free-form text pertinent to this document, conveying
    // information that is not contained explicitly in other structures."
    private Set<TextType> note = new HashSet<>();



    //Required
    //"A reference to the Order being responded to.
    private Set<OrderReference> orderReference = new HashSet<>();

    //Required
    //"The buyer."
    private CustomerParty buyerCustomerParty;

    //Required
    //"The seller."
    private SupplierParty sellerSupplierParty;


    //"A line associated with a line in the Catalogue and specifying a kind of
    // item being ordered."
    private Set<OrderLine> orderLine = new HashSet<>();

    //"The total amount of the Order (or counter-offer)."
    private MonetaryTotal legalMonetaryTotal;
}
