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

@WebServlet("/deleteReview")
public class DeleteReviewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        super.init();
        FileManager.init(getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleDelete(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleDelete(request, response);
    }

    private void handleDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int reviewId = Integer.parseInt(request.getParameter("id"));
            List<Review> reviews = FileManager.readReviews();
            reviews.removeIf(review -> review.getId() == reviewId);
            FileManager.writeReviews(reviews);
            request.getSession().setAttribute("message", "Review deleted successfully");
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting review: " + e.getMessage());
        }
    }
}