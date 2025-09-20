package com.ccrm.models;

import com.ccrm.enums.StudentStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private long id;
    private String regNo;
    private String fullName;
    private String email;
    private StudentStatus status;
    private final List<Enrollment> enrollments;
    private final LocalDateTime registrationDate;

    public Student(long id, String regNo, String fullName, String email) {
        this.id = id;
        this.regNo = regNo;
        this.fullName = fullName;
        this.email = email;
        this.status = StudentStatus.ACTIVE;
        this.enrollments = new ArrayList<>();
        this.registrationDate = LocalDateTime.now();
    }

    // Getters and Setters
    public long getId() { return id; }
    public String getRegNo() { return regNo; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public StudentStatus getStatus() { return status; }
    public void setStatus(StudentStatus status) { this.status = status; }
    public List<Enrollment> getEnrollments() { return enrollments; }
    public LocalDateTime getRegistrationDate() { return registrationDate; }

    public double calculateGPA() {
        if (enrollments.isEmpty()) {
            return 0.0;
        }
        double totalPoints = 0;
        int totalCredits = 0;
        for (Enrollment e : enrollments) {
            totalPoints += e.getGrade().getGradePoint() * e.getCourse().getCredits();
            totalCredits += e.getCourse().getCredits();
        }
        return totalCredits == 0 ? 0.0 : totalPoints / totalCredits;
    }

    @Override
    public String toString() {
        return String.format("Student [ID: %d, RegNo: %s, Name: %s, Email: %s, Status: %s, GPA: %.2f, Registered On: %s]",
                id, regNo, fullName, email, status, calculateGPA(), registrationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }
}