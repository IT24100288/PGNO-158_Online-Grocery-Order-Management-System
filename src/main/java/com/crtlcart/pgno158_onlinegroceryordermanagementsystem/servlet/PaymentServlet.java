package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/processPayment")
public class PaymentServlet extends HttpServlet {
    private static final String FILE_PATH = "C:/Users/Gaveesha/IdeaProjects/newtest/data/payments.txt"; // Update this path

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ServletException {

        // 1. Read form data
        String paymentMethod = request.getParameter("paymentMethod"); // set via JavaScript if needed
        String cardholder = request.getParameter("cardholder");
        String cardnumber = request.getParameter("cardnumber");
        String expiry = request.getParameter("expiry");
        String cvv = request.getParameter("cvv");
        String orderType = request.getParameter("ordertype");
        String table = request.getParameter("table");
        String subtotal = request.getParameter("subtotal");

        // 2. Build record line
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String line = String.join(" | ",
                timestamp, paymentMethod, cardholder, cardnumber, expiry, orderType, table, "Subtotal: $" + subtotal);

        // 3. Save to file
        File file = new File(FILE_PATH);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(line);
            writer.newLine();
        }

        // 4. Forward to confirmation page
        request.setAttribute("name", cardholder);
        request.setAttribute("table", table);
        request.setAttribute("subtotal", subtotal);
        request.getRequestDispatcher("conform.jsp").forward(request, response);
    }
}
