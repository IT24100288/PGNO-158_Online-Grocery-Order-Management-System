<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Product List</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
  <h2>Product List</h2>
  <form action="listProducts" method="get" class="mb-3">
    <label for="sortBy" class="form-label">Sort By:</label>
    <select name="sortBy" id="sortBy" class="form-select d-inline w-auto">
      <option value="category">Category</option>
      <option value="price">Price</option>
    </select>
    <button type="submit" class="btn btn-primary">Sort</button>
  </form>
  <table class="table table-striped">
    <thead>
    <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Price</th>
      <th>Category</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="product" items="${products}">
      <tr>
        <td>${product.id}</td>
        <td>${product.name}</td>
        <td>${product.price}</td>
        <td>${product.category}</td>
        <td>
          <a href="updateProduct?id=${product.id}" class="btn btn-sm btn-warning">Update</a>
          <a href="deleteProduct?id=${product.id}" class="btn btn-sm btn-danger">Delete</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <h3>Add New Product</h3>
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
  </form>
</div>
</body>
</html>