package com.crtlcart.pgno158_onlinegroceryordermanagementsystem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HelloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init(); // Optional: can initialize resources here
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>Hello Servlet</title></head>");
        out.println("<body>");
        out.println("<h1>Hello from HelloServlet!</h1>");
        out.println("<p>Java SDK: 24 | Servlet API: Jakarta 6.1</p>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    public void destroy() {
        // Clean up any resources if needed
        super.destroy();
    }
}
