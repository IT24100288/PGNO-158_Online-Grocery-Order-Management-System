package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.servlet;

import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model.Review;
import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.utils.FileManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

@WebServlet("/deleteReview")
public class DeleteReviewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(DeleteReviewServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleDelete(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleDelete(request, response);
    }

    private void handleDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String reviewIdStr = request.getParameter("id");
            if (reviewIdStr == null || reviewIdStr.trim().isEmpty()) {
                LOGGER.warning("Delete attempt with null or empty review ID");
                throw new IllegalArgumentException("Review ID is required");
            }

            int reviewId = Integer.parseInt(reviewIdStr);
            LOGGER.info("Attempting to delete review with ID: " + reviewId);
            
            List<Review> reviews = FileManager.getInstance().readReviews();
            LOGGER.info("Found " + reviews.size() + " reviews in the system");
            
            // Create a new list excluding the review to delete
            List<Review> updatedReviews = new ArrayList<>();
            boolean found = false;
            
            for (Review review : reviews) {
                if (review.getId() == reviewId) {
                    found = true;
                    LOGGER.info("Found review to delete: " + review.getDisplayName());
                } else {
                    updatedReviews.add(review);
                }
            }
            
            if (!found) {
                LOGGER.warning("Review not found with ID: " + reviewId);
                throw new IllegalArgumentException("Review not found");
            }
            
            // Write the updated list back to file
            FileManager.getInstance().writeReviews(updatedReviews);
            LOGGER.info("Successfully deleted review and updated file");
            
            request.getSession().setAttribute("message", "Review deleted successfully");
            response.sendRedirect(request.getContextPath() + "/");
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARNING, "Validation error during review deletion", e);
            request.getSession().setAttribute("error", e.getMessage());
            response.sendRedirect(request.getContextPath() + "/");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Unexpected error during review deletion", e);
            request.getSession().setAttribute("error", "An unexpected error occurred while deleting the review. Please try again.");
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}