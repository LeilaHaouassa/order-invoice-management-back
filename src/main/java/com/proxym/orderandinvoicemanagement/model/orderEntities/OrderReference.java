package com.proxym.orderandinvoicemanagement.model.orderEntities;

//what is weird for me here that the reference holds no id of the referenced
//order, it just holds the exact time and notes
//further investigation is needed !!!


import com.proxym.orderandinvoicemanagement.model.baseEntities.DateType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.IdentifierType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.TimeType;

//"A class to define a reference to an Order."
public class OrderReference {

    //Required
    //"An identifier for this order reference, assigned by the buyer."
    private IdentifierType id;

    //"The date on which the referenced Order was issued."
    private DateType issueDate ;

    //"The time on which the referenced Order was issued."
    private TimeType issueTime ;
}


