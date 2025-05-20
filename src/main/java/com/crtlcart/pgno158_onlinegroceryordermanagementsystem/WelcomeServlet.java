package com.crtlcart.pgno158_onlinegroceryordermanagementsystem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Create session if not exists (change from false to true)
        HttpSession session = request.getSession(true);



        // Dummy check (in real app, you'd validate user)
        if (session.getAttribute("username") == null) {
            session.setAttribute("username", "DemoUser");
            session.setAttribute("profilePic", "images/default-profile.png");
        }

        request.getRequestDispatcher("/welcome.jsp").forward(request, response);
    }
}
