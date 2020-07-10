package com.tempest.tech.demo.utils;

import com.tempest.tech.demo.models.User;

public class UserNotFoundException extends Exception {
    private String user_error;

    public UserNotFoundException(String user_input) {
        super("User Id: ");
        user_error = user_input;
    }

    @Override
    public String getMessage(){
        return super.getMessage() + user_error + " Not Found.";
    }
}
