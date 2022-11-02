package com.proxym.orderandinvoicemanagement.model.orderEntities;

import com.proxym.orderandinvoicemanagement.model.baseEntities.*;
import com.proxym.orderandinvoicemanagement.model.communEntities.CustomerParty;
import com.proxym.orderandinvoicemanagement.model.communEntities.MonetaryTotal;
import com.proxym.orderandinvoicemanagement.model.communEntities.SupplierParty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//"A document used to order goods and services."
@Document
@Data
public class Order {
    @Id
    private String technicalId;

    //Required
    //"An identifier for this document, assigned by the sender.
    @Indexed(unique = true)
    private IdentifierType id;

    //Technical attribute to mark the status of the order
    private OrderStatus status;

    //Technical attribute to mark the action associated with this document
    private ActionType actionType= ActionType.SEND;

    //Required
    //"The date, assigned by the sender, on which this document was issued.",
    private DateType issueDate ;

    //"The time, assigned by the sender, on which this document was issued.",
    private TimeType issueTime ;


//    //"Free-form text pertinent to this document, conveying
//    // information that is not contained explicitly in other structures."
//    private Set<TextType> note = new HashSet<>();

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

    private String IdOfDocumentToNegotiateOver;

    private String IdOfDocumentForOrderChange;

    private String IdOfDocumentForInvoice;

    private List<Object> historyStack = new ArrayList<>();

    public void addToHistoryStack(Object obj){
        historyStack.add(obj);
    }
}