package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.servlet;


import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.utils.ProductFileUtil;
import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/updateProduct")
public class UpdateProductServlet extends HttpServlet {
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
        String idStr = request.getParameter("id");
        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            List<Product> products = ProductFileUtil.readProducts();
            Product product = products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
            request.setAttribute("product", product);
            request.getRequestDispatcher("/WEB-INF/updateProduct.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");
        String category = request.getParameter("category");

        if (idStr != null && name != null && priceStr != null && category != null && !name.isEmpty() && !category.isEmpty()) {
            int id = Integer.parseInt(idStr);
            double price = Double.parseDouble(priceStr);
            List<Product> products = ProductFileUtil.readProducts();
            for (Product product : products) {
                if (product.getId() == id) {
                    product.setName(name);
                    product.setPrice(price);
                    product.setCategory(category);
                    break;
                }
            }
            ProductFileUtil.writeProducts(products);
        }
        response.sendRedirect("listProducts");
    }
}