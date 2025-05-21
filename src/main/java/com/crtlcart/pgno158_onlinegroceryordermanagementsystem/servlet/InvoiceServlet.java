package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.servlet;

import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model.Invoice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/invoice")
public class InvoiceServlet extends HttpServlet {
    private static final String FILE_PATH = "C:\\Users\\Gaveesha\\OneDrive - Sri Lanka Institute of Information Technology\\Documents\\OOP Project\\PGNO-158_Online-Grocery-Order-Management-System\\data\\invoices.txt"; // Update path as needed
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private Stack<Invoice> loadInvoices() throws IOException {
        Stack<Invoice> stack = new Stack<>();
        File file = new File(FILE_PATH);
        System.out.println(FILE_PATH);
        System.out.println(file);
        if (!file.exists()){
            file.getParentFile().mkdir();
            file.createNewFile();
        }
        System.out.println("reading file: " + file.getAbsolutePath());
        System.out.println("file exists?: " + file.exists());
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts.length != 4) {
                System.out.println("skipping malformed line:" + line);
                continue;
            }

            try {
                stack.push(new Invoice(
                        parts[0],
                        parts[1],
                        Double.parseDouble(parts[2]),
                        new Date(Long.parseLong(parts[3]))
                ));
            }
            catch (Exception e) {
                System.out.println("error handling line" + line);
                e.printStackTrace();
            }
        }
        reader.close();
        return stack;
    }

    private void saveInvoices(Stack<Invoice> invoices) throws IOException {
        File file = new File(FILE_PATH);

        if(!file.exists()) {
            file.getParentFile().mkdir();
            file.createNewFile();
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
        System.out.println("saving to" );
        for (Invoice inv : invoices) {
            writer.write(inv.getInvoiceId() + "|" +
                    inv.getCustomer() + "|" +
                    inv.getAmount() + "|" +
                    inv.getDate().getTime() + "\n");
        }
        writer.close();
        System.out.println("file saved successfully");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            System.out.println(customer);
            double amount = Double.parseDouble(req.getParameter("amount"));
            System.out.println(amount);
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
