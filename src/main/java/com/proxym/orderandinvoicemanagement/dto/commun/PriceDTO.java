package com.proxym.orderandinvoicemanagement.dto.commun;

import com.proxym.orderandinvoicemanagement.model.baseEntities.AmountType;
import lombok.Data;

@Data
public class PriceDTO {
    private AmountType priceAmount;
}
