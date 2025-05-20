package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.servlet;

import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model.Review;
import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.utils.FileManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/submitReview")
public class SubmitReviewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get parameters from the form
            String productName = request.getParameter("productName");
            String userName = request.getParameter("userName");
            String reviewText = request.getParameter("reviewText");
            String ratingStr = request.getParameter("rating");

            // Validate input
            if (productName == null || productName.trim().isEmpty()) {
                throw new IllegalArgumentException("Product name is required");
            }
            if (userName == null || userName.trim().isEmpty()) {
                throw new IllegalArgumentException("User name is required");
            }
            if (reviewText == null || reviewText.trim().isEmpty()) {
                throw new IllegalArgumentException("Review text is required");
            }
            if (ratingStr == null || ratingStr.trim().isEmpty()) {
                throw new IllegalArgumentException("Rating is required");
            }

            int rating = Integer.parseInt(ratingStr);
            if (rating < 1 || rating > 5) {
                throw new IllegalArgumentException("Rating must be between 1 and 5");
            }

            // Read current reviews
            List<Review> reviews = FileManager.getInstance().readReviews();

            // Generate new review ID
            int newId = reviews.isEmpty() ? 1 : reviews.get(reviews.size() - 1).getId() + 1;

            // Create new review
            Review newReview = new Review(newId, 0, productName.trim(), userName.trim(), reviewText.trim(), rating);
            reviews.add(newReview);
            FileManager.getInstance().writeReviews(reviews);

            // Set success message and redirect
            request.getSession().setAttribute("message", "Review submitted successfully!");
            response.sendRedirect(request.getContextPath() + "/");
        } catch (IllegalArgumentException e) {
            request.getSession().setAttribute("error", e.getMessage());
            response.sendRedirect(request.getContextPath() + "/");
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "An unexpected error occurred. Please try again.");
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}