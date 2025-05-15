package com.crtlcart.pgno158_onlinegroceryordermanagementsystem.utils;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class FileManagerInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        FileManager.init(sce.getServletContext());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Cleanup if needed
    }
}