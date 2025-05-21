<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Grocery Shopping</title>
    <link rel="stylesheet" href="assets/css/style.css">
    <link rel="stylesheet" href="assets/css/catalog.css">
    <link rel="stylesheet" href="assets/css/footer.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <style>
        .navbar a:hover,
        .navbar i:hover {
            color: yellow;
        }
        .action-buttons {
            display: flex;
            justify-content: center;
            gap: 20px;
            margin-top: 30px;
            margin-bottom: 40px;
        }
        .action-buttons button {
            padding: 10px 20px;
            font-size: 16px;
            font-weight: bold;
            border: none;
            border-radius: 5px;
            background-color: red;
            color: white;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .action-buttons button:hover {
            background-color: darkred;
        }
    </style>
</head>
<body>

<!-- Header -->
<div class="header">CTRL+CART</div>

<!-- Navigation Bar -->
<div class="navbar" style="display: flex; justify-content: space-between; align-items: center; padding: 10px 20px; background-color: #333; color: white;">
    <div style="display: flex; gap: 20px;">
        <a href="index.jsp" style="color: white; text-decoration: none;">Home</a>
        <a href="#" style="color: white; text-decoration: none;">About Us</a>
        <a href="catalog.jsp" style="color: white; text-decoration: none;">Shop</a>
        <a href="#" style="color: white; text-decoration: none;">Rate us</a>
    </div>
    <div style="display: flex; align-items: center; gap: 15px;">
        <div style="display: flex; align-items: center; gap: 10px;">
            <i class="fa-solid fa-user" style="color: white;"></i>
            <div style="background-color: red; border-radius: 5px; overflow: hidden; display: flex;">
                <a href="/login" style="color: white; padding: 8px 12px; text-decoration: none; font-weight: bold; border-right: 1px solid white;">Login</a>
                <a href="/Register" style="color: white; padding: 8px 12px; text-decoration: none; font-weight: bold;">Register</a>
            </div>
        </div>
        <a href="cart.jsp" style="color: white;">
            <i class="fa-solid fa-cart-shopping"></i>
        </a>
    </div>
</div>


<div class="hero" style="text-align: left; padding: 60px;">
    <div class="text">
        <h1>We Bring the Store to Your Door</h1>
        <p>Shop Smart, Live Healthy – Your Favorite Groceries Just a Click Away!</p>
        <a href="catalog?name=snacks" style="background: red; color: white; padding: 10px 20px; border-radius: 5px; display: inline-block; margin-top: 30px;">Shop Now</a>
    </div>
    <img src="assets/images/banner1.png" width="800" height="400" alt="Fresh Produce">
</div>

<!-- Action Buttons -->
<div class="action-buttons">
    <a href="catalog?name=snacks"><button>Catalog</button></a>
    <a href="cart.jsp"><button>Cart</button></a>
    <a href="orders"><button>Admin Orders</button></a>
</div>

<!-- Section Title -->
<div class="section-title" style="text-align: center; font-size: 24px;">Shop By <b>Category</b></div>

<!-- Products Section -->
<div class="products" style="display: flex; flex-wrap: wrap; justify-content: center; gap: 20px; padding: 20px;">
    <div class="product">
        <a href="catalog?name=snacks">
            <img src="assets/images/classic.jpg" alt="Snacks">
            <div style="text-align: center;">Snacks</div>
        </a>
    </div>
    <div class="product">
        <a href="catalog?name=beverages">
            <img src="assets/images/cola.png" alt="Beverages">
            <div style="text-align: center;">Beverages</div>
        </a>
    </div>
    <div class="product">
        <a href="catalog?name=bakery">
            <img src="assets/images/bread.jpg" alt="Bakery & Bread">
            <div style="text-align: center;">Bakery & Bread</div>
        </a>
    </div>
    <div class="product">
        <a href="catalog?name=sweets">
            <img src="assets/images/choc1.jpg" alt="Sweets & Chocolates">
            <div style="text-align: center;">Sweets & Chocolates</div>
        </a>
    </div>
    <div class="product">
        <a href="catalog?name=frozen">
            <img src="assets/images/corn.jpg" alt="Frozen Foods">
            <div style="text-align: center;">Frozen Foods</div>
        </a>
    </div>
</div>

<!-- Footer -->
<footer>
    <div class="footer-container">
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
</footer>

</body>
</html>
