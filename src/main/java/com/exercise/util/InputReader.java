package com.exercise.util;

import java.util.Scanner;

public class InputReader {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Reads an integer from the console with error handling.
     * @param prompt The prompt to display to the user.
     * @return The integer entered by the user.
     */
    public static int inputInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    /**
     * Reads a string from the console.
     * @param prompt The prompt to display to the user.
     * @return The string entered by the user.
     */
    public static String inputString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
