package com.toystore.onlinetoystore.model;

public class CartItem {
    private String cartItemId;
    private String userId;
    private String toyId;
    private String toyName;
    private double price;
    private int quantity;
    private String imageUrl;

    public CartItem() {}

    public CartItem(String cartItemId, String userId, String toyId, String toyName, double price, int quantity, String imageUrl) {
        this.cartItemId = cartItemId;
        this.userId = userId;
        this.toyId = toyId;
        this.toyName = toyName;
        this.price = price;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    public String getCartItemId() { return cartItemId; }
    public void setCartItemId(String cartItemId) { this.cartItemId = cartItemId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getToyId() { return toyId; }
    public void setToyId(String toyId) { this.toyId = toyId; }
    public String getToyName() { return toyName; }
    public void setToyName(String toyName) { this.toyName = toyName; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public double getTotal() { return price * quantity; }
}