package com.toystore.onlinetoystore.service;

import com.toystore.onlinetoystore.model.CartItem;
import java.util.ArrayList;
import java.util.List;

public class CartService {

    private static final String FILE = System.getProperty("user.dir") + "/cart.txt";

    public void addToCart(String userId, String toyId, String toyName, double price, int quantity, String imageUrl) {
        List<String> lines = FileHelper.readLines(FILE);
        int id = lines.size() + 1;
        String line = "C" + id + "," + userId + "," + toyId + "," + toyName + "," + price + "," + quantity + "," + imageUrl;
        List<String> newLine = new ArrayList<>();
        newLine.add(line);
        FileHelper.writeLines(FILE, newLine);
    }

    public List<CartItem> getCartByUser(String userId) {
        List<CartItem> items = new ArrayList<>();
        List<String> lines = FileHelper.readLines(FILE);
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length >= 7 && parts[1].equals(userId)) {
                CartItem item = new CartItem(
                        parts[0], parts[1], parts[2],
                        parts[3],
                        Double.parseDouble(parts[4]),
                        Integer.parseInt(parts[5]),
                        parts[6]);
                items.add(item);
            }
        }
        return items;
    }

    public void removeFromCart(String cartItemId) {
        List<CartItem> allItems = getAllCartItems();
        List<String> lines = new ArrayList<>();
        for (CartItem item : allItems) {
            if (!item.getCartItemId().equals(cartItemId)) {
                lines.add(item.getCartItemId() + "," + item.getUserId() + "," +
                        item.getToyId() + "," + item.getToyName() + "," +
                        item.getPrice() + "," + item.getQuantity() + "," +
                        item.getImageUrl());
            }
        }
        FileHelper.overwriteLines(FILE, lines);
    }

    public void clearCartByUser(String userId) {
        List<CartItem> allItems = getAllCartItems();
        List<String> lines = new ArrayList<>();
        for (CartItem item : allItems) {
            if (!item.getUserId().equals(userId)) {
                lines.add(item.getCartItemId() + "," + item.getUserId() + "," +
                        item.getToyId() + "," + item.getToyName() + "," +
                        item.getPrice() + "," + item.getQuantity() + "," +
                        item.getImageUrl());
            }
        }
        FileHelper.overwriteLines(FILE, lines);
    }

    public List<CartItem> getAllCartItems() {
        List<CartItem> items = new ArrayList<>();
        List<String> lines = FileHelper.readLines(FILE);
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length >= 7) {
                CartItem item = new CartItem(
                        parts[0], parts[1], parts[2],
                        parts[3],
                        Double.parseDouble(parts[4]),
                        Integer.parseInt(parts[5]),
                        parts[6]);
                items.add(item);
            }
        }
        return items;
    }

    public double getCartTotal(String userId) {
        double total = 0;
        for (CartItem item : getCartByUser(userId)) {
            total += item.getTotal();
        }
        return total;
    }
}