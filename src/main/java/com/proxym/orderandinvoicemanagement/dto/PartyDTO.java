package com.proxym.orderandinvoicemanagement.dto;

import com.proxym.orderandinvoicemanagement.model.baseEntities.TextType;
import lombok.Data;

@Data
public class PartyDTO {
    private String technicalId;
    private PartyIdentificationDTO partyIdentification;
    private PartyNameDTO partyName;
    private AddressDTO postalAddress;
    private ContactDTO contact;
    private FinancialAccountDTO financialAccount;

}
