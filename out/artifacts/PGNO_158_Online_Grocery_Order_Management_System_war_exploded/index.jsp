<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model.Review" %>
<%@ page import="com.crtlcart.pgno158_onlinegroceryordermanagementsystem.utils.FileManager" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Feedback and Review Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        body {
            background: #f4f6fb;
        }
        .main-card {
            background: #fff;
            border-radius: 16px;
            box-shadow: 0 4px 24px rgba(80, 72, 229, 0.08);
            padding: 32px 24px;
            margin: 40px auto;
            max-width: 700px;
        }
        .main-header {
            background: #7b5cff;
            color: #fff;
            border-radius: 16px 16px 0 0;
            padding: 24px 0 16px 0;
            text-align: center;
            font-size: 2rem;
            font-weight: 600;
            margin-bottom: 0;
        }
        .alert-purple {
            background: #7b5cff;
            color: #fff;
            border-radius: 8px;
            border: none;
            margin-bottom: 24px;
        }
        .form-label {
            font-weight: 500;
        }
        .form-control, textarea {
            border-radius: 8px;
            border: 1px solid #e0e0e0;
        }
        .btn-primary {
            background: #7b5cff;
            border: none;
            border-radius: 8px;
            font-weight: 500;
        }
        .btn-primary:hover, .btn-danger:hover {
            opacity: 0.9;
        }
        .table-purple th {
            background: #7b5cff;
            color: #fff;
            border: none;
        }
        .table-purple td {
            background: #f8f6ff;
            border: none;
            vertical-align: middle;
        }
        .star {
            color: #ffb400;
            font-size: 1.1em;
        }
        .action-btn {
            border-radius: 6px;
            font-size: 0.95em;
            margin-right: 4px;
        }
        .btn-danger {
            background: #ff5c5c;
            border: none;
        }
        .clear-btn {
            background: #ff5c5c;
            color: #fff;
            border-radius: 8px;
            border: none;
            font-weight: 500;
            float: right;
        }
        @media (max-width: 800px) {
            .main-card { padding: 16px 4px; }
            .main-header { font-size: 1.3rem; }
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="main-card mt-5">
            <div class="main-header">Feedback and Review Management</div>
            <% String message = (String) session.getAttribute("message"); if (message != null) { %>
                <div class="alert alert-purple mt-4"> <%= message %> </div>
                <% session.removeAttribute("message"); } %>
            <form id="reviewForm" action="submitReview" method="post" class="mb-4">
                <div class="mb-3">
                    <label for="productName" class="form-label">Product Name</label>
                    <input type="text" class="form-control" id="productName" name="productName" required placeholder="Enter product name">
                </div>
                <div class="mb-3">
                    <label for="userName" class="form-label">Your Name</label>
                    <input type="text" class="form-control" id="userName" name="userName" required>
                </div>
                <div class="mb-3">
                    <label for="reviewText" class="form-label">Your Review</label>
                    <textarea class="form-control" id="reviewText" name="reviewText" rows="3" required></textarea>
                </div>
                <div class="mb-3">
                    <label for="rating" class="form-label">Rating (0-5)</label>
                    <input type="number" class="form-control" id="rating" name="rating" min="1" max="5" required>
                </div>
                <button type="submit" class="btn btn-primary w-100">Submit Review</button>
            </form>
        </div>
        <div class="main-card mt-4">
            <div class="d-flex justify-content-between align-items-center mb-3">
                <h4 class="mb-0">All Reviews</h4>
                <form action="deleteAllReviews" method="post" style="display:inline;">
                    <button type="submit" class="clear-btn" onclick="return confirm('Are you sure you want to delete all reviews?')">
                        <i class="fas fa-trash"></i> Clear All Reviews
                    </button>
                </form>
            </div>
            <div class="table-responsive">
                <table class="table table-purple align-middle text-center">
                    <thead>
                        <tr>
                            <th>NO</th>
                            <th>Product Name</th>
                            <th>User</th>
                            <th>Rating</th>
                            <th>Comment</th>
                            <th>Date</th>
                            <th>Time</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                        List<Review> reviews = FileManager.readReviews();
                        int i = 1;
                        for(Review review : reviews) {
                            String[] dateTime = review.getSubmissionDate().split(" ");
                            String date = dateTime.length > 0 ? dateTime[0] : "";
                            String time = dateTime.length > 1 ? dateTime[1] : "";
                        %>
                        <tr>
                            <td><%= i++ %></td>
                            <td><%= review.getProductName() %></td>
                            <td><%= review.getUserName() %></td>
                            <td>
                                <% for(int s = 0; s < review.getRating(); s++) { %>
                                    <span class="star">&#9733;</span>
                                <% } %>
                                <% for(int s = review.getRating(); s < 5; s++) { %>
                                    <span class="star text-secondary">&#9733;</span>
                                <% } %>
                                <span class="ms-1">(<%= review.getRating() %>.0)</span>
                            </td>
                            <td><%= review.getReviewText() %></td>
                            <td><%= date %></td>
                            <td><%= time %></td>
                            <td>
                                <a href="editReview?id=<%= review.getId() %>" class="btn btn-primary action-btn"><i class="fas fa-edit"></i> Edit</a>
                                <a href="deleteReview?id=<%= review.getId() %>" class="btn btn-danger action-btn"><i class="fas fa-trash"></i> Delete</a>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>