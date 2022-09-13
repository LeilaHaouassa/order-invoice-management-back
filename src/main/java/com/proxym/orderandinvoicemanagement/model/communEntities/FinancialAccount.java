package com.proxym.orderandinvoicemanagement.model.communEntities;

import com.proxym.orderandinvoicemanagement.model.baseEntities.CodeType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.IdentifierType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.TextType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor  //for model mapping
public class FinancialAccount {
    @Id
    private String technicalId;

    //"The identifier for this financial account; the bank account number."
    private IdentifierType id;

    //"A code signifying the currency in which this financial account is held."
    private CodeType currencyCode;

    //"Free-form text applying to the Payment for the owner of this account."
    private TextType paymentNote;


}
