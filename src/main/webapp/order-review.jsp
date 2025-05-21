<%@ page import="java.util.ArrayList" %>
<%@ page import="com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.modal.Cart" %>
<%@ page import="com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.service.CartService" %>
<%@ page import="com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.utils.PriceString" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="author" content="Ctrl+Cart">
    <title>Ctrl+Cart | Order Review</title>
    <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet"/>
    <link rel="stylesheet" href="assets/css/general.css">
    <link rel="stylesheet" href="assets/css/header.css">
    <link rel="stylesheet" href="assets/css/footer.css">
    <link rel="stylesheet" href="assets/css/order-review.css">
</head>
<body>
<%
    CartService cartService = new CartService();
    ArrayList<Cart> cartItems = cartService.getCartItems(request);

    int quantity = cartService.getCartQuantity(cartItems);

    String customerFirstName = (String) session.getAttribute("customerFirstName");
    String customerLastName = (String) session.getAttribute("customerLastName");
    String customerAddress = (String) session.getAttribute("customerAddress");
    String customerPhone = (String) session.getAttribute("customerPhone");
    String customerEmail = (String) session.getAttribute("customerEmail");
    String customerCity = (String) session.getAttribute("customerCity");
    String customerPostalCode = (String) session.getAttribute("customerPostalCode");
%>
<header class="header">
    <div class="nav-bar">
        <p class="bakery-logo"><a class="bakery-logo-a" href="index.jsp">Ctrl+Cart</a></p>
        <div class="nav-content">
            <div class="nav-items">
                <a href="catalog?name=bread">Bread</a>
            </div>
            <div class="nav-items">
                <a href="catalog?name=pastry">Pastry</a>
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
        Review Your Order
    </section>
    <section class="main-container">
        <div class="client-details-section">
            <div class="form-area">
                <div class="form-title-1">Client Details</div>
                <div><%=customerFirstName != null? customerFirstName :"--------"%> <%=customerLastName != null? customerLastName : ""%></div>
                <div><%=customerPhone != null? customerPhone : "--------"%></div>
                <div><%=customerEmail != null?customerEmail:"--------"%></div>
                <div class="form-title-2">Delivery Details</div>
                <div>Deliver to</div>
                <div><%=customerAddress!=null?customerAddress:"--------"%></div>
                <div><%=customerCity!=null?customerCity:"--------"%></div>
                <div><%=customerPostalCode!=null?customerPostalCode:"--------"%></div>
            </div>
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
                    <div><%=PriceString.StringFormat(cart.getProductPrice() * cart.getCartQuantity())%></div>
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
                <div class="place-order-btn">
                    <div><a href="OrderServlet?action=place"><button>Place Order</button></a></div>
                    <div><a href="OrderServlet?action=cancel"><button>Cancel</button></a></div>
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
                <p><a href="catalog?name=bread">Snacks</a></p>
                <p><a href="catalog?name=pastry">Sweets & Chocolates</a></p>
                <p><a href="catalog?name=beverages">Beverage</a></p>
            </div>
            <div class="sec-2-icons">
                <p><a href=""><i class="ri-facebook-circle-fill"></i></a></p>
                <p><a href=""><i class="ri-instagram-fill"></i></a></p>
                <p><a href=""><i class="ri-whatsapp-fill"></i></a></p>
            </div>
            <p class="copyright">Dreamy Delights &copy; <span id="copy-year"></span></p>
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

