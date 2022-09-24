package com.proxym.orderandinvoicemanagement.dto.orderRelated;

import com.proxym.orderandinvoicemanagement.dto.commun.CustomerPartyDTO;
import com.proxym.orderandinvoicemanagement.dto.commun.SupplierPartyDTO;
import com.proxym.orderandinvoicemanagement.model.baseEntities.DateType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.IdentifierType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.TextType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.TimeType;
import com.proxym.orderandinvoicemanagement.model.communEntities.CustomerParty;
import com.proxym.orderandinvoicemanagement.model.communEntities.SupplierParty;
import com.proxym.orderandinvoicemanagement.model.orderEntities.OrderReference;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class OrderCancellationDTO {
    private IdentifierType id;
    private DateType issueDate ;
    private TimeType issueTime ;
    private Set<TextType> cancellationNote = new HashSet<>();
    private Set<OrderReferenceDTO> orderReference = new HashSet<>();
    private CustomerPartyDTO buyerCustomerParty;
    private SupplierPartyDTO sellerSupplierParty;
}
