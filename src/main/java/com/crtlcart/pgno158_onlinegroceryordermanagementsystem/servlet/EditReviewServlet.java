package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.servlet;

import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model.Review;
import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model.ReviewStatus;
import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.utils.FileManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/editReview")
public class EditReviewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String reviewIdStr = request.getParameter("id");
            if (reviewIdStr == null || reviewIdStr.trim().isEmpty()) {
                throw new IllegalArgumentException("Review ID is required");
            }

            int reviewId = Integer.parseInt(reviewIdStr);
            List<Review> reviews = FileManager.getInstance().readReviews();
            Review reviewToEdit = reviews.stream()
                    .filter(r -> r.getId() == reviewId)
                    .findFirst()
                    .orElse(null);

            if (reviewToEdit != null) {
                request.setAttribute("review", reviewToEdit);
                request.getRequestDispatcher("/pages/editReview.jsp").forward(request, response);
            } else {
                throw new IllegalArgumentException("Review not found");
            }
        } catch (IllegalArgumentException e) {
            request.getSession().setAttribute("error", e.getMessage());
            response.sendRedirect(request.getContextPath() + "/");
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "An unexpected error occurred while loading the review");
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String reviewIdStr = request.getParameter("id");
            String productName = request.getParameter("productName");
            String userName = request.getParameter("userName");
            String reviewText = request.getParameter("reviewText");
            String ratingStr = request.getParameter("rating");
            String statusStr = request.getParameter("status");

            // Validate input
            if (reviewIdStr == null || productName == null || userName == null || 
                reviewText == null || ratingStr == null || statusStr == null) {
                throw new IllegalArgumentException("All fields are required");
            }

            int reviewId = Integer.parseInt(reviewIdStr);
            int rating = Integer.parseInt(ratingStr);

            if (rating < 1 || rating > 5) {
                throw new IllegalArgumentException("Rating must be between 1 and 5");
            }

            // Convert status string to enum
            ReviewStatus status;
            try {
                status = ReviewStatus.valueOf(statusStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid status value");
            }

            List<Review> reviews = FileManager.getInstance().readReviews();
            Review reviewToEdit = reviews.stream()
                    .filter(r -> r.getId() == reviewId)
                    .findFirst()
                    .orElse(null);

            if (reviewToEdit != null) {
                reviewToEdit.setProductName(productName.trim());
                reviewToEdit.setUserName(userName.trim());
                reviewToEdit.setReviewText(reviewText.trim());
                reviewToEdit.setRating(rating);
                reviewToEdit.setStatus(status);

                FileManager.getInstance().writeReviews(reviews);
                request.getSession().setAttribute("message", "Review updated successfully");
                response.sendRedirect(request.getContextPath() + "/");
            } else {
                throw new IllegalArgumentException("Review not found");
            }
        } catch (IllegalArgumentException e) {
            request.getSession().setAttribute("error", e.getMessage());
            response.sendRedirect(request.getContextPath() + "/");
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "An unexpected error occurred while updating the review");
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}