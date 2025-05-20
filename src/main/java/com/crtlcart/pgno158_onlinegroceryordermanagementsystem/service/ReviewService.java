package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.service;

import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model.Review;
import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model.ReviewStatus;
import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.utils.FileManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.logging.Level;

public class ReviewService {
    private static final Logger LOGGER = Logger.getLogger(ReviewService.class.getName());
    private static ReviewService instance;
    private final FileManager fileManager;

    private ReviewService() {
        this.fileManager = FileManager.getInstance();
    }

    public static synchronized ReviewService getInstance() {
        if (instance == null) {
            instance = new ReviewService();
        }
        return instance;
    }

    public List<Review> getAllReviews() {
        try {
            return fileManager.readReviews();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error reading reviews", e);
            return new ArrayList<>();
        }
    }

    public Optional<Review> getReviewById(int id) {
        try {
            return fileManager.readReviews().stream()
                    .filter(review -> review.getId() == id)
                    .findFirst();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error getting review by ID: " + id, e);
            return Optional.empty();
        }
    }

    public Review createReview(int productId, String productName, String userName, String reviewText, int rating) {
        try {
            List<Review> reviews = fileManager.readReviews();
            int newId = reviews.isEmpty() ? 1 : reviews.get(reviews.size() - 1).getId() + 1;
            
            Review newReview = new Review(newId, productId, productName, userName, reviewText, rating);
            reviews.add(newReview);
            fileManager.writeReviews(reviews);
            
            return newReview;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error creating review", e);
            throw new RuntimeException("Failed to create review", e);
        }
    }

    public void updateReview(Review review) {
        try {
            List<Review> reviews = fileManager.readReviews();
            reviews.removeIf(r -> r.getId() == review.getId());
            reviews.add(review);
            fileManager.writeReviews(reviews);
            LOGGER.info("Successfully updated review with ID: " + review.getId());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error updating review", e);
            throw new RuntimeException("Failed to update review", e);
        }
    }

    public void deleteReview(int id) {
        try {
            List<Review> reviews = fileManager.readReviews();
            boolean removed = reviews.removeIf(review -> review.getId() == id);
            
            if (!removed) {
                throw new IllegalArgumentException("Review not found with ID: " + id);
            }
            
            fileManager.writeReviews(reviews);
            LOGGER.info("Successfully deleted review with ID: " + id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error deleting review", e);
            throw new RuntimeException("Failed to delete review", e);
        }
    }

    public void updateReviewStatus(int reviewId, ReviewStatus status) {
        getReviewById(reviewId).ifPresent(review -> {
            review.setStatus(status);
            updateReview(review);
        });
    }

    public List<Review> getReviewsByStatus(ReviewStatus status) {
        return fileManager.readReviews().stream()
                .filter(review -> review.getStatus() == status)
                .toList();
    }
} 