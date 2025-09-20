package com.ccrm.services;

import com.ccrm.enums.Semester;
import com.ccrm.models.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CourseService {
    private final List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }

    public Optional<Course> findCourseByCode(String code) {
        return courses.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst();
    }

    // Stream API for filtering
    public List<Course> filterByInstructor(String instructor) {
        return courses.stream()
                .filter(c -> c.getInstructor().equalsIgnoreCase(instructor))
                .collect(Collectors.toList());
    }

    public List<Course> filterByDepartment(String department) {
        return courses.stream()
                .filter(c -> c.getDepartment().equalsIgnoreCase(department))
                .collect(Collectors.toList());
    }

    public List<Course> filterBySemester(Semester semester) {
        return courses.stream()
                .filter(c -> c.getSemester() == semester)
                .collect(Collectors.toList());
    }
}