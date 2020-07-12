package com.tempest.tech.demo.models;

public class User {
    private final String tokenId;
    private final UserProfile data;

    public User(String tokenId, UserProfile data) {
        this.tokenId = tokenId;
        this.data = data;
    }

    public String getTokenId() {
        return tokenId;
    }

    public UserProfile getData() {
        return data;
    }
}
