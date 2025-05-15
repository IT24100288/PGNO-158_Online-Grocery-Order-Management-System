package com.crtlcart.pgno158_onlinegroceryordermanagementsystem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null || username.trim().isEmpty() || password.trim().isEmpty()) {
            request.setAttribute("error", "Username and password are required.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        File file = new File(getServletContext().getRealPath("/data/users.txt"));
        boolean userFound = false;

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts.length >= 2 && parts[0].equals(username) && parts[1].equals(password)) {
                        userFound = true;
                        HttpSession session = request.getSession();
                        session.setAttribute("username", username);
                        session.setAttribute("email", parts.length > 2 ? parts[2] : "");
                        session.setAttribute("mobile", parts.length > 2 ? parts[2] : ""); // Placeholder, no mobile
                        session.setAttribute("address", parts.length > 4 ? parts[4] : "");
                        response.sendRedirect("welcome.jsp");
                        return;
                    }
                }
            } catch (IOException e) {
                request.setAttribute("error", "Error reading user data.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
        }

        request.setAttribute("error", "No account found with the given username and password.");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}