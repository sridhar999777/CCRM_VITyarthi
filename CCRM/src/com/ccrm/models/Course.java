package com.ccrm.models;

import com.ccrm.enums.Semester;

public class Course {
    private String code;
    private String title;
    private int credits;
    private String instructor;
    private Semester semester;
    private String department;
    private boolean active;

    public Course(String code, String title, int credits, String instructor, Semester semester, String department) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.instructor = instructor;
        this.semester = semester;
        this.department = department;
        this.active = true;
    }

    // Getters and Setters
    public String getCode() { return code; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }
    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }
    public Semester getSemester() { return semester; }
    public String getDepartment() { return department; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    @Override
    public String toString() {
        return String.format("Course [Code: %s, Title: %s, Credits: %d, Instructor: %s, Semester: %s, Dept: %s, Active: %b]",
                code, title, credits, instructor, semester, department, active);
    }
}