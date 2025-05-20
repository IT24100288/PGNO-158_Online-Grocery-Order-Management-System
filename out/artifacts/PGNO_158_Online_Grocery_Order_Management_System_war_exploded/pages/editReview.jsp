<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Review</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #2176ae 0%, #8f6aff 100%);
            min-height: 100vh;
            padding: 30px;
        }
        .form-container {
            max-width: 800px;
            margin: 0 auto;
            background: rgba(255,255,255,0.97);
            padding: 38px 36px 32px 36px;
            border-radius: 22px;
            box-shadow: 0 8px 32px rgba(33, 118, 174, 0.10), 0 2px 8px rgba(143, 106, 255, 0.08);
        }
        .form-label {
            color: #2176ae;
            font-weight: 600;
            font-size: 1.05rem;
            letter-spacing: 0.2px;
        }
        .form-control, textarea, select {
            border-radius: 12px;
            border: 2px solid #e3eaf2;
            padding: 13px 14px;
            font-size: 1rem;
            transition: border-color 0.3s, box-shadow 0.3s;
        }
        .form-control:focus, textarea:focus, select:focus {
            border-color: #8f6aff;
            box-shadow: 0 0 0 0.18rem rgba(143, 106, 255, 0.13);
        }
        .btn-primary {
            background: linear-gradient(90deg, #2176ae 0%, #8f6aff 100%);
            border: none;
            padding: 12px 28px;
            border-radius: 12px;
            font-weight: 600;
            font-size: 1.05rem;
            letter-spacing: 0.5px;
            box-shadow: 0 4px 16px rgba(143, 106, 255, 0.10);
            transition: background 0.3s, box-shadow 0.3s, transform 0.2s;
        }
        .btn-primary:hover {
            background: linear-gradient(90deg, #8f6aff 0%, #2176ae 100%);
            box-shadow: 0 8px 24px rgba(33, 118, 174, 0.13);
            transform: translateY(-2px) scale(1.03);
        }
        .btn-secondary {
            background: linear-gradient(90deg, #bdbdbd 0%, #757575 100%);
            border: none;
            padding: 12px 28px;
            border-radius: 12px;
            font-weight: 600;
            font-size: 1.05rem;
            color: #fff;
            letter-spacing: 0.5px;
            box-shadow: 0 2px 8px rgba(189, 189, 189, 0.10);
            transition: background 0.3s, box-shadow 0.3s, transform 0.2s;
        }
        .btn-secondary:hover {
            background: linear-gradient(90deg, #757575 0%, #bdbdbd 100%);
            color: #fff;
            box-shadow: 0 4px 16px rgba(33, 118, 174, 0.10);
            transform: translateY(-2px) scale(1.03);
        }
        .alert-success {
            background: linear-gradient(90deg, #e0ffe7 0%, #b2f7ef 100%);
            color: #2176ae;
            border: none;
            border-radius: 10px;
            font-weight: 500;
            box-shadow: 0 2px 8px rgba(33, 118, 174, 0.07);
        }
        h2 {
            color: #6a82fb;
            font-weight: 700;
            letter-spacing: 1px;
            margin-bottom: 28px;
            text-shadow: 0 2px 8px rgba(143, 106, 255, 0.08);
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2 class="mb-4" style="color: #7c3aed;">Edit Review</h2>
        
        <% if (request.getAttribute("message") != null) { %>
            <div class="alert alert-success"><%= request.getAttribute("message") %></div>
        <% } %>

        <form action="${pageContext.request.contextPath}/editReview" method="post">
            <input type="hidden" name="id" value="${review.id}">
            
            <div class="mb-3">
                <label for="productName" class="form-label">Product Name</label>
                <input type="text" class="form-control" id="productName" name="productName" 
                       value="${review.productName}" required>
            </div>

            <div class="mb-3">
                <label for="userName" class="form-label">User Name</label>
                <input type="text" class="form-control" id="userName" name="userName" 
                       value="${review.userName}" required>
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
                <a href="${pageContext.request.contextPath}/" class="btn btn-secondary">Cancel</a>
            </div>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html> 