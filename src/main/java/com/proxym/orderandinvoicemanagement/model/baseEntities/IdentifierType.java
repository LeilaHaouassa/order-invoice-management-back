package com.proxym.orderandinvoicemanagement.model.baseEntities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

//"A character string to identify and distinguish uniquely, one instance of
// an object in an identification scheme from all other objects in the same
// scheme together with relevant supplementary information."

//For deserialisation purposes, must have a zero-arg constructor.

@Data
@NoArgsConstructor
@AllArgsConstructor //for builder
@Builder
public class IdentifierType {

    //Required
    private String identifierContent;

//    private String identificationSchemeIdentifier;
//    private String identificationSchemeNameText;
//    private String identificationSchemeAgencyIdentifier;
//    private String identificationSchemeAgencyNameText;
//    private String identificationSchemeVersionIdentifier;
//    private String identificationSchemeDataUniformResourceIdentifier;
//    private String identificationSchemeUniformResourceIdentifier;
}
