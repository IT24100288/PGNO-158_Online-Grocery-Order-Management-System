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
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }

    public static List<Product> readProducts() throws IOException {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    double price = Double.parseDouble(parts[2].trim());
                    String category = parts[3].trim();
                    products.add(new Product(id, name, price, category));
                }
            }
        }
        return products;
    }

    public static void writeProducts(List<Product> products) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Product product : products) {
                String line = product.getId() + "," + product.getName() + "," + product.getPrice() + "," + product.getCategory();
                writer.write(line);
                writer.newLine();
            }
        }
    }
}