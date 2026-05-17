package com.toystore.onlinetoystore.controller;

import com.toystore.onlinetoystore.model.CartItem;
import com.toystore.onlinetoystore.service.CartService;
import com.toystore.onlinetoystore.service.OrderService;
import com.toystore.onlinetoystore.service.ToyService;
import com.toystore.onlinetoystore.model.Toy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CartController {

    private CartService cartService = new CartService();
    private OrderService orderService = new OrderService();
    private ToyService toyService = new ToyService();

    @GetMapping("/cart")
    public String showCart(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }
        List<CartItem> items = cartService.getCartByUser(userId);
        double total = cartService.getCartTotal(userId);
        model.addAttribute("cartItems", items);
        model.addAttribute("total", total);
        return "cart";
    }

    @GetMapping("/cart/add")
    public String showAddToCart(@RequestParam String toyId,
                                HttpSession session,
                                Model model) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }
        Toy toy = toyService.findById(toyId);
        model.addAttribute("toy", toy);
        return "add-to-cart";
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam String toyId,
                            @RequestParam int quantity,
                            HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }
        Toy toy = toyService.findById(toyId);
        if (toy != null) {
            cartService.addToCart(userId, toyId, toy.getName(),
                    toy.getPrice(), quantity, toy.getImageUrl());
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart/remove")
    public String removeFromCart(@RequestParam String cartItemId) {
        cartService.removeFromCart(cartItemId);
        return "redirect:/cart";
    }

    @PostMapping("/cart/checkout")
    public String checkout(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }
        List<CartItem> items = cartService.getCartByUser(userId);
        if (!items.isEmpty()) {
            orderService.placeOrder(userId, items);
            cartService.clearCartByUser(userId);
            model.addAttribute("message", "Order placed successfully!");
        }
        return "redirect:/orders";
    }
}