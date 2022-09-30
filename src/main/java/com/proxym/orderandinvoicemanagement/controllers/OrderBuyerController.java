package com.proxym.orderandinvoicemanagement.controllers;


import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderCancellationDTO;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderChangeDTO;
import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderDTO;
import com.proxym.orderandinvoicemanagement.model.orderEntities.Order;
import com.proxym.orderandinvoicemanagement.model.orderEntities.OrderChange;
import com.proxym.orderandinvoicemanagement.services.IOrderBuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("api/v1/parties/{partyId}/customer-service/orders")
public class OrderBuyerController {

    @Autowired
    private IOrderBuyerService orderBuyerService;

//todo to be deleted, for test purposes only
    @GetMapping("/all")
    public ResponseEntity<Set<OrderDTO>> getAll(){
        return ResponseEntity.ok().body(orderBuyerService.getAll());
    }

    @GetMapping("/sent")
    public ResponseEntity<Set<OrderDTO>> getAllSentOrders(@PathVariable("partyId") String partyId){
        return ResponseEntity.ok().body(orderBuyerService.getAllSentOrders(partyId));
    }

    @PostMapping("/send")
    public ResponseEntity<OrderDTO> placeOrder(@PathVariable("partyId") String partyId, @RequestBody OrderDTO orderDTO){
        //TODO switch to created Http Response
        //URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/parties/{partyId}/customerSide/orders/send").build().toUriString());
        return ResponseEntity.ok().body(orderBuyerService.placeOrder(orderDTO));
    }

    @PutMapping
    public ResponseEntity<OrderChangeDTO> changeOrder(@RequestBody OrderChangeDTO orderChangeDTO){
        return ResponseEntity.ok().body(orderBuyerService.changeOrder(orderChangeDTO));
    }

    @PostMapping("/cancel")
    public ResponseEntity<OrderCancellationDTO> cancelOrder(@RequestBody OrderCancellationDTO orderCancellationDTO){
        return ResponseEntity.ok().body(orderBuyerService.cancelOrder(orderCancellationDTO));
    }

    @PostMapping("/accept")
    public ResponseEntity<?> acceptOrder(@RequestParam String technicalId){
        orderBuyerService.acceptOrder(technicalId);
        return ResponseEntity.ok("Order with id "+technicalId+ " is accepted!");
    }









}
