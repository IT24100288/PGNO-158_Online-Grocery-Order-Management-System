package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.utils;

import com.grocery.servlet.util.FileHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("adminLogin.jsp");
    }

    @Override
    public void init() throws ServletException {
        String dataPath = getServletContext().getRealPath("/data");
        FileHandler.setDataDir(dataPath);
    }
}