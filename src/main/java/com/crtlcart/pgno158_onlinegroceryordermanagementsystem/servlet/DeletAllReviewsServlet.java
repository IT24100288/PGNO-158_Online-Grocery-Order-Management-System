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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/deleteAllReviews")
public class DeleteAllReviewsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        super.init();
        FileManager.init(getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Create an empty list to replace all reviews
            List<Review> emptyReviews = new ArrayList<>();

            // Write empty list to file
            FileManager.writeReviews(emptyReviews);

            // Get products for the JSP
            List<Product> products = FileManager.readProducts();

            // Set attributes for JSP
            request.setAttribute("message", "All reviews have been cleared successfully.");
            request.setAttribute("reviews", emptyReviews);
            request.setAttribute("products", products);

            // Forward to review submission page
            request.getRequestDispatcher("/reviewSubmission.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("message", "Error: Failed to clear reviews. " + e.getMessage());
            List<Product> products = FileManager.readProducts();
            List<Review> reviews = FileManager.readReviews();
            request.setAttribute("products", products);
            request.setAttribute("reviews", reviews);
            request.getRequestDispatcher("/reviewSubmission.jsp").forward(request, response);
        }
    }
}