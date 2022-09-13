package com.proxym.orderandinvoicemanagement.security.controller;

import com.proxym.orderandinvoicemanagement.security.payload.request.SignUpRequest;
import com.proxym.orderandinvoicemanagement.security.payload.response.MessageResponse;
import com.proxym.orderandinvoicemanagement.security.service.AuthService;
import com.proxym.orderandinvoicemanagement.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) throws Exception {
        MessageResponse messageResponse = authService.signUp(signUpRequest);
        if (messageResponse.getMessage().startsWith("Error")) {
            return ResponseEntity
                    .badRequest()
                    .body(messageResponse);
        } else {
            return ResponseEntity.ok(messageResponse);
        }
    }

    @GetMapping("/refreshtoken")
    public void refreshToken (HttpServletRequest request, HttpServletResponse response) throws IOException {
        authService.refreshToken(request,response);
    }
}


