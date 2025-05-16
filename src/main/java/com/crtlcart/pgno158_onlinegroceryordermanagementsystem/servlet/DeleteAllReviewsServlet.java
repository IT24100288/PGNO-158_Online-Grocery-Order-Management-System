package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.servlet;

import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model.Review;
import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.utils.FileManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

@WebServlet("/deleteAllReviews")
public class DeleteAllReviewsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(DeleteAllReviewsServlet.class.getName());

    @Override
    public void init() throws ServletException {
        super.init();
        FileManager.init(getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            logger.info("Attempting to clear all reviews");
            
            // Create an empty list to replace all reviews
            List<Review> emptyReviews = new ArrayList<>();
            
            // Write the empty list to the file
            FileManager.writeReviews(emptyReviews);
            
            logger.info("Successfully cleared all reviews");
            request.getSession().setAttribute("message", "All reviews have been cleared successfully.");
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error clearing reviews", e);
            request.getSession().setAttribute("error", "Failed to clear reviews: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }
}