package com.proxym.orderandinvoicemanagement.model.baseEntities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

//"An instance of time that occurs every day."
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TimeType {
    //@Pattern(regexp = "^-*[^T:-]+:.+$")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private  String timeContent;
}

// Remember it has to follow the following conditions
//"allOf": [
//        {
//        "type": "string",
//        "format": "date-time"
//        },
//        {
//        "type": "string",
//        "pattern": "^-*[^T:-]+:.+$"
//        }
//        ]