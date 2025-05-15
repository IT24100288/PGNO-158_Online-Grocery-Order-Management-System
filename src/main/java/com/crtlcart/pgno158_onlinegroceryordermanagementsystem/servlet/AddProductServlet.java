package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.servlet;

import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model.Product;
import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.utils.ProductFileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("AddProductServlet: doPost invoked.");
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");
        String category = request.getParameter("category");

        System.out.println("Received parameters - Name: " + name + ", Price: " + priceStr + ", Category: " + category);

        if (name != null && priceStr != null && category != null && !name.isEmpty() && !category.isEmpty()) {
            try {
                double price = Double.parseDouble(priceStr);
                List<Product> products = ProductFileUtil.readProducts();
                int newId = products.size() > 0 ? products.get(products.size() - 1).getId() + 1 : 1;
                Product product = new Product(newId, name, price, category);
                products.add(product);

                System.out.println("Adding product: " + product);
                ProductFileUtil.writeProducts(products);

                System.out.println("Attempting to write products: " + products);
                System.out.println("Writing line: " + product.toString());
                System.out.println("Write operation completed successfully.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid price format: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Failed to write products: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid or missing parameters.");
        }

        response.sendRedirect("listProducts");
    }
}