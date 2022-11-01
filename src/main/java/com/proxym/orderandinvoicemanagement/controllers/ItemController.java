package com.proxym.orderandinvoicemanagement.controllers;

import com.proxym.orderandinvoicemanagement.dto.commun.ItemDTO;
import com.proxym.orderandinvoicemanagement.model.communEntities.Item.Item;
import com.proxym.orderandinvoicemanagement.services.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("api/v1/products")
public class ItemController {

    @Autowired
    private IItemService itemService;

    @GetMapping
    public ResponseEntity<Set<Item>> getAll(){
        return ResponseEntity.ok().body(itemService.getAll());
    }

    @PostMapping
    public ResponseEntity<ItemDTO> createItem(@RequestBody ItemDTO itemDTO){
        return ResponseEntity.ok().body(itemService.createItem(itemDTO));
    }

}
