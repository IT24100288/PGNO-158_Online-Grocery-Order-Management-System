package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model;

public class Payment {
    private String timestamp;
    private String paymentMethod;
    private String cardholder;
    private String cardNumber;
    private String expiry;
    private String orderType;
    private String table;
    private String subtotal;

    public Payment(String timestamp, String paymentMethod, String cardholder, String cardNumber,
                   String expiry, String orderType, String table, String subtotal) {
        this.timestamp = timestamp;
        this.paymentMethod = paymentMethod;
        this.cardholder = cardholder;
        this.cardNumber = cardNumber;
        this.expiry = expiry;
        this.orderType = orderType;
        this.table = table;
        this.subtotal = subtotal;
    }

    public Payment() {

    }

    // Getters and setters...

    public String getTimestamp() { return timestamp; }
    public String getPaymentMethod() { return paymentMethod; }
    public String getCardholder() { return cardholder; }
    public String getCardNumber() { return cardNumber; }
    public String getExpiry() { return expiry; }
    public String getOrderType() { return orderType; }
    public String getTable() { return table; }
    public String getSubtotal() { return subtotal; }

    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public void setCardholder(String cardholder) { this.cardholder = cardholder; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public void setExpiry(String expiry) { this.expiry = expiry; }
    public void setOrderType(String orderType) { this.orderType = orderType; }
    public void setTable(String table) { this.table = table; }
    public void setSubtotal(String subtotal) { this.subtotal = subtotal; }

    public void setMethod(String part) {
    }
}
