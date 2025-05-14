package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Review {
    private int id;
    private int productId;
    private int userId;
    private String userName;
    private String comment;
    private double rating;
    private String status;
    private String submissionDate;
    private String submissionTime;

    public Review(int id, int productId, int userId, String userName, String comment, double rating, String status) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.userName = userName;
        this.comment = comment;
        this.rating = rating;
        this.status = status;
        LocalDateTime now = LocalDateTime.now();
        this.submissionDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.submissionTime = now.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getSubmissionDate() { return submissionDate; }
    public void setSubmissionDate(String submissionDate) { this.submissionDate = submissionDate; }
    public String getSubmissionTime() { return submissionTime; }
    public void setSubmissionTime(String submissionTime) { this.submissionTime = submissionTime; }
}