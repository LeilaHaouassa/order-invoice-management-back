package com.proxym.orderandinvoicemanagement.model.baseEntities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

//"One calendar day according the Gregorian calendar."
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class DateType {
    //@Pattern(regexp="^-*[^T:-]+-[^T:]+$")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String dateContent;
}



// Remember it has to follow the following conditions
//"allOf": [
//        {
//        "type": "string",
//        "format": "date-time"   YYYY:MM::DDThh:mm:ss.sTZD  yyyy-MM-dd'T'HH:mm:ss.SSSXXX
//        },
//        {
//        "type": "string",
//        "pattern": "^-*[^T:-]+-[^T:]+$"
//        }
//        ]