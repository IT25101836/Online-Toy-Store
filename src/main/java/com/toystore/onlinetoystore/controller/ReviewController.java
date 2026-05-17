package com.toystore.onlinetoystore.controller;

import com.toystore.onlinetoystore.model.Review;
import com.toystore.onlinetoystore.service.ReviewService;
import com.toystore.onlinetoystore.service.ToyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ReviewController {

    private ReviewService reviewService = new ReviewService();
    private ToyService toyService = new ToyService();

    @GetMapping("/reviews")
    public String showReviews(Model model) {
        model.addAttribute("reviews", reviewService.getAllReviews());
        model.addAttribute("toys", toyService.getAllToys());
        return "reviews";
    }

    @GetMapping("/reviews/add")
    public String showAddReview(@RequestParam String toyId,
                                HttpSession session,
                                Model model) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }
        model.addAttribute("toy", toyService.findById(toyId));
        return "add-review";
    }

    @PostMapping("/reviews/add")
    public String addReview(@RequestParam String toyId,
                            @RequestParam String toyName,
                            @RequestParam int rating,
                            @RequestParam String comment,
                            HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        String username = (String) session.getAttribute("username");
        if (userId == null) {
            return "redirect:/login";
        }
        reviewService.addReview(userId, username, toyId, toyName, rating, comment);
        return "redirect:/reviews";
    }

    @GetMapping("/reviews/delete")
    public String deleteReview(@RequestParam String reviewId,
                               HttpSession session) {
        String role = (String) session.getAttribute("role");
        if (role == null || !role.equals("ADMIN")) {
            return "redirect:/login";
        }
        reviewService.deleteReview(reviewId);
        return "redirect:/reviews";
    }
}