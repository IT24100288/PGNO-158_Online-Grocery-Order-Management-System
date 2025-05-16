<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <!-- Avoid favicon error -->
    <link rel="icon" href="data:,">
    <!-- Link your CSS -->
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="header">
    CTERL+CART 🛒
</div>

<div class="welcome-container centered">
    <%
        String username = (String) session.getAttribute("username");
        if (username == null || username.trim().isEmpty()) {
            username = "Guest";
        }

        String profilePic = (String) session.getAttribute("profilePic");
        if (profilePic == null || profilePic.trim().isEmpty()) {
            profilePic = "images/default-profile.png"; // make sure this image exists
        }
    %>

    <div class="profile-pic-container">
        <a href="settings.jsp">
            <img class="profile-pic"
                 src="<%= profilePic %>"
                 alt="Profile Picture"
                 onerror="this.src='images/default-profile.png';">
        </a>
    </div>

    <h2>Welcome, <%= username.toUpperCase() %>!</h2>
</div>
</body>
</html>

