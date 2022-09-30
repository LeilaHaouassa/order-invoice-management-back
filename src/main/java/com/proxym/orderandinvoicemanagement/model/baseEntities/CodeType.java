package com.proxym.orderandinvoicemanagement.model.baseEntities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// "A character string (letters, figures, or symbols) that for brevity
// and/or language independence may be used to represent or replace
// a definitive value or text of an attribute together with relevant
// supplementary information."

//For deserialisation purposes, must have a zero-arg constructor.

@Data
@NoArgsConstructor
public class CodeType {
    //Required
    private String codeContent;

//    private String codeListIdentifier;
//    private String codeListAgencyIdentifier;
//    private String codeListAgencyNameText;
//    private String codeListNameText;
//    private String codeListVersionIdentifier;
//    private String codeNameText;
//    private String languageIdentifier;
//    private String codeListUniformResourceIdentifier;
//    private String codeListSchemeUniformResourceIdentifier;
}
