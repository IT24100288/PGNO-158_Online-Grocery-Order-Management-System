<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String category = request.getParameter("category");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Shop - Grocery Store</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <style>
        .navbar {
            background-color: #ef233c;
            color: white;
            display: flex;
            justify-content: space-between;
            padding: 15px 30px;
            font-weight: bold;
        }

        .navbar a {
            color: white;
            text-decoration: none;
            margin-right: 20px;
            transition: color 0.3s;
        }

        .navbar a:hover {
            color: #fffacd;
        }

        .section-title {
            font-size: 28px;
            text-align: center;
            margin-top: 30px;
            color: #ef233c;
        }

        .products {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
            gap: 20px;
            padding: 30px;
            max-width: 1000px;
            margin: auto;
        }

        .product-card {
            display: block;
            text-align: center;
            text-decoration: none;
            background: #fff0f5;
            border: 1px solid #fffacd;
            padding: 15px;
            border-radius: 10px;
            color: #333;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .product-card:hover {
            transform: scale(1.05);
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
        }

        .product-card img {
            width: 100px;
            height: 100px;
            object-fit: cover;
            border-radius: 8px;
        }

        .cart-icon {
            font-size: 20px;
        }
    </style>
</head>
<body>

<div class="navbar">
    <div>
        <a href="index.jsp">Home</a>
        <a href="about.jsp">About Us</a>
        <a href="shop.jsp">Shop</a>
        <a href="#">Rate Us</a>
    </div>
    <div>
        <a href="cart.jsp" title="Go to Cart">
            <i class="fa-solid fa-cart-shopping cart-icon"></i>
        </a>
    </div>
</div>

<div class="section-title">
    <%
        if (category != null) {
            out.print("<b>" + category.substring(0,1).toUpperCase() + category.substring(1) + "</b>");
        } else {
            out.print("<b>Welcome to Our Shop</b>");
        }
    %>
</div>

<div class="products">

    <%
        // Sample product info: name, price, image, category
        String[][] products = {
                {"Cabbage", "5", "cabbage.jpeg", "vegetables"},
                {"Carrot", "3", "carrot.jpeg", "vegetables"},
                {"Onion", "2", "Onion.png", "vegetables"},
                {"Leaks", "2.3", "leaks.jpeg", "vegetables"},
                {"Eggplant", "3", "Eggplant.jpeg", "vegetables"},
                {"Apple", "1", "Apple.jpg", "fruits"},
                {"Bananas", "1.5", "Bananas.jpeg", "fruits"},
                {"Avocado", "3", "Avocado.jpeg", "fruits"},
                {"Strawberry Jam", "2", "stjam.jpeg", "jam"},
                {"Classic Potato Chips", "2.2", "ClassicPotatoChips.jpeg", "snacks"},
                {"Sunquick Orange 700ml", "2.3", "Sunquick.jpeg", "snacks"},
                {"Basmati Rice", "4", "Basmati.jpeg", "others"}
        };

        for (String[] p : products) {
            String name = p[0];
            String price = p[1];
            String img = p[2];
            String cat = p[3];

            if (category == null || category.equalsIgnoreCase(cat)) {
    %>
    <a href="order.jsp?product=<%=name%>&price=<%=price%>&image=<%=img%>" class="product-card">
        <img src="Images/<%=img%>" alt="<%=name%>">
        <div><%=name%></div>
        <div>Price = $<%=price%></div>
    </a>
    <%
            }
        }
    %>

</div>

<jsp:include page="footer.jsp" />
</body>
</html>
