package edu.ccrm.cli;

import edu.ccrm.config.AppConfig;
import edu.ccrm.domain.*;
import edu.ccrm.io.ImportExportService;
import edu.ccrm.service.*;
import edu.ccrm.util.DuplicateEnrollmentException;
import edu.ccrm.util.MaxCreditLimitExceededException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

public class CCRM_CLI {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService();
    private static final ImportExportService importExportService = new ImportExportService(AppConfig.getInstance().getDataFolderPath());

    public static void main(String[] args) {
        AppConfig.getInstance(); // This call initializes the singleton
        System.out.println("Campus Course & Records Manager (CCRM) started.");

        boolean exit = false;
        while (!exit) {
            printMainMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    manageStudents();
                    break;
                case "2":
                    manageCourses();
                    break;
                case "3":
                    manageEnrollmentAndGrades();
                    break;
                case "4":
                    manageFileOperations();
                    break;
                case "5":
                    System.out.println("Reports not yet implemented.");
                    break;
                case "6":
                    System.out.println("Exiting CCRM. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void printMainMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Courses");
        System.out.println("3. Manage Enrollment & Grades");
        System.out.println("4. File Operations");
        System.out.println("5. Reports");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void manageStudents() {
        System.out.println("\n--- Student Management ---");
        System.out.println("1. Add a new student");
        System.out.println("2. List all students");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.print("Enter student ID: ");
                String id = scanner.nextLine();
                System.out.print("Enter registration number: ");
                String regNo = scanner.nextLine();
                System.out.print("Enter full name: ");
                String fullName = scanner.nextLine();
                System.out.print("Enter email: ");
                String email = scanner.nextLine();
                Student newStudent = new Student(id, regNo, fullName, email, LocalDate.now(), "Active");
                studentService.addStudent(newStudent);
                break;
            case "2":
                System.out.println("\n--- List of All Students ---");
                studentService.listAllStudents().forEach(Student::printProfile);
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void manageCourses() {
        System.out.println("\n--- Course Management ---");
        System.out.println("1. Add a new course");
        System.out.println("2. List all courses");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.print("Enter course code: ");
                String code = scanner.nextLine();
                System.out.print("Enter course title: ");
                String title = scanner.nextLine();
                System.out.print("Enter credits: ");
                int credits = Integer.parseInt(scanner.nextLine());
                
                Course newCourse = new Course.CourseBuilder(code, title, credits)
                                           .department("Computer Science")
                                           .semester(Semester.FALL)
                                           .build();
                courseService.addCourse(newCourse);
                break;
            case "2":
                System.out.println("\n--- List of All Courses ---");
                courseService.listAllCourses().forEach(System.out::println);
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void manageEnrollmentAndGrades() {
        System.out.println("\n--- Enrollment & Grading ---");
        System.out.println("1. Enroll a student in a course");
        System.out.println("2. Record a grade");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.print("Enter student registration number: ");
                String studentRegNo = scanner.nextLine();
                System.out.print("Enter course code: ");
                String courseCode = scanner.nextLine();

                Optional<Student> studentOpt = studentService.findStudentByRegNo(studentRegNo);
                Optional<Course> courseOpt = courseService.listAllCourses().stream()
                                    .filter(c -> c.getCode().equalsIgnoreCase(courseCode))
                                    .findFirst();

                if (studentOpt.isPresent() && courseOpt.isPresent()) {
                    try {
                        enrollmentService.enrollStudent(studentOpt.get(), courseOpt.get());
                    } catch (MaxCreditLimitExceededException | DuplicateEnrollmentException e) {
                        System.err.println("Enrollment failed: " + e.getMessage());
                    }
                } else {
                    System.err.println("Student or course not found.");
                }
                break;
            case "2":
                System.out.println("Record Grade functionality will be added here.");
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void manageFileOperations() {
        System.out.println("\n--- File Operations ---");
        System.out.println("1. Import students from CSV");
        System.out.println("2. Create backup and show size");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.print("Enter filename (e.g., students.csv): ");
                String importFile = scanner.nextLine();
                importExportService.importStudents(importFile, studentService);
                break;
            case "2":
                importExportService.createBackup();
                Path backupDir = Paths.get("backups");
                long totalSize = importExportService.getDirectorySize(backupDir);
                System.out.println("Total size of backup directory: " + totalSize + " bytes");
                break;
            default:
                System.out.println("Invalid option.");
        }
    }
}