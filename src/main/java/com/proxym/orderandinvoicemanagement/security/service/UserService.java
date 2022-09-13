package com.proxym.orderandinvoicemanagement.security.service;

import com.proxym.orderandinvoicemanagement.security.model.CustomUser;

import java.util.Set;

public interface  UserService {
    CustomUser saveUser(CustomUser user);
    CustomUser getUserByEmail(String email);
    Set<CustomUser> getAllUsers();
    void deleteUser(String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);


}
