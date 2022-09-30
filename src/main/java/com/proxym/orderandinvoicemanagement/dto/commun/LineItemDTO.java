package com.proxym.orderandinvoicemanagement.dto.commun;

import com.proxym.orderandinvoicemanagement.model.baseEntities.IdentifierType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.QuantityType;
import lombok.Data;

@Data
public class LineItemDTO {

    private IdentifierType id;
    private QuantityType quantity;
    private PriceDTO price;
    private ItemDTO item;
}
