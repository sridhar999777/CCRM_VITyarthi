# CCRM Usage Guide

This guide provides detailed instructions on how to use the Campus Course & Records Manager (CCRM) application.

---

## ▶️ Running the Application

1.  Open the project in a Java IDE (like VS Code or IntelliJ).
2.  Locate and run the `Main.java` file.
3.  The application will start in your IDE's integrated terminal, displaying the main menu.

---

## 🗺️ Main Menu Overview

The application is controlled through a series of numerical menus. The main menu provides access to all major modules:
--- Campus Course & Records Manager ---
1. Student Management
2. Course Management
3. Enrollment & Grading
4. File Operations
5. Exit

---

## 🚀 Step-by-Step Workflows

Here are common workflows to guide you through the application's features.

### 1. Initial Setup: Importing Data

This is the recommended first step to populate the system with sample data.

1.  From the main menu, enter `4` to go to **File Operations**.
2.  In the File Operations menu, enter `1` to **Import Students and Courses from CSV**.
3.  The system will read from the `students.csv` and `courses.csv` files located in the `data/` directory and load the records.

### 2. Managing Students

This module handles all student-related tasks.

#### Adding a New Student
1.  From the main menu, enter `1` for **Student Management**.
2.  Enter `1` to **Add New Student**.
3.  Follow the prompts to enter the student's **Registration No**, **Full Name**, and **Email**.

#### Viewing a Student's Profile and Transcript
1.  From the Student Management menu, enter `5` for **View Student Profile & Transcript**.
2.  Enter the **Student ID** of the student you wish to view (you can find this ID by listing all students first with option `2`).
3.  The application will display the student's profile information, followed by a formatted transcript of their enrolled courses, grades, and current GPA.

### 3. Managing Courses

This module allows you to view and filter course information.

#### Filtering Courses by Instructor
1.  From the main menu, enter `2` for **Course Management**.
2.  Enter `2` to **Search/Filter Courses**.
3.  Enter `1` to filter by **Instructor**.
4.  Enter the full name of the instructor (e.g., `Dr. Smith`).
5.  A list of all courses taught by that instructor will be displayed.

### 4. Handling Enrollments and Grades

This is the core academic module for managing student-course relationships.

#### Enrolling a Student in a Course
1.  From the main menu, enter `3` for **Enrollment & Grading**.
2.  Enter `1` to **Enroll Student in a Course**.
3.  Enter the **Student ID** and the **Course Code** (e.g., `CS101`).
4.  The system will validate the enrollment against business rules (e.g., max credit limits) and confirm upon success.

#### Recording Marks for a Course
1.  From the Enrollment & Grading menu, enter `2` to **Record Marks/Grade**.
2.  Enter the **Student ID**. A list of their currently enrolled courses will be shown.
3.  Enter the **Course Code** for which you want to record marks.
4.  Enter the marks obtained by the student (0-100).
5.  The system will automatically calculate and assign a letter grade (S, A, B, etc.) and update the student's overall GPA.

### 5. Exporting and Backing Up Data

This module handles data persistence and recovery.

1.  From the main menu, enter `4` for **File Operations**.
2.  To save the current data, select option `2` to **Export All Data**. This will overwrite the `.csv` files in the `data/` directory with the current application state.
3.  To create a safe backup, select option `3` to **Create Backup**. This copies all files from the `data/` directory into a new, timestamped folder inside the `backup/` directory.
