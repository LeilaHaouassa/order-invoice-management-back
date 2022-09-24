package com.proxym.orderandinvoicemanagement.dto.commun;

import com.proxym.orderandinvoicemanagement.model.baseEntities.IdentifierType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.TextType;
import lombok.Data;

@Data
public class AddressDTO {
    private IdentifierType id;
    private CountryDTO Country;
    private TextType cityName;
    private TextType postalZone;
}
