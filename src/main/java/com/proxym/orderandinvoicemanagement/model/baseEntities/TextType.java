package com.proxym.orderandinvoicemanagement.model.baseEntities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//"A character string (i.e. a finite set
// of characters) generally in the form of words of a language."

//For deserialisation purposes, must have a zero-arg constructor.

@Data
@NoArgsConstructor
public class TextType {
    //Required
    private String textContent;

//    private String languageIdentifier;
//    private String languageLocaleIdentifier;
}
