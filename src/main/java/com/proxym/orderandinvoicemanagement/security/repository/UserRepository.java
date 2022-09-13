package com.proxym.orderandinvoicemanagement.security.repository;

import com.proxym.orderandinvoicemanagement.security.model.CustomUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<CustomUser,String> {

    Optional<CustomUser> findByEmail(String s);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
