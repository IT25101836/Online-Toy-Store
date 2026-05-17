package com.toystore.onlinetoystore.service;

import com.toystore.onlinetoystore.model.Review;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReviewService {

    private static final String FILE = System.getProperty("user.dir") + "/reviews.txt";
    public void addReview(String userId, String username, String toyId,
                          String toyName, int rating, String comment) {
        List<String> lines = FileHelper.readLines(FILE);
        int id = lines.size() + 1;
        String date = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String line = "R" + id + "," + userId + "," + username + "," +
                toyId + "," + toyName + "," + rating + "," + comment + "," + date;
        List<String> newLine = new ArrayList<>();
        newLine.add(line);
        FileHelper.writeLines(FILE, newLine);
    }

    public List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<>();
        List<String> lines = FileHelper.readLines(FILE);
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length >= 8) {
                Review review = new Review(
                        parts[0], parts[1], parts[2],
                        parts[3], parts[4],
                        Integer.parseInt(parts[5]),
                        parts[6], parts[7]);
                reviews.add(review);
            }
        }
        return reviews;
    }

    public List<Review> getReviewsByToy(String toyId) {
        List<Review> reviews = new ArrayList<>();
        for (Review review : getAllReviews()) {
            if (review.getToyId().equals(toyId)) {
                reviews.add(review);
            }
        }
        return reviews;
    }

    public List<Review> getReviewsByUser(String userId) {
        List<Review> reviews = new ArrayList<>();
        for (Review review : getAllReviews()) {
            if (review.getUserId().equals(userId)) {
                reviews.add(review);
            }
        }
        return reviews;
    }

    public void deleteReview(String reviewId) {
        List<Review> reviews = getAllReviews();
        List<String> lines = new ArrayList<>();
        for (Review review : reviews) {
            if (!review.getReviewId().equals(reviewId)) {
                lines.add(review.getReviewId() + "," + review.getUserId() + "," +
                        review.getUsername() + "," + review.getToyId() + "," +
                        review.getToyName() + "," + review.getRating() + "," +
                        review.getComment() + "," + review.getDate());
            }
        }
        FileHelper.overwriteLines(FILE, lines);
    }
}