package com.toystore.onlinetoystore.model;

public class Payment {
    private String paymentId;
    private String orderId;
    private String userId;
    private double amount;
    private String method;
    private String status;
    private String billingName;
    private String billingAddress;
    private String date;

    public Payment() {}

    public Payment(String paymentId, String orderId, String userId,
                   double amount, String method, String status,
                   String billingName, String billingAddress, String date) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.userId = userId;
        this.amount = amount;
        this.method = method;
        this.status = status;
        this.billingName = billingName;
        this.billingAddress = billingAddress;
        this.date = date;
    }

    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getBillingName() { return billingName; }
    public void setBillingName(String billingName) { this.billingName = billingName; }
    public String getBillingAddress() { return billingAddress; }
    public void setBillingAddress(String billingAddress) { this.billingAddress = billingAddress; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}