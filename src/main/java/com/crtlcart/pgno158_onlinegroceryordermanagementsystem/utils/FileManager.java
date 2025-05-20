package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.utils;

import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model.Product;
import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model.Review;
import jakarta.servlet.ServletContext;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static FileManager instance;
    private static final String PRODUCTS_FILE = "products.txt";
    private static final String REVIEWS_FILE = "reviews.dat";
    private ServletContext servletContext;

    private FileManager() {
        // Private constructor for singleton
    }

    public static synchronized FileManager getInstance() {
        if (instance == null) {
            instance = new FileManager();
        }
        return instance;
    }

    public void setServletContext(ServletContext context) {
        this.servletContext = context;
        initializeFiles();
    }

    private void initializeFiles() {
        if (servletContext == null) {
            throw new IllegalStateException("ServletContext not set");
        }

        String realPath = servletContext.getRealPath("/");
        File productsFile = new File(realPath, PRODUCTS_FILE);
        File reviewsFile = new File(realPath, REVIEWS_FILE);

        try {
            if (!productsFile.exists()) {
                productsFile.createNewFile();
            }
            if (!reviewsFile.exists()) {
                reviewsFile.createNewFile();
                // Initialize with empty list
                writeReviews(new ArrayList<>());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize files", e);
        }
    }

    public List<Product> readProducts() {
        if (servletContext == null) {
            throw new IllegalStateException("ServletContext not set");
        }

        List<Product> products = new ArrayList<>();
        String realPath = servletContext.getRealPath("/");
        File file = new File(realPath, PRODUCTS_FILE);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 4) {
                    Product product = new Product(
                        Integer.parseInt(parts[0]),  // id
                        parts[1],                    // name
                        Double.parseDouble(parts[2]), // price
                        Integer.parseInt(parts[3])    // stock
                    );
                    products.add(product);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    public void writeProducts(List<Product> products) {
        if (servletContext == null) {
            throw new IllegalStateException("ServletContext not set");
        }

        String realPath = servletContext.getRealPath("/");
        File file = new File(realPath, PRODUCTS_FILE);

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (Product product : products) {
                writer.println(String.format("%d|%s|%.2f|%d",
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getStock()));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to write products", e);
        }
    }

    public List<Review> readReviews() {
        if (servletContext == null) {
            throw new IllegalStateException("ServletContext not set");
        }

        String realPath = servletContext.getRealPath("/");
        File file = new File(realPath, REVIEWS_FILE);

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            @SuppressWarnings("unchecked")
            List<Review> reviews = (List<Review>) ois.readObject();
            return reviews;
        } catch (EOFException e) {
            // File is empty, return empty list
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read reviews", e);
        }
    }

    public void writeReviews(List<Review> reviews) {
        if (servletContext == null) {
            throw new IllegalStateException("ServletContext not set");
        }

        String realPath = servletContext.getRealPath("/");
        File file = new File(realPath, REVIEWS_FILE);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(reviews);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to write reviews", e);
        }
    }
} 