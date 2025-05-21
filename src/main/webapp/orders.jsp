<%@ page import="java.util.ArrayList" %>
<%@ page import="com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.modal.Queue" %>
<%@ page import="com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.modal.Order" %>
<%@ page import="com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.modal.Cart" %>
<%@ page import="com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.utils.PriceString" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="author" content="Dreamy Delights">
  <title>Dashboard | Orders</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
  <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet"/>
  <link rel="stylesheet" href="assets/css/admin-general.css">
  <link rel="stylesheet" href="assets/css/orders.css">
  <link rel="stylesheet" href="assets/css/slider.css">
</head>
<body>
    <%
        Queue orders = (Queue) request.getAttribute("orders");
    %>
    <div class="container">
      <div class="sidebar">
        <div class="logo">CTRT+CART</div>
        <ul>
            <li><a href="orders" class="active">
              <div class="slide-nav-1">
                <div><i class="ri-shopping-cart-2-fill"></i></div>
                <div><span>Orders</span></div>
              </div>
            </a></li>
        </ul>
        <div class="logout">
            <a href="" onclick="return confirm('you want to logout?')"><i class="fas fa-sign-out-alt"></i><span>Log out</span></a>
        </div>
    </div>
        <div class="main-content">
            <div class="header-title">All Order</div>
            <div class="orders">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>CUSTOMER</th>
                            <th>ITEMS</th>
                            <th>DATE</th>
                            <th>TOTAL</th>
                            <th>STATUS</th>
                            <th>ACTION</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%
                        if(orders != null){
                            while (!orders.isEmpty()){
                                Order order = orders.remove();
                                assert order != null;%>
                    <tr>
                        <td><%=order.getOrderId()%></td>
                        <td><%=order.getCustomerFirstName()%> <%=order.getCustomerLastName()%></td>
                        <td>
                            <%
                                ArrayList<Cart> cartItems = order.getProducts();
                                for(Cart cartItem : cartItems){
                            %>
                            <span><%=cartItem.getProductName()%></span>,
                            <%
                                }
                            %>
                        </td>
                        <td><%=order.getOrderDate()%></td>
                        <td><%=PriceString.StringFormat(order.getTotalPrice())%></td>
                        <td>
                            <%if(order.getOrderStatus().equalsIgnoreCase("pending")){%>
                            <span class="status pending">Pending</span>
                            <%}else if (order.getOrderStatus().equalsIgnoreCase("processing")) {%>
                            <span class="status processing">Processing</span>
                            <%}else{%>
                            <span class="status completed">Completed</span>
                            <%}%>
                        </td>
                        <td class="view"><a href="order-details?id=<%=order.getOrderId()%>">View order</a></td>
                    </tr>
                        <%}
                    }%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>