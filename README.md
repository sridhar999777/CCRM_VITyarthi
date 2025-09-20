# Campus Course & Records Manager (CCRM)

![Java Version](https://img.shields.io/badge/Java-11+-blue.svg)
![Platform](https://img.shields.io/badge/Platform-CLI-lightgrey.svg)
![License](https://img.shields.io/badge/License-MIT-green.svg)

A comprehensive, command-line interface (CLI) application built with modern Java to manage student records, course catalogs, and academic enrollments. This project demonstrates core Java principles, modern APIs, and best practices in software design.

---

## 📜 Table of Contents
- [Features](#-features)
- [Project-Structure](#-project-structure)
- [Technologies-Used](#-technologies-used)
- [Prerequisites](#-prerequisites)
- [Getting-Started](#-getting-started)
- [How-to-Run](#-how-to-run)

---

## ✨ Features

CCRM is built with a modular structure and provides a rich set of features accessible through an interactive menu:

* **🎓 Student Management**:
    * Add, list, update, and deactivate student records.
    * Each student has a unique ID, registration number, name, email, status, and enrollment date/time.
    * Generate a detailed student profile and a full academic transcript, including GPA calculation.

* **📚 Course Management**:
    * Add, list, update, and deactivate courses.
    * Courses are defined by their code, title, credits, instructor, semester, and department.
    * Powerful search and filtering capabilities using the **Java Stream API** (filter by instructor, department, or semester).

* **📝 Enrollment & Grading**:
    * Enroll and unenroll students from courses with built-in business logic (e.g., maximum credits per semester).
    * Record student marks for courses, which automatically compute letter grades (S, A, B, etc.) and grade points.
    * Utilizes Java `Enum` types for robustly handling `Semester` and `Grade` data.

* **💾 File Operations (NIO.2)**:
    * Import student and course data from simple `.csv` text files.
    * Export the current state of students, courses, and enrollments to `.csv` files.
    * **Timestamped Backups**: A backup command copies all exported data files into a timestamped folder for versioning.
    * **Recursive Utility**: Includes a utility function to recursively compute and display the total size of the backup directory.

* **💻 Interactive CLI Workflow**:
    * A user-friendly, menu-driven console for all operations.
    * Demonstrates a wide range of Java constructs, including enhanced `switch` statements, all major loop types (`while`, `do-while`, `for`, `enhanced-for`), and control flow statements (`break`, `continue`, labeled jump).

---

## 📂 Project Structure

The project is organized into a clean, package-based architecture to separate concerns:

```plaintext
CCRM/
└── src/
    └── com/
        └── ccrm/
            ├── Main.java               # Main application entry point and CLI handler
            ├── enums/                  # Enum types (Grade, Semester, etc.)
            │   ├── Grade.java
            │   ├── Semester.java
            │   └── StudentStatus.java
            ├── models/                 # Data model classes (POJOs)
            │   ├── Course.java
            │   ├── Enrollment.java
            │   └── Student.java
            ├── services/               # Business logic and data management
            │   ├── CourseService.java
            │   ├── EnrollmentService.java
            │   └── StudentService.java
            └── utils/                  # Helper and utility classes
                ├── FileHandler.java
                └── InputHelper.java

---

## 🛠️ Technologies Used

* **Language**: Java (JDK 11 or newer)
* **Core APIs**:
    * Java Date/Time API (`java.time`)
    * Java Stream API
    * Java NIO.2 for modern file I/O (`java.nio.file`)
* **IDE**: Developed to be compatible with any modern Java IDE like Visual Studio Code, IntelliJ IDEA, or Eclipse.

---

## ✅ Prerequisites

Before you begin, ensure you have the following installed on your system:

* **Java Development Kit (JDK)**: Version 11 or higher.
* **A Java IDE**: Visual Studio Code (with the "Extension Pack for Java") is recommended for an easy setup.

---

## 🚀 Getting Started

Follow these steps to get the project running on your local machine.

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/your-username/CCRM.git](https://github.com/your-username/CCRM.git)
    ```

2.  **Navigate to the project directory:**
    ```bash
    cd CCRM
    ```

3.  **Open the project in your IDE:**
    * **Visual Studio Code**: Open the root `CCRM` folder (`File > Open Folder...`). Do **not** open the `src` folder directly.
    * **IntelliJ IDEA / Eclipse**: Import the project as a new Java project from existing sources.

---

## ▶️ How to Run

1.  Once the project is open in your IDE, locate the main entry point:
    `src/com/ccrm/Main.java`

2.  Run the `Main.java` file. Most IDEs provide a simple "Run" button:
    * In VS Code, a "Run" link will appear above the `main` method.
    * In IntelliJ or Eclipse, you can right-click the file and select "Run 'Main.main()'".

3.  The application will start, and the main menu will be displayed in the integrated terminal.

### Quick Start Usage

The application includes a `preloadData()` function that automatically creates sample `students.csv` and `courses.csv` files in a `data/` directory.

* On first run, navigate to **Option 4: File Operations**.
* Select **Option 1: Import Students and Courses from CSV** to populate the application with sample data.
* You can now explore other features like listing students, enrolling them in courses, and generating transcripts.
