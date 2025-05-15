<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome - Ctrl+Cart</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<header>
    <div class="container">
        <h1>Welcome to Ctrl+Cart</h1>
    </div>
</header>

<nav>
    <div class="container">
        <a href="listProducts">View Products</a>
        <a href="reviewSubmission.jsp">Reviews</a>
    </div>
</nav>

<div class="container">
    <div class="card">
        <h2>Quick Links</h2>
        <div class="row">
            <div class="col-md-6">
                <div class="card">
                    <h3>Products</h3>
                    <p>Browse our extensive collection of products</p>
                    <a href="listProducts" class="btn btn-primary">View Products</a>
                </div>
            </div>
            <div class="col-md-6">
                <div class="card">
                    <h3>Reviews</h3>
                    <p>Share your experience with our products</p>
                    <a href="reviewSubmission.jsp" class="btn btn-primary">View Reviews</a>
                </div>
            </div>
        </div>
    </div>
</div>

<footer>
    <div class="container">
        <p>&copy; 2024 Ctrl+Cart. All rights reserved.</p>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>