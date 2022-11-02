package com.proxym.orderandinvoicemanagement.model.invoiceEntities;

import com.proxym.orderandinvoicemanagement.model.baseEntities.DateType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.IdentifierType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.TimeType;
import com.proxym.orderandinvoicemanagement.model.communEntities.CustomerParty;
import com.proxym.orderandinvoicemanagement.model.communEntities.MonetaryTotal;
import com.proxym.orderandinvoicemanagement.model.communEntities.SupplierParty;
import com.proxym.orderandinvoicemanagement.model.orderEntities.OrderLine;
import com.proxym.orderandinvoicemanagement.model.orderEntities.OrderReference;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Data
@Document
public class Invoice {
    @Id
    private String technicalId;

    //Required
    //"An identifier for this document, assigned by the sender.
    @Indexed(unique = true)
    private IdentifierType id;

    //Required
    //"The date, assigned by the sender, on which this document was issued.",
    private DateType issueDate ;

    //"The time, assigned by the sender, on which this document was issued.",
    private TimeType issueTime ;

    //A reference to the Order with which this Invoice is associated.
    private OrderReference orderReference;

    //"The accounting customer party."  //Required
    private CustomerParty accountingCustomerParty;

    //"The accounting supplier party.."  //Required
    private SupplierParty accountingSupplierParty;

    //"The buyer."
    private CustomerParty buyerCustomerParty;

    //"The seller."
    private SupplierParty sellerSupplierParty;

    //Required
    //"The total amount payable on the Invoice, including Allowances, Charges, and Taxes."
    private MonetaryTotal legalMonetaryTotal;

    //Required
    //A line describing an invoice item
    private Set<InvoiceLine> invoiceLine = new HashSet<>();
}
