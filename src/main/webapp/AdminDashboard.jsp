<%@ page import="java.util.*, java.io.*, java.text.*" %>
<%@ page import="com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model.Payment" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Admin Dashboard</title>
  <link rel="stylesheet" href="css/adminD.css">
</head>
<body>
<h2>Payment Records</h2>
<table border="1">
  <tr>
    <th>Timestamp</th><th>Method</th><th>Cardholder</th><th>Card No</th><th>Expiry</th><th>Order Type</th><th>Table</th><th>Subtotal</th><th>Actions</th>
  </tr>
  <%
    List<Payment> payments = (List<Payment>) request.getAttribute("payments");
    for (int i = 0; i < payments.size(); i++) {
      Payment p = payments.get(i);
  %>
  <tr>
    <form method="post" action="adminPayment">
      <input type="hidden" name="index" value="<%= i %>"/>
      <td><%= p.getTimestamp() %></td>
      <td><input type="text" name="method" value="<%= p.getPaymentMethod() %>"/></td>
      <td><input type="text" name="cardholder" value="<%= p.getCardholder() %>"/></td>
      <td><input type="text" name="cardnumber" value="<%= p.getCardNumber() %>"/></td>
      <td><input type="text" name="expiry" value="<%= p.getExpiry() %>"/></td>
      <td><input type="text" name="ordertype" value="<%= p.getOrderType() %>"/></td>
      <td><input type="text" name="table" value="<%= p.getTable() %>"/></td>
      <td><input type="text" name="subtotal" value="<%= p.getSubtotal() %>"/></td>
      <td>
        <button name="action" value="update">Update</button>
        <button name="action" value="delete">Delete</button>
      </td>
    </form>
  </tr>
  <% } %>
</table>
</body>
</html>
