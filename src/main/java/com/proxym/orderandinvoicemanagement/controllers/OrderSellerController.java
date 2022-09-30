package com.proxym.orderandinvoicemanagement.controllers;

import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderDTO;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderResponseDTO;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderResponseSimpleDTO;
import com.proxym.orderandinvoicemanagement.model.orderEntities.OrderResponseSimple;
import com.proxym.orderandinvoicemanagement.services.IOrderSellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("api/v1/parties/{partyId}/seller-side/orders")
public class OrderSellerController {

    @Autowired
    private IOrderSellerService sellerService;

    @GetMapping("/received")
    public ResponseEntity<Set<OrderDTO>> getAllReceivedOrders(@PathVariable("partyId") String partyId){
        return ResponseEntity.ok().body(sellerService.getAllReceivedOrders(partyId));
    }

    @PostMapping("/reject")
    public ResponseEntity<OrderResponseSimpleDTO> rejectOrder(@RequestBody OrderResponseSimpleDTO orderResponseSimpleDTO){
        return ResponseEntity.ok().body(sellerService.rejectOrder(orderResponseSimpleDTO));
    }

    @PostMapping("/add-detail")
    public ResponseEntity<OrderResponseDTO> addDetail(@RequestBody OrderResponseDTO orderResponseDTO){
        return  ResponseEntity.ok().body(sellerService.addDetail(orderResponseDTO));
    }

    @PostMapping("/accept-required")
    public ResponseEntity<OrderResponseSimpleDTO> acceptOrderResponseRequired(@RequestBody OrderResponseSimpleDTO orderResponseSimpleDTO){
        return ResponseEntity.ok().body(sellerService.acceptOrderResponseRequired(orderResponseSimpleDTO));
    }

    @PostMapping("/accept")
    public  ResponseEntity<?> acceptOrder(@RequestParam String technicalId){
        sellerService.acceptOrderWithoutResponse(technicalId);
        return ResponseEntity.ok("Order with id "+technicalId+ " is accepted!");
    }

}
