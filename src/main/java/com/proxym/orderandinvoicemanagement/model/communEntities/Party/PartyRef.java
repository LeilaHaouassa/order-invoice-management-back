package com.proxym.orderandinvoicemanagement.model.communEntities.Party;

import com.proxym.orderandinvoicemanagement.model.communEntities.*;
import lombok.Data;


//A class To avoid nested documents in Order
//Doesn't follow the UBL, but I inspired from it
@Data
public class PartyRef {
    //Id of referenced party
    private String technicalId;
    private PartyIdentification partyIdentification;
    private PartyName partyName;
    private Address postalAddress ;
    private Contact contact;
    private FinancialAccount financialAccount ;

}
