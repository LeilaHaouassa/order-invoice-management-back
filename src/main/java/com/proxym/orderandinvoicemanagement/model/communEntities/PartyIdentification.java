package com.proxym.orderandinvoicemanagement.model.communEntities;

import com.proxym.orderandinvoicemanagement.model.baseEntities.IdentifierType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


//"A class to define an identifier for a party."
@Data
@NoArgsConstructor  //for model mapping
//@AllArgsConstructor
@Builder
public class PartyIdentification {


    //"An identifier for the party."
    private IdentifierType id ;

    public PartyIdentification(IdentifierType id) {
        setId(id);
    }
}
