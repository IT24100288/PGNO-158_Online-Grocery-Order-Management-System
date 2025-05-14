package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String product = request.getParameter("product");
        String quantity = request.getParameter("quantity");
        String address = request.getParameter("address");
        String payment = request.getParameter("payment");

        // For demo purposes: Print the order to console or store in file/database
        System.out.println("Order Placed:");
        System.out.println("Product: " + product);
        System.out.println("Quantity: " + quantity);
        System.out.println("Address: " + address);
        System.out.println("Payment: " + payment);

        // Redirect to confirmation page
        response.sendRedirect("confirmation.jsp");
    }
}
