package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.servlet;

import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model.Payment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/adminPayment")
public class AdminPaymentServlet extends HttpServlet {

    private static final String FILE_PATH = "C:/Users/Gaveesha/IdeaProjects/newtest/data/payments.txt";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Payment> payments = loadPayments();
        request.setAttribute("payments", payments);
        request.getRequestDispatcher("AdminDashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Payment> payments = loadPayments();
        int index = Integer.parseInt(request.getParameter("index"));
        String action = request.getParameter("action");

        if ("update".equals(action)) {
            Payment updated = new Payment(
                    payments.get(index).getTimestamp(),
                    request.getParameter("method"),
                    request.getParameter("cardholder"),
                    request.getParameter("cardnumber"),
                    request.getParameter("expiry"),
                    request.getParameter("ordertype"),
                    request.getParameter("table"),
                    request.getParameter("subtotal")
            );
            payments.set(index, updated);
        } else if ("delete".equals(action)) {
            payments.remove(index);
        }

        savePayments(payments);
        response.sendRedirect("adminPayment"); // triggers doGet
    }

    private List<Payment> loadPayments() throws IOException {
        List<Payment> payments = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" \\| ");
            if (parts.length >= 8) {
                payments.add(new Payment(parts[0], parts[1], parts[2], parts[3],
                        parts[4], parts[5], parts[6], parts[7].replace("Subtotal: Rs.", "")));
            }
        }

        reader.close();
        return payments;
    }

    private void savePayments(List<Payment> payments) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
        for (Payment p : payments) {
            writer.write(String.join(" | ",
                    p.getTimestamp(), p.getPaymentMethod(), p.getCardholder(),
                    p.getCardNumber(), p.getExpiry(), p.getOrderType(),
                    p.getTable(), "Subtotal: Rs." + p.getSubtotal()));
            writer.newLine();
        }
        writer.close();
    }
}
