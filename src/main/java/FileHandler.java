import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler package com.grocery.servlet.util;

import java.io.*;
        import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static String dataDir = "data/"; // Default path, can be overridden

    // Method to set the data directory dynamically (e.g., from a servlet)
    public static void setDataDir(String dir) {
        dataDir = dir.endsWith("/") ? dir : dir + "/";
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static List<String> readFile(String fileName) throws IOException {
        List<String> lines = new ArrayList<>();
        File file = new File(dataDir + fileName);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    public static void writeFile(String fileName, List<String> lines) throws IOException {
        File file = new File(dataDir + fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    public static void appendToFile(String fileName, String line) throws IOException {
        File file = new File(dataDir + fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(line);
            writer.newLine();
        }
    }

}
