package com.proxym.orderandinvoicemanagement.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


//This class is named custom to differentiate it from the class or interface User of spring security
@Document(value="users")
@Data
public class CustomUser {
    @Id
    @JsonIgnore
    private String id;
    @NotBlank
    @Size(max = 20)
    private String username;
    @Indexed(unique = true)
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @NotBlank
    @JsonIgnore
    private String password;

    private Role role;

    public CustomUser(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }



}
