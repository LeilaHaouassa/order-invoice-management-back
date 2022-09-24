package com.proxym.orderandinvoicemanagement.dto.commun;

import com.proxym.orderandinvoicemanagement.model.baseEntities.TextType;
import lombok.Data;

@Data
public class ItemDTO {
    private String technicalId;
    private TextType name;
}
