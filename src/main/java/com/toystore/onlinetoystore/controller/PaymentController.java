package com.toystore.onlinetoystore.controller;

import com.toystore.onlinetoystore.model.Order;
import com.toystore.onlinetoystore.model.Payment;
import com.toystore.onlinetoystore.service.OrderService;
import com.toystore.onlinetoystore.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PaymentController {

    private PaymentService paymentService = new PaymentService();
    private OrderService orderService = new OrderService();

    @GetMapping("/payment")
    public String showPaymentPage(@RequestParam String orderId,
                                  HttpSession session,
                                  Model model) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }
        List<Order> orders = orderService.getOrdersByUser(userId);
        double total = 0;
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) {
                total = order.getTotalPrice();
                break;
            }
        }
        model.addAttribute("orderId", orderId);
        model.addAttribute("total", total);
        return "payment";
    }

    @PostMapping("/payment")
    public String makePayment(@RequestParam String orderId,
                              @RequestParam double amount,
                              @RequestParam String method,
                              @RequestParam String billingName,
                              @RequestParam String billingAddress,
                              HttpSession session,
                              Model model) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }
        paymentService.makePayment(orderId, userId, amount,
                method, billingName, billingAddress);
        orderService.updateOrderStatus(orderId, "Processing");
        model.addAttribute("orderId", orderId);
        model.addAttribute("amount", amount);
        model.addAttribute("method", method);
        model.addAttribute("billingName", billingName);
        model.addAttribute("billingAddress", billingAddress);
        return "invoice";
    }

    @GetMapping("/payments")
    public String showPaymentHistory(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }
        List<Payment> payments = paymentService.getPaymentsByUser(userId);
        model.addAttribute("payments", payments);
        return "payments";
    }

    @GetMapping("/admin/payments")
    public String showAdminPayments(HttpSession session, Model model) {
        String role = (String) session.getAttribute("role");
        if (role == null || !role.equals("ADMIN")) {
            return "redirect:/login";
        }
        List<Payment> payments = paymentService.getAllPayments();
        double totalRevenue = paymentService.getTotalRevenue();
        model.addAttribute("payments", payments);
        model.addAttribute("totalRevenue", totalRevenue);
        return "admin-payments";
    }
}