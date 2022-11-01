package com.proxym.orderandinvoicemanagement.model.orderEntities;


import com.proxym.orderandinvoicemanagement.model.baseEntities.*;
import com.proxym.orderandinvoicemanagement.model.communEntities.CustomerParty;
import com.proxym.orderandinvoicemanagement.model.communEntities.SupplierParty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.HashSet;
import java.util.Set;

//A document used to indicate simple
// acceptance or rejection of an entire Order."
@Document
@Data
public class OrderResponseSimple {

    @Id
    private String technicalId;

    //Required
    @Indexed(unique = true)
    private IdentifierType id;

    //Required
    //"The date, assigned by the sender, on which this document was issued.",
    private DateType issueDate ;

    //"The time, assigned by the sender, on which this document was issued.",
    private TimeType issueTime ;

    //Required
    //"Indicates whether the Order is accepted (true) or rejected (false)."
    private IndicatorType acceptedIndicator;

    //Required
    //"A reference to the Order being responded to.
    private OrderReference orderReference;

    //"The reason for rejection if the order was not accepted."
    private Set<TextType> rejectionNote = new HashSet<>();

    //Required
    //"The buyer."
    private CustomerParty buyerCustomerParty;

    //Required
    //"The seller."
    private SupplierParty sellerSupplierParty;

    //Technical attribute to mark the action associated with this document
    private ActionType actionType;


}
