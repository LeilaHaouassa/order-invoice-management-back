package com.proxym.orderandinvoicemanagement.model.baseEntities;


import org.springframework.format.annotation.DateTimeFormat;

//"An instance of time that occurs every day."
public class TimeType {
    //@Pattern(regexp = "^-*[^T:-]+:.+$")
    @DateTimeFormat
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