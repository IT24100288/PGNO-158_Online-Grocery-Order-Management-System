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
  <a href="addProduct.jsp" class="btn btn-primary mb-3">Add New Product</a>
  <form action="listProducts" method="get" class="mb-3">
    <label for="sortBy" class="form-label">Sort By:</label>
    <select name="sortBy" id="sortBy" class="form-select d-inline w-auto">
      <option value="none">None</option>
      <option value="price">Price (Low to High)</option>
      <option value="price" data-order="desc">Price (High to Low)</option>
    </select>
    <input type="hidden" name="sortOrder" id="sortOrder" value="asc">
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
</div>
<script>
  // JavaScript to set sortOrder based on selected option
  document.getElementById("sortBy").addEventListener("change", function() {
    var selectedOption = this.options[this.selectedIndex];
    var sortOrder = selectedOption.getAttribute("data-order") || "asc";
    document.getElementById("sortOrder").value = sortOrder;
  });
</script>
</body>
</html>