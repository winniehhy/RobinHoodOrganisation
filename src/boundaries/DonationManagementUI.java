package boundaries;

import entity.Donation;
import utility.*;
import ADT.*;

import java.util.Scanner;

import control.RobinHoodOrganisation;

/**
 * 
 * Author: Heng Han Yee
 */
public class DonationManagementUI {
    private static Scanner scanner = new Scanner(System.in);

    public int getManagementChoice() {
        clearScreen();
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
        return utility.IntValidation.inputChoice(9);
    }

    public void showAddDonationPrompt() {
        clearScreen();
        System.out.println("=== Add New Donation ===");
    }

    public String showRemoveDonationPrompt() {
        System.out.print("Enter the donor's name to remove: ");
        return scanner.nextLine().trim();  
    }

    public void showSearchDonationPrompt() {
        clearScreen();
        System.out.println("=== Search Donation Details ===");
    }   

    public void showAmendDonationPrompt() {
        clearScreen();
        System.out.println("=== Amend Donation Details ===");
    }

    public void showDonationQueue(DoublyLinkedQueue<Donation> queue) {
        clearScreen();
        System.out.println("=== Current Available Donation ===");
        for (Donation donation : queue) {
            System.out.println(donation);
        }
    }

    public int getTrackDonationChoice() {
        clearScreen();
        System.out.println("=== Track Donation Items ===");
        System.out.println("[1] Track by Donation Type");
        System.out.println("[2] Track by Donation Date");
        System.out.println("[3] Track by Most Donated item");
        System.out.println("[4] Back to Management Menu");
        return utility.IntValidation.inputChoice(4);
    }

    public int inputDonationType() {
        System.out.println("Select Donation Type:");
        System.out.println("[1] Cash");
        System.out.println("[2] Books");
        System.out.println("[3] Toys");
        return IntValidation.inputChoice(3);
    }

    public void showDonationListHeader(String donationType) {
        String header = String.format("%50s\n", donationType + " Donations");
        String separator = "=".repeat(88);
        
        String columnHeaders;
        if ("Cash".equals(donationType)) {
            columnHeaders = String.format("| %-15s  | %-15s | %-15s | %-28s | \n", 
                                        "Donor", "Donated Item", "Amount", "Donation Date");
        } else { // Toy or Book
            columnHeaders = String.format("| %-15s  | %-15s | %-15s | %-28s | \n", 
                                        "Donor", "Donated Item", "Amount", "Donation Date");
        }
    
        System.out.print(header);
        System.out.println(separator);
        System.out.print(columnHeaders);
        System.out.println(separator);
    }    
    
    public void showDonationDetails(Donation donation) {
        String formattedRow;
        if (donation.getDonationType() == 1) { // Cash Donation
            formattedRow = String.format("| %-16s | %-15s | RM %-12d | %-25s | \n",
                                        donation.getDonorName(),
                                        "Cash",
                                        donation.getAmount(),
                                        donation.getDonationDate() != null ? donation.getDonationDate() : "N/A");
        } else { // Toy or Book Donation
            String itemType = donation.getDonationType() == 2 ? "Books" : "Toys";
            formattedRow = String.format("| %-15s  | %-15s | %-15d | %-25s |\n",
                                        donation.getDonorName(),
                                        itemType,
                                        donation.getAmount(),
                                        donation.getDonationDate() != null ? donation.getDonationDate() : "N/A");
        }
    
        System.out.print(formattedRow);
    }    
    

    public void showTotal(int count, double total, String type) {
        String separator = "=".repeat(88);
        System.out.println(separator);
        System.out.printf("Total count: %d\n", count);
        System.out.printf("Total donation amount: %s%.2f\n", type, total);
    }

    public int listDonationsOptions() {
        clearScreen();
        System.out.println("Choose an option:");
        System.out.println("[1] List donations by donor name");
        System.out.println("[2] List all available donation items");
        return utility.IntValidation.inputChoice(2);  // Expecting a choice between 1 and 2
    }

    public void filterDonationsMenu() {
        clearScreen();
        System.out.println("=== Filter Donations ===");
        System.out.println("[1] Filter by Year");
        System.out.println("[2] Filter by Donation Frequency");
        System.out.println("[3] Filter by Donation Amount Size");
        System.out.println("[4] Back to Management Menu");
    }


    public static int inputInteger(String prompt) {
        int value = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine().trim());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }

        return value;
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showErrorMessage(String message) {
        System.out.println(message);
    }

    public char getConfirmation(String message) {
        System.out.println(message);
        char confirmation = scanner.nextLine().trim().toUpperCase().charAt(0);
        return confirmation;
    }

    public void displayContinue() {
        System.out.print("\nPress Enter to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

