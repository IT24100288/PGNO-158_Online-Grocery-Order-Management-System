<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome - Ctrl+Cart</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #E0EAFC 0%, #CFDEF3 100%);
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }
        header {
            background: linear-gradient(45deg, #6B48FF, #8A6CFF);
            color: white;
            padding: 2rem 0;
            margin-bottom: 2rem;
            box-shadow: 0 4px 15px rgba(107, 72, 255, 0.2);
        }
        nav {
            background: linear-gradient(45deg, #8A6CFF, #6B48FF);
            padding: 1rem 0;
            margin-bottom: 2rem;
            box-shadow: 0 4px 15px rgba(107, 72, 255, 0.2);
        }
        nav a {
            color: white;
            text-decoration: none;
            margin-right: 1.5rem;
            padding: 0.5rem 1rem;
            border-radius: 20px;
            transition: all 0.3s ease;
            background: rgba(255,255,255,0.1);
        }
        nav a:hover {
            background: rgba(255,255,255,0.2);
            transform: translateY(-2px);
        }
        .card {
            background: white;
            border-radius: 15px;
            box-shadow: 0 8px 20px rgba(107, 72, 255, 0.1);
            margin-bottom: 1.5rem;
            border: none;
            transition: transform 0.3s ease;
        }
        .card:hover {
            transform: translateY(-5px);
        }
        .card h2 {
            color: #6B48FF;
            padding: 1.5rem;
            margin: 0;
            border-bottom: 2px solid #f0f0f0;
        }
        .card h3 {
            color: #6B48FF;
            margin-bottom: 1rem;
        }
        .btn-primary {
            background: linear-gradient(45deg, #6B48FF, #8A6CFF);
            border: none;
            padding: 0.75rem 1.5rem;
            border-radius: 25px;
            transition: all 0.3s ease;
        }
        .btn-primary:hover {
            background: linear-gradient(45deg, #8A6CFF, #6B48FF);
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(107, 72, 255, 0.3);
        }
        footer {
            background: linear-gradient(45deg, #6B48FF, #8A6CFF);
            color: white;
            padding: 1.5rem 0;
            margin-top: auto;
            box-shadow: 0 -4px 15px rgba(107, 72, 255, 0.2);
        }
        .feature-icon {
            font-size: 2rem;
            color: #6B48FF;
            margin-bottom: 1rem;
        }
    </style>
</head>
<body>
<header>
    <div class="container">
        <h1 class="display-4">Welcome to Ctrl+Cart</h1>
        <p class="lead">Your one-stop shop for all your grocery needs</p>
    </div>
</header>

<nav>
    <div class="container">
        <a href="listProducts"><i class="fas fa-shopping-basket"></i> View Products</a>
        <a href="reviewPage"><i class="fas fa-star"></i> Reviews</a>
    </div>
</nav>

<div class="container">
    <div class="card">
        <h2>Quick Links</h2>
        <div class="row p-4">
            <div class="col-md-6 mb-4">
                <div class="card h-100">
                    <div class="card-body text-center">
                        <i class="fas fa-shopping-basket feature-icon"></i>
                        <h3>Products</h3>
                        <p class="mb-4">Browse our extensive collection of products</p>
                        <a href="listProducts" class="btn btn-primary">View Products</a>
                    </div>
                </div>
            </div>
            <div class="col-md-6 mb-4">
                <div class="card h-100">
                    <div class="card-body text-center">
                        <i class="fas fa-star feature-icon"></i>
                        <h3>Reviews</h3>
                        <p class="mb-4">Share your experience with our products</p>
                        <a href="reviewPage" class="btn btn-primary">View Reviews</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<footer>
    <div class="container text-center">
        <p class="mb-0">&copy; 2024 Ctrl+Cart. All rights reserved.</p>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>