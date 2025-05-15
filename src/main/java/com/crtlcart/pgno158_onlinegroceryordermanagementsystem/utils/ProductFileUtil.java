package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.utils;

import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model.Product;
import jakarta.servlet.ServletContext;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductFileUtil {
    private static String filePath;

    public static void init(ServletContext context) throws IOException {
        filePath = context.getRealPath("/WEB-INF/classes/products.txt");
        System.out.println("Attempting to resolve file path: " + filePath);

        if (filePath == null || filePath.isEmpty()) {
            System.out.println("Real path resolution failed. Falling back to default deployment path.");
            filePath = context.getRealPath("/") + "WEB-INF/classes/products.txt";
        }

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File does not exist at: " + filePath + ". Creating new file...");
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        if (!file.canWrite()) {
            System.out.println("File is not writable: " + filePath);
            throw new IOException("Cannot write to products.txt at: " + filePath);
        }

        System.out.println("File path resolved: " + filePath);
        System.out.println("File exists: " + file.exists());
        System.out.println("File is writable: " + file.canWrite());
    }

    public static List<Product> readProducts() {
        List<Product> products = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File does not exist during read: " + filePath);
            return products;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length == 4) { // Updated to expect 4 fields: id,name,price,category
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    double price = Double.parseDouble(parts[2].trim());
                    String category = parts[3].trim();
                    products.add(new Product(id, name, price, category));
                }
            }
            System.out.println("Read products: " + products);
        } catch (IOException e) {
            System.out.println("Failed to read products: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing product data: " + e.getMessage());
        }
        return products;
    }

    public static void writeProducts(List<Product> products) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File does not exist during write: " + filePath + ". Creating new file...");
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        if (!file.canWrite()) {
            System.out.println("File is not writable during write: " + filePath);
            throw new IOException("Cannot write to products.txt at: " + filePath);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Product product : products) {
                String line = product.getId() + "," + product.getName() + "," + product.getPrice() + "," + product.getCategory();
                writer.write(line);
                writer.newLine();
                System.out.println("Wrote line to buffer: " + line);
            }
            writer.flush();
            System.out.println("Buffer flushed to file: " + filePath);

            try (BufferedReader verifier = new BufferedReader(new FileReader(filePath))) {
                String content = verifier.readLine();
                System.out.println("Verified file content: " + (content != null ? content : "Empty"));
            } catch (IOException e) {
                System.out.println("Verification failed: " + e.getMessage());
            }

            System.out.println("Products written to: " + filePath);
        } catch (IOException e) {
            System.out.println("Write failed: " + e.getMessage());
            throw e;
        }
    }
}