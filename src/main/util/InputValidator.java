package util;

import java.util.Scanner;

/**
 * Utility helpers for safe console input and common validation rules
 * (marks range, non-empty strings, menu choices).
 */
public class InputValidator {

    private InputValidator() {
        // utility class
    }

    /**
     * Reads a non-empty line from the scanner, re-prompting until valid.
     *
     * @param scanner console scanner
     * @param prompt  message shown to the user
     * @return trimmed non-empty string
     */
    public static String readNonEmptyString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    /**
     * Reads an integer from the scanner, catching non-numeric input.
     *
     * @param scanner console scanner
     * @param prompt  message shown to the user
     * @return the parsed integer
     */
    public static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid number. Please enter a whole number.");
            }
        }
    }

    /**
     * Reads an integer within an inclusive range.
     *
     * @param scanner console scanner
     * @param prompt  message shown to the user
     * @param min     inclusive minimum
     * @param max     inclusive maximum
     * @return a valid integer in [min, max]
     */
    public static int readIntInRange(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            int value = readInt(scanner, prompt);
            if (value >= min && value <= max) {
                return value;
            }
            System.out.println("Please enter a number between " + min + " and " + max + ".");
        }
    }

    /**
     * Reads marks as a double in the range 0–100 inclusive.
     *
     * @param scanner console scanner
     * @param prompt  message shown to the user
     * @return valid marks in [0, 100]
     */
    public static double readMarks(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                double marks = Double.parseDouble(line);
                if (marks < 0 || marks > 100) {
                    System.out.println("Marks must be between 0 and 100.");
                    continue;
                }
                return marks;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid marks. Please enter a numeric value.");
            }
        }
    }

    /**
     * Checks whether a marks value is in the valid range [0, 100].
     *
     * @param marks marks to validate
     * @return true if marks are valid
     */
    public static boolean isValidMarks(double marks) {
        return marks >= 0 && marks <= 100;
    }

    /**
     * Checks whether a string is non-null and non-blank after trimming.
     *
     * @param value candidate string
     * @return true if usable as an ID or name
     */
    public static boolean isNonEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
