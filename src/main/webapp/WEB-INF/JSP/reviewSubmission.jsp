<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
  <title>Feedback and Review Management - Ctrl+Cart</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
  <link href="css/style.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
  <style>
    body {
      background: linear-gradient(135deg, #E0EAFC 0%, #CFDEF3 100%);
    }
    .container {
      max-width: 900px;
      margin: 0 auto;
      padding: 0 20px;
    }
    header {
      background: linear-gradient(45deg, #6B48FF, #8A6CFF);
      color: white;
      padding: 25px 0;
      margin-bottom: 25px;
      box-shadow: 0 4px 15px rgba(107, 72, 255, 0.2);
    }
    header h1 {
      font-size: 2.2rem;
      margin: 0;
      text-shadow: 2px 2px 4px rgba(0,0,0,0.1);
    }
    nav {
      background: linear-gradient(45deg, #8A6CFF, #6B48FF);
      padding: 12px 0;
      margin-bottom: 25px;
      box-shadow: 0 4px 15px rgba(107, 72, 255, 0.2);
    }
    nav a {
      color: white;
      text-decoration: none;
      margin-right: 20px;
      padding: 8px 15px;
      border-radius: 20px;
      transition: all 0.3s ease;
      background: rgba(255,255,255,0.1);
    }
    nav a:hover {
      background-color: rgba(255,255,255,0.2);
      color: white;
      transform: translateY(-2px);
      box-shadow: 0 4px 10px rgba(0,0,0,0.1);
    }
    .card {
      background-color: white;
      border-radius: 15px;
      box-shadow: 0 8px 20px rgba(107, 72, 255, 0.1);
      margin-bottom: 25px;
      border: none;
      transition: transform 0.3s ease;
      max-width: 100%;
    }
    .card:hover {
      transform: translateY(-5px);
      box-shadow: 0 12px 25px rgba(107, 72, 255, 0.15);
    }
    .card h2 {
      color: #6B48FF;
      padding: 20px;
      margin: 0;
      border-bottom: 2px solid #f0f0f0;
      font-weight: 600;
    }
    .table {
      font-size: 0.95rem;
      border-radius: 10px;
      overflow: hidden;
    }
    .table thead th {
      background: linear-gradient(45deg, #6B48FF, #8A6CFF);
      color: white;
      border: none;
      padding: 12px;
      font-size: 0.95rem;
      font-weight: 500;
    }
    .table tbody tr:hover {
      background-color: #f8f9fa;
    }
    .table tbody td {
      padding: 10px 12px;
      vertical-align: middle;
    }
    .btn-primary {
      background: linear-gradient(45deg, #6B48FF, #8A6CFF);
      border: none;
      padding: 10px 25px;
      border-radius: 25px;
      transition: all 0.3s ease;
    }
    .btn-primary:hover {
      background: linear-gradient(45deg, #8A6CFF, #6B48FF);
      transform: translateY(-2px);
      box-shadow: 0 5px 15px rgba(107, 72, 255, 0.3);
    }
    .btn-warning {
      background: linear-gradient(45deg, #FF9A8B, #FF6B6B);
      border: none;
      transition: all 0.3s ease;
    }
    .btn-warning:hover {
      background: linear-gradient(45deg, #FF6B6B, #FF9A8B);
      transform: translateY(-2px);
      box-shadow: 0 5px 15px rgba(255,107,107,0.3);
    }
    .btn-danger {
      background: linear-gradient(45deg, #FF6B6B, #FF416C);
      border: none;
      transition: all 0.3s ease;
    }
    .btn-danger:hover {
      background: linear-gradient(45deg, #FF416C, #FF6B6B);
      transform: translateY(-2px);
      box-shadow: 0 5px 15px rgba(255,65,108,0.3);
    }
    .modal-content {
      border-radius: 15px;
      border: none;
      box-shadow: 0 10px 30px rgba(107, 72, 255, 0.15);
    }
    .modal-header {
      background: linear-gradient(45deg, #6B48FF, #8A6CFF);
      color: white;
      border-radius: 15px 15px 0 0;
      padding: 20px;
    }
    .modal-body {
      padding: 25px;
    }
    .modal-footer {
      border-top: 1px solid #f0f0f0;
      padding: 20px;
    }
    .rating .star.filled {
      color: #FF9A8B;
    }
    footer {
      background: linear-gradient(45deg, #6B48FF, #8A6CFF);
      color: white;
      padding: 25px 0;
      margin-top: 30px;
      box-shadow: 0 -4px 15px rgba(107, 72, 255, 0.2);
    }
    .alert-success {
      background: linear-gradient(45deg, #6B48FF, #8A6CFF);
      border: none;
      color: white;
      border-radius: 10px;
      padding: 15px 20px;
      margin-bottom: 20px;
      box-shadow: 0 4px 15px rgba(107, 72, 255, 0.1);
    }
    .form-control {
      border-radius: 10px;
      padding: 12px;
      border: 2px solid #f0f0f0;
      transition: all 0.3s ease;
    }
    .form-control:focus {
      border-color: #6B48FF;
      box-shadow: 0 0 0 0.2rem rgba(107, 72, 255, 0.25);
    }
    .form-label {
      color: #6B48FF;
      font-weight: 500;
    }
    .btn-sm {
      padding: 4px 12px;
      font-size: 0.85rem;
    }
    .modal-dialog {
      max-width: 600px;
    }
    @media (max-width: 768px) {
      .container {
        max-width: 100%;
        padding: 0 15px;
      }
      .table {
        font-size: 0.9rem;
      }
      .btn-sm {
        padding: 3px 10px;
        font-size: 0.8rem;
      }
    }
  </style>
</head>
<body>
<header>
  <div class="container">
    <h1>Feedback and Review Management</h1>
  </div>
</header>

<nav>
  <div class="container">
    <c:if test="${sessionScope.isAdmin}">
      <a href="reviewModeration.jsp">Moderate Reviews</a>
    </c:if>
  </div>
</nav>

<div class="container">
  <div class="card">
    <c:if test="${not empty message}">
      <div class="alert alert-success">${message}</div>
    </c:if>

    <form action="submitReview" method="post" onsubmit="return validateForm(this)">
      <div class="mb-3">
        <label for="productName" class="form-label">Product Name</label>
        <input type="text" class="form-control" id="productName" name="productName" list="productList" required>
        <input type="hidden" id="productId" name="productId">
        <input type="hidden" id="isNewProduct" name="isNewProduct" value="false">
        <datalist id="productList">
          <c:forEach var="product" items="${products}">
          <option value="${product.name}" data-id="${product.id}">
            </c:forEach>
        </datalist>
      </div>
      <div class="mb-3">
        <label for="userName" class="form-label">Your Name</label>
        <input type="text" class="form-control" id="userName" name="userName" required>
      </div>
      <div class="mb-3">
        <label for="comment" class="form-label">Your Review</label>
        <textarea class="form-control" id="comment" name="comment" rows="4" required></textarea>
      </div>
      <div class="mb-3">
        <label for="rating" class="form-label">Rating (0-5)</label>
        <input type="number" class="form-control" id="rating" name="rating" min="0" max="5" step="0.1" required>
      </div>
      <button type="submit" class="btn btn-primary">Submit Review</button>
    </form>
  </div>

  <div class="card mt-4">
    <h2 style="color: black;">All Reviews</h2>
    <div class="text-end mb-3">
      <form action="deleteAllReviews" method="post" style="display: inline;">
        <button type="submit" class="btn btn-danger"
                onclick="return confirm('Are you sure you want to delete all reviews? This action cannot be undone.')"
                style="background: linear-gradient(45deg, #FF6B6B, #FF416C); border: none;">
          <i class="fas fa-trash-alt"></i> Clear All Reviews
        </button>
      </form>
    </div>
    <div class="table-responsive">
      <table class="table">
        <thead>
        <tr>
          <th>NO</th>
          <th>Product Name</th>
          <th>User</th>
          <th>Rating</th>
          <th>Comment</th>
          <th>Date</th>
          <th>Time</th>
          <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="review" items="${reviews}">
          <tr>
            <td>${review.id}</td>
            <td>
              <c:forEach var="product" items="${products}">
                <c:if test="${product.id == review.productId}">
                  ${product.name}
                </c:if>
              </c:forEach>
            </td>
            <td>${review.userName}</td>
            <td>
              <div class="rating">
                <c:forEach begin="1" end="5" var="i">
                  <span class="star ${i <= review.rating ? 'filled' : ''}">★</span>
                </c:forEach>
                (${review.rating})
              </div>
            </td>
            <td>${review.comment}</td>
            <td>${review.submissionDate}</td>
            <td>${review.submissionTime}</td>
            <td>
              <button type="button" class="btn btn-warning btn-sm"
                      data-bs-toggle="modal"
                      data-bs-target="#editModal${review.id}"
                      style="background-color: #FF9800; color: white; border: none; padding: 5px 15px; border-radius: 4px;">
                <i class="fas fa-edit"></i> Edit
              </button>
              <form action="deleteReview" method="post" style="display: inline;">
                <input type="hidden" name="id" value="${review.id}">
                <button type="submit" class="btn btn-danger btn-sm"
                        onclick="return confirm('Are you sure you want to delete this review?')"
                        style="color: white; border: none; padding: 5px 15px; border-radius: 4px;">
                  <i class="fas fa-trash"></i> Delete
                </button>
              </form>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</div>

<!-- Edit Modals -->
<c:forEach var="review" items="${reviews}">
  <div class="modal fade" id="editModal${review.id}" tabindex="-1"
       aria-labelledby="editModalLabel${review.id}" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header" style="background-color: #FF9800; color: white;">
          <h5 class="modal-title" id="editModalLabel${review.id}">
            <i class="fas fa-edit"></i> Edit Review
          </h5>
          <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <form action="editReview" method="post" id="editForm${review.id}" onsubmit="return validateForm(this)">
          <div class="modal-body">
            <input type="hidden" name="id" value="${review.id}">
            <div class="mb-3">
              <label for="productName${review.id}" class="form-label">Product Name</label>
              <input type="text" class="form-control" id="productName${review.id}"
                     name="productName" list="productList${review.id}" required
                     value="<c:forEach var='product' items='${products}'><c:if test='${product.id == review.productId}'>${product.name}</c:if></c:forEach>">
              <input type="hidden" id="productId${review.id}" name="productId" value="${review.productId}">
              <input type="hidden" id="isNewProduct${review.id}" name="isNewProduct" value="false">
              <datalist id="productList${review.id}">
                <c:forEach var="product" items="${products}">
                <option value="${product.name}" data-id="${product.id}">
                  </c:forEach>
              </datalist>
            </div>
            <div class="mb-3">
              <label for="userName${review.id}" class="form-label">Your Name</label>
              <input type="text" class="form-control" id="userName${review.id}"
                     name="userName" value="${review.userName}" required>
            </div>
            <div class="mb-3">
              <label for="comment${review.id}" class="form-label">Your Review</label>
              <textarea class="form-control" id="comment${review.id}"
                        name="comment" required rows="4">${review.comment}</textarea>
            </div>
            <div class="mb-3">
              <label for="rating${review.id}" class="form-label">Rating (0-5)</label>
              <input type="number" class="form-control" id="rating${review.id}"
                     name="rating" value="${review.rating}" required min="0" max="5" step="0.1">
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
              <i class="fas fa-times"></i> Cancel
            </button>
            <button type="submit" class="btn btn-warning" style="background-color: #FF9800; color: white; border: none;">
              <i class="fas fa-save"></i> Save Changes
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</c:forEach>

<footer>
  <div class="container">
    <p>&copy; 2024 Ctrl+Cart. All rights reserved.</p>
  </div>
</footer>

<script>
  function validateForm(form) {
    var rating = parseFloat(form.rating.value);
    if (rating < 0 || rating > 5) {
      alert("Rating must be between 0 and 5");
      return false;
    }

    return true;
  }

  // Function to update product ID when product name is selected or typed
  function updateProductId(input) {
    var datalist = document.getElementById(input.getAttribute('list'));
    var options = datalist.getElementsByTagName('option');
    var productName = input.value;
    var productId = '';
    var isNewProduct = true;

    // Find matching product ID
    for (var i = 0; i < options.length; i++) {
      if (options[i].value === productName) {
        productId = options[i].getAttribute('data-id');
        isNewProduct = false;
        break;
      }
    }

    // Update hidden inputs
    var hiddenInput = input.parentElement.querySelector('input[name="productId"]');
    var newProductInput = input.parentElement.querySelector('input[name="isNewProduct"]');
    hiddenInput.value = productId;
    newProductInput.value = isNewProduct;
  }

  // Add event listeners when page loads
  document.addEventListener('DOMContentLoaded', function() {
    // Add event listeners to all product name inputs
    var productInputs = document.querySelectorAll('input[list^="productList"]');
    productInputs.forEach(function(input) {
      input.addEventListener('change', function() {
        updateProductId(this);
      });
      input.addEventListener('input', function() {
        updateProductId(this);
      });
      // Initialize product ID for existing values
      updateProductId(input);
    });
  });
</script>
</body>
</html>