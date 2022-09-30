package com.proxym.orderandinvoicemanagement.model.communEntities;

import com.proxym.orderandinvoicemanagement.model.baseEntities.AmountType;
import lombok.Data;

//"A class to describe a price, expressed in a data structure
// containing multiple properties (compare with UnstructuredPrice)."
@Data
public class Price {
    //Required
    private AmountType priceAmount;
}
