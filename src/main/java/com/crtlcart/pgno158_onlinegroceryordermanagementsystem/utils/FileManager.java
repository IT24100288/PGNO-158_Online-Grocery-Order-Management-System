package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.utils;

import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model.Product;
import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model.Review;
import jakarta.servlet.ServletContext;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String PRODUCTS_FILE = "products.txt";
    private static final String REVIEWS_FILE = "reviews.txt";
    private static ServletContext servletContext;

    public static void init(ServletContext context) {
        servletContext = context;
    }

    private static String getFilePath(String filename) {
        return servletContext.getRealPath("/WEB-INF/" + filename);
    }

    public static List<Product> readProducts() {
        List<Product> products = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getFilePath(PRODUCTS_FILE)))) {
            products = (List<Product>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // If file doesn't exist or is empty, return empty list
        }
        return products;
    }

    public static void writeProducts(List<Product> products) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getFilePath(PRODUCTS_FILE)))) {
            oos.writeObject(products);
        }
    }

    public static List<Review> readReviews() {
        List<Review> reviews = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getFilePath(REVIEWS_FILE)))) {
            reviews = (List<Review>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // If file doesn't exist or is empty, return empty list
        }
        return reviews;
    }

    public static void writeReviews(List<Review> reviews) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getFilePath(REVIEWS_FILE)))) {
            oos.writeObject(reviews);
        }
    }
} 