package edu.ccrm.io;

import edu.ccrm.domain.Student;
import edu.ccrm.service.StudentService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class ImportExportService {

    private final Path dataPath;

    public ImportExportService(Path dataPath) {
        this.dataPath = dataPath;
    }

    public void importStudents(String filename, StudentService studentService) {
        Path filePath = dataPath.resolve(filename);
        if (Files.notExists(filePath)) {
            System.err.println("File not found: " + filePath.toAbsolutePath());
            return;
        }
        System.out.println("Importing students from " + filePath + "...");
        try (Stream<String> lines = Files.lines(filePath)) {
            lines.forEach(line -> {
                String[] parts = line.split(",");
                if (parts.length >= 4) { // Use >= in case of extra commas
                    // Assuming basic student data is at least 4 parts
                    Student student = new Student(parts[0], parts[1], parts[2], parts[3], null, "Active");
                    studentService.addStudent(student);
                    System.out.println("Imported student: " + parts[2]);
                }
            });
        } catch (IOException e) {
            System.err.println("Error importing students: " + e.getMessage());
        }
    }

    public void createBackup() {
        // Use Date/Time API for a timestamped folder name
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        Path backupPath = Paths.get("backups", "ccrm_backup_" + timestamp);
        try {
            Files.createDirectories(backupPath);
            // In a complete project, you'd copy exported data files here.
            System.out.println("Backup created at: " + backupPath.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error creating backup: " + e.getMessage());
        }
    }

    // A recursive utility to compute directory size
    public long getDirectorySize(Path dir) {
        try {
            // Use Files.walk() to traverse the directory recursively
            try (Stream<Path> walk = Files.walk(dir)) {
                return walk.filter(Files::isRegularFile)
                           .mapToLong(p -> {
                               try {
                                   return Files.size(p);
                               } catch (IOException e) {
                                   return 0L;
                               }
                           })
                           .sum();
            }
        } catch (IOException e) {
            System.err.println("Error calculating directory size: " + e.getMessage());
            return 0L;
        }
    }
}