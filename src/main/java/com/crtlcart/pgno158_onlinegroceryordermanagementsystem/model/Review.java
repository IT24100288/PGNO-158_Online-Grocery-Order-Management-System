package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Review implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private int productId;
    private String productName;
    private String userName;
    private String reviewText;
    private int rating;
    private String status;
    private String submissionDate;

    public Review(int id, int productId, String productName, String userName, String reviewText, int rating) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.userName = userName;
        this.reviewText = reviewText;
        this.rating = rating;
        this.status = "PENDING";
        this.submissionDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getReviewText() { return reviewText; }
    public void setReviewText(String reviewText) { this.reviewText = reviewText; }
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getSubmissionDate() { return submissionDate; }
    public void setSubmissionDate(String submissionDate) { this.submissionDate = submissionDate; }
}