package com.crtlcart.pgno158_onlinegroceryordermanagementsystem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

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

        if (isEmpty(username) || isEmpty(password) || isEmpty(email)) {
            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        String usersFilePath = getServletContext().getRealPath("/WEB-INF/users.txt");
        File usersFile = new File(usersFilePath);
        usersFile.getParentFile().mkdirs(); // Ensure parent dir exists

        if (!usersFile.exists()) {
            usersFile.createNewFile();
        }

        boolean userExists = false;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(usersFile), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length > 0 && parts[0].equalsIgnoreCase(username)) {
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

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(usersFile, true), StandardCharsets.UTF_8))) {
            writer.write(username + ":" + password + ":" + email + "::\n");
        } catch (IOException e) {
            request.setAttribute("error", "Error saving user data.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Save additional data to user_data.txt
        String userDataPath = getServletContext().getRealPath("/WEB-INF/user_data.txt");
        File userDataFile = new File(userDataPath);
        userDataFile.getParentFile().mkdirs();

        if (!userDataFile.exists()) {
            userDataFile.createNewFile();
        }

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(userDataFile, true), StandardCharsets.UTF_8))) {
            writer.write(username + "," + email + "\n");
        } catch (IOException e) {
            request.setAttribute("error", "Error saving user profile info.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        session.setAttribute("email", email);

        response.sendRedirect("welcome.jsp");
    }

    private boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }
}
