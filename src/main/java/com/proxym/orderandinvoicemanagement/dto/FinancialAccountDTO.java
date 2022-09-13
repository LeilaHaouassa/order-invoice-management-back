package com.proxym.orderandinvoicemanagement.dto;

import com.proxym.orderandinvoicemanagement.model.baseEntities.CodeType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.IdentifierType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.TextType;
import lombok.Data;

@Data
public class FinancialAccountDTO {
    private IdentifierType id;
    private CodeType currencyCode;
    private TextType paymentNote;
}
