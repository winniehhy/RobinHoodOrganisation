package utility;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Utility class for integer validation tasks.
 * Contains methods for validating integer inputs.
 * All methods are static and can be accessed using the class name.
 *
 * @author Ho Zhi Xuen
 */
public class IntValidation {

    // Static Scanner instance for reading user input
    private static final Scanner scanner = new Scanner(System.in);
  
     /**
     * Prompts the user to enter a choice within a specified range and validates the input.
     * If the input is invalid or out of range, the user is prompted again.
     *
     * @param range  The upper bound of the valid input range (inclusive). The lower bound is always 1.
     * @return       The validated choice entered by the user.
     */
    public static int inputChoice(int range) {
        int userChoice = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter choice: ");
            try {
                userChoice = scanner.nextInt(); // Read the user input

                // Validate that the choice is within the acceptable range
                if (userChoice >= 1 && userChoice <= range) {
                    validInput = true; // Valid choice, exit loop
                } else {
                    // Choice is out of range
                    System.out.println("Please enter a value between 1 and " + range + ".");
                }
            } catch (InputMismatchException ex) {
                // Handle case where input is not an integer
                System.out.println("Invalid input. Please enter an integer value.");
                scanner.next(); // Clear the invalid input from the scanner buffer
            }
        }

        return userChoice; // Return the validated user choice
    }
    
    /**
     * Prompts the user for an integer input and validates it based on whether negative numbers are allowed.
     * If the input is invalid or does not meet the criteria, the user is prompted again.
     *
     * @param displayText     The message to be displayed to the user for input.
     * @param allowNegatives  A flag indicating whether negative integers are permitted.
     * @return                The validated integer input from the user.
     */
    public static int integerValidation(String displayText, boolean allowNegatives) {
        int integer = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print(displayText); // Display the prompt message to the user
            try {
                integer = scanner.nextInt(); // Read the integer input from the user

                // Check if negative numbers are allowed and if the input meets the criteria
                if (!allowNegatives && integer < 0) {
                    System.out.println("Please enter positive integers only.");
                } else {
                    valid = true; // Input is valid, exit the loop
                }
            } catch (InputMismatchException e) {
                // Handle the case where the input is not an integer
                System.out.println("Invalid input. Please enter an integers value.");
                scanner.next(); // Clear the invalid input
            }
        }

        return integer; // Return the validated integer
    }

    public static int inputYear() {
        int year = 0;
        boolean valid = false;
        Scanner scanner = new Scanner(System.in);
    
        while (!valid) {
            System.out.print("Enter the year: ");
            try {
                year = Integer.parseInt(scanner.nextLine().trim());
                if (year > 0) {
                    valid = true;
                } else {
                    System.out.println("Invalid input. Please enter a valid year.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid year.");
            }
        }
        return year;
    }
}
