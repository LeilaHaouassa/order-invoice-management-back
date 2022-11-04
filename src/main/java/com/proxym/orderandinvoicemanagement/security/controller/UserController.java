package com.proxym.orderandinvoicemanagement.security.controller;

import com.proxym.orderandinvoicemanagement.security.model.CustomUser;
import com.proxym.orderandinvoicemanagement.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<Set<CustomUser>> getAllUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }
}