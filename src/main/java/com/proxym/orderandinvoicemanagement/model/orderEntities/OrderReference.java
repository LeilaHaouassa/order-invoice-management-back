package com.proxym.orderandinvoicemanagement.model.orderEntities;

//what is weird for me here that the reference holds no id of the referenced
//order, it just holds the exact time and notes
//further investigation is needed !!!


import com.proxym.orderandinvoicemanagement.model.baseEntities.DateType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.IdentifierType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.TimeType;
import lombok.Data;
import org.springframework.data.annotation.Id;

//"A class to define a reference to an Order."
@Data
public class OrderReference {

    //Id of referenced order
    private String technicalId;

    //Required
    //"An identifier for this order reference, assigned by the buyer."
    private IdentifierType id;

    //"The date on which the referenced Order was issued."
    private DateType issueDate ;

    //"The time on which the referenced Order was issued."
    private TimeType issueTime ;
}


