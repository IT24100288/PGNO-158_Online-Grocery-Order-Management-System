package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model;

public class Item {
    private String name;
    private double price;
    private int quantity;
    private String note;
    private String imgPath;

    public Item(String name, double price, int quantity, String note, String imgPath) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.note = note;
        this.imgPath = imgPath;
    }

    // Getters and setters...
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }
    public String getNote() {
        return note;
    }
    public String getImgPath() {
        return imgPath;
    }

    public double getTotal() {
        return price * quantity;
    }
}
