<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://jakarta.apache.org/jsp/jstl/core" %>
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

    <div class="form-centered">
        <c:if test="${not empty message}">
            <div class="alert alert-success">${message}</div>
        </c:if>
        <form action="submitReview" method="post" onsubmit="return validateForm(this)">
            <label for="productName">Product Name</label>
            <input type="text" id="productName" name="productName" list="productList" required>
            <input type="hidden" id="productId" name="productId">
            <input type="hidden" id="isNewProduct" name="isNewProduct" value="false">
            <datalist id="productList">
                <c:forEach var="product" items="${products}">
                    <option value="${product.name}" data-id="${product.id}">
                </c:forEach>
            </datalist>

            <label for="userName">Your Name</label>
            <input type="text" id="userName" name="userName" required>

            <label for="comment">Your Review</label>
            <textarea id="comment" name="comment" rows="4" required></textarea>

            <label for="rating">Rating (0-5)</label>
            <input type="number" id="rating" name="rating" min="0" max="5" step="0.1" required>

            <button type="submit">Submit Review</button>
        </form>
    </div>

    <div class="form-centered" style="margin-top: 2rem;">
        <h2 style="color: #7c3aed; text-align:center; margin-bottom: 1.5rem;">All Reviews</h2>
        <div style="text-align: right; margin-bottom: 1rem;">
            <form action="deleteAllReviews" method="post" style="display: inline;">
                <button type="submit" class="btn" style="background: linear-gradient(90deg, #8f6aff 0%, #6a82fb 100%); color: #fff; border-radius: 24px; padding: 0.6rem 1.5rem; font-weight: 600;" onclick="return confirm('Are you sure you want to delete all reviews?')">Delete All Reviews</button>
            </form>
        </div>
        <div style="overflow-x:auto;">
            <table class="table" style="background: #f8f9ff; border-radius: 16px; overflow: hidden; box-shadow: 0 2px 8px rgba(138, 43, 226, 0.06);">
                <thead style="background: linear-gradient(90deg, #8f6aff 0%, #6a82fb 100%); color: #fff;">
                    <tr>
                        <th>Product</th>
                        <th>User</th>
                        <th>Rating</th>
                        <th>Comment</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="review" items="${reviews}">
                        <tr style="background: #fff; border-bottom: 1px solid #ececff;">
                            <td>${review.productName}</td>
                            <td>${review.userName}</td>
                            <td>${review.rating}</td>
                            <td>${review.comment}</td>
                            <td>${review.status}</td>
                            <td>
                                <form action="deleteReview" method="post" style="display: inline;">
                                    <input type="hidden" name="reviewId" value="${review.id}">
                                    <button type="submit" class="btn" style="background: linear-gradient(90deg, #ff6b6b 0%, #ff9a8b 100%); color: #fff; border-radius: 16px; padding: 0.3rem 1rem; font-size: 0.95rem; font-weight: 500;" onclick="return confirm('Are you sure you want to delete this review?')">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <footer>
        <div class="container">
            <p class="text-center mb-0">&copy; 2025 Ctrl+Cart. All rights reserved.</p>
        </div>
    </footer>

    <script>
        function validateForm(form) {
            const rating = parseFloat(form.rating.value);
            if (rating < 0 || rating > 5) {
                alert('Rating must be between 0 and 5');
                return false;
            }
            return true;
        }

        document.getElementById('productName').addEventListener('change', function() {
            const productList = document.getElementById('productList');
            const options = productList.getElementsByTagName('option');
            const selectedValue = this.value;
            
            for (let option of options) {
                if (option.value === selectedValue) {
                    document.getElementById('productId').value = option.getAttribute('data-id');
                    document.getElementById('isNewProduct').value = 'false';
                    return;
                }
            }
            
            document.getElementById('productId').value = '';
            document.getElementById('isNewProduct').value = 'true';
        });
    </script>
</body>
</html> 