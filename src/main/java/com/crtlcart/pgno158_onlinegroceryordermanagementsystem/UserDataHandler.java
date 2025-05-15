package com.crtlcart.pgno158_onlinegroceryordermanagementsystem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletContext;

public class UserDataHandler {
    // Store the file in Tomcat's temp directory which has write permissions
    private static String FILE_PATH;

    static {
        try {
            // Default to temp directory if init() is not called
            String tempDir = System.getProperty("java.io.tmpdir");
            FILE_PATH = new File(tempDir, "user_data.txt").getAbsolutePath();
            System.out.println("Default user data file path: " + FILE_PATH);
        } catch (Exception e) {
            // Fallback to user.home directory
            FILE_PATH = System.getProperty("user.home") + File.separator + "user_data.txt";
            System.out.println("Fallback user data file path: " + FILE_PATH);
        }
    }

    // Initialize with a ServletContext to get a reliable file path in the webapp
    public static void init(ServletContext context) {
        try {
            // Try to use WEB-INF directory first (safer location that isn't publicly accessible)
            String webInfPath = context.getRealPath("/WEB-INF");
            File webInfDir = new File(webInfPath);
            if (webInfDir.exists() && webInfDir.canWrite()) {
                FILE_PATH = new File(webInfDir, "user_data.txt").getAbsolutePath();
            } else {
                // Fallback to context root if WEB-INF isn't writable
                String contextPath = context.getRealPath("/");
                FILE_PATH = new File(contextPath, "user_data.txt").getAbsolutePath();
            }
            System.out.println("Initialized user data file path: " + FILE_PATH);
        } catch (Exception e) {
            // Keep the default path from static initializer
            e.printStackTrace();
        }
    }

    // Save a new user to the file
    public static synchronized boolean saveUser(String username, String password, String email) {
        try {
            // Check if user already exists
            if (userExists(username)) {
                return false;
            }

            // Append the new user to the file
            try (FileWriter fw = new FileWriter(FILE_PATH, true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println(username + ":" + password + ":" + email);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Check if a user with the given username exists
    public static boolean userExists(String username) {
        List<String[]> users = getAllUsers();
        for (String[] user : users) {
            if (user[0].equals(username)) {
                return true;
            }
        }
        return false;
    }

    // Validate user login
    public static boolean validateUser(String username, String password) {
        List<String[]> users = getAllUsers();
        for (String[] user : users) {
            if (user[0].equals(username) && user[1].equals(password)) {
                return true;
            }
        }
        return false;
    }

    // Get user email by username
    public static String getUserEmail(String username) {
        List<String[]> users = getAllUsers();
        for (String[] user : users) {
            if (user[0].equals(username)) {
                return user[2];
            }
        }
        return null;
    }

    // Get all users from the file
    public static List<String[]> getAllUsers() {
        List<String[]> users = new ArrayList<>();
        File file = new File(FILE_PATH);

        // Create file if it doesn't exist
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return users;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(":");
                if (userData.length >= 3) {
                    users.add(userData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }
}