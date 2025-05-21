package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/order-data")
public class OrderCustomerDataServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String customerFirstName = request.getParameter("customerFirstName");
        String customerLastName = request.getParameter("customerLastName");
        String customerAddress = request.getParameter("customerAddress");
        String customerPhone = request.getParameter("customerPhone");
        String customerEmail = request.getParameter("customerEmail");
        String customerCity = request.getParameter("customerCity");
        String customerPostalCode = request.getParameter("customerPostalCode");

        HttpSession session = request.getSession();

        session.setAttribute("customerFirstName", customerFirstName);
        session.setAttribute("customerLastName", customerLastName);
        session.setAttribute("customerAddress", customerAddress);
        session.setAttribute("customerCity", customerCity);
        session.setAttribute("customerPhone", customerPhone);
        session.setAttribute("customerEmail", customerEmail);
        session.setAttribute("customerPostalCode", customerPostalCode);

        response.sendRedirect("order-review.jsp");

    }
}
