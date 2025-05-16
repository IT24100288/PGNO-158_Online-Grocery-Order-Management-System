package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.servlet;

import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model.Product;
import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model.Review;
import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.utils.FileManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listReviews")
public class ListReviewsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Read reviews and products using FileManager
        List<Review> reviews = FileManager.readReviews();
        List<Product> products = FileManager.readProducts();

        // Set attributes for JSP
        request.setAttribute("reviews", reviews);
        request.setAttribute("products", products);

        // Forward to reviewModeration.jsp
        request.getRequestDispatcher("/reviewModeration.jsp").forward(request, response);
    }
}
