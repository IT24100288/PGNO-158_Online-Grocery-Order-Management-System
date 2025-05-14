<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        :root {
            --primary: #ef233c;
            --secondary: #c71f36;
            --accent: #f4b41a;
            --light: #ffffff;
            --dark: #000000;
        }

        body {
            background: linear-gradient(135deg, #ffffff 0%, #f2f2f2 100%);
            min-height: 100vh;
            font-family: 'Segoe UI', Roboto, 'Helvetica Neue', sans-serif;
        }

        .navbar {
            background-color: var(--primary);
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        .navbar-brand {
            color: var(--light) !important;
            font-weight: 700;
        }

        .nav-link {
            color: var(--light) !important;
            font-weight: 500;
        }

        .nav-link:hover {
            color: var(--accent) !important;
        }

        .container {
            margin-top: 30px;
        }

        .card {
            border: none;
            border-radius: 12px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s;
        }

        .card:hover {
            transform: translateY(-5px);
        }

        .card-header {
            background-color: var(--primary);
            color: var(--light);
            text-align: center;
            padding: 20px;
            border-radius: 12px 12px 0 0;
        }

        .card-body {
            padding: 30px;
            text-align: center;
        }

        .btn-primary {
            background-color: var(--primary);
            border-color: var(--primary);
            padding: 12px 20px;
            font-weight: 600;
            border-radius: 8px;
            transition: all 0.3s;
        }

        .btn-primary:hover, .btn-primary:focus {
            background-color: var(--secondary);
            border-color: var(--secondary);
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(239, 35, 60, 0.3);
        }

        .welcome-message {
            margin-bottom: 30px;
            font-size: 24px;
            color: var(--dark);
        }
    </style>
</head>
<body>
<%
    // Check if the user is logged in
    String admin = (String) session.getAttribute("admin");
    if (admin == null) {
        // If not logged in, redirect to adminLogin.jsp
        response.sendRedirect("adminLogin.jsp");
        return;
    }
%>

<nav class="navbar navbar-expand-lg">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Admin Dashboard</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="logout"><i class="fas fa-sign-out-alt me-2"></i>Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div class="welcome-message">
        Welcome, <%= admin %>!
    </div>
    <div class="row">
        <div class="col-md-4">
            <div class="card">
                <div class="card-header">
                    <h5>Manage Users</h5>
                </div>
                <div class="card-body">
                    <p>View and manage user accounts.</p>
                    <a href="manageUsers" class="btn btn-primary"><i class="fas fa-users me-2"></i>Go to Users</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card">
                <div class="card-header">
                    <h5>Manage Products</h5>
                </div>
                <div class="card-body">
                    <p>Add, update, or remove products.</p>
                    <a href="manageProducts" class="btn btn-primary"><i class="fas fa-box me-2"></i>Go to Products</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card">
                <div class="card-header">
                    <h5>Manage Orders</h5>
                </div>
                <div class="card-body">
                    <p>Track and manage customer orders.</p>
                    <a href="manageOrders" class="btn btn-primary"><i class="fas fa-shopping-cart me-2"></i>Go to Orders</a>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>