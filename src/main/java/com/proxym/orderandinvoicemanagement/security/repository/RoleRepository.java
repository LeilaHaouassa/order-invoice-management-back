package com.proxym.orderandinvoicemanagement.security.repository;

import com.proxym.orderandinvoicemanagement.security.model.ERole;
import com.proxym.orderandinvoicemanagement.security.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role,String> {
    Optional<Role> findByName(ERole name);
}
