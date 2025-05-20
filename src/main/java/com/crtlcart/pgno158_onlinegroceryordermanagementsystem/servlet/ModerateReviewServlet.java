package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.servlet;

import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model.Product;
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

@WebServlet("/moderateReview")
public class ModerateReviewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int reviewId = Integer.parseInt(request.getParameter("id"));
            String action = request.getParameter("action");
            
            List<Review> reviews = FileManager.getInstance().readReviews();
            Review reviewToModerate = reviews.stream()
                    .filter(r -> r.getId() == reviewId)
                    .findFirst()
                    .orElse(null);

            if (reviewToModerate != null) {
                if ("approve".equals(action)) {
                    reviewToModerate.setStatus(ReviewStatus.APPROVED);
                } else if ("reject".equals(action)) {
                    reviewToModerate.setStatus(ReviewStatus.REJECTED);
                }
                
                FileManager.getInstance().writeReviews(reviews);
                request.getSession().setAttribute("message", "Review has been " + action + "d successfully");
            } else {
                request.getSession().setAttribute("message", "Review not found");
            }
            
            response.sendRedirect(request.getContextPath() + "/reviewPage");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error moderating review: " + e.getMessage());
        }
    }
}