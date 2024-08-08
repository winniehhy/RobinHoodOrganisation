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
     * Prompts the user to enter an integer choice and validates the input.
     * The valid input must be within the range from 1 to the specified upper limit (inclusive).
     * If the input is valid, it returns the user's choice. Otherwise, it prompts the user again until a valid input is received.
     *
     * @param range the upper limit of the valid input range (inclusive)
     * @return the valid user choice within the specified range
     */
    public static int inputChoice(int range) {
        int userChoice = 0;
        boolean hasError;
    
        do {
            hasError = false;
            System.out.print("Enter choice: ");
            try {
                userChoice = scanner.nextInt();
    
                if (userChoice >= 1 && userChoice <= range) {
                    // Input is within the valid range
                    break;
                } else {
                    // Input is outside the valid range
                    throw new IllegalArgumentException();
                }
    
            } catch (IllegalArgumentException | InputMismatchException ex) {
                // Handle invalid input, either out of range or non-integer
                System.out.println("Please enter a value between 1 and " + range + "!");
                scanner.nextLine(); // Clear the invalid input
                hasError = true;
            }
        } while (hasError);
    
        return userChoice;
    }
}
