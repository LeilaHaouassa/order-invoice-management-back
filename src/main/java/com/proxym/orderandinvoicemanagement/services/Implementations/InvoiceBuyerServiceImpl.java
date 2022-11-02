package com.proxym.orderandinvoicemanagement.services.Implementations;

import com.proxym.orderandinvoicemanagement.dto.invoiceRelated.InvoiceDTO;
import com.proxym.orderandinvoicemanagement.model.invoiceEntities.Invoice;
import com.proxym.orderandinvoicemanagement.repositories.InvoiceRepository;
import com.proxym.orderandinvoicemanagement.services.IInvoiceBuyerService;
import com.proxym.orderandinvoicemanagement.services.IInvoiceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class InvoiceBuyerServiceImpl implements IInvoiceBuyerService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private IInvoiceService invoiceService;

    @Override
    public Set<InvoiceDTO> getReceivedInvoices(String buyerPartyId) {
        Set<Invoice> invoices = invoiceRepository.findAllByBuyerCustomerParty_Party_TechnicalId(buyerPartyId);
        return invoiceService.convertSetToDTO(invoices);
    }
}
