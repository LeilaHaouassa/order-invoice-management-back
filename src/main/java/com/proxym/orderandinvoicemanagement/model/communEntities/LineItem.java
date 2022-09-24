package com.proxym.orderandinvoicemanagement.model.communEntities;

import com.proxym.orderandinvoicemanagement.model.baseEntities.IdentifierType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.QuantityType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//"A class to describe a line item."
@Document
public class LineItem {

    @Id
    private String technicalId;

    //Required
    //"An identifier for this line item, assigned by the buyer."
    private IdentifierType id;

    //"The quantity of items associated with this line item."
    private QuantityType quantity;

    //"The price of the item of trade associated with this line item."
    private Price price;

    //Required
    private Item item;
}
