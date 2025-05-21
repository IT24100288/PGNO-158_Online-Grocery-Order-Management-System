package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.modal;

import java.util.ArrayList;

public class Order {
    private final String orderId;
    private final String customerEmail;
    private final ArrayList<Cart>  products;
    private final double totalPrice;
    private final String customerFirstName;
    private final String customerLastName;
    private final String customerPhone;
    private final String customerAddress;
    private final String customerCity;
    private final String customerPostalCode;
    private final String orderDate;
    private final String orderStatus;

    public Order(String orderId, String customerEmail, ArrayList<Cart> products, double totalPrice, String customerFirstName, String customerLastName, String customerPhone, String customerAddress, String customerCity, String customerPostalCode, String orderDate, String orderStatus) {
        this.orderId = orderId;
        this.customerEmail = customerEmail;
        this.products = products;
        this.totalPrice = totalPrice;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.customerCity = customerCity;
        this.customerPostalCode = customerPostalCode;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;

    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public ArrayList<Cart> getProducts() {
        return products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public String getCustomerPostalCode() {
        return customerPostalCode;
    }
}
