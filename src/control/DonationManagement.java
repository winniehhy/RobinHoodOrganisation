package control;

import entity.*;
import utility.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import boundaries.DonationManagementUI;

/**
 * Author: Heng Han Yee
 */
public class DonationManagement {

    private static DoublyLinkedQueue<Donation> cashDonationQueue = RobinHoodOrganisation.cashDonationQueue;
    private static DoublyLinkedQueue<Donation> bookDonationQueue = RobinHoodOrganisation.bookDonationQueue;
    private static DoublyLinkedQueue<Donation> toyDonationQueue = RobinHoodOrganisation.toyDonationQueue;
    private static DonationManagementUI ui = new DonationManagementUI();
    private static Scanner scanner = new Scanner(System.in);

    // Private Methods
    private static String getDistributionType(int donationType) {
        switch (donationType) {
            case 1: return "Cash";
            case 2: return "Book";
            case 3: return "Toy";
            default: return "";
        }
    }

    private static DoublyLinkedQueue<Donation> getQueueByDonationType(int donationType) {
        switch (donationType) {
            case 1:
                return cashDonationQueue;
            case 2:
                return bookDonationQueue;
            case 3:
                return toyDonationQueue;
            default:
                throw new IllegalArgumentException("Invalid donation type: " + donationType);
        }
    }

    private static void trackByDonationType(int donationType) {
        DoublyLinkedQueue<Donation> selectedQueue = getQueueByDonationType(donationType);
    
        if (selectedQueue.isEmpty()) {
            ui.showErrorMessage("No donations available for the selected donation type.");
            return;
        }
    
        ui.showDonationListHeader(getDistributionType(donationType));
    
        int count = 0;
        double total = 0;
        for (Donation donation : selectedQueue) {
            ui.showDonationDetails(donation); // Display formatted details
            total += donation.getAmount();
            count++;
        }
    
        String type = (donationType == 1) ? "RM " : "";
        ui.showTotal(count, total, type);
    }
    

    private static void trackByDonationDate(Date donationDate) {
        boolean found = false;

        for (DoublyLinkedQueue<Donation> queue : new DoublyLinkedQueue[]{cashDonationQueue, bookDonationQueue, toyDonationQueue}) {
            for (Donation donation : queue) {
                if (donation.getDonationDate().equals(donationDate)) {
                    ui.showDonationDetails(donation);
                    found = true;
                }
            }
        }

        if (!found) {
            ui.showErrorMessage("No donations found for the date: " + donationDate);
        }
    }

    // Public Methods
    public static void addDonation() {
        // Prompt user for details
        ui.showAddDonationPrompt();
        String donorName = StringValidation.alphabetValidation("Enter donor name: ");
        Date donationDate = StringValidation.dateValidation("Enter donation date (dd-MM-yyyy): ");
        int donationType = ui.inputDonationType();

        int amount = 0;
        if (donationType == 1) {
            amount = ui.inputInteger("Enter amount to donate (whole number): ");
        }

        // Create and enqueue new Donation object
        Donation donation = new Donation();
        donation.setDonorName(donorName);
        donation.setDonationDate(donationDate);
        donation.setDonationType(donationType);
        donation.setAmount(amount);

        switch (donationType) {
            case 1:
                cashDonationQueue.enqueue(donation);
                break;
            case 2:
                bookDonationQueue.enqueue(donation);
                break;
            case 3:
                toyDonationQueue.enqueue(donation);
                break;
        }
        ui.showSuccessMessage("Donation added successfully!");

        ui.displayContinue();
    }

    public static void removeDonation() {

        // Prompt for the donor name first
        String donorName = StringValidation.alphabetValidation("Enter donor name to remove: ");
        
        // Then, ask for the donation type
        int donationType = ui.inputDonationType();
        DoublyLinkedQueue<Donation> queue = getQueueByDonationType(donationType);
    
        if (queue.isEmpty()) {
            ui.showErrorMessage("No donations available to remove.");
            return;
        }
    
        // Find the donation associated with the donor name in the selected queue
        Donation donationToRemove = null;
        for (Donation donation : queue) {
            if (donation.getDonorName().equalsIgnoreCase(donorName)) {
                donationToRemove = donation;
                break;
            }
        }
    
        // If donation is found, confirm deletion
        if (donationToRemove != null) {
            ui.showDonationDetails(donationToRemove); // Display donation details
            char confirmation = ui.getConfirmation("Are you sure you want to delete this donation? (Y/N): ");
            if (confirmation == 'Y' || confirmation == 'y') {
                queue.remove(donationToRemove); // Remove the donation from the queue
                ui.showSuccessMessage("Donation removed successfully!");
            } else {
                ui.showSuccessMessage("No changes made.");
            }
        } else {
            ui.showErrorMessage("No donation found for the donor: " + donorName + " in the selected donation type.");
        }
    
        ui.displayContinue();
    }
    
    public static void searchDonation() {
        ui.showSearchDonationPrompt();
        String donorName = StringValidation.alphabetValidation("Enter donor name to search: ");
        int donationType = ui.inputDonationType();  // Prompt user to choose the donation type
    
        Donation foundDonation = null;
    
        // Select the appropriate queue based on the donation type
        DoublyLinkedQueue<Donation> selectedQueue = getQueueByDonationType(donationType);
    
        // Search in the selected queue for a matching donor name
        for (Donation donation : selectedQueue) {
            if (donation.getDonorName().equalsIgnoreCase(donorName)) {
                foundDonation = donation;
                break;
            }
        }
    
        if (foundDonation != null) {
            ui.showDonationDetails(foundDonation);
        } else {
            ui.showErrorMessage("No donation found for the donor: " + donorName + " with the selected donation type.");
        }
    
        ui.displayContinue();
    }
    
    public static void trackDonation() {
        int choice = ui.getTrackDonationChoice();

        switch (choice) {
            case 1:
                // Track by Donation Type
                int donationType = ui.inputDonationType();
                trackByDonationType(donationType);
                break;
            case 2:
                // Track by Donation Date
                Date donationDate = StringValidation.dateValidation("Enter donation date to track (dd-MM-yyyy): ");
                trackByDonationDate(donationDate);
                break;
            case 3:
                // List All Donations
                listAllDonations(bookDonationQueue, bookDonationQueue, bookDonationQueue);
                break;
            case 4:
                // Back to Management Menu
                ui.getTrackDonationChoice();
                break;
            default:
                ui.showErrorMessage("Invalid choice.");
        }
        ui.displayContinue();
    }

    public static void amendDonation() {
        System.out.print("\033[H\033[2J");
        // Prompt for the donor name first
        String donorName = StringValidation.alphabetValidation("Enter donor name to amend: ");
        
        // Then, ask for the donation type
        int donationType = ui.inputDonationType();
        DoublyLinkedQueue<Donation> queue = getQueueByDonationType(donationType);
    
        if (queue.isEmpty()) {
            ui.showErrorMessage("No donations available to amend.");
            return;
        }
    
        // Find the donation associated with the donor name in the selected queue
        Donation donationToAmend = null;
        for (Donation donation : queue) {
            if (donation.getDonorName().equalsIgnoreCase(donorName)) {
                donationToAmend = donation;
                break;
            }
        }
    
        // If donation is found, confirm amendment
        if (donationToAmend != null) {
            ui.showDonationDetails(donationToAmend); // Display current donation details
            
            char confirmation = ui.getConfirmation("Do you want to update details? (Y/N): ");
            if (confirmation == 'Y' || confirmation == 'y') {
    
                // Allow user to choose what to update
                System.out.println("Choose the detail to update:");
                System.out.println("[1] Donor Name");
                System.out.println("[2] Donation Date");
                if (donationType == 1) {
                    System.out.println("[3] Amount");
                }
                int updateChoice = ui.inputInteger("Enter your choice: ");
    
                switch (updateChoice) {
                    case 1:
                        // Update Donor Name
                        String newDonorName = StringValidation.alphabetValidation("Enter new donor name: ");
                        donationToAmend.setDonorName(newDonorName);
                        break;
                    case 2:
                        // Update Donation Date
                        Date newDonationDate = StringValidation.dateValidation("Enter new donation date (dd-MM-yyyy): ");
                        donationToAmend.setDonationDate(newDonationDate);
                        break;
                    case 3:
                        if (donationType == 1) {
                            // Update Amount
                            int newAmount = ui.inputInteger("Enter new amount: ");
                            donationToAmend.setAmount(newAmount);
                        } else {
                            ui.showErrorMessage("Invalid choice.");
                        }
                        break;
                    default:
                        ui.showErrorMessage("Invalid choice.");
                }
    
                ui.showSuccessMessage("Donation details updated successfully!");
                ui.showDonationDetails(donationToAmend); // Show updated details
            } else {
                ui.showSuccessMessage("No changes made.");
            }
        } else {
            ui.showErrorMessage("No donation found for the donor: " + donorName + " in the selected donation type.");
        }
    
        ui.displayContinue();
    }
    
    public static void listDonations() {
        int option = ui.listDonationsOptions();
        switch (option) {
            case 1: // List by Donor Name
                String donorName = StringValidation.alphabetValidation("Enter donor name to search: ");
                listDonationsByDonorName(donorName, cashDonationQueue, bookDonationQueue, toyDonationQueue);
                break;
            case 2: // List All Current Items
                listAllDonations(cashDonationQueue, bookDonationQueue, toyDonationQueue);
                break;
            default:
                ui.showErrorMessage("Invalid choice! Returning to management menu.");
        }
        ui.displayContinue();
    }

    public static void listDonationsByDonorName(String donorName, DoublyLinkedQueue<Donation> cashQueue, DoublyLinkedQueue<Donation> bookQueue, DoublyLinkedQueue<Donation> toyQueue) {
        boolean found = false;
    
        for (Donation donation : cashQueue) {
            if (donation.getDonorName().equalsIgnoreCase(donorName)) {
                ui.showDonationDetails(donation);
                found = true;
            }
        }
        for (Donation donation : bookQueue) {
            if (donation.getDonorName().equalsIgnoreCase(donorName)) {
                ui.showDonationDetails(donation);
                found = true;
            }
        }
        for (Donation donation : toyQueue) {
            if (donation.getDonorName().equalsIgnoreCase(donorName)) {
                ui.showDonationDetails(donation);
                found = true;
            }
        }
    
        if (!found) {
            ui.showErrorMessage("No donations found for the donor: " + donorName);
        }
    }
    

    public static void listAllDonations(DoublyLinkedQueue<Donation> cashQueue, DoublyLinkedQueue<Donation> bookQueue, DoublyLinkedQueue<Donation> toyQueue) {
        ui.clearScreen();
        System.out.println("=== All Current Available Donations ===");
    
        double totalCash = 0.0;
    
        // Cash donations
        if (!cashQueue.isEmpty()) {
            System.out.println("\n");
            ui.showDonationListHeader("Cash");
            for (Donation donation : cashQueue) {
                ui.showDonationDetails(donation);
                totalCash += donation.getAmount(); // Assuming getAmount() returns the donation amount
            }
        } else {
            System.out.println("No cash donations available.");
        }
    
        // Book donations
        if (!bookQueue.isEmpty()) {
            System.out.println("\n");
            ui.showDonationListHeader("Book");
            for (Donation donation : bookQueue) {
                ui.showDonationDetails(donation);
            }
        } else {
            System.out.println("No book donations available.");
        }
    
        // Toy donations
        if (!toyQueue.isEmpty()) {
            System.out.println("\n");
            ui.showDonationListHeader("Toy");
            for (Donation donation : toyQueue) {
                ui.showDonationDetails(donation);
            }
        } else {
            System.out.println("No toy donations available.");
        }
    
        // Display totals
        System.out.println("\n=== Donation Totals ===");
        System.out.printf("Total Cash Donations: RM%.2f%n", totalCash);
    
        ui.displayContinue();
    }
    
    public static void filterDonation(DoublyLinkedQueue<Donation> cashQueue, DoublyLinkedQueue<Donation> bookQueue, DoublyLinkedQueue<Donation> toyQueue) {
        DonationManagementUI ui = new DonationManagementUI();
        ui.filterDonationsMenu();
        int choice = utility.IntValidation.inputChoice(4);

        switch (choice) {
            case 1:
                filterByYear(cashQueue, bookQueue, toyQueue);
                break;
            case 2:
                filterByFrequency(cashQueue, bookQueue, toyQueue);
                break;
            case 3:
                filterByAmountSize(cashQueue);
                break;
            default:
                ui.showErrorMessage("Invalid choice.");
        }
        ui.displayContinue();
    }

    public static void filterByYear(DoublyLinkedQueue<Donation> cashQueue, DoublyLinkedQueue<Donation> bookQueue, DoublyLinkedQueue<Donation> toyQueue) {
        ui.clearScreen();
        int year = utility.IntValidation.inputYear();
        int total = 0;
        boolean found = false;

        System.out.println("Filtering for the year: " + year);

        for (Donation donation : cashQueue) {
            // Adjusting to compare the year correctly
            if (donation.getDonationDate().getYear() + 1900 == year) {
                System.out.println(donation);
                total += donation.getAmount();
                found = true;
            }
        }

        for (Donation donation : bookQueue) {
            if (donation.getDonationDate().getYear() + 1900 == year) {
                System.out.println(donation);
                found = true;
            }
        }

        for (Donation donation : toyQueue) {
            if (donation.getDonationDate().getYear() + 1900 == year) {
                System.out.println(donation);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No donations found for the year " + year);
        } else {
            System.out.printf("Total cash donations in %d: RM %d\n", year, total);
        }
    }

    public static void filterByFrequency(DoublyLinkedQueue<Donation> cashQueue, DoublyLinkedQueue<Donation> bookQueue, DoublyLinkedQueue<Donation> toyQueue) {
        Map<String, Integer> donorFrequency = new HashMap<>();

        for (Donation donation : cashQueue) {
            donorFrequency.put(donation.getDonorName(), donorFrequency.getOrDefault(donation.getDonorName(), 0) + 1);
        }

        for (Donation donation : bookQueue) {
            donorFrequency.put(donation.getDonorName(), donorFrequency.getOrDefault(donation.getDonorName(), 0) + 1);
        }

        for (Donation donation : toyQueue) {
            donorFrequency.put(donation.getDonorName(), donorFrequency.getOrDefault(donation.getDonorName(), 0) + 1);
        }

        System.out.println("Filter by Frequency:");
        System.out.println("[1] One-time Donor");
        System.out.println("[2] Regular Donor");
        int choice = utility.IntValidation.inputChoice(2);

        for (Map.Entry<String, Integer> entry : donorFrequency.entrySet()) {
            if ((choice == 1 && entry.getValue() == 1) || (choice == 2 && entry.getValue() > 1)) {
                System.out.println("Donor: " + entry.getKey() + " | Donations: " + entry.getValue());
            }
        }
    }

    public static void filterByAmountSize(DoublyLinkedQueue<Donation> cashQueue) {
        System.out.println("Filter by Amount:");
        System.out.println("[1] Less than 500");
        System.out.println("[2] More than 500");
        int choice = utility.IntValidation.inputChoice(2);

        for (Donation donation : cashQueue) {
            if ((choice == 1 && donation.getAmount() < 500) || (choice == 2 && donation.getAmount() >= 500)) {
                System.out.println(donation);
            }
        }
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
                    searchDonation();
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
                case 7:
                    filterDonation(cashDonationQueue, bookDonationQueue, toyDonationQueue);
                    break;
                case 8:
                   // displaySummary();
                    break;
                case 9:
                    RobinHoodOrganisation.main(null);
                    break;
                default:
                    ui.showErrorMessage("Invalid choice! Please choose again.");
            }
        }
    }
}
