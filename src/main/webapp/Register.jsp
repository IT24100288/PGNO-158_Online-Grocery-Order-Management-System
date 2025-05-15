<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="styles.css">
    <meta charset="UTF-8">
</head>
<body>
<div class="header">
    CTERL+CART🛒
</div>
<div class="container">
    <h2>Register</h2>
    <% if (request.getAttribute("error") != null) { %>
    <p class="error"><%= request.getAttribute("error") %></p>
    <% } %>
    <form action="register" method="post">
        <input type="text" name="username" placeholder="Username" required>
        <input type="password" name="password" placeholder="Password" required>
        <input type="email" name="email" placeholder="Email" required>
        <button type="submit">Register</button>
    </form>
    <p>Already have an account? <a class="link" href="login.jsp">Login</a></p>
</div>
</body>
</html>