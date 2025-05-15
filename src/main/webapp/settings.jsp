<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<html>
<head>
    <title>Settings</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="header">
    CTERL+CART🛒
</div>
<div class="container centered">
    <h2>Settings</h2>
    <form id="settingsForm" action="settings" method="post" enctype="multipart/form-data">
        <!-- Hidden File Input -->
        <input type="file" id="photoInput" name="profilePic" accept="image/*" style="display:none;" onchange="document.getElementById('settingsForm').submit();">

        <!-- Profile Photo -->
        <div class="profile-pic-container">
            <% if (session.getAttribute("profilePic") != null) { %>
            <img class="profile-pic" src="<%= session.getAttribute("profilePic") %>" alt="Profile Picture" onclick="triggerFileInput()">
            <button type="submit" name="removePhoto" value="true">Remove Photo</button>
            <% } else { %>
            <img class="profile-pic" src="images/default-profile.png" alt="Profile Picture"
                 onerror="this.src='https://via.placeholder.com/80/cccccc/000000?text=User';" onclick="triggerFileInput()">
            <button type="button" name="addPhoto" onclick="triggerFileInput()">Add Photo</button>
            <% } %>
        </div>

        <!-- Username (editable) -->
        <input type="text" name="username" value="<%= session.getAttribute("username") != null ? session.getAttribute("username") : "" %>">

        <!-- Email Address (read-only, replacing mobile) -->
        <input type="email" name="email" placeholder="Email Address" value="<%= session.getAttribute("email") != null ? session.getAttribute("email") : "" %>" readonly>

        <!-- Address -->
        <input type="text" name="address" placeholder="Address" value="<%= session.getAttribute("address") != null ? session.getAttribute("address") : "" %>">

        <!-- Update Password -->
        <input type="password" name="newPassword" placeholder="New Password" value="">
        <input type="password" name="confirmPassword" placeholder="Confirm New Password" value="">

        <button type="submit">Save</button>
    </form>
    <p><a class="link" href="welcome.jsp">Back to Welcome</a></p>
    <form action="settings" method="post" style="margin-top: 20px;">
        <button type="submit" name="deleteAccount" value="true" style="background: linear-gradient(90deg, #ff5252, #ff8a80); width: 100%; padding: 12px; border: none; border-radius: 10px; color: #ffffff; font-size: 16px; font-weight: 500; cursor: pointer;">Delete Account</button>
    </form>

    <!-- ✅ Display list of registered users -->
    <div style="margin-top: 40px;">
        <h3>Registered Users</h3>
        <%
            List<String> users = (List<String>) request.getAttribute("userList");
            if (users != null && !users.isEmpty()) {
        %>
        <table border="1" style="width: 100%; border-collapse: collapse;">
            <tr>
                <th>Username</th>
                <th>Email</th>
            </tr>
            <%
                for (String user : users) {
                    String[] parts = user.split(",");
                    if (parts.length >= 2) {
            %>
            <tr>
                <td><%= parts[0] %></td>
                <td><%= parts[1] %></td>
            </tr>
            <%
                    }
                }
            %>
        </table>
        <% } else { %>
        <p>No users found.</p>
        <% } %>
    </div>
</div>

<script>
    function triggerFileInput() {
        document.getElementById('photoInput').click();
    }
</script>
</body>
</html>
