package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model;

import java.util.Date;

public class Invoice {
    private String invoiceId;
    private String customer;
    private double amount;
    private Date date;

    public Invoice(String invoiceId, String customer, double amount, Date date) {
        this.invoiceId = invoiceId;
        this.customer = customer;
        this.amount = amount;
        this.date = date;
    }

    // Getters & Setters
    public String getInvoiceId() {
        return invoiceId;
    }
    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getCustomer() {
        return customer;
    }
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}
