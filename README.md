📚 Campus Course & Records Manager (CCRM)
A Java SE Console Application for Academic Administration
Welcome to the Campus Course & Records Manager, a robust, console-based Java application designed to streamline academic record-keeping. This project demonstrates a wide array of core Java principles and modern programming practices, from a clear OOP design to advanced file handling with NIO.2.

🚀 Getting Started
Prerequisites
You'll need a Java Development Kit (JDK) installed on your machine.
  --To check your JDK version, open your terminal and run: java -version

Running the Application --

1. Clone the Repository - 
git clone- https://github.com/saturn-16/Campus-Course-Records-Manager-CCRM-
cd ccrm-project

2.Compile the Code - 
javac -d bin -cp bin src/edu/ccrm/**/*.java

3.Run the Main Class
java -cp bin edu.ccrm.cli.CCRM_CLI

📋 Features --

CCRM offers a full suite of academic management tools, all accessible via a simple, menu-driven command-line interface.
1.Student Management: Add, update, and deactivate students. Print student profiles and transcripts.
2.Course Management: Add, update, and deactivate courses. Search and filter courses by instructor, department, or semester using the Stream API.
3.Enrollment & Grading: Enroll and unenroll students from courses with built-in business rules (e.g., max credits per semester). Record marks and compute GPAs.
4.File Utilities: Import students/courses from CSV files and export current data to files. Create a timestamped backup folder and calculate its total size recursively.

🛠️ Technical Details --

This project is a showcase of key Java SE concepts.

Java Platform & Architecture

1.Java SE is the core platform used for this project. It is differentiated from Java EE (for large-scale enterprise apps) and Java ME (for embedded devices).

2.The project relies on the standard JDK, JRE, and JVM architecture.


Core Language Concepts --

1.Object-Oriented Programming (OOP): The project demonstrates Encapsulation, Inheritance, Abstraction, and Polymorphism. The Person class is an abstract parent for Student and Instructor.

2.Enums: Used for Semester and Grade with custom fields and constructors.

3.Stream API: Used for efficient data processing, such as searching and filtering course lists.

4.File I/O: The modern NIO.2 (Path, Files) API is used for all file operations.

5.Exceptions: The application features robust exception handling, including custom checked exceptions like MaxCreditLimitExceededException.

Design Patterns -- 

1.Singleton: The AppConfig class is a singleton to manage application-wide configuration.
2.Builder: The Course class uses a Builder pattern to simplify object creation

🗺️ Project Structure --

The project is organized into clear packages for maintainability and logical separation of concerns.
1.edu.ccrm.cli: The menu-driven command-line interface.
2.edu.ccrm.config: Singleton configuration classes.
3.edu.ccrm.domain: The core data model classes (e.g., Student, Course).
4.edu.ccrm.io: Services for file import, export, and backups.
5.edu.ccrm.service: The business logic layer for all operations.
6.edu.ccrm.util: Utility and helper classes, including custom exceptions

📝 License --

This project is licensed under the MIT License.
