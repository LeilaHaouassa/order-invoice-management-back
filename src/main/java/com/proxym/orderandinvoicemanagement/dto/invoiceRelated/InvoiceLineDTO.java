package com.proxym.orderandinvoicemanagement.dto.invoiceRelated;

import com.proxym.orderandinvoicemanagement.dto.commun.ItemDTO;
import com.proxym.orderandinvoicemanagement.model.baseEntities.AmountType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.IdentifierType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.QuantityType;
import lombok.Data;

@Data
public class InvoiceLineDTO {
    private IdentifierType id;
    private QuantityType invoicedQuantity;
    private AmountType lineExtensionAmount;
    private ItemDTO item;
}
