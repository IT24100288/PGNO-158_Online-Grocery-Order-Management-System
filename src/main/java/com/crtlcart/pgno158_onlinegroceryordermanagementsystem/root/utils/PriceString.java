package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.utils;

public class PriceString {
    public static String StringFormat(double value) {
        if(value == 0){
            return "Rs.0.00";
        }else{
            return String.format("Rs.%.2f", value);
        }
    }
}
