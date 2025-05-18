package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.servlet;

import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.utils.FileHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class ManageOrdersServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        List<String> orders = FileHandler.readFile("orders.txt");

        if ("delete".equals(action)) {
            String orderId = request.getParameter("id");
            orders.removeIf(order -> order.startsWith(orderId + ","));
            FileHandler.writeFile("orders.txt", orders);
        }

        response.sendRedirect("manageOrders.jsp");
    }
}