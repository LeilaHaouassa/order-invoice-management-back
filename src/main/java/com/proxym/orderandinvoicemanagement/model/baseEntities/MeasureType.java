package com.proxym.orderandinvoicemanagement.model.baseEntities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//"A numeric value determined
// by measuring an object using a specified unit of measure."
@Data
@NoArgsConstructor
public class MeasureType {
    //Required
    private Long measureContent;
    //Required
    private String measureUnitCode;

    //private String measureUnitCodeListVersionIdentifier;
}
