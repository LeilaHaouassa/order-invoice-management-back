package com.proxym.orderandinvoicemanagement.exception;

//This exception's purpose is to pinpoint when the user provides wrong id or
//other parameter to fetch an object

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
