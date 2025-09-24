package edu.ccrm.io;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;
import edu.ccrm.domain.Grade;
import edu.ccrm.util.MaxCreditLimitExceededException; // Corrected import path
import edu.ccrm.util.DuplicateEnrollmentException; // Corrected import path
import java.util.ArrayList;
import java.util.List;

public class EnrollmentService {

    private List<Enrollment> enrollments = new ArrayList<>();
    private static final int MAX_CREDITS_PER_SEMESTER = 20;

    public void enrollStudent(Student student, Course course) throws MaxCreditLimitExceededException, DuplicateEnrollmentException {
        boolean alreadyEnrolled = enrollments.stream()
            .anyMatch(e -> e.getStudent().equals(student) && e.getCourse().equals(course));
        if (alreadyEnrolled) {
            throw new DuplicateEnrollmentException("Student " + student.getFullName() + " is already enrolled in course " + course.getTitle());
        }
        
        int currentCredits = enrollments.stream()
            .filter(e -> e.getStudent().equals(student) && e.getCourse().getSemester().equals(course.getSemester()))
            .mapToInt(e -> e.getCourse().getCredits())
            .sum();

        if (currentCredits + course.getCredits() > MAX_CREDITS_PER_SEMESTER) {
            throw new MaxCreditLimitExceededException("Cannot enroll. Student " + student.getFullName() + 
                                                      " would exceed the maximum credit limit for the semester.");
        }

        enrollments.add(new Enrollment(student, course));
        System.out.println("Student " + student.getFullName() + " enrolled in " + course.getTitle());
    }

    public void recordGrade(Student student, Course course, Grade grade) {
        enrollments.stream()
            .filter(e -> e.getStudent().equals(student) && e.getCourse().equals(course))
            .findFirst()
            .ifPresent(e -> e.setGrade(grade));
    }
}