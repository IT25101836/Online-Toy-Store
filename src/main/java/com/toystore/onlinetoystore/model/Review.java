package com.toystore.onlinetoystore.model;

public class Review {
    private String reviewId;
    private String userId;
    private String username;
    private String toyId;
    private String toyName;
    private int rating;
    private String comment;
    private String date;

    public Review() {}

    public Review(String reviewId, String userId, String username,
                  String toyId, String toyName, int rating,
                  String comment, String date) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.username = username;
        this.toyId = toyId;
        this.toyName = toyName;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }

    public String getReviewId() { return reviewId; }
    public void setReviewId(String reviewId) { this.reviewId = reviewId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getToyId() { return toyId; }
    public void setToyId(String toyId) { this.toyId = toyId; }
    public String getToyName() { return toyName; }
    public void setToyName(String toyName) { this.toyName = toyName; }
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}