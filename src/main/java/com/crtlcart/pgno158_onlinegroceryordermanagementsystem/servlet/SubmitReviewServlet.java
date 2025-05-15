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
            // Get parameters
            String productName = request.getParameter("productName");
            boolean isNewProduct = Boolean.parseBoolean(request.getParameter("isNewProduct"));
            String userName = request.getParameter("userName");
            String comment = request.getParameter("comment");
            double rating = Double.parseDouble(request.getParameter("rating"));
            String status = "PENDING"; // Reviews start as pending

            // Get or create product ID
            List<Product> products = FileManager.readProducts();
            int productId;

            if (isNewProduct) {
                // Create new product
                int newProductId = products.isEmpty() ? 1 : products.get(products.size() - 1).getId() + 1;
                Product newProduct = new Product(newProductId, productName, 0.0, 0);
                products.add(newProduct);
                FileManager.writeProducts(products);
                productId = newProductId;
            } else {
                productId = Integer.parseInt(request.getParameter("productId"));
            }

            // Generate a new review ID
            List<Review> reviews = FileManager.readReviews();
            int newId = reviews.isEmpty() ? 1 : reviews.get(reviews.size() - 1).getId() + 1;

            // Create new review
            Review review = new Review(newId, productId, 0, userName, comment, rating, status);

            // Add to file
            reviews.add(review);
            FileManager.writeReviews(reviews);

            // Set message in session and redirect to review page
            request.getSession().setAttribute("message", "Review submitted successfully! Awaiting approval.");
            response.sendRedirect("reviewPage");
        } catch (NumberFormatException e) {
            // Set error message in session and redirect to review page
            request.getSession().setAttribute("message", "Error: Invalid input. Please check your rating.");
            response.sendRedirect("reviewPage");
        }
    }
}
