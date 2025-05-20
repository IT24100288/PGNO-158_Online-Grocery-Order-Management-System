<%--
  Created by IntelliJ IDEA.
  User: amashasawandi
  Date: 2025-05-08
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.crtlcart.pgno158_onlinegroceryordermanagementsystem.utils.FileHandler" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Manage Products</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<div class="container">
    <h2>Manage Products</h2>
    <%
        if (session.getAttribute("admin") == null) {
            response.sendRedirect("adminLogin.jsp");
            return;
        }
        List<String> products = FileHandler.readFile("products.txt");
    %>
    <h3>Add Product</h3>
    <form action="manageProducts" method="post">
        <input type="hidden" name="action" value="add">
        <label>Name: <input type="text" name="name" required></label><br>
        <label>Price: <input type="number" step="0.01" name="price" required></label><br>
        <label>Stock: <input type="number" name="stock" required></label><br>
        <input type="submit" value="Add Product">
    </form>
    <h3>Product List</h3>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Stock</th>
            <th>Actions</th>
        </tr>
        <% for (String product : products) {
            String[] parts = product.split(",");
        %>
        <tr>
            <td><%= parts[0] %></td>
            <td><%= parts[1] %></td>
            <td><%= parts[2] %></td>
            <td><%= parts[3] %></td>
            <td>
                <form action="manageProducts" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="<%= parts[0] %>">
                    <input type="hidden" name="action" value="delete">
                    <input type="submit" value="Delete" onclick="return confirm('Are you sure?');">
                </form>
            </td>
        </tr>
        <% } %>
    </table>
    <a href="adminDashboard.jsp">Back to Dashboard</a>
</div>
</body>
</html>
