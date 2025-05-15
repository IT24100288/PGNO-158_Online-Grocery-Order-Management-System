package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.servlet;

import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.utils.ProductFileUtil;
import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/deleteProduct")
public class DeleteProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        try {
            ProductFileUtil.init(getServletContext());
        } catch (IOException e) {
            throw new ServletException("Failed to initialize ProductFileUtil", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<Product> products = ProductFileUtil.readProducts();
        List<Product> updatedProducts = new ArrayList<>();
        for (Product p : products) {
            if (p.getId() != id) {
                updatedProducts.add(p);
            }
        }
        ProductFileUtil.writeProducts(updatedProducts);
        response.sendRedirect("listProducts");
    }
}