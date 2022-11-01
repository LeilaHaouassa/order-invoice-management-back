package com.proxym.orderandinvoicemanagement.model.communEntities;

import com.proxym.orderandinvoicemanagement.model.baseEntities.IdentifierType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.QuantityType;
import com.proxym.orderandinvoicemanagement.model.communEntities.Item.Item;
import com.proxym.orderandinvoicemanagement.model.communEntities.Item.ItemRef;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

//"A class to describe a line item."
@Data
public class LineItem {
    //Required
    //"An identifier for this line item, assigned by the buyer."
    @Indexed(unique = true)
    private IdentifierType id;

    //"The quantity of items associated with this line item."
    private QuantityType quantity;

    //"The price of the item of trade associated with this line item."
    private Price price;

    //Required
    private ItemRef item;
}
