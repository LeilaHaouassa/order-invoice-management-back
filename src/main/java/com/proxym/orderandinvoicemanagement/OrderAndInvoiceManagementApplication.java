package com.proxym.orderandinvoicemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableMongoRepositories
public class OrderAndInvoiceManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderAndInvoiceManagementApplication.class, args);
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    CommandLineRunner run(UserService userService){
//        return args -> {
//            userService.saveUser( new CustomUser("John Travolta","john@jhannem.tn","pass123"));
//            userService.saveUser( new CustomUser("John weld3amou","cousin@jhannem.tn","pass123"));
//            userService.saveUser( new CustomUser("John bouh","father@jhannem.tn","pass123"));
//
//            userService.addRoleToUser("user","john@jhannem.tn");
//            userService.addRoleToUser("user","cousin@jhannem.tn");
//            userService.addRoleToUser("user","father@jhannem.tn");
//        };
//    }


}

