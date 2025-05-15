package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.utils;

import com.crtlcart.pgno158_onlinegroceryordermanagementsystem.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MergeSortUtil {

    public static List<Product> sort(List<Product> products, String sortBy) {
        if (products == null || products.size() <= 1) {
            return products;
        }
        List<Product> productList = new ArrayList<>(products);
        mergeSort(productList, 0, productList.size() - 1, sortBy);
        return productList;
    }

    private static void mergeSort(List<Product> products, int left, int right, String sortBy) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(products, left, mid, sortBy);
            mergeSort(products, mid + 1, right, sortBy);
            merge(products, left, mid, right, sortBy);
        }
    }

    private static void merge(List<Product> products, int left, int mid, int right, String sortBy) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        List<Product> leftList = new ArrayList<>(n1);
        List<Product> rightList = new ArrayList<>(n2);

        for (int i = 0; i < n1; i++) {
            leftList.add(products.get(left + i));
        }
        for (int j = 0; j < n2; j++) {
            rightList.add(products.get(mid + 1 + j));
        }

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            Product leftProduct = leftList.get(i);
            Product rightProduct = rightList.get(j);

            boolean shouldSwap;
            if ("category".equalsIgnoreCase(sortBy)) {
                shouldSwap = leftProduct.getCategory().compareToIgnoreCase(rightProduct.getCategory()) <= 0;
            } else {
                shouldSwap = leftProduct.getPrice() <= rightProduct.getPrice();
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

        while (i < n1) {
            products.set(k, leftList.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            products.set(k, rightList.get(j));
            j++;
            k++;
        }
    }
}