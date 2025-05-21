<%@ page import="java.util.ArrayList" %>
<%@ page import="com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.modal.Order" %>
<%@ page import="com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.modal.Cart" %>
<%@ page import="com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.utils.PriceString" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="author" content="Dreamy Delights">
    <title>Bakery Dashboard</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet"/>
    <link rel="stylesheet" href="assets/css/admin-general.css">
    <link rel="stylesheet" href="assets/css/slider.css">
    <link rel="stylesheet" href="assets/css/order-details.css">
</head>
<body>
<%
    Order order = (Order) request.getAttribute("orders");
%>
    <div class="container">
        <div class="sidebar">
            <div class="logo">Ctrl+Cart</div>
            <ul>
                <li><a href="orders" class="active">
                  <div class="slide-nav-1">
                    <div><i class="ri-shopping-cart-2-fill"></i></div>
                    <div><span>Orders</span></div>
                  </div>
                </a></li>
            </ul>
        </div>
        <div class="main-content">
            <div class="header-title">Order Details</div>
            <div class="breadcrumb">
                <a href="orders">Orders</a> / <%=order != null?order.getOrderId():""%>
            </div>
            <%
                if(order != null){
            %>
            <div class="details">
                <div class="section">
                    <h2>Order Information</h2>
                    <p><strong>Order ID: </strong><%=order.getOrderId()%></p>
                    <p><strong>Name: </strong><%=order.getCustomerFirstName()%> <%=order.getCustomerLastName()%></p>
                    <p><strong>Email: </strong><%=order.getCustomerEmail()%></p>
                    <p><strong>Phone: </strong><%=order.getCustomerPhone()%></p>
                    <p><strong>Postal Code: </strong><%=order.getCustomerPostalCode()%></p>
                    <p><strong>Address: </strong><%=order.getCustomerAddress()%><p>
                    <p><strong>Order Date: </strong><%=order.getOrderDate()%></p>
                </div>
                <div class="section">
                    <h2>Ordered Items</h2>
                    <table>
                        <thead>
                            <tr>
                                <th>Product ID</th>
                                <th>Item</th>
                                <th>Category</th>
                                <th>Quantity</th>
                                <th>TOTAL</th>
                            </tr>
                        </thead>
                        <tbody>
                           <%
                               ArrayList<Cart> cartItems = order.getProducts();
                               for(Cart cart : cartItems){
                           %>
                           <tr>
                               <td><%=cart.getProductId()%></td>
                               <td><%=cart.getProductName()%></td>
                               <td><%=cart.getProductCategory()%></td>
                               <td><%=cart.getCartQuantity()%></td>
                               <td><%=PriceString.StringFormat(cart.getProductPrice() * cart.getCartQuantity())%></td>
                           </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>
                </div>
                <div class="section">
                    <h2>Actions</h2>
                    <div class="actions">
                        <form action="update-order-status" method="post">
                            <input type="hidden" name="orderId" value="<%= order.getOrderId() %>">
                            <select name="status">
                                <option value="pending" <%= "Pending".equalsIgnoreCase(order.getOrderStatus()) ? "selected" : "" %>>Pending</option>
                                <option value="processing" <%= "Processing".equalsIgnoreCase(order.getOrderStatus()) ? "selected" : "" %>>Processing</option>
                                <option value="completed" <%= "Completed".equalsIgnoreCase(order.getOrderStatus()) ? "selected" : "" %>>Completed</option>
                            </select>
                            <button type="submit">Update</button>
                        </form>
                        <a href="delete-admin-order?id=<%=order.getOrderId()%>"><button class="contact">Remove</button></a>
                    </div>
                </div>
            </div>
            <%
                }
            %>
        </div>
    </div>
</body>
</html>