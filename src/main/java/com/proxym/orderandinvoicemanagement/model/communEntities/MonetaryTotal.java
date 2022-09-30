package com.proxym.orderandinvoicemanagement.model.communEntities;


import com.proxym.orderandinvoicemanagement.model.baseEntities.AmountType;
import lombok.Data;

//"A class to define a monetary total."
@Data
public class MonetaryTotal {

    //Required
    //"The amount of the monetary total to be paid."
    private AmountType payableAmount ;
}
