package com.proxym.orderandinvoicemanagement.dto.commun;

import lombok.Data;
//needed in supplier party and customer party dtos
@Data
public class PartyRefDTO {
    private String technicalId;
    private PartyIdentificationDTO partyIdentification;
    private PartyNameDTO partyName;
}
