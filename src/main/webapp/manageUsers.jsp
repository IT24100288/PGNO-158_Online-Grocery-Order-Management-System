<%--
  Created by IntelliJ IDEA.
  User: amashasawandi
  Date: 2025-05-08
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.grocery.servlet.util.FileHandler" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Manage Users</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<div class="container">
    <h2>Manage Users</h2>
    <%
        if (session.getAttribute("admin") == null) {
            response.sendRedirect("adminLogin.jsp");
            return;
        }
        List<String> users = FileHandler.readFile("users.txt");
    %>
    <table>
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Active</th>
            <th>Actions</th>
        </tr>
        <% for (String user : users) {
            String[] parts = user.split(",");
        %>
        <tr>
            <td><%= parts[0] %></td>
            <td><%= parts[1] %></td>
            <td><%= parts[3] %></td>
            <td><%= parts[4] %></td>
            <td>
                <form action="manageUsers" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="<%= parts[0] %>">
                    <input type="hidden" name="action" value="<%= parts[4].equals("true") ? "suspend" : "activate" %>">
                    <input type="submit" value="<%= parts[4].equals("true") ? "Suspend" : "Activate" %>">
                </form>
                <form action="manageUsers" method="post" style="display:inline;">
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