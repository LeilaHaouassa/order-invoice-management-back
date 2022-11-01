package com.proxym.orderandinvoicemanagement.controllers;

import com.proxym.orderandinvoicemanagement.dto.orderRelated.OrderDTO;
import com.proxym.orderandinvoicemanagement.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("orderId") String orderTechnicalId){
        return ResponseEntity.ok().body(orderService.getOrderByTechnicalId(orderTechnicalId));
    }

    @GetMapping("/{orderId}/history")
    public ResponseEntity<List<Object>> getHistory(@PathVariable("orderId") String orderTechnicalId){
        return ResponseEntity.ok().body(orderService.getHistory(orderTechnicalId));
    }
}
