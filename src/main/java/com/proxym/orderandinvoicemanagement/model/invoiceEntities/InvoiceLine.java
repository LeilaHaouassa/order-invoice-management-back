package com.proxym.orderandinvoicemanagement.model.invoiceEntities;

import com.proxym.orderandinvoicemanagement.model.baseEntities.AmountType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.IdentifierType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.QuantityType;
import com.proxym.orderandinvoicemanagement.model.communEntities.Item.ItemRef;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
public class InvoiceLine {
    //Required
    //"An identifier for this document, assigned by the sender.
    @Indexed(unique = true)
    private IdentifierType id;

    //"The quantity (of items) on this invoice line."
    private QuantityType invoicedQuantity;

    //Required
    //"The total amount for this invoice line, including allowance charges but net of taxes."
    private AmountType lineExtensionAmount;

    //Required
    //"The item associated with this invoice line.",
    private ItemRef item;
}
