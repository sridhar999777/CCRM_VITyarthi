package com.ccrm.models;

import com.ccrm.enums.Grade;

public class Enrollment {
    private final Student student;
    private final Course course;
    private Grade grade;
    private double marks;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.grade = Grade.NOT_ASSIGNED;
        this.marks = 0;
    }

    // Getters and Setters
    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public Grade getGrade() { return grade; }
    public double getMarks() { return marks; }

    public void setMarks(double marks) {
        this.marks = marks;
        this.grade = Grade.fromMarks(marks);
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-30s | %-5s | %-15s | %.2f",
                course.getCode(), course.getTitle(), grade, course.getSemester(), marks);
    }
}