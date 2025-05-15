<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Update Product</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
  <h2>Update Product</h2>
  <form action="updateProduct" method="post">
    <input type="hidden" name="id" value="${product.id}">
    <div class="mb-3">
      <label for="name" class="form-label">Product Name:</label>
      <input type="text" class="form-control" id="name" name="name" value="${product.name}" required>
    </div>
    <div class="mb-3">
      <label for="price" class="form-label">Price:</label>
      <input type="number" class="form-control" id="price" name="price" step="0.01" value="${product.price}" required>
    </div>
    <div class="mb-3">
      <label for="category" class="form-label">Category:</label>
      <input type="text" class="form-control" id="category" name="category" value="${product.category}" required>
    </div>
    <button type="submit" class="btn btn-primary">Update Product</button>
    <a href="listProducts" class="btn btn-secondary">Cancel</a>
  </form>
</div>
</body>
</html>