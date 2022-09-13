package com.proxym.orderandinvoicemanagement.model.baseEntities;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

//"One calendar day according the Gregorian calendar."
@Data
public class DateType {
    //@Pattern(regexp="^-*[^T:-]+-[^T:]+$")
    @DateTimeFormat
    private String dateContent;
}



// Remember it has to follow the following conditions
//"allOf": [
//        {
//        "type": "string",
//        "format": "date-time"   YYYY:MM::DDThh:mm:ss.sTZD
//        },
//        {
//        "type": "string",
//        "pattern": "^-*[^T:-]+-[^T:]+$"
//        }
//        ]