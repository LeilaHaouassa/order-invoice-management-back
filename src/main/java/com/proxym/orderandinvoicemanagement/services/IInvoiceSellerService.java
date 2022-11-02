package com.proxym.orderandinvoicemanagement.services;

import com.proxym.orderandinvoicemanagement.dto.invoiceRelated.InvoiceDTO;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderDTO;

import java.util.Set;

public interface IInvoiceSellerService {

    Set<InvoiceDTO> getSentInvoices(String sellerPartyId);

    InvoiceDTO sendInvoice(String sellerPartyId, InvoiceDTO invoiceDTO) throws IllegalArgumentException;

    Object getDocumentForInvoice(String orderTechnicalId);
}
