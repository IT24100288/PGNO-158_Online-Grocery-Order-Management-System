
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment Confirmation</title>
    <style>
        .btn {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #EA7C69;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
            font-size: 16px;
        }
        .btn:hover {
            background-color: #d86c5c;
        }
    </style>
</head>
<body style="background-color:#1F1D2B; color:white; font-family:sans-serif; padding:2rem;">
<h2>Payment Confirmed!</h2>
<p>Thank you, <b><%= request.getAttribute("name") %></b>.</p>
<p>Your payment for Order <b><%= request.getAttribute("table") %></b> of <b>$<%= request.getAttribute("subtotal") %></b> has been received.</p>
<!-- Button to go to invoice.jsp -->
<form action="invoice.jsp" method="get">
    <button class="btn" type="submit">View Invoice</button>
</form>
</body>
</html>
