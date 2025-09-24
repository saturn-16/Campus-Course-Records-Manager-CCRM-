package edu.ccrm.config;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class AppConfig {

    private static AppConfig instance;
    private final Path dataFolderPath;

    private AppConfig() {
        this.dataFolderPath = Paths.get("data");
        System.out.println("AppConfig initialized. Data folder: " + dataFolderPath.toAbsolutePath());
        try {
            if (!java.nio.file.Files.exists(dataFolderPath)) {
                java.nio.file.Files.createDirectories(dataFolderPath);
            }
        } catch (IOException e) {
            System.err.println("Error creating data directory: " + e.getMessage());
        }
    }

    public static synchronized AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    public Path getDataFolderPath() {
        return dataFolderPath;
    }
}