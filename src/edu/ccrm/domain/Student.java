package edu.ccrm.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person {

    private String regNo;
    private String status;
    private List<Enrollment> enrolledCourses;

    public Student(String id, String regNo, String fullName, String email, LocalDate dateOfBirth, String status) {
        super(id, fullName, email, dateOfBirth);
        this.regNo = regNo;
        this.status = status;
        this.enrolledCourses = new ArrayList<>();
    }

    @Override
    public void printProfile() {
        System.out.println("--- Student Profile ---");
        System.out.println("ID: " + getId());
        System.out.println("Registration No: " + this.regNo);
        System.out.println("Full Name: " + getFullName());
        System.out.println("Email: " + getEmail());
        System.out.println("Status: " + this.status);
    }
    
    public String getRegNo() {
        return regNo;
    }

    public List<Enrollment> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void addEnrollment(Enrollment enrollment) {
        this.enrolledCourses.add(enrollment);
    }
    
    public void removeEnrollment(Enrollment enrollment) {
        this.enrolledCourses.remove(enrollment);
    }
}