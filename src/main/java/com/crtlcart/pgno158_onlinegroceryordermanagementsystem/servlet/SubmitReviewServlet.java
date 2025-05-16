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
    public void init() throws ServletException {
        super.init();
        FileManager.init(getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get parameters from the form
            String productName = request.getParameter("productName");
            String userName = request.getParameter("userName");
            String reviewText = request.getParameter("reviewText");
            String ratingStr = request.getParameter("rating");

            // Debug logging
            System.out.println("Received review submission:");
            System.out.println("Product Name: " + productName);
            System.out.println("User Name: " + userName);
            System.out.println("Review Text: " + reviewText);
            System.out.println("Rating: " + ratingStr);

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
            List<Review> reviews = FileManager.readReviews();
            System.out.println("Current number of reviews: " + reviews.size());

            // Generate new review ID
            int newId = reviews.isEmpty() ? 1 : reviews.get(reviews.size() - 1).getId() + 1;

            // Create new review
            Review newReview = new Review(newId, 0, productName.trim(), userName.trim(), reviewText.trim(), rating);
            System.out.println("Created new review with ID: " + newId);

            // Add to list and save
            reviews.add(newReview);
            FileManager.writeReviews(reviews);
            System.out.println("Successfully saved review");

            // Set success message
            request.getSession().setAttribute("message", "Review submitted successfully!");

            // Redirect back to index page
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid argument: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            System.err.println("Error submitting review: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error submitting review: " + e.getMessage());
        }
    }
}