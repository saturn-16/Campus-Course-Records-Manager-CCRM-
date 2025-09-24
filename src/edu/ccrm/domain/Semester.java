package edu.ccrm.domain;

public enum Semester {
    SPRING(1),
    SUMMER(2),
    FALL(3);

    private final int semesterNumber;

    Semester(int semesterNumber) {
        this.semesterNumber = semesterNumber;
    }

    public int getSemesterNumber() {
        return semesterNumber;
    }
}