package com.ccrm;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.ccrm.enums.Semester;
import com.ccrm.enums.StudentStatus;
import com.ccrm.models.Course;
import com.ccrm.models.Student;
import com.ccrm.services.CourseService;
import com.ccrm.services.EnrollmentService;
import com.ccrm.services.StudentService;
import com.ccrm.utils.FileHandler;
import com.ccrm.utils.InputHelper;

public class Main {
    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService();

    public static void main(String[] args) {
        // Pre-load some data for demonstration
        preloadData();
        
        // Main application loop (while loop)
        while (true) {
            printMainMenu();
            int choice = InputHelper.getInt("Enter your choice: ");
            
            // Enhanced switch statement
            switch (choice) {
                case 1 -> handleStudentMenu();
                case 2 -> handleCourseMenu();
                case 3 -> handleEnrollmentMenu();
                case 4 -> handleFileMenu();
                case 0 -> {
                    System.out.println("Exiting application. Goodbye! 👋");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n--- Campus Course & Records Manager ---");
        System.out.println("1. Student Management");
        System.out.println("2. Course Management");
        System.out.println("3. Enrollment & Grading");
        System.out.println("4. File Operations");
        System.out.println("0. Exit");
        System.out.println("---------------------------------------");
    }

    private static void handleStudentMenu() {
        // do-while loop for the submenu
        int choice;
        do {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Add New Student");
            System.out.println("2. List All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Deactivate Student");
            System.out.println("5. View Student Profile & Transcript");
            System.out.println("0. Back to Main Menu");
            choice = InputHelper.getInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    String regNo = InputHelper.getString("Enter Registration No: ");
                    String name = InputHelper.getString("Enter Full Name: ");
                    String email = InputHelper.getString("Enter Email: ");
                    studentService.addStudent(regNo, name, email);
                    System.out.println("Student added successfully!");
                    break;
                case 2:
                    System.out.println("\n--- All Students ---");
                    // Enhanced for loop
                    for (Student s : studentService.getAllStudents()) {
                        System.out.println(s);
                    }
                    break;
                case 3:
                    long idToUpdate = InputHelper.getInt("Enter Student ID to update: ");
                    studentService.findStudentById(idToUpdate).ifPresentOrElse(s -> {
                        s.setFullName(InputHelper.getString("Enter new Full Name: "));
                        s.setEmail(InputHelper.getString("Enter new Email: "));
                        System.out.println("Student updated.");
                    }, () -> System.out.println("Student not found."));
                    break;
                case 4:
                    long idToDeactivate = InputHelper.getInt("Enter Student ID to deactivate: ");
                    studentService.findStudentById(idToDeactivate).ifPresentOrElse(s -> {
                        s.setStatus(StudentStatus.INACTIVE);
                        System.out.println("Student deactivated.");
                    }, () -> System.out.println("Student not found."));
                    break;
                case 5:
                    long idToView = InputHelper.getInt("Enter Student ID to view: ");
                    studentService.findStudentById(idToView).ifPresentOrElse(s -> {
                        studentService.printStudentProfile(s);
                        studentService.printStudentTranscript(s);
                    }, () -> System.out.println("Student not found."));
                    break;
                case 0:
                    break; // break from switch to exit do-while loop
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private static void handleCourseMenu() {
        System.out.println("\n--- Course Management ---");
        System.out.println("1. List All Courses");
        System.out.println("2. Search/Filter Courses");
        System.out.println("0. Back to Main Menu");
        int choice = InputHelper.getInt("Enter choice: ");

        switch (choice) {
            case 1:
                System.out.println("\n--- All Courses ---");
                courseService.getAllCourses().forEach(System.out::println);
                break;
            case 2:
                // Labeled jump example
                SEARCH_BLOCK: {
                    System.out.println("Filter by: 1. Instructor, 2. Department, 3. Semester");
                    int filterChoice = InputHelper.getInt("Enter filter type: ");
                    String filterValue;
                    switch (filterChoice) {
                        case 1:
                            filterValue = InputHelper.getString("Enter Instructor name: ");
                            System.out.println("--- Filtered by Instructor: " + filterValue + " ---");
                            courseService.filterByInstructor(filterValue).forEach(System.out::println);
                            break;
                        case 2:
                            filterValue = InputHelper.getString("Enter Department name: ");
                            System.out.println("--- Filtered by Department: " + filterValue + " ---");
                            courseService.filterByDepartment(filterValue).forEach(System.out::println);
                            break;
                        case 3:
                            filterValue = InputHelper.getString("Enter Semester (SPRING, SUMMER, FALL): ").toUpperCase();
                             try {
                                Semester semester = Semester.valueOf(filterValue);
                                System.out.println("--- Filtered by Semester: " + semester + " ---");
                                courseService.filterBySemester(semester).forEach(System.out::println);
                            } catch (IllegalArgumentException e) {
                                System.out.println("Invalid semester. Aborting search.");
                                break SEARCH_BLOCK; // Labeled break to exit the outer block
                            }
                            break;
                        default:
                            System.out.println("Invalid filter type.");
                    }
                }
                break;
        }
    }

    private static void handleEnrollmentMenu() {
        System.out.println("\n--- Enrollment & Grading ---");
        System.out.println("1. Enroll Student in a Course");
        System.out.println("2. Record Marks/Grade");
        System.out.println("0. Back to Main Menu");
        int choice = InputHelper.getInt("Enter choice: ");

        switch (choice) {
            case 1:
                long studentId = InputHelper.getInt("Enter Student ID: ");
                String courseCode = InputHelper.getString("Enter Course Code: ");
                studentService.findStudentById(studentId).ifPresent(student -> 
                    courseService.findCourseByCode(courseCode).ifPresent(course -> 
                        enrollmentService.enrollStudent(student, course)
                    )
                );
                break;
            case 2:
                long sId = InputHelper.getInt("Enter Student ID: ");
                studentService.findStudentById(sId).ifPresentOrElse(student -> {
                    studentService.printStudentTranscript(student);
                    if (student.getEnrollments().isEmpty()) return;
                    String cCode = InputHelper.getString("Enter Course Code to grade: ");
                    
                    // Classic for loop with continue demonstration
                    for (int i = 0; i < student.getEnrollments().size(); i++) {
                        var enrollment = student.getEnrollments().get(i);
                        if (!enrollment.getCourse().getCode().equalsIgnoreCase(cCode)) {
                            continue; // Skip if this is not the course we are looking for
                        }
                        double marks = InputHelper.getDouble("Enter marks (0-100) for " + cCode + ": ");
                        enrollment.setMarks(marks);
                        System.out.println("Marks recorded. New Grade: " + enrollment.getGrade());
                        return; // Exit after grading
                    }
                    System.out.println("Student is not enrolled in course " + cCode);

                }, () -> System.out.println("Student not found."));
                break;
        }
    }

    private static void handleFileMenu() {
        System.out.println("\n--- File Operations ---");
        System.out.println("1. Import Students and Courses from CSV");
        System.out.println("2. Export All Data");
        System.out.println("3. Create Backup");
        System.out.println("4. Show Backup Directory Size (Recursive Utility)");
        System.out.println("0. Back to Main Menu");
        int choice = InputHelper.getInt("Enter choice: ");

        switch (choice) {
            case 1:
                FileHandler.importStudents("students.csv", studentService);
                FileHandler.importCourses("courses.csv", courseService);
                break;
            case 2:
                FileHandler.exportData(studentService, courseService);
                break;
            case 3:
                FileHandler.backupData();
                break;
            case 4:
                FileHandler.printDirectorySize();
                break;
        }
    }

    private static void preloadData() {
        // Create dummy CSV files if they don't exist
        try {
            Path dataDir = Paths.get("data");
            Files.createDirectories(dataDir);
            Path studentsFile = dataDir.resolve("students.csv");
            Path coursesFile = dataDir.resolve("courses.csv");

            if (!Files.exists(studentsFile)) {
                Files.writeString(studentsFile, "regNo,fullName,email\n" +
                        "2025-CS-01,Alice Johnson,alice.j@example.com\n" +
                        "2025-EE-02,Bob Williams,bob.w@example.com");
            }
            if (!Files.exists(coursesFile)) {
                 Files.writeString(coursesFile, "code,title,credits,instructor,semester,department\n" +
                         "CS101,Intro to Programming,4,Dr. Smith,FALL,Computer Science\n" +
                         "MA202,Calculus II,3,Dr. Jones,FALL,Mathematics\n" +
                         "PHY101,General Physics,4,Dr. Chen,SPRING,Physics");
            }
        } catch (Exception e) {
            System.err.println("Could not create pre-load files.");
        }
    }
}