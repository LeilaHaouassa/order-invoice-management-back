package com.proxym.orderandinvoicemanagement.model.communEntities;

import com.proxym.orderandinvoicemanagement.model.communEntities.Party;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//A class to describe a customer party.
@Document
public class CustomerParty {

    @Id
    private String technicalId;

    //"The customer party itself."
    private Party party;



}


//    //"A customer contact for deliveries."
//    private Contact deliveryContact;
//
//    //"A customer contact for  accounting."
//    private Contact accountingContact;
//
//    //"A customer contact for purchasing."
//    private Contact buyerContact;