<%@ page import="com.yourapp.model.Invoice" %>
<%@ page import="java.util.Stack" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Invoice</title>
    <link rel="stylesheet" href="css/invoice.css" />
    <script defer src="js/invoice.js"></script>
</head>
<body>
<div class="invoice-container">
    <h1>Payment Invoice</h1>
    <table id="invoiceTable">
        <thead>
        <tr>
            <th>Invoice ID</th>
            <th>Customer</th>
            <th>Amount</th>
            <th>Date</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <%
            Stack<Invoice> invoices = (Stack<com.yourapp.model.Invoice>) request.getAttribute("invoices");
            if (invoices != null) {
                for (com.yourapp.model.Invoice inv : invoices) {
        %>
        <tr>
            <td><%= inv.getInvoiceId() %></td>
            <td><%= inv.getCustomer() %></td>
            <td>$<%= String.format("%.2f", inv.getAmount()) %></td>
            <td><%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(inv.getDate()) %></td>
            <td>
                <form method="post" style="display:inline;">
                    <input type="hidden" name="invoiceId" value="<%= inv.getInvoiceId() %>"/>
                    <input type="hidden" name="action" value="delete"/>
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
        <%
                }
            }
        %>
        </tbody>

    </table>

    <button onclick="addInvoice()">Add Invoice</button>
    <form method="post">
        <input type="hidden" name="action" value="add" />
        <input type="text" name="customer" placeholder="Customer Name" required />
        <input type="number" step="0.01" name="amount" placeholder="Amount" required />
        <button type="submit">Save</button>
    </form>


</div>
</body>
</html>
