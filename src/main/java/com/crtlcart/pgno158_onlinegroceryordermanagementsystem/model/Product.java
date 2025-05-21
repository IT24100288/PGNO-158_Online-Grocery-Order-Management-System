package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model;

import java.io.Serializable;

public class Product extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private double price;
    private int stock;

    public Product(int id, String name, double price, int stock) {
        super(id);
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    @Override
    public String getDisplayName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s (ID: %d, Price: $%.2f, Stock: %d)", name, getId(), price, stock);
    }
}