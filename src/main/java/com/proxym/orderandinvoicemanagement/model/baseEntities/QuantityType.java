package com.proxym.orderandinvoicemanagement.model.baseEntities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//"A counted number of non-monetary units possibly including fractions."
@Data
@NoArgsConstructor
public class QuantityType {
    //Required
    private Double quantityContent;
}
