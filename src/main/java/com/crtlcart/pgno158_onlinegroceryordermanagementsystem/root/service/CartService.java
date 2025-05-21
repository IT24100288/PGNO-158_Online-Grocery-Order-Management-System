package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.service;

import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.modal.Cart;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;

public class CartService {

    //add items to the cart
    public void addToCart(HttpServletRequest request, Cart cart) {
        HttpSession session = request.getSession();
        ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cartList");

        if (cartList == null) {
            cartList = new ArrayList<>();
            session.setAttribute("cartList", cartList);
        }
        boolean exists = false;
        for (Cart cartItem : cartList) {
            if (cartItem.getProductId().equals(cart.getProductId())) {
                cartItem.setCartQuantity(cartItem.getCartQuantity() + cart.getCartQuantity());
                exists = true;
                break;
            }
        }
        if (!exists) {
            cartList.add(cart);
        }
    }

    //remove the items form the cart
    public void removeFromCart(HttpServletRequest request, String productId) {
        HttpSession session = request.getSession();
        ArrayList<Cart> cartItems = (ArrayList<Cart>) session.getAttribute("cartList");

        if (cartItems != null) {
            cartItems.removeIf(cartItem -> cartItem.getProductId().equals(productId));
        }
    }

    //get all cart items from the session
    public ArrayList<Cart> getCartItems(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (ArrayList<Cart>) session.getAttribute("cartList");
    }

    //clear the cart
    public void clearCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("cartList");
    }

    //calculate cart items total
    public double calculateTotalPrice(ArrayList<Cart> cartList) {
        double subtotal = getSubtotal(cartList);
        double shippingFee = getDeliveryCost();

        if(subtotal == 0){
            return 0;
        }else{
            return subtotal + shippingFee;
        }
    }

    //calculate subtotal
    public double getSubtotal(ArrayList<Cart> cartList) {
        double subtotal = 0;

        if (cartList != null) {
            for (Cart cartItem : cartList) {
                subtotal += cartItem.getProductPrice() * cartItem.getCartQuantity();
            }
            return subtotal;
        }else{
            return 0;
        }
    }

    //return delivery charge
    public double getDeliveryCost() {
        return 100;
    }

    //return cart quantity
    public int getCartQuantity(ArrayList<Cart> cartList) {
        int quantity = 0;

        if (cartList != null) {
            for (Cart cartItem : cartList) {
                quantity += cartItem.getCartQuantity();
            }
            return quantity;
        }else{
            return 0;
        }
    }
}
