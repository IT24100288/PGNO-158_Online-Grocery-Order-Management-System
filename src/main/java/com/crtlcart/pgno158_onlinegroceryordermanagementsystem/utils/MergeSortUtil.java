package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.utils;

import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model.Product;

public class MergeSortUtil {

    // Main method to sort products by price (asc for low to high, desc for high to low)
    public static java.util.List<Product> sort(java.util.List<Product> products, String sortOrder) {
        if (products == null || products.size() <= 1) {
            return products; // Return if list is empty or has one item
        }
        java.util.List<Product> productList = new java.util.ArrayList<>(products);
        mergeSort(productList, 0, productList.size() - 1, sortOrder); // Sort the entire list
        return productList;
    }

    // Recursive method to divide the list into smaller parts
    private static void mergeSort(java.util.List<Product> products, int left, int right, String sortOrder) {
        if (left < right) {
            int mid = (left + right) / 2; // Find the middle point to divide
            mergeSort(products, left, mid, sortOrder); // Sort left half
            mergeSort(products, mid + 1, right, sortOrder); // Sort right half
            merge(products, left, mid, right, sortOrder); // Merge the sorted halves
        }
    }

    // Method to merge two sorted sublists
    private static void merge(java.util.List<Product> products, int left, int mid, int right, String sortOrder) {
        // Create temporary lists for left and right halves
        java.util.List<Product> leftList = new java.util.ArrayList<>();
        java.util.List<Product> rightList = new java.util.ArrayList<>();

        // Copy data to temporary lists
        for (int i = left; i <= mid; i++) {
            leftList.add(products.get(i));
        }
        for (int i = mid + 1; i <= right; i++) {
            rightList.add(products.get(i));
        }

        // Merge the temporary lists back into the original list
        int i = 0; // Index for leftList
        int j = 0; // Index for rightList
        int k = left; // Index for merged result

        while (i < leftList.size() && j < rightList.size()) {
            boolean shouldSwap;
            if ("desc".equalsIgnoreCase(sortOrder)) {
                // High to low (descending)
                shouldSwap = leftList.get(i).getPrice() >= rightList.get(j).getPrice();
            } else {
                // Low to high (ascending, default)
                shouldSwap = leftList.get(i).getPrice() <= rightList.get(j).getPrice();
            }

            if (shouldSwap) {
                products.set(k, leftList.get(i));
                i++;
            } else {
                products.set(k, rightList.get(j));
                j++;
            }
            k++;
        }

        // Add remaining elements from leftList, if any
        while (i < leftList.size()) {
            products.set(k, leftList.get(i));
            i++;
            k++;
        }

        // Add remaining elements from rightList, if any
        while (j < rightList.size()) {
            products.set(k, rightList.get(j));
            j++;
            k++;
        }
    }
}