package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.servlet;


import com.grocery.servlet.util.FileHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import java.util.List;

public class AdminLoginServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        String dataPath = getServletContext().getRealPath("/data");
        if (dataPath == null) {
            throw new ServletException("Data directory path is null");
        }
        System.out.println("Data path set to: " + dataPath);
        FileHandler.setDataDir(dataPath);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to adminLogin.jsp for GET requests
        System.out.println("AdminLoginServlet: Handling GET request, forwarding to adminLogin.jsp");
        request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("Attempting login with username: " + username + ", password: " + password);

        List<String> admins = FileHandler.readFile("admin.txt");
        System.out.println("Admin list: " + admins);

        boolean isValid = admins.stream().anyMatch(line -> {
            String[] parts = line.split(",");
            if (parts.length != 2) {
                System.out.println("Invalid line format in admin.txt: " + line);
                return false;
            }
            String storedUsername = parts[0].trim();
            String storedPassword = parts[1].trim();
            System.out.println("Comparing with stored: username=" + storedUsername + ", password=" + storedPassword);
            return storedUsername.equals(username.trim()) && storedPassword.equals(password.trim());
        });

        if (isValid) {
            HttpSession session = request.getSession();
            session.setAttribute("admin", username);
            System.out.println("Login successful for user: " + username);
            response.sendRedirect("adminDashboard.jsp");
        } else {
            System.out.println("Login failed: Invalid username or password");
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
        }
    }
}