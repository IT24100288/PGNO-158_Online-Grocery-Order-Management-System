<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Review</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: #f8f9ff;
            padding: 20px;
        }
        .form-container {
            max-width: 800px;
            margin: 0 auto;
            background: white;
            padding: 30px;
            border-radius: 16px;
            box-shadow: 0 2px 8px rgba(138, 43, 226, 0.06);
        }
        .form-label {
            color: #6B48FF;
            font-weight: 500;
        }
        .form-control:focus {
            border-color: #6B48FF;
            box-shadow: 0 0 0 0.2rem rgba(107, 72, 255, 0.25);
        }
        .btn-primary {
            background: linear-gradient(90deg, #8f6aff 0%, #6a82fb 100%);
            border: none;
            padding: 10px 20px;
            border-radius: 8px;
        }
        .btn-secondary {
            background: #6c757d;
            border: none;
            padding: 10px 20px;
            border-radius: 8px;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2 class="mb-4" style="color: #7c3aed;">Edit Review</h2>
        
        <% if (request.getAttribute("message") != null) { %>
            <div class="alert alert-success"><%= request.getAttribute("message") %></div>
        <% } %>

        <form action="editReview" method="post">
            <input type="hidden" name="id" value="${review.id}">
            
            <div class="mb-3">
                <label for="productName" class="form-label">Product Name</label>
                <input type="text" class="form-control" id="productName" name="productName" 
                       value="${review.productName}" required>
            </div>

            <div class="mb-3">
                <label for="reviewText" class="form-label">Review Text</label>
                <textarea class="form-control" id="reviewText" name="reviewText" 
                          rows="4" required>${review.reviewText}</textarea>
            </div>

            <div class="mb-3">
                <label for="rating" class="form-label">Rating (1-5)</label>
                <input type="number" class="form-control" id="rating" name="rating" 
                       min="1" max="5" value="${review.rating}" required>
            </div>

            <div class="mb-3">
                <label for="status" class="form-label">Status</label>
                <select class="form-control" id="status" name="status">
                    <option value="PENDING" ${review.status == 'PENDING' ? 'selected' : ''}>Pending</option>
                    <option value="APPROVED" ${review.status == 'APPROVED' ? 'selected' : ''}>Approved</option>
                    <option value="REJECTED" ${review.status == 'REJECTED' ? 'selected' : ''}>Rejected</option>
                </select>
            </div>

            <div class="d-flex gap-2">
                <button type="submit" class="btn btn-primary">Update Review</button>
                <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-secondary">Cancel</a>
            </div>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html> 