package boundaries;

import entity.Donation;
import utility.IntValidation;
import utility.StringValidation;
import java.util.Scanner;
import java.util.Date;

/**
 * Donation Management UI class
 * Author: Heng Han Yee
 */
public class DonationManagementUI {

    private Scanner scanner = new Scanner(System.in);

    public int getManagementChoice() {
        System.out.print("\033[H\033[2J");
        System.out.println("Donation Management Menu");
        System.out.println("[1] Add New Donation");
        System.out.println("[2] Remove Donation");
        System.out.println("[3] Search Donation Details");
        System.out.println("[4] Amend Donation Details");
        System.out.println("[5] Track Donated Items");
        System.out.println("[6] List All Current Donations");
        System.out.println("[7] Filter Donations");
        System.out.println("[8] Display Summary Report");
        System.out.println("[9] Back");
        int userChoice = IntValidation.inputChoice(9);
        return userChoice;
    }

    public Donation createDonation() {
        Donation donation = new Donation();

        // Set donor name
        donation.setDonorName(getDonorName());

        // Set donation date
        donation.setDonationDate(getDonationDate());


        return donation;
    }

    public String getDonorName() {
        System.out.print("\033[H\033[2J");
        System.out.println("Adding New Donation\n-------------------\n ");
        System.out.print("Enter donor name: ");
        return scanner.nextLine();
    }

    public int getDonationType() {
        int donationType = 0;
        boolean validType = false;
    
        while (!validType) {
            System.out.println("[1] Cash");
            System.out.println("[2] Book");
            System.out.println("[3] Toy");
            System.out.print("Enter donation type: ");  // Use print instead of println
    
            if (scanner.hasNextInt()) {
                donationType = scanner.nextInt();
                scanner.nextLine(); // Consume the leftover newline character
    
                if (donationType >= 1 && donationType <= 3) {
                    validType = true;
                } else {
                    System.out.println("Invalid donation type. Please enter a number between 1 and 3.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
        return donationType;
    }

    public Date getDonationDate() {
        return StringValidation.dateValidation("Enter donation date (dd-MM-yyyy): ");
    }

    public void showSuccessMessage() {
        System.out.println("\nDonation added successfully.");
        System.out.flush();
    }

    public void showErrorMessage(String message) {
        System.out.println(message);
    }

    public int getAmendChoice() {
        System.out.println("What would you like to amend?");
        System.out.println("[1] Donor Name");
        System.out.println("[2] Donation Amount");
        System.out.println("[3] Donation Date");
        System.out.println("[4] Back");
        int amendChoice = IntValidation.inputChoice(4);
        return amendChoice;
    }

    public int getFilterCriteria() {
        System.out.println("Filter by:");
        System.out.println("[1] Donation Type");
        System.out.println("[2] Donation Date");
        System.out.println("[3] Donor Name");
        System.out.println("[4] Back");
        int filterChoice = IntValidation.inputChoice(4);
        return filterChoice;
    }

    public char getConfirmation() {
        System.out.println("This action cannot be undone. Continue?");
        char userChoice = StringValidation.inputYN();
        return userChoice;
    }

    public void displayContinue() {
        System.out.print("\n\nPress Enter to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }
}