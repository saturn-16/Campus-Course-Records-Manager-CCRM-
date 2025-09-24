Campus Course & Records Manager (CCRM)
Project Overview
CCRM is a console-based Java application designed to manage student and course records for an educational institution. The application allows for student and course management (creation, updates, and listings), enrollment, and grading. It includes robust file utilities for data import/export and backups. The project is built using Java SE and demonstrates key programming concepts such as Object-Oriented Programming (OOP) , modern I/O with NIO.2 , the Stream API , and design patterns.


How to Run
Prerequisites: Ensure you have a Java Development Kit (JDK) version 8 or higher installed. You can verify this by running java -version in your terminal.
Clone the Repository: Clone this Git repository to your local machine.
Navigate to the Project Directory: Open your terminal or command prompt and go to the project's root directory.

Compile the Code: Compile all Java source files using the javac command.
javac -d bin -cp bin src/edu/ccrm/**/*.java

Run the Application: Execute the main class from the bin directory.
java -cp bin edu.ccrm.cli.CCRM_CLI

Enable Assertions (Optional): If you want to run the application with assertions enabled, use the -ea flag.
java -ea -cp bin edu.ccrm.cli.CCRM_CLI

Notes on Enabling Assertions
Assertions are disabled by default in the Java Virtual Machine (JVM). To enable them at runtime, you must use the -ea or -enableassertions flag in the java command. For example, java -ea MyMainClass. Assertions are used in this project to enforce invariants, such as non-null IDs and valid credit bounds, and should be enabled during development and testing to catch logical errors.

Java Platform Comparison
Java SE (Standard Edition): The core platform for building desktop and server-side applications. It includes the Java language, the Java Virtual Machine (JVM), and a comprehensive set of libraries.
Java EE (Enterprise Edition): A set of specifications and APIs for building large-scale, multi-tiered, and distributed enterprise applications. It extends Java SE with features for web services, networking, and security.
Java ME (Micro Edition): A subset of Java designed for resource-constrained devices like mobile phones, embedded systems, and consumer electronics.
Java Architecture: JDK, JRE, and JVM

JVM (Java Virtual Machine): An abstract machine that provides the runtime environment for executing Java bytecode. The JVM reads .class files and translates the bytecode into the native machine code of the underlying hardware. It's the component that makes Java "write once, run anywhere."

JRE (Java Runtime Environment): A set of software tools that includes the JVM and the core Java class libraries. It is the minimum requirement for running a Java application.


JDK (Java Development Kit): A superset of the JRE that includes tools for developing, debugging, and monitoring Java applications. It contains the Java compiler (javac), the Java debugger (jdb), and other tools.
