package com.proxym.orderandinvoicemanagement.model.communEntities;

import com.proxym.orderandinvoicemanagement.model.baseEntities.IdentifierType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.TextType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//"A class to define common information related to an address."
@Data
@NoArgsConstructor  //for model mapping
public class Address {

    //"An identifier for this address within an agreed scheme of address identifiers.",
    private IdentifierType id;

    //"The country in which this address is situated."
    private Country  country;

    //"The name of a city, town, or village."
    private TextType cityName;

    //"The postal identifier for this address according to the
    // relevant national postal service, such as a ZIP code or Post Code."
    private TextType  postalZone;
}
