package utility;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.text.ParseException;

/**
 * Utility class for validating character inputs.
 * Contains methods for validating 'Y' and 'N' choices.
 * All methods are static and can be accessed using the class name.
 * 
 * @author Heng Han Yee
 *         Ho Zhi Xuen
 */
public class StringValidation {

    // Static Scanner instance for reading user input
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Prompts the user to enter a 'Y' or 'N' choice and validates the input.
     * The input must be either 'Y' for Yes or 'N' for No. If the input is valid, it returns the user's choice. Otherwise, it prompts the user again until a valid input is received.
     *
     * @return the valid user choice, 'Y' for Yes or 'N' for No
     */
    public static char inputYN() {
        char input = ' ';
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
                System.out.println("Please enter 'Y' or 'N' only!");
                scanner.nextLine(); // Clear the invalid input
                hasError = true;
            }
        } while (hasError);

        return input;
    }
    
    /**
     * Prompts the user for a string input and validates that it contains only alphabetic characters.
     * If the input is invalid, the user is prompted again.
     *
     * @param displayText  The message to be displayed to the user for input.
     * @return             The validated string containing only alphabetic characters.
     */
    public static String alphabetValidation(String displayText) {
        String input = "";
        boolean valid = false;

        while (!valid) {
            System.out.print(displayText); // Display the prompt message to the user
            input = scanner.nextLine().trim(); // Read the input and trim any leading/trailing spaces

            // Validate that the input contains only alphabetic characters
            if (input.matches("[a-zA-Z ]+")) {
                valid = true; // Input is valid, exit the loop
            } else {
                System.out.println("Invalid input. Please enter only alphabetic characters.");
            }
        }

        return input; // Return the validated input
    }
    
    /**
     * Prompts the user for a date input and validates it against the specified date format.
     * If the input is invalid, the user is prompted again.
     *
     * @param displayText  The message to be displayed to the user for input.
     * @return             The validated date as a Date object.
     */
    public static Date dateValidation(String displayText) {
        Date date = null;
        boolean valid = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false); // Ensure strict date validation

        while (!valid) {
            System.out.print(displayText); // Display the prompt message to the user
            String input = scanner.nextLine().trim(); // Read the input and trim any leading/trailing spaces

            // Validate the input against the date format
            if (input.matches("\\d{2}-\\d{2}-\\d{4}")) { // Regex to ensure correct format (dd-MM-yyyy)
                try {
                    date = dateFormat.parse(input); // Attempt to parse the input as a date
                    valid = true; // Input is valid, exit the loop
                } catch (ParseException e) {
                    System.out.println("Invalid date. Please enter a valid date in dd-MM-yyyy format.");
                }
            } else {
                System.out.println("Invalid date format. Please enter the date in dd-MM-yyyy format.");
            }
        }

        return date; // Return the validated Date object
    }
}
