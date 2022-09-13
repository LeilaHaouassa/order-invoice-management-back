package com.proxym.orderandinvoicemanagement.security.service;

import com.proxym.orderandinvoicemanagement.security.model.CustomUser;
import com.proxym.orderandinvoicemanagement.security.model.Role;
import com.proxym.orderandinvoicemanagement.security.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // This is the only method that belongs to UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CustomUser user = getUserByEmail(email);
        if (user == null) {
            log.error("user not found in the database");
            throw new UsernameNotFoundException("user not found in the database");
        } else {
            log.info("user found in the DB");
        }
        Collection<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName().toString()));
        return new User(user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public CustomUser saveUser(CustomUser user) {
        log.info("Saving new user to the database");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.insert(user);
    }

//    @Override
//    public void addRoleToUser(String role, String email) throws Exception {
//
//        CustomUser user = getUserByEmail(email);
//        Role roleToAdd = roleService.findByName(role);
//        log.info("adding role {} to user with email {}", role, email);
//        user.setRole(roleToAdd);
//        userRepository.save(user);
//    }

    @Override
    public CustomUser getUserByEmail(String email) {
        log.info("Fetching user with email {}", email);
        Optional<CustomUser> user = userRepository.findByEmail(email);
        return user.orElse(null);
    }

    @Override
    public Set<CustomUser> getAllUsers() {
        log.info("Fetching all users ");
        HashSet<CustomUser> users = new HashSet<>(userRepository.findAll());
        return users;
    }

    @Override
    public void deleteUser(String email) {
        log.info("USer with email {} is deleted", email);
        CustomUser user = getUserByEmail(email);
        userRepository.delete(user);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


}
