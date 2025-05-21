<%@ page import="java.util.ArrayList" %>
<%@ page import="com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.modal.Product" %>
<%@ page import="com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.utils.PriceString" %>
<%@ page import="com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.modal.Cart" %>
<%@ page import="com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.service.CartService" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="Dreamy Delights">
<head>
    <title>Dreamy Delights | Product</title>
    <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet"/>
    <link rel="stylesheet" href="assets/css/general.css">
    <link rel="stylesheet" href="assets/css/product-card.css">
    <link rel="stylesheet" href="assets/css/header.css">
    <link rel="stylesheet" href="assets/css/footer.css">
    <link rel="icon" type="image/x-icon" href="icon.ico">
</head>
<body>
    <%
        Product product = (Product) request.getAttribute("product");
        String productId = request.getParameter("id");
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
        <section class="main-container">
            <div class="product-img">
                <img src="<%=product.getProductImage()%>" alt="<%=product.getProductName()%>">
                <div class="img-outline"></div>
                <div class="img-outline-2"></div>
            </div>

            <div class="product-info">
                <div class="product-main-text">
                    <p class="product-name"><%=product.getProductName()%></p>
                    <p class="product-description"><%=product.getDescription()%></p>
                </div>
                <p class="product-price"><%=PriceString.StringFormat(product.getProductPrice())%></p>
                <div class="action-btn">
                    <div class="add-to-cart-btn"><button class="js-add-to-cart" data-id="<%=product.getProductId()%>">Add to Cart</button></div>
                    <div class="buy-now-btn"><button class="js-buy-now-btn" data-id="<%=product.getProductId()%>">Buy Now</button></div>
                </div>
            </div>
        </section>
    </main>
    <footer>
        <div class="footer-container">
            <div class="footer-sec-1">
                <p>Dreamy Delights</p>
            </div>
            <div class="footer-sec-2">
                <div class="sec-2-text">
                    <p><a href="catalog?name=snacks">Snacks</a></p>
                    <p><a href="catalog?name=beverages">Beverages</a></p>
                    <p><a href="catalog?name=sweets">Sweets & Chocolates</a></p>
                </div>
                <div class="sec-2-icons">
                    <p><a href=""><i class="ri-facebook-circle-fill"></i></a></p>
                    <p><a href=""><i class="ri-instagram-fill"></i></a></p>
                    <p><a href=""><i class="ri-whatsapp-fill"></i></a></p>
                </div>
                <p class="copyright">CTRL+CART &copy; <span id="copy-year"></span></p>
            </div>
            <div class="footer-sec-3">
                <p>Contact Us</p>
                <p>011-7544801</p>
                <p>Pittugala</p>
            </div>
        </div>
    </footer>
    <script src="assets/js/cart.js"></script>
</body>
</html>
