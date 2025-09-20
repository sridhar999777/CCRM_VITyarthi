package com.ccrm.utils;

import com.ccrm.enums.Semester;
import com.ccrm.models.Course;
import com.ccrm.models.Student;
import com.ccrm.services.CourseService;
import com.ccrm.services.StudentService;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileHandler {

    private static final String DATA_DIR = "data";
    private static final String BACKUP_DIR = "backup";

    public static void importStudents(String fileName, StudentService studentService) {
        Path path = Paths.get(DATA_DIR, fileName);
        try (Stream<String> lines = Files.lines(path)) {
            lines.skip(1) // Skip header
                 .map(line -> line.split(","))
                 .forEach(data -> studentService.addStudent(data[0].trim(), data[1].trim(), data[2].trim()));
            System.out.println("Students imported successfully from " + fileName);
        } catch (IOException e) {
            System.err.println("Error reading student file: " + e.getMessage());
        }
    }

    public static void importCourses(String fileName, CourseService courseService) {
        Path path = Paths.get(DATA_DIR, fileName);
        try (Stream<String> lines = Files.lines(path)) {
            lines.skip(1) // Skip header
                 .map(line -> line.split(","))
                 .forEach(data -> {
                     Course course = new Course(
                             data[0].trim(),
                             data[1].trim(),
                             Integer.parseInt(data[2].trim()),
                             data[3].trim(),
                             Semester.valueOf(data[4].trim().toUpperCase()),
                             data[5].trim()
                     );
                     courseService.addCourse(course);
                 });
            System.out.println("Courses imported successfully from " + fileName);
        } catch (IOException e) {
            System.err.println("Error reading course file: " + e.getMessage());
        }
    }

    public static void exportData(StudentService studentService, CourseService courseService) {
        try {
            Files.createDirectories(Paths.get(DATA_DIR));
            // Export students
            List<String> studentData = studentService.getAllStudents().stream()
                .map(s -> String.join(",", String.valueOf(s.getId()), s.getRegNo(), s.getFullName(), s.getEmail(), s.getStatus().name()))
                .collect(Collectors.toList());
            studentData.add(0, "id,regNo,fullName,email,status");
            Files.write(Paths.get(DATA_DIR, "students_export.csv"), studentData);

            // Export courses
            List<String> courseData = courseService.getAllCourses().stream()
                .map(c -> String.join(",", c.getCode(), c.getTitle(), String.valueOf(c.getCredits()), c.getInstructor(), c.getSemester().name(), c.getDepartment()))
                .collect(Collectors.toList());
            courseData.add(0, "code,title,credits,instructor,semester,department");
            Files.write(Paths.get(DATA_DIR, "courses_export.csv"), courseData);
            
            System.out.println("Data exported successfully to the '" + DATA_DIR + "' directory.");
        } catch (IOException e) {
            System.err.println("Error exporting data: " + e.getMessage());
        }
    }

    public static void backupData() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        Path source = Paths.get(DATA_DIR);
        Path destination = Paths.get(BACKUP_DIR, "backup_" + timestamp);

        try {
            if (!Files.exists(source)) {
                System.out.println("Data directory does not exist. Nothing to backup.");
                return;
            }
            Files.createDirectories(destination);
            try (Stream<Path> stream = Files.walk(source)) {
                stream.forEach(src -> {
                    try {
                        Files.copy(src, destination.resolve(source.relativize(src)), StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        System.err.println("Failed to copy file: " + src + " - " + e.getMessage());
                    }
                });
            }
            System.out.println("Backup created successfully at: " + destination);
        } catch (IOException e) {
            System.err.println("Error creating backup: " + e.getMessage());
        }
    }
    
    // Recursive utility to compute directory size
    public static void printDirectorySize() {
        Path path = Paths.get(BACKUP_DIR);
        if (!Files.exists(path)) {
            System.out.println("Backup directory does not exist.");
            return;
        }
        try {
            AtomicLong size = new AtomicLong(0);
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    size.addAndGet(attrs.size());
                    return FileVisitResult.CONTINUE;
                }
            });
            System.out.printf("Total size of backup directory '%s': %.2f MB\n", path, size.get() / (1024.0 * 1024.0));
        } catch (IOException e) {
            System.err.println("Error calculating directory size: " + e.getMessage());
        }
    }
}