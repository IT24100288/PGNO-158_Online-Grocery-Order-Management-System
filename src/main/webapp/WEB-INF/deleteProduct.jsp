<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Delete Product - Ctrl+Cart</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;700&family=Montserrat:wght@700&display=swap" rel="stylesheet">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
  <!-- Custom CSS -->
  <style>
    :root {
      --primary: #ef233c;
      --secondary: #f4b41a;
      --dark: #000000;
      --light: #ffffff;
      --primary-transparent: rgba(239, 35, 60, 0.1);
      --secondary-transparent: rgba(244, 180, 26, 0.1);
      --shadow: 0 8px 30px rgba(0, 0, 0, 0.15);
      --theme: dark;
    }

    body {
      background: var(--dark);
      color: var(--light);
      font-family: 'Poppins', sans-serif;
      margin: 0;
      height: 100vh;
      position: relative;
      transition: background 0.3s;
    }

    body.light-theme {
      background: var(--light);
      color: var(--dark);
    }

    .splash-screen {
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background: var(--dark);
      display: flex;
      justify-content: center;
      align-items: center;
      z-index: 2000;
      animation: fadeOut 1.5s forwards 1.5s;
    }

    .splash-screen h1 {
      font-family: 'Montserrat', sans-serif;
      font-size: 4em;
      color: var(--light);
      position: relative;
      animation: logoReveal 2s ease-out;
    }

    .splash-screen h1::after {
      content: "";
      position: absolute;
      bottom: -10px;
      left: 0;
      width: 100%;
      height: 4px;
      background: linear-gradient(90deg, var(--primary), var(--secondary));
    }

    .header {
      background: var(--dark);
      padding: 15px 30px;
      position: fixed;
      width: 100%;
      top: 0;
      z-index: 1000;
      box-shadow: var(--shadow);
      transition: background 0.3s;
    }

    .light-theme .header {
      background: var(--light);
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
    }

    .header-content {
      display: flex;
      justify-content: space-between;
      align-items: center;
      max-width: 1200px;
      margin: 0 auto;
    }

    .header h1 {
      margin: 0;
      font-family: 'Montserrat', sans-serif;
      font-size: 28px;
      color: var(--light);
      display: flex;
      align-items: center;
    }

    .light-theme .header h1 {
      color: var(--dark);
    }

    .cart-icon {
      margin-left: 10px;
      color: var(--primary);
      animation: bounce 2s infinite;
    }

    .content {
      position: relative;
      z-index: 100;
      display: none;
      animation: slideIn 0.5s ease-out forwards;
      padding: 20px;
    }

    .card {
      background: transparent;
      border: 2px solid var(--primary-transparent);
      border-radius: 20px;
      box-shadow: var(--shadow);
      padding: 30px;
      width: 100%;
      max-width: 450px;
      margin-top: 100px;
      backdrop-filter: blur(10px);
      transition: transform 0.3s, box-shadow 0.3s;
    }

    .light-theme .card {
      background: rgba(255, 255, 255, 0.9);
      border: 2px solid rgba(239, 35, 60, 0.1);
    }

    .card:hover {
      transform: translateY(-10px);
      box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
    }

    .card-title {
      text-align: center;
      margin-bottom: 30px;
      color: var(--light);
      font-family: 'Montserrat', sans-serif;
      font-size: 1.8em;
      font-weight: 700;
      position: relative;
      display: inline-block;
      width: 100%;
    }

    .light-theme .card-title {
      color: var(--dark);
    }

    .card-title::after {
      content: "";
      position: absolute;
      bottom: -10px;
      left: 25%;
      width: 50%;
      height: 3px;
      background: linear-gradient(90deg, var(--primary), var(--secondary));
      border-radius: 3px;
    }

    .confirmation-text {
      text-align: center;
      margin-bottom: 30px;
      font-size: 1.1em;
      color: var(--light);
    }

    .light-theme .confirmation-text {
      color: var(--dark);
    }

    .btn-primary {
      background: var(--primary);
      border: none;
      border-radius: 12px;
      width: 100%;
      padding: 12px;
      font-weight: bold;
      text-transform: uppercase;
      letter-spacing: 1px;
      position: relative;
      overflow: hidden;
      transition: transform 0.3s, box-shadow 0.3s;
      z-index: 1;
    }

    .btn-primary::before {
      content: '';
      position: absolute;
      top: 0;
      left: -100%;
      width: 100%;
      height: 100%;
      background: var(--secondary);
      transition: left 0.3s ease;
      z-index: -1;
    }

    .btn-primary:hover {
      transform: translateY(-5px);
      box-shadow: 0 10px 20px rgba(239, 35, 60, 0.3);
      background: var(--primary);
    }

    .btn-primary:hover::before {
      left: 0;
    }

    .text-muted {
      margin-top: 20px;
      text-align: center;
      color: rgba(255, 255, 255, 0.7) !important;
    }

    .light-theme .text-muted {
      color: rgba(0, 0, 0, 0.7) !important;
    }

    .text-muted a {
      color: var(--secondary);
      text-decoration: none;
      font-weight: 500;
      transition: color 0.3s;
      position: relative;
    }

    .text-muted a::after {
      content: '';
      position: absolute;
      width: 0;
      height: 2px;
      bottom: -2px;
      left: 0;
      background: var(--primary);
      transition: width 0.3s;
    }

    .text-muted a:hover {
      color: var(--primary);
    }

    .text-muted a:hover::after {
      width: 100%;
    }

    .theme-switch {
      position: relative;
      width: 60px;
      height: 30px;
      background: rgba(255, 255, 255, 0.2);
      border-radius: 15px;
      cursor: pointer;
      transition: background 0.4s;
      display: flex;
      align-items: center;
      justify-content: space-between;
      padding: 0 5px;
    }

    .light-theme .theme-switch {
      background: rgba(0, 0, 0, 0.2);
    }

    .theme-switch .sun {
      color: var(--secondary);
      font-size: 14px;
      z-index: 2;
    }

    .theme-switch .moon {
      color: var(--light);
      font-size: 14px;
      z-index: 2;
    }

    .theme-switch::after {
      content: '';
      width: 22px;
      height: 22px;
      background: var(--light);
      border-radius: 50%;
      position: absolute;
      left: 4px;
      transition: transform 0.4s, background 0.4s;
    }

    .light-theme .theme-switch::after {
      transform: translateX(30px);
      background: var(--dark);
    }

    .floating-elements {
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      pointer-events: none;
      z-index: 0;
      overflow: hidden;
    }

    .floating-element {
      position: absolute;
      width: 100px;
      height: 100px;
      background: linear-gradient(45deg, var(--primary-transparent), var(--secondary-transparent));
      border-radius: 50%;
      filter: blur(30px);
      opacity: 0.5;
      animation: float 15s infinite linear;
    }

    .floating-element:nth-child(1) {
      top: 20%;
      left: 10%;
      width: 150px;
      height: 150px;
      animation-duration: 25s;
    }

    .floating-element:nth-child(2) {
      top: 70%;
      left: 80%;
      width: 200px;
      height: 200px;
      animation-duration: 30s;
      animation-delay: 2s;
    }

    .floating-element:nth-child(3) {
      top: 40%;
      left: 40%;
      width: 120px;
      height: 120px;
      animation-duration: 20s;
      animation-delay: 5s;
    }

    .delete-icon {
      font-size: 3em;
      color: var(--primary);
      display: block;
      text-align: center;
      margin-bottom: 20px;
      opacity: 0.8;
    }

    @keyframes logoReveal {
      0% { opacity: 0; transform: scale(0.8); }
      50% { opacity: 1; transform: scale(1.1); }
      100% { opacity: 1; transform: scale(1); }
    }

    @keyframes fadeOut {
      from { opacity: 1; }
      to { opacity: 0; display: none; }
    }

    @keyframes slideIn {
      from { opacity: 0; transform: translateY(30px); }
      to { opacity: 1; transform: translateY(0); }
    }

    @keyframes bounce {
      0%, 100% { transform: translateY(0); }
      50% { transform: translateY(-5px); }
    }

    @keyframes float {
      0% { transform: translate(0, 0) rotate(0deg); }
      50% { transform: translate(100px, 100px) rotate(180deg); }
      100% { transform: translate(0, 0) rotate(360deg); }
    }
  </style>
</head>
<body>
<!-- Floating Background Elements -->
<div class="floating-elements">
  <div class="floating-element"></div>
  <div class="floating-element"></div>
  <div class="floating-element"></div>
</div>

<!-- Splash Screen -->
<div class="splash-screen">
  <h1>Ctrl+Cart</h1>
</div>

<!-- Header -->
<div class="header">
  <div class="header-content">
    <h1>Ctrl+Cart <span class="cart-icon"><i class="fas fa-shopping-cart"></i></span></h1>
    <div class="theme-switch" onclick="toggleTheme()">
      <i class="sun fas fa-sun"></i>
      <i class="moon fas fa-moon"></i>
    </div>
  </div>
</div>

<!-- Main Content -->
<div class="content">
  <div class="card">
    <i class="fas fa-trash-alt delete-icon"></i>
    <h5 class="card-title">Delete Product</h5>
    <p class="confirmation-text">Are you sure you want to delete this product?</p>
    <form action="deleteProduct" method="get">
      <input type="hidden" name="id" value="${param.id}">
      <button type="submit" class="btn btn-primary">Confirm Delete</button>
    </form>
    <p class="text-muted mt-4">
      <a href="listProducts">Cancel and Return</a>
    </p>
  </div>
</div>

<!-- Bootstrap JS and Custom Script -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdlZxGxv/4e1L2o1" crossorigin="anonymous"></script>
<script>
  window.onload = function() {
    setTimeout(() => {
      document.querySelector('.splash-screen').style.display = 'none';
      document.querySelector('.content').style.display = 'flex';
      document.querySelector('.content').style.justifyContent = 'center';
      document.querySelector('.content').style.alignItems = 'center';
      document.querySelector('.content').style.height = 'calc(100vh - 60px)';
    }, 3000);
  };

  function toggleTheme() {
    const body = document.body;
    body.classList.toggle('light-theme');
  }
</script>
</body>
</html>