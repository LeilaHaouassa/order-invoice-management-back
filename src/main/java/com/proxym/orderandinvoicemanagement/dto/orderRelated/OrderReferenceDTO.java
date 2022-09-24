package com.proxym.orderandinvoicemanagement.dto.orderRelated;

import com.proxym.orderandinvoicemanagement.model.baseEntities.DateType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.IdentifierType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.TimeType;
import lombok.Data;

@Data
public class OrderReferenceDTO {
    private String technicalId;
    private IdentifierType id;
    private DateType issueDate;
    private TimeType issueTime ;
}
