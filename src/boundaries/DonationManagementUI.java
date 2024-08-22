package boundaries;

import entity.Donation;
import utility.DoublyLinkedQueue;
import java.util.Scanner;

/**
 * Donation Management UI class
 * Author: Heng Han Yee
 */
public class DonationManagementUI {
    private Scanner scanner = new Scanner(System.in);

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
    
    public void showDonationDetails(Donation donation) {
        System.out.println("Donation Details:");
        System.out.println("-----------------");
        System.out.println("Donor Name:        " + donation.getDonorName());
        System.out.println("Donee Name:        " + donation.getDoneeName());
        System.out.println("Amount:            RM " + donation.getAmount());
        System.out.println("Donation Date:     " + donation.getDonationDate());
        System.out.println("Distribution Date: " + donation.getDistributionDate());
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
        System.out.println("[1] Track by Donor Name");
        System.out.println("[2] Track by Donation Type");
        System.out.println("[3] Track by Donation Date");
        System.out.println("[4] Track Current Queue");
        return utility.IntValidation.inputChoice(4);
    }

    public void showTrackDonationPrompt(int choice) {
        switch (choice) {
            case 1:
                System.out.println("Enter Donor Name:");
                break;
            case 2:
                System.out.println("Enter Donation Type:");
                break;
            case 3:
                System.out.println("Enter Donation Date (yyyy-mm-dd):");
                break;
            case 4:
                clearScreen();
                System.out.println("=== Current Donation Queue ===");
                break;
        }
    }

    public void showFirstAndLastInQueue(Donation firstDonation, Donation lastDonation) {
        clearScreen();
        System.out.println("=== First Donation in Queue ===");
        showDonationDetails(firstDonation);
        System.out.println("\n=== Last Donation in Queue ===");
        showDonationDetails(lastDonation);
    }


    public void showSuccessMessage(String message) {
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

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
