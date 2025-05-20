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
            background: linear-gradient(135deg, #0d3c61 0%, #2176ae 100%);
            min-height: 100vh;
            font-size: 0.96rem;
        }
        .main-card {
            background: #fff;
            border-radius: 24px;
            box-shadow: 0 15px 35px rgba(13, 60, 97, 0.12), 0 5px 15px rgba(0, 0, 0, 0.07);
            padding: 35px 28px;
            margin: 40px auto;
            max-width: 1100px;
            transition: all 0.4s ease;
            backdrop-filter: blur(10px);
        }
        .main-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 20px 40px rgba(0, 150, 136, 0.15), 0 8px 20px rgba(0, 0, 0, 0.1);
        }
        .main-header {
            background: linear-gradient(135deg, #2176ae 0%, #0d3c61 100%);
            color: #fff;
            border-radius: 24px 24px 0 0;
            padding: 30px 0 22px 0;
            text-align: center;
            font-size: 1.3rem;
            font-weight: 600;
            margin-bottom: 0;
            box-shadow: 0 4px 20px rgba(13, 60, 97, 0.18);
            letter-spacing: 0.5px;
        }
        .alert-purple {
            background: linear-gradient(135deg, #2176ae 0%, #0d3c61 100%);
            color: #fff;
            border-radius: 16px;
            border: none;
            margin-bottom: 28px;
            padding: 18px 24px;
            box-shadow: 0 4px 20px rgba(13, 60, 97, 0.12);
            font-weight: 500;
        }
        .form-label {
            font-weight: 600;
            color: #0d3c61;
            margin-bottom: 10px;
            font-size: 0.98rem;
        }
        .form-control, textarea {
            border-radius: 14px;
            border: 2px solid #e3eaf2;
            padding: 14px;
            transition: all 0.3s ease;
            font-size: 0.96rem;
        }
        .form-control:focus, textarea:focus {
            border-color: #2176ae;
            box-shadow: 0 0 0 0.2rem rgba(33, 118, 174, 0.15);
        }
        .btn-primary {
            background: linear-gradient(135deg, #2176ae 0%, #0d3c61 100%);
            border: none;
            border-radius: 14px;
            font-weight: 600;
            padding: 14px 28px;
            transition: all 0.3s ease;
            font-size: 0.98rem;
            letter-spacing: 0.5px;
        }
        .btn-primary:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 20px rgba(33, 118, 174, 0.18);
        }
        .table-purple th {
            background: linear-gradient(135deg, #2176ae 0%, #0d3c61 100%);
            color: #fff;
            border: none;
            padding: 18px;
            font-weight: 600;
            font-size: 0.98rem;
            letter-spacing: 0.5px;
        }
        .table-purple td {
            background: #fff;
            border: none;
            vertical-align: middle;
            padding: 16px;
            color: #0d3c61;
            font-size: 0.96rem;
        }
        .table-purple tr:hover td {
            background: #e3eaf2;
        }
        .star {
            color: #FFD600;
            font-size: 1.2em;
            text-shadow: 0 2px 4px rgba(255, 214, 0, 0.25);
        }
        .action-btn {
            border-radius: 12px;
            font-size: 0.95rem;
            margin-right: 8px;
            padding: 10px 18px;
            transition: all 0.3s ease;
            font-weight: 500;
        }
        .btn-danger {
            background: linear-gradient(135deg, #e65100 0%, #bf360c 100%);
            border: none;
        }
        .btn-danger:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 20px rgba(191, 54, 12, 0.18);
        }
        .clear-btn {
            background: linear-gradient(135deg, #e65100 0%, #bf360c 100%);
            color: #fff;
            border-radius: 14px;
            border: none;
            font-weight: 600;
            padding: 12px 24px;
            transition: all 0.3s ease;
            font-size: 0.98rem;
            letter-spacing: 0.5px;
            display: flex;
            align-items: center;
            gap: 8px;
        }
        .clear-btn i, .action-btn i {
            font-size: 0.9rem !important;
        }
        .table-responsive {
            border-radius: 18px;
            overflow: hidden;
            box-shadow: 0 8px 25px rgba(13, 60, 97, 0.10);
            max-height: 500px;
            overflow-y: auto;
        }
        .table-purple {
            margin-bottom: 0;
        }
        .table-purple td {
            background: #ffffff;
            border: none;
            vertical-align: middle;
            padding: 20px;
            color: #00695C;
            font-size: 1rem;
            line-height: 1.5;
        }
        .table-purple th {
            position: sticky;
            top: 0;
            z-index: 1;
            background: linear-gradient(135deg, #00897B 0%, #00695C 100%);
            color: #fff;
            border: none;
            padding: 20px;
            font-weight: 600;
            font-size: 0.98rem;
            letter-spacing: 0.5px;
        }
        .review-comment {
            max-width: 300px;
            white-space: normal;
            word-wrap: break-word;
        }
        .product-name {
            font-weight: 500;
            color: #00897B;
        }
        .user-name {
            font-weight: 500;
            color: #00695C;
        }
        .date-time {
            font-size: 0.9rem;
            color: #00897B;
        }
        .action-buttons {
            white-space: nowrap;
        }
        .table-purple tr:hover td {
            background: #E0F2F1;
        }
        .main-card.reviews-card {
            margin-top: 30px;
            margin-bottom: 40px;
            min-height: 350px;
            max-width: 1100px;
        }
        .reviews-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 25px;
            padding: 0 10px;
        }
        .reviews-title {
            font-size: 1.3rem;
            font-weight: 600;
            color: #0d3c61;
            margin: 0;
        }
        .clear-btn {
            background: linear-gradient(135deg, #E64A19 0%, #BF360C 100%);
            color: #fff;
            border-radius: 14px;
            border: none;
            font-weight: 600;
            padding: 12px 24px;
            transition: all 0.3s ease;
            font-size: 0.98rem;
            letter-spacing: 0.5px;
            display: flex;
            align-items: center;
            gap: 8px;
        }
        .clear-btn i {
            font-size: 1.1rem;
        }
        .action-btn i, .clear-btn i {
            font-size: 0.9rem !important;
        }
        @media (max-width: 800px) {
            .main-card { 
                padding: 25px 18px;
                margin: 25px 12px;
            }
            .main-header { 
                font-size: 1.1rem;
                padding: 24px 0;
            }
            .action-btn {
                padding: 8px 14px;
                font-size: 0.9rem;
            }
            .table-responsive {
                max-height: 350px;
            }
            .review-comment {
                max-width: 200px;
            }
            .table-purple td, .table-purple th {
                padding: 15px;
            }
            .reviews-title {
                font-size: 1.1rem;
            }
            .clear-btn {
                padding: 10px 20px;
                font-size: 0.95rem;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="main-card mt-5">
            <div class="main-header">Feedback and Review Management</div>
            <% 
            String message = (String) session.getAttribute("message"); 
            String error = (String) session.getAttribute("error");
            if (message != null) { 
            %>
                <div class="alert alert-purple mt-4"> <%= message %> </div>
                <% session.removeAttribute("message"); 
            } 
            if (error != null) { 
            %>
                <div class="alert alert-danger mt-4"> <%= error %> </div>
                <% session.removeAttribute("error"); 
            } 
            %>
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
        <div class="main-card reviews-card">
            <div class="reviews-header">
                <h4 class="reviews-title">All Reviews</h4>
                <form action="deleteAllReviews" method="post" style="display:inline;">
                    <button type="submit" class="clear-btn" onclick="return confirm('Are you sure you want to delete all reviews?')">
                        <i class="fas fa-trash"></i> Clear All Reviews
                    </button>
                </form>
            </div>
            <div class="table-responsive">
                <table class="table table-purple align-middle">
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
                        List<Review> reviews = FileManager.getInstance().readReviews();
                        int i = 1;
                        for(Review review : reviews) {
                            String formattedDate = review.getFormattedSubmissionDate();
                            String[] dateTime = formattedDate.split(" ");
                            String date = dateTime.length > 0 ? dateTime[0] : "";
                            String time = dateTime.length > 1 ? dateTime[1] : "";
                        %>
                        <tr>
                            <td><%= i++ %></td>
                            <td><span class="product-name"><%= review.getProductName() %></span></td>
                            <td><span class="user-name"><%= review.getUserName() %></span></td>
                            <td>
                                <% for(int s = 0; s < review.getRating(); s++) { %>
                                    <span class="star">&#9733;</span>
                                <% } %>
                                <% for(int s = review.getRating(); s < 5; s++) { %>
                                    <span class="star text-secondary">&#9733;</span>
                                <% } %>
                                <span class="ms-1">(<%= review.getRating() %>.0)</span>
                            </td>
                            <td><div class="review-comment"><%= review.getReviewText() %></div></td>
                            <td><span class="date-time"><%= date %></span></td>
                            <td><span class="date-time"><%= time %></span></td>
                            <td class="action-buttons">
                                <a href="${pageContext.request.contextPath}/editReview?id=<%= review.getId() %>" class="btn btn-primary action-btn"><i class="fas fa-edit"></i> Edit</a>
                                <form action="${pageContext.request.contextPath}/deleteReview" method="post" style="display: inline;">
                                    <input type="hidden" name="id" value="<%= review.getId() %>">
                                    <button type="submit" class="btn btn-danger action-btn" onclick="return confirm('Are you sure you want to delete this review?')">
                                        <i class="fas fa-trash"></i> Delete
                                    </button>
                                </form>
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