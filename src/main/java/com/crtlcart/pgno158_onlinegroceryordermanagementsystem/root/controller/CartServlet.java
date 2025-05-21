package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.controller;

import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.modal.Cart;
import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.modal.Product;
import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.service.CartService;
import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

    private final CartService cartService = new CartService();
    private final ProductService productService = new ProductService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<Cart> cartItems = cartService.getCartItems(request);
        String action = request.getParameter("action");
        String productId = request.getParameter("id");

        //add or remove products
        if (action.equals("add") && productId != null) {
            Product product = productService.getProductById(productId);
            if (product != null) {
                Cart cart = new Cart(product.getProductId(),product.getProductName(), product.getProductPrice(),product.getDescription(),product.getProductCategory(), product.getProductImage(), 1);
                cartService.addToCart(request,cart);
            }
        } else if (action.equals("remove")) {
            cartService.removeFromCart(request,productId);
        }

        //increase or decrease each cart product quantity
        if (cartItems != null && productId != null) {
            for (Cart cart : cartItems) {
                if(cart.getProductId().equals(productId)){
                    if(action.equals("increase")){
                        cart.setCartQuantity(cart.getCartQuantity()+1);
                    } else if (action.equals("decrease")) {
                        if (cart.getCartQuantity() > 1) {
                            cart.setCartQuantity(cart.getCartQuantity() - 1);
                        } else if (cart.getCartQuantity() == 1) {
                            cartItems.remove(cart);
                        }
                    }
                    break;
                }
            }
        }
    }
}
