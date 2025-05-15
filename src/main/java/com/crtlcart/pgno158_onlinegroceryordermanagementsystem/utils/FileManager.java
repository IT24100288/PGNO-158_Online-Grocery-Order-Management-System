package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.utils;

import org.example.ctrlcart.model.Product;
import org.example.ctrlcart.model.Review;
import jakarta.servlet.ServletContext;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static String productsFilePath;
    private static String reviewsFilePath;

    // Initialize file paths using ServletContext
    public static void init(ServletContext context) {
        productsFilePath = context.getRealPath("/WEB-INF/classes/products.txt");
        reviewsFilePath = context.getRealPath("/WEB-INF/classes/reviews.txt");
    }

    // Read products from products.txt
    public static List<Product> readProducts() {
        List<Product> products = new ArrayList<>();
        File file = new File(productsFilePath);
        if (!file.exists()) {
            return products; // Return empty list if file doesn't exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    int stock = Integer.parseInt(parts[3]);
                    products.add(new Product(id, name, price, stock));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Write products to products.txt
    public static void writeProducts(List<Product> products) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(productsFilePath))) {
            for (Product product : products) {
                writer.write(product.getId() + "," + product.getName() + "," + product.getPrice() + "," + product.getStock());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read reviews from reviews.txt
    public static List<Review> readReviews() {
        List<Review> reviews = new ArrayList<>();
        File file = new File(reviewsFilePath);
        if (!file.exists()) {
            return reviews; // Return empty list if file doesn't exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    try {
                        int id = Integer.parseInt(parts[0]);
                        int productId = Integer.parseInt(parts[1]);
                        int userId = Integer.parseInt(parts[2]);
                        String userName = parts[3];
                        String comment = parts[4];
                        double rating = Double.parseDouble(parts[5]);
                        String status = parts[6];

                        Review review = new Review(id, productId, userId, userName, comment, rating, status);
                        if (parts.length > 7) {
                            review.setSubmissionDate(parts[7]);
                            if (parts.length > 8) {
                                review.setSubmissionTime(parts[8]);
                            }
                        }
                        reviews.add(review);
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing review line: " + line);
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    // Write reviews to reviews.txt
    public static void writeReviews(List<Review> reviews) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(reviewsFilePath))) {
            for (Review review : reviews) {
                writer.write(review.getId() + "," +
                        review.getProductId() + "," +
                        review.getUserId() + "," +
                        review.getUserName() + "," +
                        review.getComment() + "," +
                        review.getRating() + "," +
                        review.getStatus() + "," +
                        review.getSubmissionDate() + "," +
                        review.getSubmissionTime());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
