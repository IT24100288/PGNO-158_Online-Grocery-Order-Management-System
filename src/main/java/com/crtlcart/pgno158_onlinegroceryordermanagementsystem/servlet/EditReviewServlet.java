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


@WebServlet("/editReview")
public class EditReviewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        super.init();
        FileManager.init(getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String reviewIdStr = request.getParameter("id");
            if (reviewIdStr == null || reviewIdStr.trim().isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Review ID is required");
                return;
            }

            int reviewId = Integer.parseInt(reviewIdStr);
            List<Review> reviews = FileManager.readReviews();
            Review reviewToEdit = reviews.stream()
                    .filter(r -> r.getId() == reviewId)
                    .findFirst()
                    .orElse(null);

            if (reviewToEdit != null) {
                request.setAttribute("review", reviewToEdit);
                request.getRequestDispatcher("/WEB-INF/JSP/editReview.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Review not found");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid review ID format");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading review: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String reviewIdStr = request.getParameter("id");
            String productName = request.getParameter("productName");
            String reviewText = request.getParameter("reviewText");
            String ratingStr = request.getParameter("rating");
            String status = request.getParameter("status");

            // Validate input
            if (reviewIdStr == null || productName == null || reviewText == null || ratingStr == null || status == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "All fields are required");
                return;
            }

            int reviewId = Integer.parseInt(reviewIdStr);
            int rating = Integer.parseInt(ratingStr);

            if (rating < 1 || rating > 5) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Rating must be between 1 and 5");
                return;
            }

            List<Review> reviews = FileManager.readReviews();
            Review reviewToEdit = reviews.stream()
                    .filter(r -> r.getId() == reviewId)
                    .findFirst()
                    .orElse(null);

            if (reviewToEdit != null) {
                reviewToEdit.setProductName(productName.trim());
                reviewToEdit.setReviewText(reviewText.trim());
                reviewToEdit.setRating(rating);
                reviewToEdit.setStatus(status);

                FileManager.writeReviews(reviews);
                request.getSession().setAttribute("message", "Review updated successfully");
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Review not found");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error updating review: " + e.getMessage());
        }
    }
}