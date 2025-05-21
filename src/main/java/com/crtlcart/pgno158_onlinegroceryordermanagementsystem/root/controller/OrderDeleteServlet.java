package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.controller;

import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.service.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete-admin-order")
public class OrderDeleteServlet extends HttpServlet {

    private final OrderService orderService = new OrderService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String orderId = request.getParameter("id");

        boolean isCancelled = orderService.cancelOrder(orderId);

        if (isCancelled) {
            response.sendRedirect("orders?message=Order%20Cancelled%20Successfully");
        } else {
            response.sendRedirect("orders?message=Order%20Cancelled%20Failed");
        }
    }
}
