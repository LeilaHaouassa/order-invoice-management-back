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

//    @PostMapping("/add")
//    public ResponseEntity<CustomUser> saveUser(@RequestBody UserPayload user ){
//        //The following line is just to get the uri from the path
//        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/users/add").toUriString());
//        return ResponseEntity.created(uri).body(userService.saveUser(user));
//    }






}

