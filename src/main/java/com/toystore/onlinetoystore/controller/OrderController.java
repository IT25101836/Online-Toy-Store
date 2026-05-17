package com.toystore.onlinetoystore.controller;

import com.toystore.onlinetoystore.model.Order;
import com.toystore.onlinetoystore.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;   // ✅ ADDED
import org.springframework.web.bind.annotation.RequestParam;  // ✅ ADDED
import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrderController {

    private OrderService orderService = new OrderService();

    @GetMapping("/orders")
    public String showOrders(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }
        List<Order> orders = orderService.getOrdersByUser(userId);
        model.addAttribute("orders", orders);
        return "orders";
    }

    @PostMapping("/orders/cancel")
    public String cancelOrder(@RequestParam("orderId") String orderId,
                              HttpSession session,
                              Model model) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }
        boolean success = orderService.cancelOrder(orderId, userId);
        if (!success) {
            model.addAttribute("message", "Order cannot be cancelled.");
        } else {
            model.addAttribute("message", "Order cancelled successfully.");
        }
        List<Order> orders = orderService.getOrdersByUser(userId);
        model.addAttribute("orders", orders);
        return "orders";
    }
}
