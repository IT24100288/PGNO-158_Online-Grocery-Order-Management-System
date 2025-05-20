<%--
  Created by IntelliJ IDEA.
  User: amashasawandi
  Date: 2025-05-08
  Time: 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.crtlcart.pgno158_onlinegroceryordermanagementsystem.utils.FileHandler" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Sales Report</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<div class="container">
    <h2>Sales Report</h2>
    <%
        if (session.getAttribute("admin") == null) {
            response.sendRedirect("adminLogin.jsp");
            return;
        }
        List<String> orders = FileHandler.readFile("orders.txt");
        double totalSales = orders.stream()
                .mapToDouble(line -> Double.parseDouble(line.split(",")[4]))
                .sum();
        int completedOrders = (int) orders.stream()
                .filter(line -> line.split(",")[5].equals("Delivered"))
                .count();
    %>
    <p>Total Sales: $<%= String.format("%.2f", totalSales) %></p>
    <p>Completed Orders: <%= completedOrders %></p>
    <table>
        <tr>
            <th>Order ID</th>
            <th>User ID</th>
            <th>Product ID</th>
            <th>Quantity</th>
            <th>Total Price</th>
            <th>Status</th>
        </tr>
        <% for (String order : orders) {
            String[] parts = order.split(",");
        %>
        <tr>
            <td><%= parts[0] %></td>
            <td><%= parts[1] %></td>
            <td><%= parts[2] %></td>
            <td><%= parts[3] %></td>
            <td><%= parts[4] %></td>
            <td><%= parts[5] %></td>
        </tr>
        <% } %>
    </table>
    <a href="adminDashboard.jsp">Back to Dashboard</a>
</div>
</body>
</html>