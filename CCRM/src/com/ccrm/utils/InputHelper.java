package com.ccrm.utils;

import java.util.Scanner;

public class InputHelper {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next(); // Consume the invalid input
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return value;
    }

    public static String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    
    public static double getDouble(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }
}