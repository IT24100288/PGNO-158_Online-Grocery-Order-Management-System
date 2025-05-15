package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.servlet;

import org.example.ctrlcart.model.Product;
import org.example.ctrlcart.model.Review;
import org.example.ctrlcart.utils.FileManager;
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
        // Check if user is admin
        Boolean isAdmin = (Boolean) request.getSession().getAttribute("isAdmin");
        if (isAdmin == null || !isAdmin) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Admin access required");
            return;
        }

        // Get parameters
        int id = Integer.parseInt(request.getParameter("id"));
        String action = request.getParameter("action");

        // Read the current list of reviews
        List<Review> reviews = FileManager.readReviews();

        // Find and update the review
        for (Review review : reviews) {
            if (review.getId() == id) {
                if ("approve".equals(action)) {
                    review.setStatus("APPROVED");
                } else if ("reject".equals(action)) {
                    review.setStatus("REJECTED");
                }
                break;
            }
        }

        // Write the updated list back to the file
        FileManager.writeReviews(reviews);

        // Get products for the JSP
        List<Product> products = FileManager.readProducts();

        // Set attributes for JSP
        request.setAttribute("message", "Review " + action + "d successfully!");
        request.setAttribute("reviews", reviews);
        request.setAttribute("products", products);

        // Forward to review submission page
        request.getRequestDispatcher("/reviewSubmission.jsp").forward(request, response);
    }
}
