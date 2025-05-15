package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.servlet;

import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.utils.ProductFileUtil;
import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.utils.MergeSortUtil;
import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listProducts")
public class ListProductsServlet extends HttpServlet {
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
        System.out.println("ListProductsServlet: Forwarding to /WEB-INF/listProducts.jsp");
        List<Product> products = ProductFileUtil.readProducts();

        // Get sorting parameter
        String sortBy = request.getParameter("sortBy");
        if (sortBy != null && (sortBy.equalsIgnoreCase("category") || sortBy.equalsIgnoreCase("price"))) {
            products = MergeSortUtil.sort(products, sortBy);
            System.out.println("Sorted products by " + sortBy + ": " + products);
        }

        request.setAttribute("products", products);
        request.getRequestDispatcher("/WEB-INF/listProducts.jsp").forward(request, response);
    }
}