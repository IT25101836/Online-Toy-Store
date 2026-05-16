package com.toystore.onlinetoystore.service;

import com.toystore.onlinetoystore.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private static final String FILE = System.getProperty("user.dir") + "/users.txt";

    public void registerUser(String username, String password, String email, String address) {
        List<String> lines = FileHelper.readLines(FILE);
        int id = lines.size() + 1;
        String line = "U" + id + "," + username + "," + password + "," + email + "," + address + ",CUSTOMER";
        List<String> newLine = new ArrayList<>();
        newLine.add(line);
        FileHelper.writeLines(FILE, newLine);
    }

    public User loginUser(String username, String password) {
        List<String> lines = FileHelper.readLines(FILE);
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length >= 6) {
                if (parts[1].equals(username) && parts[2].equals(password)) {
                    return new User(parts[0], parts[1], parts[2],
                            parts[3], parts[4], parts[5]);
                }
            }
        }
        return null;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        List<String> lines = FileHelper.readLines(FILE);
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length >= 6) {
                users.add(new User(parts[0], parts[1], parts[2],
                        parts[3], parts[4], parts[5]));
            }
        }
        return users;
    }

    public User findById(String userId) {
        List<String> lines = FileHelper.readLines(FILE);
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length >= 6 && parts[0].equals(userId)) {
                return new User(parts[0], parts[1], parts[2],
                        parts[3], parts[4], parts[5]);
            }
        }
        return null;
    }

    public void updateUser(String userId, String username, String address) {
        List<User> users = getAllUsers();
        List<String> lines = new ArrayList<>();
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                lines.add(userId + "," + username + "," + user.getPassword() + "," +
                        user.getEmail() + "," + address + "," + user.getRole());
            } else {
                lines.add(user.getUserId() + "," + user.getUsername() + "," +
                        user.getPassword() + "," + user.getEmail() + "," +
                        user.getAddress() + "," + user.getRole());
            }
        }
        FileHelper.overwriteLines(FILE, lines);
    }

    public void deleteUser(String userId) {
        List<User> users = getAllUsers();
        List<String> lines = new ArrayList<>();
        for (User user : users) {
            if (!user.getUserId().equals(userId)) {
                lines.add(user.getUserId() + "," + user.getUsername() + "," +
                        user.getPassword() + "," + user.getEmail() + "," +
                        user.getAddress() + "," + user.getRole());
            }
        }
        FileHelper.overwriteLines(FILE, lines);
    }
}