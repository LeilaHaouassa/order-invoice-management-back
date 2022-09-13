package com.proxym.orderandinvoicemanagement.model.communEntities;


import com.proxym.orderandinvoicemanagement.model.baseEntities.CodeType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.TextType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.HashSet;
import java.util.Set;


//"A class to describe an organization, sub-organization,
// or individual fulfilling a role in a business process."
@Document
@Data
@NoArgsConstructor
public class  Party {
    @Id
    private String technicalId;

    //"An identifier for this party."
    //Originally a Party has a list of PartyIdentification
    private PartyIdentification partyIdentification;

    //"A name for this party."
    private PartyName partyName;

    //"The party's postal address."
    private Address postalAddress ;

    //"The primary contact for this party.
    private Contact  contact;

    //"The financial account associated with this party."
    private FinancialAccount financialAccount ;


}