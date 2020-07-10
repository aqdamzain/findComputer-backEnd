package com.tempest.tech.demo.models;

public class UserProfile {
    private final String id;
    private String name;
    private String address;
    private String profileImg;

    public UserProfile(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public void setAddress(String address) {
        this.address = address;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }
}
