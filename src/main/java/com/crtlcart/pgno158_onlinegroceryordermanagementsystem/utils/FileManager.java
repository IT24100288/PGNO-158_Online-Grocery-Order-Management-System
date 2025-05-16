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
        // Create files if they don't exist
        createFileIfNotExists(PRODUCTS_FILE);
        createFileIfNotExists(REVIEWS_FILE);
    }

    private static void createFileIfNotExists(String filename) {
        File file = new File(getFilePath(filename));
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getFilePath(String filename) {
        return servletContext.getRealPath("/WEB-INF/" + filename);
    }

    public static List<Product> readProducts() {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(getFilePath(PRODUCTS_FILE)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    int stock = Integer.parseInt(parts[3]);
                    products.add(new Product(id, name, price, stock));
                }
            }
        } catch (IOException e) {
            // If file doesn't exist or is empty, return empty list
            e.printStackTrace();
        }
        return products;
    }

    public static void writeProducts(List<Product> products) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(getFilePath(PRODUCTS_FILE)))) {
            for (Product product : products) {
                writer.println(String.format("%d,%s,%.2f,%d",
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getStock()));
            }
        }
    }

    public static List<Review> readReviews() {
        List<Review> reviews = new ArrayList<>();
        File file = new File(getFilePath(REVIEWS_FILE));
        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                reviews = (List<Review>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return reviews;
    }

    public static void writeReviews(List<Review> reviews) throws IOException {
        File file = new File(getFilePath(REVIEWS_FILE));
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(reviews);
        }
    }
} 