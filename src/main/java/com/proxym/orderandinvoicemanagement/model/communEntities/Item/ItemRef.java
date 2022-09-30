package com.proxym.orderandinvoicemanagement.model.communEntities.Item;

import com.proxym.orderandinvoicemanagement.model.baseEntities.TextType;
import lombok.Data;

@Data
public class ItemRef {
    private String technicalId;
    private TextType name;
}
