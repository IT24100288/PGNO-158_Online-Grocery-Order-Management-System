package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.servlet;

import com.grocery.servlet.util.FileHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class ManageProductsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        List<String> products = FileHandler.readFile("products.txt");

        if (action.equals("add")) {
            String name = request.getParameter("name");
            String price = request.getParameter("price");
            String stock = request.getParameter("stock");
            int id = products.size() + 1;
            String newProduct = id + "," + name + "," + price + "," + stock;
            FileHandler.appendToFile("products.txt", newProduct);
        } else if (action.equals("delete")) {
            String id = request.getParameter("id");
            products.removeIf(product -> product.startsWith(id + ","));
            FileHandler.writeFile("products.txt", products);
        }

        response.sendRedirect("manageProducts.jsp");
    }
}