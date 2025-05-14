<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Add Product</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
  <h2>Add New Product</h2>
  <form action="addProduct" method="post">
    <div class="mb-3">
      <label for="name" class="form-label">Product Name:</label>
      <input type="text" class="form-control" id="name" name="name" required>
    </div>
    <div class="mb-3">
      <label for="price" class="form-label">Price:</label>
      <input type="number" class="form-control" id="price" name="price" step="0.01" required>
    </div>
    <div class="mb-3">
      <label for="category" class="form-label">Category:</label>
      <input type="text" class="form-control" id="category" name="category" required>
    </div>
    <button type="submit" class="btn btn-primary">Add Product</button>
    <a href="listProducts" class="btn btn-secondary">Cancel</a>
  </form>
</div>
</body>
</html>