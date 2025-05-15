package com.crtlcart.pgno158_onlinegroceryordermanagementsystem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        if (username == null || password == null || email == null || username.trim().isEmpty() || password.trim().isEmpty() || email.trim().isEmpty()) {
            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        File file = new File(getServletContext().getRealPath("/data/users.txt"));

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        boolean userExists = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length > 0 && parts[0].equals(username)) {
                    userExists = true;
                    break;
                }
            }
        }

        if (userExists) {
            request.setAttribute("error", "Username already exists.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(username + ":" + password + ":" + email + "::\n");
        }

        // Save to correct path under /WEB-INF
        String userDataPath = getServletContext().getRealPath("/WEB-INF/user_data.txt");
        File userDataFile = new File(userDataPath);

        // Make sure the parent directory exists
        if (!userDataFile.getParentFile().exists()) {
            userDataFile.getParentFile().mkdirs();
        }

        if (!userDataFile.exists()) {
            userDataFile.createNewFile();
        }

        try (BufferedWriter userDataWriter = new BufferedWriter(new FileWriter(userDataFile, true))) {
            userDataWriter.write(username + "," + email + "\n");
        }

        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        session.setAttribute("email", email);

        response.sendRedirect("welcome.jsp");
    }
}