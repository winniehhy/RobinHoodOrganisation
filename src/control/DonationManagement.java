package control;

import entity.Donation;
import utility.*;

import java.util.Date;
import java.util.Scanner;

import boundaries.DonationManagementUI;

/**
 * Donation Management Class
 * Author: Heng Han Yee
 */
public class DonationManagement {

    private static DoublyLinkedQueue<Donation> donationQueue = new DoublyLinkedQueue<>();
    private static DonationManagementUI ui = new DonationManagementUI();
    private static Scanner scanner = new Scanner(System.in);

    public static void addDonation() {
        // Prompt user for details
        ui.showAddDonationPrompt();
        String donorName = StringValidation.alphabetValidation("Enter donor name: ");
        Date donationDate = StringValidation.dateValidation("Enter donation date (dd-MM-yyyy): ");
        int donationType = inputDonationType();

        int amount = 0;
        if (donationType == 1) {
            amount = inputInteger("Enter amount to donate (whole number): ");
        }

        // Create and enqueue new Donation object
        Donation donation = new Donation();
        donation.setDonorName(donorName);
        donation.setDonationDate(donationDate);
        donation.setDonationType(donationType);
        donation.setAmount(amount);

        donationQueue.enqueue(donation);
        ui.showSuccessMessage("Donation added successfully!");

        ui.displayContinue();
    }
    public static void removeDonation() {
        if (donationQueue.isEmpty()) {
            ui.showErrorMessage("No donations available to remove.");
            return;
        }
    
        String donorName = StringValidation.alphabetValidation("Enter donor name to remove: ");
    
        // Find the donation associated with the donor name
        Donation donationToRemove = null;
        for (Donation donation : donationQueue) {
            if (donation.getDonorName().equalsIgnoreCase(donorName)) {
                donationToRemove = donation;
                break;
            }
        }
    
        // If donation found, confirm deletion
        if (donationToRemove != null) {
            ui.showDonationDetails(donationToRemove); // Display donation details
            char confirmation = ui.getConfirmation("Are you sure you want to delete this donation? (Y/N): ");
            if (confirmation == 'Y' || confirmation == 'y') {
                donationQueue.remove(donationToRemove); // Remove the donation from the queue
                ui.showSuccessMessage("Donation removed successfully!");
            } else {
                ui.showSuccessMessage("No changes made.");
            }
        } else {
            ui.showErrorMessage("No donation found for the donor: " + donorName);
        }
    
        ui.displayContinue();
    }
    
    public static void searchDonationInformation() {
        ui.showSearchDonationPrompt();
        String donorName = StringValidation.alphabetValidation("Enter donor name to search: ");
        
        Donation foundDonation = null;
        for (Donation donation : donationQueue) {
            if (donation.getDonorName().equalsIgnoreCase(donorName)) {
                foundDonation = donation;
                break;
            }
        }
    
        if (foundDonation != null) {
            ui.showDonationDetails(foundDonation);
        } else {
            ui.showErrorMessage("No donation found for the donor: " + donorName);
        }
    
        ui.displayContinue();
    }
    public static void amendDonation() {
        if (donationQueue.isEmpty()) {
            ui.showErrorMessage("No donations available to amend.");
            return;
        }

        // Display current donations
        ui.showDonationQueue(donationQueue);

        // Confirm if user wants to amend
        char confirmation = ui.getConfirmation("This action cannot be done. Continue?");
        if (confirmation == 'Y' || confirmation == 'y') {
            Donation firstDonation = donationQueue.peek();  // Get the first donation in the queue

            // Update details
            ui.showAmendDonationPrompt();
            firstDonation.setDonorName(StringValidation.alphabetValidation("Enter new donor name: "));
            firstDonation.setDonationDate(StringValidation.dateValidation("Enter new donation date (dd-MM-yyyy): "));
            firstDonation.setDonationType(inputDonationType());
            if (firstDonation.getDonationType() == 1) {
                firstDonation.setAmount(inputInteger("Enter new amount to donate (whole number): "));
            }

            ui.showSuccessMessage("Donation details updated successfully!");
        } else {
            ui.showSuccessMessage("No changes made.");
        }

        ui.displayContinue();
    }
    public static void trackDonation() {
        int choice = ui.getTrackDonationChoice();

        switch (choice) {
            case 1:
                ui.showTrackDonationPrompt(1);
                String donorName = StringValidation.alphabetValidation("Enter donor name to track: ");
                trackByDonorName(donorName);
                break;
            case 2:
                ui.showTrackDonationPrompt(2);
                int donationType = inputDonationType();
                trackByDonationType(donationType);
                break;
            case 3:
                ui.showTrackDonationPrompt(3);
                Date donationDate = StringValidation.dateValidation("Enter donation date to track (dd-MM-yyyy): ");
                trackByDonationDate(donationDate);
                break;
            case 4:
                trackCurrentQueue();
                break;
            default:
                ui.showErrorMessage("Invalid choice.");
        }
        ui.displayContinue();
    }
    public static void listDonations() {
        if (donationQueue.isEmpty()) {
            ui.showErrorMessage("No donations available to display.");
        } else {
            System.out.println("=== Current Donations ===");
            for (Donation donation : donationQueue) {
                ui.showDonationDetails(donation);
            }
        }
        ui.displayContinue();
    }
    
    private static void trackByDonorName(String donorName) {
        boolean found = false;
        for (Donation donation : donationQueue) {
            if (donation.getDonorName().equalsIgnoreCase(donorName)) {
                ui.showDonationDetails(donation);
                found = true;
            }
        }
        if (!found) {
            ui.showErrorMessage("No donation found for the donor: " + donorName);
        }
    }
    private static void trackByDonationType(int donationType) {
        boolean found = false;
        for (Donation donation : donationQueue) {
            if (donation.getDonationType() == donationType) {
                ui.showDonationDetails(donation);
                found = true;
            }
        }
        if (!found) {
            ui.showErrorMessage("No donation found for the type: " + donationType);
        }
    }
    private static void trackByDonationDate(Date donationDate) {
        boolean found = false;
        for (Donation donation : donationQueue) {
            if (donation.getDonationDate().equals(donationDate)) {
                ui.showDonationDetails(donation);
                found = true;
            }
        }
        if (!found) {
            ui.showErrorMessage("No donation found for the date: " + donationDate);
        }
    }
    private static void trackCurrentQueue() {
        if (!donationQueue.isEmpty()) {
            Donation firstDonation = donationQueue.getFirst();
            Donation lastDonation = donationQueue.getLast();
            ui.showFirstAndLastInQueue(firstDonation, lastDonation);
        } else {
            ui.showErrorMessage("The donation queue is empty.");
        }
    }
    

    private static int inputDonationType() {
        int donationType = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print("Choose donation type (1: Cash, 2: Books, 3: Toys): ");
            try {
                donationType = Integer.parseInt(scanner.nextLine().trim());
                if (donationType >= 1 && donationType <= 3) {
                    valid = true;
                } else {
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        return donationType;
    }

    private static int inputInteger(String prompt) {
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


    public static void main(String[] args) {
        while (true) {
            int choice = ui.getManagementChoice();
            switch (choice) {
                case 1:
                    addDonation();
                    break;
                case 2:
                    removeDonation();
                    break;
                case 3:
                    searchDonationInformation();
                    break;
                case 4:
                    amendDonation();
                    break;
                case 5:
                    trackDonation();
                    break;
                case 6:
                    listDonations();
                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    ui.showErrorMessage("Invalid choice! Please choose again.");
            }
        }
    }
}
