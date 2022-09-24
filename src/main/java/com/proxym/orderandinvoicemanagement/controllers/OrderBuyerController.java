package com.proxym.orderandinvoicemanagement.controllers;


import com.proxym.orderandinvoicemanagement.services.IOrderBuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("api/v1/parties")
public class OrderBuyerController {

    @Autowired
    private IOrderBuyerService orderBuyerService;


}
