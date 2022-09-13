package com.proxym.orderandinvoicemanagement.model.communEntities;

import com.proxym.orderandinvoicemanagement.model.baseEntities.TextType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

//"The name of the party."
@Data
@NoArgsConstructor  //for model mapping
@AllArgsConstructor   // for builder
@Builder
@Document
public class PartyName {
    @Id
    private String technicalId;

    @Indexed(unique = true)
    private TextType name;

    public PartyName(TextType partyName) {
        setName(partyName);
    }
}