package com.tempest.tech.demo.utils;

import com.tempest.tech.demo.models.User;

public class EmailAlreadyExistsException extends Exception {
    private String email_error;

    public EmailAlreadyExistsException(String email_input) {
        super("User Email: ");
        email_error = email_input;
    }

    @Override
    public String getMessage(){
        return super.getMessage() + email_error + " already exists.";
    }
}
