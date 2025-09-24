package edu.ccrm.domain;

import java.time.LocalDate;

public class Instructor extends Person {

    private String department;

    public Instructor(String id, String fullName, String email, LocalDate dateOfBirth, String department) {
        super(id, fullName, email, dateOfBirth);
        this.department = department;
    }

    @Override
    public void printProfile() {
        System.out.println("--- Instructor Profile ---");
        System.out.println("ID: " + getId());
        System.out.println("Full Name: " + getFullName());
        System.out.println("Email: " + getEmail());
        System.out.println("Department: " + this.department);
    }

    public String getDepartment() {
        return department;
    }
}