package com.proxym.orderandinvoicemanagement.services;

import com.proxym.orderandinvoicemanagement.dto.invoiceRelated.InvoiceDTO;
import com.proxym.orderandinvoicemanagement.model.baseEntities.DateType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.TimeType;
import com.proxym.orderandinvoicemanagement.model.invoiceEntities.Invoice;

import java.util.Set;

public interface IInvoiceService {

    Set<InvoiceDTO> convertSetToDTO(Set<Invoice> invoices);

    TimeType getCurrentTime();

    DateType getCurrentDate();
}
