package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Review extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private int productId;
    private String productName;
    private String userName;
    private String reviewText;
    private int rating;
    private ReviewStatus status;
    private LocalDateTime submissionDate;

    public Review(int id, int productId, String productName, String userName, String reviewText, int rating) {
        super(id);
        this.productId = productId;
        this.productName = productName;
        this.userName = userName;
        this.reviewText = reviewText;
        this.rating = rating;
        this.status = ReviewStatus.PENDING;
        this.submissionDate = LocalDateTime.now();
    }



    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        this.rating = rating;
    }

    public ReviewStatus getStatus() {
        return status;
    }

    public void setStatus(ReviewStatus status) {
        this.status = status;
    }



    public String getFormattedSubmissionDate() {
        return submissionDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String getDisplayName() {
        return String.format("Review #%d for %s", getId(), productName);
    }

    @Override
    public String toString() {
        return String.format("%s - Rating: %d/5 - Status: %s", getDisplayName(), rating, status);
    }
}