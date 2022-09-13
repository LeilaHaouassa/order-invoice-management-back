package com.proxym.orderandinvoicemanagement.security.payload.request;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class SignUpRequest {
    private String username;
    private String email;
    private String password;

}
