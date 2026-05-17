package com.toystore.onlinetoystore.service;

import com.toystore.onlinetoystore.model.CartItem;
import com.toystore.onlinetoystore.model.Order;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private static final String FILE = System.getProperty("user.dir") + "/orders.txt";

    public void placeOrder(String userId, List<CartItem> cartItems) {
        List<String> lines = FileHelper.readLines(FILE);
        int id = lines.size() + 1;
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        List<String> newLines = new ArrayList<>();
        for (CartItem item : cartItems) {
            double total = item.getPrice() * item.getQuantity();
            String line = "O" + id + "," + userId + "," + item.getToyName() + "," +
                    item.getQuantity() + "," + total + ",Pending," + date;
            newLines.add(line);
            id++;
        }
        FileHelper.writeLines(FILE, newLines);
    }

    public List<Order> getOrdersByUser(String userId) {
        List<Order> orders = new ArrayList<>();
        List<String> lines = FileHelper.readLines(FILE);
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length >= 7 && parts[1].equals(userId)) {
                Order order = new Order(
                        parts[0], parts[1], parts[2],
                        Integer.parseInt(parts[3]),
                        Double.parseDouble(parts[4]),
                        parts[5], parts[6]);
                orders.add(order);
            }
        }
        return orders;
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        List<String> lines = FileHelper.readLines(FILE);
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length >= 7) {
                Order order = new Order(
                        parts[0], parts[1], parts[2],
                        Integer.parseInt(parts[3]),
                        Double.parseDouble(parts[4]),
                        parts[5], parts[6]);
                orders.add(order);
            }
        }
        return orders;
    }

    public void updateOrderStatus(String orderId, String status) {
        List<Order> orders = getAllOrders();
        List<String> lines = new ArrayList<>();
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) {
                lines.add(order.getOrderId() + "," + order.getUserId() + "," +
                        order.getToyName() + "," + order.getQuantity() + "," +
                        order.getTotalPrice() + "," + status + "," + order.getDate());
            } else {
                lines.add(order.getOrderId() + "," + order.getUserId() + "," +
                        order.getToyName() + "," + order.getQuantity() + "," +
                        order.getTotalPrice() + "," + order.getStatus() + "," +
                        order.getDate());
            }
        }
        FileHelper.overwriteLines(FILE, lines);
    }
}