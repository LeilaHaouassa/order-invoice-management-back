package com.proxym.orderandinvoicemanagement.dto.commun;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierPartyDTO {
    private PartyRefDTO party;
}
