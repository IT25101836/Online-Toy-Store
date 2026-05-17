package com.toystore.onlinetoystore.service;

import com.toystore.onlinetoystore.model.Payment;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PaymentService {

    private static final String FILE = System.getProperty("user.dir") + "/payments.txt";

    public void makePayment(String orderId, String userId, double amount,
                            String method, String billingName,
                            String billingAddress) {
        List<String> lines = FileHelper.readLines(FILE);
        int id = lines.size() + 1;
        String date = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String line = "P" + id + "," + orderId + "," + userId + "," +
                amount + "," + method + ",Paid," +
                billingName + "," + billingAddress + "," + date;
        List<String> newLine = new ArrayList<>();
        newLine.add(line);
        FileHelper.writeLines(FILE, newLine);
    }

    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        List<String> lines = FileHelper.readLines(FILE);
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length >= 9) {
                Payment payment = new Payment(
                        parts[0], parts[1], parts[2],
                        Double.parseDouble(parts[3]),
                        parts[4], parts[5],
                        parts[6], parts[7], parts[8]);
                payments.add(payment);
            }
        }
        return payments;
    }

    public List<Payment> getPaymentsByUser(String userId) {
        List<Payment> payments = new ArrayList<>();
        for (Payment payment : getAllPayments()) {
            if (payment.getUserId().equals(userId)) {
                payments.add(payment);
            }
        }
        return payments;
    }

    public Payment getPaymentByOrder(String orderId) {
        for (Payment payment : getAllPayments()) {
            if (payment.getOrderId().equals(orderId)) {
                return payment;
            }
        }
        return null;
    }

    public void updatePaymentStatus(String paymentId, String status) {
        List<Payment> payments = getAllPayments();
        List<String> lines = new ArrayList<>();
        for (Payment payment : payments) {
            if (payment.getPaymentId().equals(paymentId)) {
                lines.add(payment.getPaymentId() + "," +
                        payment.getOrderId() + "," +
                        payment.getUserId() + "," +
                        payment.getAmount() + "," +
                        payment.getMethod() + "," +
                        status + "," +
                        payment.getBillingName() + "," +
                        payment.getBillingAddress() + "," +
                        payment.getDate());
            } else {
                lines.add(payment.getPaymentId() + "," +
                        payment.getOrderId() + "," +
                        payment.getUserId() + "," +
                        payment.getAmount() + "," +
                        payment.getMethod() + "," +
                        payment.getStatus() + "," +
                        payment.getBillingName() + "," +
                        payment.getBillingAddress() + "," +
                        payment.getDate());
            }
        }
        FileHelper.overwriteLines(FILE, lines);
    }

    public double getTotalRevenue() {
        double total = 0;
        for (Payment payment : getAllPayments()) {
            if (payment.getStatus().equals("Paid")) {
                total += payment.getAmount();
            }
        }
        return total;
    }
}