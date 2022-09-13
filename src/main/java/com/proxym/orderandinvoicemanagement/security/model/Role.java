package com.proxym.orderandinvoicemanagement.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class Role {
    @Id
    @JsonIgnore
    private String id;
    //@Indexed(unique = true)
    private ERole name;

    public Role(ERole name) {
        this.name = name;
    }


}
