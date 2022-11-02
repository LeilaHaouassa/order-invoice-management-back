package com.proxym.orderandinvoicemanagement.controllers;

import com.proxym.orderandinvoicemanagement.dto.invoiceRelated.InvoiceDTO;
import com.proxym.orderandinvoicemanagement.services.IInvoiceSellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("api/v1/parties/{partyId}/supplier-service/invoices")
public class InvoiceSellerController {

    @Autowired
    private IInvoiceSellerService sellerService;

    @GetMapping("/sent")
    public ResponseEntity<Set<InvoiceDTO>> getAllReceivedOrders(@PathVariable("partyId") String sellerPartyId){
        return ResponseEntity.ok().body(sellerService.getSentInvoices(sellerPartyId));
    }

    @PostMapping("/send")
    public ResponseEntity<InvoiceDTO> placeOrder(@PathVariable("partyId") String sellerPartyId, @RequestBody InvoiceDTO invoiceDTO){
        //TODO switch to created Http Response
        //URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/parties/{partyId}/supplier-side/invoices/send").build().toUriString());
        return ResponseEntity.ok().body(sellerService.sendInvoice(sellerPartyId,invoiceDTO));
    }

    @GetMapping("/prep-document/{orderId}")
    public ResponseEntity<Object> getDocumentForInvoice(@PathVariable("orderId") String orderId){

        return ResponseEntity.ok().body(sellerService.getDocumentForInvoice(orderId));
    }



}
