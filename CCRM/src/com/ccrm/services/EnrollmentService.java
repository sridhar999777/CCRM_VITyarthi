package com.ccrm.services;

import com.ccrm.models.Course;
import com.ccrm.models.Enrollment;
import com.ccrm.models.Student;

public class EnrollmentService {
    private static final int MAX_CREDITS_PER_SEMESTER = 20;

    public boolean enrollStudent(Student student, Course course) {
        // Business Rule: Check if already enrolled
        boolean isAlreadyEnrolled = student.getEnrollments().stream()
                .anyMatch(e -> e.getCourse().getCode().equalsIgnoreCase(course.getCode()));
        if (isAlreadyEnrolled) {
            System.out.println("Error: Student is already enrolled in this course.");
            return false;
        }

        // Business Rule: Check max credits per semester
        int currentCredits = student.getEnrollments().stream()
                .filter(e -> e.getCourse().getSemester() == course.getSemester())
                .mapToInt(e -> e.getCourse().getCredits())
                .sum();
        
        if (currentCredits + course.getCredits() > MAX_CREDITS_PER_SEMESTER) {
            System.out.println("Error: Exceeds max credits (" + MAX_CREDITS_PER_SEMESTER + ") for the semester.");
            return false;
        }

        Enrollment enrollment = new Enrollment(student, course);
        student.getEnrollments().add(enrollment);
        System.out.println("Enrollment successful!");
        return true;
    }
}