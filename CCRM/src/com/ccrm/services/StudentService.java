package com.ccrm.services;

import com.ccrm.models.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentService {
    private final List<Student> students = new ArrayList<>();
    private long nextId = 1;

    public Student addStudent(String regNo, String fullName, String email) {
        Student student = new Student(nextId++, regNo, fullName, email);
        students.add(student);
        return student;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public Optional<Student> findStudentById(long id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
    }
    
    public void printStudentProfile(Student student) {
        System.out.println("--- Student Profile ---");
        System.out.println(student);
        System.out.println("-----------------------");
    }

    public void printStudentTranscript(Student student) {
        System.out.println("\n--- Transcript for " + student.getFullName() + " ---");
        System.out.println("Reg No: " + student.getRegNo());
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("%-10s | %-30s | %-5s | %-15s | %s\n", "Code", "Title", "Grade", "Semester", "Marks");
        System.out.println("-------------------------------------------------------------------------");
        if (student.getEnrollments().isEmpty()) {
            System.out.println("No courses enrolled.");
        } else {
            // Polymorphism in action: calling toString() on each Enrollment object
            student.getEnrollments().forEach(System.out::println);
        }
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("Cumulative GPA: %.2f\n", student.calculateGPA());
    }
}