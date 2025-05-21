<%@ page import="java.util.ArrayList" %>
<%@ page import="com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.service.CartService" %>
<%@ page import="com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.modal.Cart" %>
<%@ page import="com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.utils.PriceString" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ctrl+Cart | Checkout</title>
    <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet"/>
    <link rel="stylesheet" href="assets/css/general.css">
    <link rel="stylesheet" href="assets/css/header.css">
    <link rel="stylesheet" href="assets/css/footer.css">
    <link rel="stylesheet" href="assets/css/checkout.css">
</head>
<body>
<%
    CartService cartService = new CartService();
    ArrayList<Cart> cartItems = cartService.getCartItems(request);

    int quantity = cartService.getCartQuantity(cartItems);
%>
<header class="header">
    <div class="nav-bar">
        <p class="bakery-logo"><a class="bakery-logo-a" href="index.jsp">Ctrl+Cart</a></p>
        <div class="nav-content">
            <div class="nav-items">
                <a href="catalog?name=snacks">Snacks</a>
            </div>
            <div class="nav-items">
                <a href="catalog?name=beverages">Beverages</a>
            </div>
            <div class="nav-items">
                <a href="cart.jsp" class="cart-icon">
                    <i class="ri-shopping-cart-2-fill"></i>
                    <span class="cart-item-display" style="<%=cartItems == null || cartItems.isEmpty()? "display:none;":""%>"><%=quantity%></span>
                </a>
            </div>
        </div>
    </div>
</header>
<main>
    <section class="main-title">
        Checkout
    </section>
    <section class="main-container">
        <div class="client-details-section">
            <form class="form-area" action="order-data" method="post">
                <div class="form-title-1">Client Details</div>
                <div class="form-sec-1">
                    <div class="first-name">
                        <div><label for="customerFirstName">First Name</label></div>
                        <input type="text" id="customerFirstName" name="customerFirstName"  required>
                    </div>
                    <div class="last-name">
                        <div><label for="customerLastName">Last Name</label></div>
                        <input type="text" id="customerLastName" name="customerLastName"  required>
                    </div>
                </div>
                <div class="form-sec-2">
                    <div class="phone-number">
                        <div><label for="customerPhone">Phone</label></div>
                        <input type="tel" id="customerPhone" name="customerPhone" required>
                    </div>
                    <div class="email-address">
                        <div><label for="customerEmail">Email</label></div>
                        <input type="email" id="customerEmail" name="customerEmail" required>
                    </div>
                </div>
                <div class="form-title-2">Delivery Details</div>
                <div class="form-sec-3">
                    <div><label for="customerAddress">Address</label></div>
                    <input type="text" id="customerAddress" name="customerAddress" required>
                </div>
                <div class="form-sec-4">
                    <div class="city">
                        <div><label for="customerCity">City/Town</label></div>
                        <input type="text" id="customerCity" name="customerCity" required>
                    </div>
                    <div class="postal-code">
                        <div><label for="customerPostalCode">Postal Code</label></div>
                        <input type="text" id="customerPostalCode" name="customerPostalCode" required>
                    </div>
                </div>
                <div class="form-submit-btn">
                    <button type="submit">Submit</button>
                </div>
            </form>
        </div>
        <div class="order-details-section">
            <div class="order-details-card">
                <div class="order-title">Your Order</div>
                <%
                    if (cartItems!= null && !cartItems.isEmpty()) {
                        for (Cart cart : cartItems) {
                %>
                    <div class="order-details">
                        <div><%=cart.getProductName()%></div>
                        <div><%=cart.getCartQuantity()%> pcs</div>
                        <div><%=PriceString.StringFormat(cart.getProductPrice())%></div>
                    </div>
                <%
                        }
                    }
                %>
                <div class="horizontal-line"></div>
                <div class="delivery-charge">
                    <div>Delivery</div>
                    <div><%=PriceString.StringFormat(cartService.getDeliveryCost())%></div>
                </div>
                <div class="total-cost">
                    <div>Total</div>
                    <div><%=PriceString.StringFormat(cartService.calculateTotalPrice(cartItems))%></div>
                </div>
            </div>
        </div>
    </section>
</main>
<footer>
    <div class="footer-container">
        <div class="footer-sec-1">
            <p>Ctrl+Cart</p>
        </div>
        <div class="footer-sec-2">
            <div class="sec-2-text">
                <p><a href="catalog?name=snacks">Snacks</a></p>
                <p><a href="catalog?name=sweets">Sweets & Chocolates</a></p>
                <p><a href="catalog?name=beverages">Beverages</a></p>
            </div>
            <div class="sec-2-icons">
                <p><a href=""><i class="ri-facebook-circle-fill"></i></a></p>
                <p><a href=""><i class="ri-instagram-fill"></i></a></p>
                <p><a href=""><i class="ri-whatsapp-fill"></i></a></p>
            </div>
            <p class="copyright">Ctrl+Cart &copy; <span id="copy-year"></span></p>
        </div>
        <div class="footer-sec-3">
            <p>Contact Us</p>
            <p>011-7544801</p>
            <p>Pittugala</p>
        </div>
    </div>
</footer>
<script src="assets/js/date.js"></script>
</body>
</html>
