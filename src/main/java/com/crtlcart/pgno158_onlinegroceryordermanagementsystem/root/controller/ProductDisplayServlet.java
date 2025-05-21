package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.controller;

import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.modal.Product;
import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/catalog")
public class ProductDisplayServlet extends HttpServlet {

    private final ProductService productService = new ProductService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String category = request.getParameter("name");
        List<Product> products = productService.displayProductByCategory(category);

        request.setAttribute("category", category);
        request.setAttribute("products", products);
        request.getRequestDispatcher("/catalog.jsp").forward(request, response);
    }

}
