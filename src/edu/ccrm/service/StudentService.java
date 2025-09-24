package edu.ccrm.service;

import edu.ccrm.domain.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentService {

    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        boolean exists = students.stream().anyMatch(s -> s.getRegNo().equals(student.getRegNo()));
        if (!exists) {
            students.add(student);
            System.out.println("Student added successfully: " + student.getFullName());
        } else {
            System.out.println("Error: Student with registration number " + student.getRegNo() + " already exists.");
        }
    }

    public List<Student> listAllStudents() {
        return new ArrayList<>(students);
    }
    
    public Optional<Student> findStudentByRegNo(String regNo) {
        return students.stream()
                       .filter(s -> s.getRegNo().equals(regNo))
                       .findFirst();
    }
}