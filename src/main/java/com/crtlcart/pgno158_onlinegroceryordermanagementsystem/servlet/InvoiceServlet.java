package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.servlet;

import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model.Invoice;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/invoice")
public class InvoiceServlet extends HttpServlet {
    private static final String FILE_PATH = "C:\\Users\\Gaveesha\\IdeaProjects\\newtest\\data\\invoices.txt"; // Update path as needed
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private Stack<Invoice> loadInvoices() throws IOException {
        Stack<Invoice> stack = new Stack<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) file.createNewFile();

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts.length == 4) {
                stack.push(new Invoice(
                        parts[0],
                        parts[1],
                        Double.parseDouble(parts[2]),
                        new Date(Long.parseLong(parts[3]))
                ));
            }
        }
        reader.close();
        return stack;
    }

    private void saveInvoices(Stack<Invoice> invoices) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false));
        for (Invoice inv : invoices) {
            writer.write(inv.getInvoiceId() + "|" +
                    inv.getCustomer() + "|" +
                    inv.getAmount() + "|" +
                    inv.getDate().getTime() + "\n");
        }
        writer.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServletException {
        Stack<Invoice> invoices = loadInvoices();
        req.setAttribute("invoices", invoices);
        req.getRequestDispatcher("/invoice.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Stack<Invoice> invoices = loadInvoices();

        if ("add".equals(action)) {
            String id = "INV-" + System.currentTimeMillis();
            String customer = req.getParameter("customer");
            double amount = Double.parseDouble(req.getParameter("amount"));
            Date date = new Date();

            invoices.push(new Invoice(id, customer, amount, date));
        } else if ("delete".equals(action)) {
            String idToDelete = req.getParameter("invoiceId");
            Stack<Invoice> temp = new Stack<>();

            while (!invoices.isEmpty()) {
                Invoice inv = invoices.pop();
                if (!inv.getInvoiceId().equals(idToDelete)) {
                    temp.push(inv);
                }
            }

            while (!temp.isEmpty()) {
                invoices.push(temp.pop());
            }
        }

        saveInvoices(invoices);
        resp.sendRedirect("invoice");
    }
}
