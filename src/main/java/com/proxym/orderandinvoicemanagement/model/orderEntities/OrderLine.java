package com.proxym.orderandinvoicemanagement.model.orderEntities;

import com.proxym.orderandinvoicemanagement.model.communEntities.LineItem;
import lombok.Data;
import org.springframework.data.annotation.Id;

//"A class to define a line in an order document (e.g., Order,
// Order Change, or Order Response) describing an item being ordered."
@Data
public class OrderLine {

    //"The line item itself."
    private LineItem lineItem;
}
