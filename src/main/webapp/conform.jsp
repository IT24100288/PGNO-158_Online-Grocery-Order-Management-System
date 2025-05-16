
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment Confirmation</title>
</head>
<body style="background-color:#1F1D2B; color:white; font-family:sans-serif; padding:2rem;">
<h2>Payment Confirmed!</h2>
<p>Thank you, <b><%= request.getAttribute("name") %></b>.</p>
<p>Your payment for Order <b><%= request.getAttribute("table") %></b> of <b>$<%= request.getAttribute("subtotal") %></b> has been received.</p>
</body>
</html>
