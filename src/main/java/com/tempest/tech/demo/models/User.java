package com.tempest.tech.demo.models;

public class User {
    private final String id;
    private final String email;
    private String password;
    private String name;
    private String address;
    private String profileImg;

    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }
}
