package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.controller;

import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.service.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/update-order-status")
public class OrderStatusUpdateServlet extends HttpServlet {

    private final OrderService orderService = new OrderService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        String orderStatus = request.getParameter("status");

        boolean newOrderStatus = orderService.updateOrderStatus(orderId,orderStatus);

        if(newOrderStatus){
            request.setAttribute("status", orderStatus);
            response.sendRedirect("order-details?id="+orderId+"&message=Order%20Updated%20Successfully");
        }else {
            response.sendRedirect("order-details?id=" + orderId + "&message=Order%20Updated%20Failed");
        }
    }
}
