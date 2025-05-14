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

@WebServlet("/editReview")
public class EditReviewServlet extends HttpServlet {
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
            int id = Integer.parseInt(request.getParameter("id"));
            String productName = request.getParameter("productName");
            boolean isNewProduct = Boolean.parseBoolean(request.getParameter("isNewProduct"));
            String userName = request.getParameter("userName");
            String comment = request.getParameter("comment");
            double rating = Double.parseDouble(request.getParameter("rating"));

            // Validate input
            if (rating < 0 || rating > 5) {
                throw new NumberFormatException("Invalid rating");
            }

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

            // Read current reviews
            List<Review> reviews = FileManager.readReviews();

            // Find and update the review
            boolean found = false;
            for (Review review : reviews) {
                if (review.getId() == id) {
                    // Update review details
                    review.setProductId(productId);
                    review.setUserName(userName);
                    review.setComment(comment);
                    review.setRating(rating);
                    review.setStatus("PENDING"); // Set status to pending after edit
                    found = true;
                    break;
                }
            }

            if (!found) {
                request.getSession().setAttribute("message", "Error: Review not found.");
            } else {
                // Write updated reviews
                FileManager.writeReviews(reviews);
                request.getSession().setAttribute("message", "Review updated successfully! Awaiting approval.");
            }

            // Redirect to review page
            response.sendRedirect("reviewPage");
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("message", "Error: Invalid input. Please check your rating.");
            response.sendRedirect("reviewPage");
        }
    }
}