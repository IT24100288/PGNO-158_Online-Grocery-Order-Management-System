<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.grocery.servlet.util.FileHandler" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Manage Orders</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<div class="container">
    <h2>Manage Orders</h2>
    <%
        if (session.getAttribute("admin") == null) {
            response.sendRedirect("adminLogin.jsp");
            return;
        }
        List<String> orders = FileHandler.readFile("orders.txt");
    %>
    <table>
        <tr>
            <th>ID</th>
            <th>User ID</th>
            <th>Product ID</th>
            <th>Quantity</th>
            <th>Total Price</th>
            <th>Status</th>
            <th>Actions</th>
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
            <td>
                <form action="manageOrders" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="<%= parts[0] %>">
                    <select name="status">
                        <option value="Pending">Pending</option>
                        <option value="Shipped">Shipped</option>
                        <option value="Delivered">Delivered</option>
                        <option value="Cancelled">Cancelled</option>
                    </select>
                    <input type="hidden" name="action" value="update">
                    <input type="submit" value="Update">
                </form>
                <form action="manageOrders" method="post" style="display:inline;">
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