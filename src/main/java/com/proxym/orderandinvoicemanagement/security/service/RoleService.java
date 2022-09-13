package com.proxym.orderandinvoicemanagement.security.service;

import com.proxym.orderandinvoicemanagement.security.model.ERole;
import com.proxym.orderandinvoicemanagement.security.model.Role;
import com.proxym.orderandinvoicemanagement.security.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
@Slf4j
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role findByName(String name) throws Exception {
        String roleName = name.toUpperCase(Locale.ROOT).trim();
        ERole roleNameEnum = ERole.valueOf(roleName);
        Optional<Role> role = roleRepository.findByName(roleNameEnum);
        if (role.isPresent()){
            return role.get();
        }else {
            log.error("role name wasn't valid or role doesn't exist");
            throw new Exception("role name wasn't valid or role doesn't exist");
        }
    }

    public Role saveRole(Role role){
        log.info("adding new role to DB");
        return roleRepository.insert(role);
    }


}
