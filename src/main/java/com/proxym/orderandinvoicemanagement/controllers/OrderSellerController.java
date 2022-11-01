package com.proxym.orderandinvoicemanagement.controllers;


import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderDTO;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderResponseDTO;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderResponseSimpleDTO;
import com.proxym.orderandinvoicemanagement.services.IOrderSellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("api/v1/parties/{partyId}/supplier-service/orders")
public class OrderSellerController {

    @Autowired
    private IOrderSellerService sellerService;

    @GetMapping("/received")
    public ResponseEntity<Set<OrderDTO>> getAllReceivedOrders(@PathVariable("partyId") String sellerPartyId){
        return ResponseEntity.ok().body(sellerService.getAllReceivedOrders(sellerPartyId));
    }

    @PostMapping("/reject")
    public ResponseEntity<OrderDTO> rejectOrder(@PathVariable("partyId") String sellerPartyId, @RequestBody OrderResponseSimpleDTO orderResponseSimpleDTO){
        return ResponseEntity.ok().body(sellerService.rejectOrder(sellerPartyId,orderResponseSimpleDTO));
    }

    @PostMapping("/add-detail")
    public ResponseEntity<Set<OrderDTO>> addDetail(@PathVariable("partyId") String sellerPartyId, @RequestBody OrderResponseDTO orderResponseDTO){
        return  ResponseEntity.ok().body(sellerService.addDetail(sellerPartyId,orderResponseDTO));
    }
//TODO maybe change accept-required to confirm
    @PostMapping("/accept-required")
    public ResponseEntity<OrderDTO> acceptOrderResponseRequired(@PathVariable("partyId") String sellerPartyId, @RequestBody OrderResponseSimpleDTO orderResponseSimpleDTO){
        return ResponseEntity.ok().body(sellerService.acceptOrderResponseRequired(sellerPartyId,orderResponseSimpleDTO));
    }

    @PostMapping("/accept")
    public  ResponseEntity<OrderDTO> acceptOrder(@RequestBody String orderTechnicalIdReceived){
        String orderTechnicalId = orderTechnicalIdReceived.substring(1,orderTechnicalIdReceived.length()-1);
        return ResponseEntity.ok().body(sellerService.acceptOrderWithoutResponse(orderTechnicalId));
    }

    @GetMapping("/{orderId}/negotiating-document")
    public  ResponseEntity<Object> getDocumentForNegotiation(@PathVariable("orderId") String orderTechnicalId){
        return ResponseEntity.ok().body(sellerService.getDocumentForNegotiation(orderTechnicalId));
    }

}
