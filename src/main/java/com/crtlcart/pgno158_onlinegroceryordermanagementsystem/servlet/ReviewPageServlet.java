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

@WebServlet("/reviewPage")
public class ReviewPageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        super.init();
        FileManager.init(getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Load products and reviews
        List<Product> products = FileManager.readProducts();
        List<Review> reviews = FileManager.readReviews();

        // Get message from session if exists
        String message = (String) request.getSession().getAttribute("message");
        if (message != null) {
            request.setAttribute("message", message);
            request.getSession().removeAttribute("message"); // Clear the message
        }

        // Set attributes for JSP
        request.setAttribute("products", products);
        request.setAttribute("reviews", reviews);

        // Forward to review submission page
        request.getRequestDispatcher("/reviewSubmission.jsp").forward(request, response);
    }
}
