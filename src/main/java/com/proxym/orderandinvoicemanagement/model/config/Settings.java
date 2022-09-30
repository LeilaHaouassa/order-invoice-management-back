package com.proxym.orderandinvoicemanagement.model.config;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
//Must implement singleton design pattern bcz it doesn't make sense there are many settings documents
public class Settings {

    private String id = "1ID";

    private Boolean responseToBuyerIsRequiredWhenAcceptingOrder;
}
