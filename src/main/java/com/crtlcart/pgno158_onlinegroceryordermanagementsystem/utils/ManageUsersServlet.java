package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.utils;

import com.grocery.servlet.util.FileHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ManageUsersServlet extends HttpServlet {
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
        // Add debug logging
        System.out.println("ManageUsersServlet: Handling GET request");
        if (request.getSession().getAttribute("admin") == null) {
            System.out.println("ManageUsersServlet: No admin session, redirecting to adminLogin.jsp");
            response.sendRedirect("adminLogin.jsp");
            return;
        }
        System.out.println("ManageUsersServlet: Admin session found, proceeding to manageUsers.jsp");
        List<String> users = FileHandler.readFile("users.txt");
        request.setAttribute("users", users);
        request.getRequestDispatcher("manageUsers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("admin") == null) {
            response.sendRedirect("adminLogin.jsp");
            return;
        }

        String action = request.getParameter("action");
        String id = request.getParameter("id");
        List<String> users = FileHandler.readFile("users.txt");

        if ("update".equals(action)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            users = users.stream().map(user -> {
                String[] parts = user.split(",");
                if (parts[0].equals(id)) {
                    return id + "," + username + "," + password;
                }
                return user;
            }).collect(Collectors.toList());
            FileHandler.writeFile("users.txt", users);
        } else if ("delete".equals(action)) {
            users = users.stream()
                    .filter(user -> !user.split(",")[0].equals(id))
                    .collect(Collectors.toList());
            FileHandler.writeFile("users.txt", users);
        }

        response.sendRedirect("manageUsers");
    }
}