package com.toystore.onlinetoystore.controller;

import com.toystore.onlinetoystore.model.Order;
import com.toystore.onlinetoystore.model.User;
import com.toystore.onlinetoystore.service.OrderService;
import com.toystore.onlinetoystore.service.ToyService;
import com.toystore.onlinetoystore.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {

    private UserService userService = new UserService();
    private ToyService toyService = new ToyService();
    private OrderService orderService = new OrderService();

    @GetMapping("/admin")
    public String showAdminDashboard(HttpSession session, Model model) {
        String role = (String) session.getAttribute("role");
        if (role == null || !role.equals("ADMIN")) {
            return "redirect:/login";
        }
        model.addAttribute("totalUsers", userService.getAllUsers().size());
        model.addAttribute("totalToys", toyService.getAllToys().size());
        model.addAttribute("totalOrders", orderService.getAllOrders().size());
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("users", userService.getAllUsers());
        return "admin-dashboard";
    }

    @GetMapping("/admin/users")
    public String showUsers(HttpSession session, Model model) {
        String role = (String) session.getAttribute("role");
        if (role == null || !role.equals("ADMIN")) {
            return "redirect:/login";
        }
        model.addAttribute("users", userService.getAllUsers());
        return "admin-users";
    }

    @GetMapping("/admin/delete-user")
    public String deleteUser(@RequestParam String userId,
                             HttpSession session, Model model) {
        String role = (String) session.getAttribute("role");
        if (role == null || !role.equals("ADMIN")) {
            return "redirect:/login";
        }
        userService.deleteUser(userId);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("message", "User deleted!");
        return "admin-users";
    }

    @PostMapping("/admin/update-order-status")
    public String updateOrderStatus(@RequestParam String orderId,
                                    @RequestParam String status,
                                    HttpSession session) {
        String role = (String) session.getAttribute("role");
        if (role == null || !role.equals("ADMIN")) {
            return "redirect:/login";
        }
        orderService.updateOrderStatus(orderId, status);
        return "redirect:/admin";
    }
}