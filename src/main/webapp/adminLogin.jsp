<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Admin Login</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
  <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<div class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-md-6">
      <div class="card">
        <div class="card-body">
          <div class="logo-container">
            <div class="logo-circle">
              <i class="fas fa-user-shield"></i>
            </div>
          </div>
          <h2 class="text-center mb-4">Admin Login</h2>
          <% if (request.getAttribute("error") != null) { %>
          <div class="alert alert-danger" role="alert">
            <i class="fas fa-exclamation-circle me-2"></i>
            <%= request.getAttribute("error") %>
          </div>
          <% } %>
          <form action="/GroceryAdmin/adminLogin" method="post">
            <div class="mb-4">
              <label for="username" class="form-label">Username</label>
              <div class="input-group">
                <i class="fas fa-user input-icon"></i>
                <input type="text" class="form-control with-icon" id="username" name="username" placeholder="Enter your username" required>
              </div>
            </div>
            <div class="mb-4">
              <label for="password" class="form-label">Password</label>
              <div class="input-group">
                <i class="fas fa-lock input-icon"></i>
                <input type="password" class="form-control with-icon" id="password" name="password" placeholder="Enter your password" required>
              </div>
            </div>
            <div class="mb-3 form-check">
              <input type="checkbox" class="form-check-input" id="rememberMe" name="rememberMe">
              <label class="form-check-label" for="rememberMe">Remember me</label>
            </div>
            <button type="submit" class="btn btn-primary w-100">
              <i class="fas fa-sign-in-alt me-2"></i>Login
            </button>
          </form>
        </div>
      </div>
      <div class="text-center mt-3 text-muted">
        <small>© 2025 Your Company. All rights reserved.</small>
      </div>
    </div>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>