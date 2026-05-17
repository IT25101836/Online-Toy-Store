package com.toystore.onlinetoystore.model;

public class Order {
    private String orderId;
    private String userId;
    private String toyName;
    private int quantity;
    private double totalPrice;
    private String status;
    private String date;

    public Order() {}

    public Order(String orderId, String userId, String toyName, int quantity, double totalPrice, String status, String date) {
        this.orderId = orderId;
        this.userId = userId;
        this.toyName = toyName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.status = status;
        this.date = date;
    }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getToyName() { return toyName; }
    public void setToyName(String toyName) { this.toyName = toyName; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }


    public boolean isCancellable() {
        return "Pending".equalsIgnoreCase(this.status);
    }
}
