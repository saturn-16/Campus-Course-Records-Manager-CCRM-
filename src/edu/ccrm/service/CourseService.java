package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Instructor;
import edu.ccrm.domain.Semester;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CourseService {

    private List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        boolean exists = courses.stream().anyMatch(c -> c.getCode().equals(course.getCode()));
        if (!exists) {
            courses.add(course);
            System.out.println("Course added successfully: " + course.getTitle());
        } else {
            System.out.println("Error: Course with code " + course.getCode() + " already exists.");
        }
    }

    public List<Course> listAllCourses() {
        return new ArrayList<>(courses);
    }

    public List<Course> searchByInstructor(Instructor instructor) {
        return courses.stream()
                      .filter(c -> c.getInstructor().equals(instructor))
                      .collect(Collectors.toList());
    }

    public List<Course> searchByDepartment(String department) {
        return courses.stream()
                      .filter(c -> c.getDepartment().equalsIgnoreCase(department))
                      .collect(Collectors.toList());
    }
    
    public List<Course> searchBySemester(Semester semester) {
        return courses.stream()
                      .filter(c -> c.getSemester().equals(semester))
                      .collect(Collectors.toList());
    }
}