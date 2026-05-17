package com.toystore.onlinetoystore.model;

public class Toy {
    private String toyId;
    private String name;
    private String category;
    private double price;
    private int stock;
    private String description;
    private String imageUrl;

    public Toy() {}

    public Toy(String toyId, String name, String category, double price, int stock, String description, String imageUrl) {
        this.toyId = toyId;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getToyId() { return toyId; }
    public void setToyId(String toyId) { this.toyId = toyId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}