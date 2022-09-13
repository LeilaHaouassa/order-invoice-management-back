package com.proxym.orderandinvoicemanagement.model.baseEntities;


import lombok.Data;

//"A numeric value determined
// by measuring an object using a specified unit of measure."
@Data
public class MeasureType {
    //Required
    private Long measureContent;
    //Required
    private String measureUnitCode;

    //private String measureUnitCodeListVersionIdentifier;
}
