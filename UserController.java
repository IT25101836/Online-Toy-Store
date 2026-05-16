package com.toystore.onlinetoystore.controller;

import com.toystore.onlinetoystore.model.User;
import com.toystore.onlinetoystore.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    private UserService userService = new UserService();

    @GetMapping("/")
    public String showLanding() {
        return "landing";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam String address,
            Model model) {
        userService.registerUser(username, password, email, address);
        model.addAttribute("message", "Registration successful! Please login.");
        return "login";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model) {
        User user = userService.loginUser(username, password);
        if (user != null) {
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", user.getRole());
            model.addAttribute("user", user);
            return "home";
        } else {
            model.addAttribute("error", "Invalid username or password!");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/home")
    public String showHome(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        return "home";
    }

    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(
            @RequestParam String username,
            @RequestParam String address,
            HttpSession session,
            Model model) {
        String userId = (String) session.getAttribute("userId");
        userService.updateUser(userId, username, address);
        session.setAttribute("username", username);
        model.addAttribute("message", "Profile updated successfully!");
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        return "profile";
    }
}