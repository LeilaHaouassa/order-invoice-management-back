package com.proxym.orderandinvoicemanagement.services.Implementations;

import com.proxym.orderandinvoicemanagement.dto.invoiceRelated.InvoiceDTO;
import com.proxym.orderandinvoicemanagement.model.baseEntities.DateType;
import com.proxym.orderandinvoicemanagement.model.baseEntities.TimeType;
import com.proxym.orderandinvoicemanagement.model.invoiceEntities.Invoice;
import com.proxym.orderandinvoicemanagement.services.IInvoiceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements IInvoiceService {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Set<InvoiceDTO> convertSetToDTO(Set<Invoice> invoices) {
        return invoices.stream().map(invoice -> modelMapper.map(invoice, InvoiceDTO.class)).collect(Collectors.toSet());
    }

    @Override
    public TimeType getCurrentTime() {
        return TimeType.builder().timeContent(LocalTime.now().toString()).build();
    }

    @Override
    public DateType getCurrentDate() {
        return DateType.builder().dateContent(LocalDate.now().toString()).build();
    }
}
