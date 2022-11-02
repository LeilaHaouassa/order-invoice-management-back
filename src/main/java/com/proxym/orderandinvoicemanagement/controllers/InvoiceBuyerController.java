package com.proxym.orderandinvoicemanagement.controllers;

import com.proxym.orderandinvoicemanagement.dto.invoiceRelated.InvoiceDTO;
import com.proxym.orderandinvoicemanagement.services.IInvoiceBuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("api/v1/parties/{partyId}/customer-service/invoices")
public class InvoiceBuyerController {

    @Autowired
    private IInvoiceBuyerService buyerService;

    @GetMapping("/received")
    public ResponseEntity<Set<InvoiceDTO>> getAllSentOrders(@PathVariable("partyId") String buyerPartyId){
        return ResponseEntity.ok().body(buyerService.getReceivedInvoices(buyerPartyId));
    }
}
