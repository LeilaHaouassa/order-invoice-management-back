package com.proxym.orderandinvoicemanagement.services.Implementations;

import com.proxym.orderandinvoicemanagement.dto.invoiceRelated.InvoiceDTO;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderChangeDTO;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderDTO;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderReferenceDTO;
import com.proxym.orderandinvoicemanagement.model.invoiceEntities.Invoice;
import com.proxym.orderandinvoicemanagement.model.orderEntities.*;
import com.proxym.orderandinvoicemanagement.repositories.InvoiceRepository;
import com.proxym.orderandinvoicemanagement.services.IInvoiceSellerService;
import com.proxym.orderandinvoicemanagement.services.IInvoiceService;
import com.proxym.orderandinvoicemanagement.services.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class InvoiceSellerServiceImpl implements IInvoiceSellerService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private IInvoiceService invoiceService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Set<InvoiceDTO> getSentInvoices(String sellerPartyId) {
        Set<Invoice> invoices = invoiceRepository.findAllBySellerSupplierParty_Party_TechnicalId(sellerPartyId);
        return invoiceService.convertSetToDTO(invoices);
    }

    @Override
    public InvoiceDTO sendInvoice(String sellerPartyId, InvoiceDTO invoiceDTO) throws IllegalArgumentException {
        OrderReferenceDTO referenceDTO = invoiceDTO.getOrderReference();
        OrderDTO orderDTO = orderService.getOrderByTechnicalId(referenceDTO.getTechnicalId());
        Order order = modelMapper.map(orderDTO,Order.class);
        invoiceDTO.setOrderReference(orderService.assignRestOfOrderRefData(referenceDTO,order));
        invoiceDTO.setSellerSupplierParty(orderDTO.getSellerSupplierParty());
        invoiceDTO.setAccountingSupplierParty(orderDTO.getSellerSupplierParty());
        invoiceDTO.setBuyerCustomerParty(orderDTO.getBuyerCustomerParty());
        invoiceDTO.setAccountingCustomerParty(orderDTO.getBuyerCustomerParty());
        invoiceDTO.setIssueDate(invoiceService.getCurrentDate());
        invoiceDTO.setIssueTime(invoiceService.getCurrentTime());
        Invoice invoice = modelMapper.map(invoiceDTO,Invoice.class);
        try {
            invoice = invoiceRepository.insert(invoice);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new IllegalArgumentException("Invalid Input: Id of invoice or Id of an invoice line is already taken. Please provide new value for it.", exception);
        }
        return modelMapper.map(invoice,InvoiceDTO.class);
    }

    @Override
    public Object getDocumentForInvoice(String orderTechnicalId) {
        OrderDTO orderDTO = orderService.getOrderByTechnicalId(orderTechnicalId);
        String documentId = orderDTO.getIdOfDocumentForInvoice();
        if( orderDTO.getTechnicalId().equals(documentId)){
            return orderDTO;
        }else{
            List<Object> stack = orderDTO.getHistoryStack();
            for (int i = stack.size() - 1; i >= 0; i--)
            {
                if( stack.get(i) instanceof OrderChange){
                    if (((OrderChange)stack.get(i)).getTechnicalId().equals(documentId)){
                        return stack.get(i);
                    }
                }
                if(stack.get(i) instanceof OrderResponse){
                    if (((OrderResponse)stack.get(i)).getTechnicalId().equals(documentId)){
                        return stack.get(i);
                    }
                }
            }
        }
        return null;
    }
}
