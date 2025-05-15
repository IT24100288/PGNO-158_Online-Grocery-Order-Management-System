package com.crtlcart.pgno158_onlinegroceryordermanagementsystem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@WebServlet("/settings")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class SettingsServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "uploads";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String username = (String) session.getAttribute("username");
        File file = new File(getServletContext().getRealPath("/data/users.txt"));
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts.length >= 3 && parts[0].equals(username)) {
                        session.setAttribute("username", parts[0]);
                        session.setAttribute("email", parts.length > 2 ? parts[2] : "");
                        session.setAttribute("address", parts.length > 4 ? parts[4] : "");
                        break;
                    }
                }
            }
        }

        // ✅ ADDITION: Load all users from user_data.txt
        List<String> userList = new ArrayList<>();
        String publicPath = getServletContext().getRealPath("/") + "user_data.txt";
        File publicFile = new File(publicPath);
        if (publicFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(publicFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    userList.add(line);
                }
            }
        }
        request.setAttribute("userList", userList);

        request.getRequestDispatcher("settings.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String username = request.getParameter("username");
        String address = request.getParameter("address");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        String removePhoto = request.getParameter("removePhoto");
        String deleteAccount = request.getParameter("deleteAccount");

        if ("true".equals(deleteAccount)) {
            File file = new File(getServletContext().getRealPath("/data/users.txt"));
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file));
                     BufferedWriter writer = new BufferedWriter(new FileWriter(file + ".tmp"))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(":");
                        if (parts.length > 0 && !parts[0].equals((String) session.getAttribute("username"))) {
                            writer.write(line + "\n");
                        }
                    }
                }
                Files.delete(file.toPath());
                Files.move(new File(file + ".tmp").toPath(), file.toPath());
            }
            session.invalidate();
            response.sendRedirect("login.jsp");
            return;
        }

        if ("true".equals(removePhoto)) {
            session.removeAttribute("profilePic");
        }

        Part filePart = request.getPart("profilePic");
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String filePath = uploadPath + File.separator + username + "_" + fileName;
            filePart.write(filePath);
            session.setAttribute("profilePic", UPLOAD_DIR + "/" + username + "_" + fileName);
        }

        File file = new File(getServletContext().getRealPath("/data/users.txt"));
        StringBuilder fileContent = new StringBuilder();
        String email = (String) session.getAttribute("email");
        boolean userFound = false;

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts.length >= 3 && parts[0].equals((String) session.getAttribute("username"))) {
                        userFound = true;
                        String currentPassword = parts[1];
                        String updatedPassword = (newPassword != null && !newPassword.isEmpty() && newPassword.equals(confirmPassword)) ? newPassword : currentPassword;
                        fileContent.append(username).append(":").append(updatedPassword).append(":")
                                .append(email).append(":")
                                .append(address != null ? address : (parts.length > 4 ? parts[4] : ""))
                                .append("\n");
                    } else {
                        fileContent.append(line).append("\n");
                    }
                }
            }
        }

        if (!userFound) {
            fileContent.append(username).append(":dummy:").append(email).append(":")
                    .append(address != null ? address : "").append("\n");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(fileContent.toString());
        }

        session.setAttribute("username", username);
        session.setAttribute("address", address);

        response.sendRedirect("settings.jsp");
    }
}
