package utility;

import java.util.Scanner;

/**
 * Utility class for validating character inputs.
 * Contains methods for validating 'Y' and 'N' choices.
 * All methods are static and can be accessed using the class name.
 * 
 * @author Ho Zhi Xuen
 */
public class YesNoValidation {

    // Static Scanner instance for reading user input
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Prompts the user to enter a 'Y' or 'N' choice and validates the input.
     * The input must be either 'Y' for Yes or 'N' for No. If the input is valid, it returns the user's choice. Otherwise, it prompts the user again until a valid input is received.
     *
     * @return the valid user choice, 'Y' for Yes or 'N' for No
     */
    public static char inputYN() {
        char input;
        boolean hasError;

        do {
            hasError = false;
            System.out.print("Enter choice [Y/N]: ");
            try {
                // Read user input and convert it to uppercase
                input = scanner.next().toUpperCase().charAt(0);

                // Validate input
                if (input != 'Y' && input != 'N') {
                    // Input is invalid
                    System.out.println("Please enter 'Y' or 'N' only!");
                    hasError = true;
                }
            } catch (Exception ex) {
                // Handle any unexpected exceptions
                System.out.println("An error occurred. Please enter 'Y' or 'N' only!");
                scanner.nextLine(); // Clear the invalid input
                hasError = true;
            }
        } while (hasError);

        return input;
    }
}
