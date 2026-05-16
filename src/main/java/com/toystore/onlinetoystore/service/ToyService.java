package com.toystore.onlinetoystore.service;

import com.toystore.onlinetoystore.model.Toy;
import java.util.ArrayList;
import java.util.List;

public class ToyService {

    private static final String FILE = System.getProperty("user.dir") + "/toys.txt";

    public void addToy(String name, String category, double price, int stock, String description, String imageUrl, int discount) {
        List<String> lines = FileHelper.readLines(FILE);
        int id = lines.size() + 1;
        String line = "T" + id + "|" + name + "|" + category + "|" + price + "|" + stock + "|" + description + "|" + imageUrl + "|" + discount;
        List<String> newLine = new ArrayList<>();
        newLine.add(line);
        FileHelper.writeLines(FILE, newLine);
    }

    public List<Toy> getAllToys() {
        List<Toy> toys = new ArrayList<>();
        List<String> lines = FileHelper.readLines(FILE);
        for (String line : lines) {
            String[] parts = line.split("\\|");
            if (parts.length >= 7) {
                int discount = parts.length >= 8 ? Integer.parseInt(parts[7]) : 0;
                Toy toy = new Toy(parts[0], parts[1], parts[2],
                        Double.parseDouble(parts[3]),
                        Integer.parseInt(parts[4]),
                        parts[5], parts[6], discount);
                toys.add(toy);
            }
        }
        return toys;
    }

    public List<Toy> searchToys(String keyword) {
        List<Toy> result = new ArrayList<>();
        for (Toy toy : getAllToys()) {
            if (toy.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                    toy.getCategory().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(toy);
            }
        }
        return result;
    }

    public Toy findById(String toyId) {
        for (Toy toy : getAllToys()) {
            if (toy.getToyId().equals(toyId)) {
                return toy;
            }
        }
        return null;
    }

    public void deleteToy(String toyId) {
        List<Toy> toys = getAllToys();
        List<String> lines = new ArrayList<>();
        for (Toy toy : toys) {
            if (!toy.getToyId().equals(toyId)) {
                lines.add(toy.getToyId() + "|" + toy.getName() + "|" +
                        toy.getCategory() + "|" + toy.getPrice() + "|" +
                        toy.getStock() + "|" + toy.getDescription() + "|" +
                        toy.getImageUrl() + "|" + toy.getDiscount());
            }
        }
        FileHelper.overwriteLines(FILE, lines);
    }
}