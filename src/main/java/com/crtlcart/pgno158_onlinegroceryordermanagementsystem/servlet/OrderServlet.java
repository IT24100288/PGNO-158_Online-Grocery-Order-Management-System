package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.servlet;

import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model.Item;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServletException {

        List<Item> items = new ArrayList<>();
        items.add(new Item("Spicy seasoned seafood noodles", 2.29, 2, "Please, just a little bit spicy only.", "images/item1.jpg"));
        items.add(new Item("Salted pasta with mushrooms", 2.69, 1, "", "images/item2.jpg"));
        items.add(new Item("Spicy instant noodle", 3.49, 3, "", "images/item3.jpg"));
        items.add(new Item("Healthy noodle with mushrooms", 3.29, 1, "", "images/item4.jpg"));

        request.setAttribute("items", items);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
