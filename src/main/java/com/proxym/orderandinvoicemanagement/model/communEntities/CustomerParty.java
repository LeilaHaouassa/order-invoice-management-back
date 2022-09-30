package com.proxym.orderandinvoicemanagement.model.communEntities;


import com.proxym.orderandinvoicemanagement.model.communEntities.Party.PartyRef;
import lombok.Data;

//A class to describe a customer party.
@Data
public class CustomerParty {
    //"The customer party itself."
    private PartyRef party;
}


//    //"A customer contact for deliveries."
//    private Contact deliveryContact;
//
//    //"A customer contact for  accounting."
//    private Contact accountingContact;
//
//    //"A customer contact for purchasing."
//    private Contact buyerContact;