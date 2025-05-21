package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.root.utils;

import java.text.SimpleDateFormat;

public class Date {

    public static String getDate(){
        return new SimpleDateFormat("yyyy MMM dd").format(new java.util.Date());
    }
}
