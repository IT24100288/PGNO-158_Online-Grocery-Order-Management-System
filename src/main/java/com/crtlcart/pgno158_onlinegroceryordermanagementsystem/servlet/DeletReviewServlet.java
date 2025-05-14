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

@WebServlet("/deleteReview")
public class DeleteReviewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        super.init();
        FileManager.init(getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get review ID
            int id = Integer.parseInt(request.getParameter("id"));

            // Read current reviews
            List<Review> reviews = FileManager.readReviews();

            // Remove the review
            reviews.removeIf(review -> review.getId() == id);

            // Write updated reviews
            FileManager.writeReviews(reviews);

            // Get products for the JSP
            List<Product> products = FileManager.readProducts();

            // Set attributes for JSP
            request.setAttribute("message", "Review deleted successfully!");
            request.setAttribute("reviews", reviews);
            request.setAttribute("products", products);

            // Forward to review submission page
            request.getRequestDispatcher("/reviewSubmission.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Error: Invalid review ID.");
            List<Product> products = FileManager.readProducts();
            List<Review> reviews = FileManager.readReviews();
            request.setAttribute("products", products);
            request.setAttribute("reviews", reviews);
            request.getRequestDispatcher("/reviewSubmission.jsp").forward(request, response);
        }
    }
}