package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.modal;

public class Cart extends Product {

    private int cartQuantity;

    public Cart(String productId, String productName, Double productPrice, String description, String productCategory, String productImage, int cartQuantity) {
        super(productId,productName,productPrice,description,productCategory,productImage);
        this.cartQuantity = cartQuantity;
    }

    public int getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(int cartQuantity) {
        this.cartQuantity = cartQuantity;
    }
}
