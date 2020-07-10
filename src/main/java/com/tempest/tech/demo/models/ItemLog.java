package com.tempest.tech.demo.models;

public class ItemLog {
    private String id;
    private String name;
    private String description;
    private String category;
    private double price;
    private String itemImg;
    private UserProfile owner;
    private UserProfile buyer;

    public ItemLog(String id, String name, String description, String category, double price, String itemImg, UserProfile owner, UserProfile buyer) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.itemImg = itemImg;
        this.owner = owner;
        this.buyer = buyer;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public String getItemImg() {
        return itemImg;
    }

    public UserProfile getOwner() {
        return owner;
    }

    public UserProfile getBuyer() {
        return buyer;
    }
}
