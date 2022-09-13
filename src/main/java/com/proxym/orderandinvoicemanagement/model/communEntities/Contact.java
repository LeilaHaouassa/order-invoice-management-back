package com.proxym.orderandinvoicemanagement.model.communEntities;

import com.proxym.orderandinvoicemanagement.model.baseEntities.IdentifierType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.TextType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor  //for model mapping
public class Contact {
    @Id
    private String technicalId;

    private IdentifierType id;

    private TextType name;

    private TextType telephone;

    private TextType electronicMail;


}
