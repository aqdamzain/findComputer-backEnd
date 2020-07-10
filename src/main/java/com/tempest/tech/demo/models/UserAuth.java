package com.tempest.tech.demo.models;

public class UserAuth {
    private final String authId;
    private final String email;
    private String password;

    public UserAuth(String authId, String email, String password) {
        this.authId = authId;
        this.email = email;
        this.password = password;
    }

    public String getAuthId() {
        return authId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
