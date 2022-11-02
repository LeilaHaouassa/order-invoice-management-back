package com.proxym.orderandinvoicemanagement.services;


import com.proxym.orderandinvoicemanagement.dto.invoiceRelated.InvoiceDTO;


import java.util.Set;

public interface IInvoiceBuyerService {

    Set<InvoiceDTO> getReceivedInvoices(String partyId);

}
