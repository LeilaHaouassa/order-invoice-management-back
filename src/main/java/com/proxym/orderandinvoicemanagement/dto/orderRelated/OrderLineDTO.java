package com.proxym.orderandinvoicemanagement.dto.orderRelated;

import com.proxym.orderandinvoicemanagement.dto.commun.LineItemDTO;
import lombok.Data;

@Data
public class OrderLineDTO {
    private LineItemDTO lineItem;
}
