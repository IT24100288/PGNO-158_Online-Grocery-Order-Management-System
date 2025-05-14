<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Grocery Shopping</title>
    <link rel="stylesheet" href="css/style.css">

    <!-- Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<div>
    <div class="header">CTRL+CART</div>

    <div class="navbar">
        <div>
            <a href="index.jsp">Home</a>
            <a href="about.jsp">About Us</a>
            <a href="shop.jsp">Shop</a>
            <a href="#">Rate us</a>
        </div>

        <div style="display: flex; align-items: center; justify-content: space-between; gap: 10px; padding: 0 20px;">
            <i class="fa-solid fa-user" style="color: white;"></i>
            <div style="background-color: #ff0000; border-radius: 5px; overflow: hidden; display: flex;">
                <a href="/login" style="color: #ffffff; padding: 8px 12px; text-decoration: none; font-weight: bold; border-right: 1px solid #fff;">
                    Login
                </a>
                <a href="/Register" style="color: #ffffff; padding: 8px 12px; text-decoration: none; font-weight: bold;">
                    Register
                </a>
            </div>
        </div>

        <div>
            <a href="cart.jsp"><i class="fa-solid fa-cart-shopping" style="margin-right: 5px;"></i></a>
        </div>
    </div>

    <div class="hero">
        <div class="text">
            <h1>We Bring the Store to Your Door</h1>
            <p>Shop Smart, Live Healthy – Your Favorite Groceries Just a Click Away!</p>
            <a href="shop.jsp" style="background:red; color:white; padding:10px 20px; border-radius:5px; display:inline-block; margin-top: 30px;">
                Shop Now
            </a>
        </div>
        <img src="Images/Banner1.png" width="800" height="2500" alt="Fresh Produce">
    </div>

    <div class="section-title">Deal of the Day</div>

    <div class="timer">29 Days : 15 Hours : 25 Minutes : 08 Seconds</div>

    <div class="products">
        <div class="product">
            <img src="Images/Vegetables.png" alt="Vegetables">
            <div>Vegetables</div>
        </div>
        <div class="product">
            <img src="Images/Fruits.png" alt="Fresh Fruits">
            <div>Fruits</div>
        </div>
        <div class="product">
            <img src="Images/Onion.png" alt="Onions">
            <div>Onions</div>
        </div>
        <div class="product">
            <img src="Images/Jam.png" alt="Fruit Jams">
            <div>Jam</div>
        </div>
        <div class="product">
            <img src="Images/Potatoes.png" alt="Potatoes">
            <div>Potatoes</div>
        </div>
    </div>

    <!-- Products section ends here -->

    <div <jsp:include page="footer.jsp" />
</div>
</body>
</html>
