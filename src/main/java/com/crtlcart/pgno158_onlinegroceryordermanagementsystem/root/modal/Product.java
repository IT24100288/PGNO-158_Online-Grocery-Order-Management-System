package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.modal;

public class Product {

    protected String productId;
    protected String productName;
    protected double productPrice;
    protected String description;
    protected String productCategory;
    protected String productImage;

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public String getDescription() {
        return description;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public String getProductImage() {
        return productImage;
    }

    public Product(String productId, String productName, double productPrice, String description, String productCategory, String productImage) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.description = description;
        this.productCategory = productCategory;
        this.productImage = productImage;
    }

    public Product() {
        super();
    }

    public Product(String productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return productId+","+productName+","+productPrice+","+description+","+productCategory+","+productImage;
    }
}
