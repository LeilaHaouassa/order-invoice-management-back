package com.proxym.orderandinvoicemanagement.dto;

import com.proxym.orderandinvoicemanagement.model.baseEntities.IdentifierType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.TextType;
import lombok.Data;

@Data
public class ContactDTO {
    private IdentifierType id;
    private TextType name;
    private TextType telephone;
    private TextType electronicMail;
}
