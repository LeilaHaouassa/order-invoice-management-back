package com.proxym.orderandinvoicemanagement.model.orderEntities;

import com.proxym.orderandinvoicemanagement.model.baseEntities.DateType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.IdentifierType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.TimeType;
import com.proxym.orderandinvoicemanagement.model.communEntities.CustomerParty;
import com.proxym.orderandinvoicemanagement.model.communEntities.MonetaryTotal;
import com.proxym.orderandinvoicemanagement.model.communEntities.SupplierParty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document
@Data
public class OrderChange {
    @Id
    private String technicalId;

    @Indexed(unique = true)
    private IdentifierType id;

    //Required
    //"The date, assigned by the sender, on which this document was issued.",
    private DateType issueDate ;

    //"The time, assigned by the sender, on which this document was issued.",
    private TimeType issueTime ;


    //Required
    //"The Order Change Sequence Number assigned by the Buyer to ensure the
    // proper sequencing of changes."
    private IdentifierType sequenceNumberID;



    //Required
    //A reference to the Order being changed.
    private OrderReference orderReference;


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

    //Technical attribute to mark the action associated with this document
    private ActionType actionType= ActionType.CHANGE;

    //"The amount of change to the total cost of the order anticipated by the buyer."
    private MonetaryTotal anticipatedMonetaryTotal;

}

