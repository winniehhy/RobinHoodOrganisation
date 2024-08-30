package control;

import entity.*;
import utility.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import boundaries.DonationManagementUI;

public class DonationManagement {

    private static DonationManagementUI ui = new DonationManagementUI();
    private static Scanner scanner = new Scanner(System.in);

    public static String getDistributionType(int donationType) {
        switch (donationType) {
            case 1: return "Cash";
            case 2: return "Book";
            case 3: return "Toy";
            default: return "";
        }
    }

    public static DoublyLinkedQueue<Donation> getQueueByDonationType(int donationType, DoublyLinkedQueue<Donation> cashQueue, DoublyLinkedQueue<Donation> bookQueue, DoublyLinkedQueue<Donation> toyQueue) {
        switch (donationType) {
            case 1:
                return cashQueue;
            case 2:
                return bookQueue;
            case 3:
                return toyQueue;
            default:
                throw new IllegalArgumentException("Invalid donation type: " + donationType);
        }
    }

    public static void addDonation(DoublyLinkedQueue<Donation> cashQueue, DoublyLinkedQueue<Donation> bookQueue, DoublyLinkedQueue<Donation> toyQueue) {
        ui.showAddDonationPrompt();
        String donorName = StringValidation.alphabetValidation("Enter donor name: ");
        Date donationDate = StringValidation.dateValidation("Enter donation date (dd-MM-yyyy): ");
        int donationType = ui.inputDonationType();
    
        int amount = 0;
        switch (donationType) {
            case 1: // Cash Donation
                amount = ui.inputInteger("Enter amount to donate (whole number): ");
                break;
            case 2: // Book Donation
                amount = ui.inputInteger("Enter number of books to donate: ");
                break;
            case 3: // Toy Donation
                amount = ui.inputInteger("Enter number of toys to donate: ");
                break;
        }
    
        Donation donation = new Donation();
        donation.setDonorName(donorName);
        donation.setDonationDate(donationDate);
        donation.setDonationType(donationType);
        donation.setAmount(amount);
    
        switch (donationType) {
            case 1:
                cashQueue.enqueue(donation);
                break;
            case 2:
                bookQueue.enqueue(donation);
                break;
            case 3:
                toyQueue.enqueue(donation);
                break;
        }
        ui.showMessage("Donation added successfully!");
    
        ui.displayContinue();
    }
    

    public static void removeDonation(DoublyLinkedQueue<Donation> cashQueue, DoublyLinkedQueue<Donation> bookQueue, DoublyLinkedQueue<Donation> toyQueue) {
        String donorName = StringValidation.alphabetValidation("Enter donor name to remove: ");
        int donationType = ui.inputDonationType();
        DoublyLinkedQueue<Donation> queue = getQueueByDonationType(donationType, cashQueue, bookQueue, toyQueue);

        if (queue.isEmpty()) {
            ui.showErrorMessage("No donations available to remove.");
            return;
        }

        Donation donationToRemove = null;
        for (Donation donation : queue) {
            if (donation.getDonorName().equalsIgnoreCase(donorName)) {
                donationToRemove = donation;
                break;
            }
        }

        if (donationToRemove != null) {
            ui.showDonationDetails(donationToRemove);
            char confirmation = ui.getConfirmation("Are you sure you want to delete this donation? (Y/N): ");
            if (confirmation == 'Y' || confirmation == 'y') {
                queue.remove(donationToRemove);
                ui.showMessage("Donation removed successfully!");
            } else {
                ui.showMessage("No changes made.");
            }
        } else {
            ui.showErrorMessage("No donation found for the donor: " + donorName + " in the selected donation type.");
        }

        ui.displayContinue();
    }

    public static void searchDonation(DoublyLinkedQueue<Donation> cashQueue, DoublyLinkedQueue<Donation> bookQueue, DoublyLinkedQueue<Donation> toyQueue) {
        ui.showSearchDonationPrompt();
        String donorName = StringValidation.alphabetValidation("Enter donor name to search: ");
        int donationType = ui.inputDonationType();

        Donation foundDonation = null;
        DoublyLinkedQueue<Donation> selectedQueue = getQueueByDonationType(donationType, cashQueue, bookQueue, toyQueue);

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

    public static void trackDonation(DoublyLinkedQueue<Donation> cashQueue, DoublyLinkedQueue<Donation> bookQueue, DoublyLinkedQueue<Donation> toyQueue) {
        int choice;
        do {
            choice = ui.getTrackDonationChoice();
    
            if (choice == 1) {
                int donationType = ui.inputDonationType();
                DoublyLinkedQueue<Donation> selectedQueue = getQueueByDonationType(donationType, cashQueue, bookQueue, toyQueue);
    
                if (selectedQueue.isEmpty()) {
                    ui.showErrorMessage("No donations available for the selected donation type.");
                } else {
                    ui.showDonationListHeader(getDistributionType(donationType));
    
                    int count = 0;
                    double total = 0;
                    for (Donation donation : selectedQueue) {
                        ui.showDonationDetails(donation);
                        total += donation.getAmount();
                        count++;
                    }
    
                    String type = (donationType == 1) ? "RM " : "";
                    ui.showTotal(count, total, type);
                }
            } else if (choice == 2) {
                Date donationDate = StringValidation.dateValidation("Enter donation date to track (dd-MM-yyyy): ");
                boolean found = false;
    
                for (DoublyLinkedQueue<Donation> queue : new DoublyLinkedQueue[]{cashQueue, bookQueue, toyQueue}) {
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
            } else if (choice == 3) {
                int cashCount = cashQueue.size();
                int bookCount = bookQueue.size();
                int toyCount = toyQueue.size();
    
                String mostDonatedType;
                int maxCount = Math.max(cashCount, Math.max(bookCount, toyCount));
    
                if (maxCount == cashCount) {
                    mostDonatedType = "Cash";
                } else if (maxCount == bookCount) {
                    mostDonatedType = "Books";
                } else {
                    mostDonatedType = "Toys";
                }
    
                ui.showMessage("Most donated item type: " + mostDonatedType + " with " + maxCount + " donations.");
            } else if (choice != 4) {
                ui.showErrorMessage("Invalid choice.");
            }
    
            ui.displayContinue();
        } while (choice != 4);
    }

    public static void amendDonation(DoublyLinkedQueue<Donation> cashQueue, DoublyLinkedQueue<Donation> bookQueue, DoublyLinkedQueue<Donation> toyQueue) {
        ui.clearScreen();
        String donorName = StringValidation.alphabetValidation("Enter donor name to amend: ");
        int donationType = ui.inputDonationType();
        DoublyLinkedQueue<Donation> queue = getQueueByDonationType(donationType, cashQueue, bookQueue, toyQueue);

        if (queue.isEmpty()) {
            ui.showErrorMessage("No donations available to amend.");
            return;
        }

        Donation donationToAmend = null;
        for (Donation donation : queue) {
            if (donation.getDonorName().equalsIgnoreCase(donorName)) {
                donationToAmend = donation;
                break;
            }
        }

        if (donationToAmend != null) {
            ui.showDonationDetails(donationToAmend);
            char confirmation = ui.getConfirmation("Do you want to update details? (Y/N): ");
            if (confirmation == 'Y' || confirmation == 'y') {
                System.out.println("Choose the detail to update:");
                System.out.println("[1] Donor Name");
                System.out.println("[2] Donation Date");
                if (donationType == 1) {
                    System.out.println("[3] Amount");
                }
                int updateChoice = ui.inputInteger("Enter your choice: ");

                switch (updateChoice) {
                    case 1:
                        String newDonorName = StringValidation.alphabetValidation("Enter new donor name: ");
                        donationToAmend.setDonorName(newDonorName);
                        break;
                    case 2:
                        Date newDonationDate = StringValidation.dateValidation("Enter new donation date (dd-MM-yyyy): ");
                        donationToAmend.setDonationDate(newDonationDate);
                        break;
                    case 3:
                        if (donationType == 1) {
                            int newAmount = ui.inputInteger("Enter new amount: ");
                            donationToAmend.setAmount(newAmount);
                        } else {
                            ui.showErrorMessage("Invalid choice.");
                        }
                        break;
                    default:
                        ui.showErrorMessage("Invalid choice.");
                }

                ui.showMessage("Donation details updated successfully!");
                ui.showDonationDetails(donationToAmend);
            } else {
                ui.showMessage("No changes made.");
            }
        } else {
            ui.showErrorMessage("No donation found for the donor: " + donorName + " in the selected donation type.");
        }

        ui.displayContinue();
    }

    public static void listDonations(DoublyLinkedQueue<Donation> cashQueue, DoublyLinkedQueue<Donation> bookQueue, DoublyLinkedQueue<Donation> toyQueue) {
        int option = ui.listDonationsOptions();
        ui.clearScreen();
        
        if (option == 1) {
            String donorName = StringValidation.alphabetValidation("Enter donor name to search: ");
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
        } else if (option == 2) {
            System.out.println("=== All Current Available Donations ===");
    
            double totalCash = 0.0;
            double totalBook = 0.0;
            double totalToy = 0.0;
    
            if (!cashQueue.isEmpty()) {
                System.out.println("\n");
                ui.showDonationListHeader("Cash");
                for (Donation donation : cashQueue) {
                    ui.showDonationDetails(donation);
                    totalCash += donation.getAmount();
                }
            } else {
                System.out.println("No cash donations available.");
            }
    
            if (!bookQueue.isEmpty()) {
                System.out.println("\n");
                ui.showDonationListHeader("Book");
                for (Donation donation : bookQueue) {
                    ui.showDonationDetails(donation);
                    totalBook += donation.getAmount();
                }
            } else {
                System.out.println("No book donations available.");
            }
    
            if (!toyQueue.isEmpty()) {
                System.out.println("\n");
                ui.showDonationListHeader("Toy");
                for (Donation donation : toyQueue) {
                    ui.showDonationDetails(donation);
                    totalToy += donation.getAmount();
                }
            } else {
                System.out.println("No toy donations available.");
            }
    
            System.out.println("\n=== Donation Totals ===");
            System.out.printf("Total Cash Donations: RM%.2f%n", totalCash);
            System.out.printf("Total Book Donations: %.1f%n", totalBook);
            System.out.printf("Total Toy Donations: %.1f%n", totalToy);
        } else {
            ui.showErrorMessage("Invalid choice! Returning to management menu.");
        }
    
        ui.displayContinue();
    }

    public static void filterDonation(DoublyLinkedQueue<Donation> cashQueue, DoublyLinkedQueue<Donation> bookQueue, DoublyLinkedQueue<Donation> toyQueue) {
        int choice;
        do {
            ui.filterDonationsMenu();
            choice = utility.IntValidation.inputChoice(4);
    
            if (choice == 1) {
                ui.clearScreen();
                int year = utility.IntValidation.inputYear();
                int total = 0;
                boolean found = false;
    
                System.out.println("Filtering for the year: " + year);
    
                for (Donation donation : cashQueue) {
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
            } else if (choice == 2) {
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
                int freqChoice = utility.IntValidation.inputChoice(2);
    
                for (Map.Entry<String, Integer> entry : donorFrequency.entrySet()) {
                    if ((freqChoice == 1 && entry.getValue() == 1) || (freqChoice == 2 && entry.getValue() > 1)) {
                        System.out.println("Donor: " + entry.getKey() + " | Donations: " + entry.getValue());
                    }
                }
            } else if (choice == 3) {
                System.out.println("Filter by Amount:");
                System.out.println("[1] Less than 500");
                System.out.println("[2] More than 500");
                int amountChoice = utility.IntValidation.inputChoice(2);
    
                for (Donation donation : cashQueue) {
                    if ((amountChoice == 1 && donation.getAmount() < 500) || (amountChoice == 2 && donation.getAmount() >= 500)) {
                        System.out.println(donation);
                    }
                }
            } else if (choice != 4) {
                ui.showErrorMessage("Invalid choice.");
            }
    
            ui.displayContinue();
        } while (choice != 4);
    }

    public static void displaySummary(DoublyLinkedQueue<Donation> cashQueue, DoublyLinkedQueue<Donation> bookQueue, DoublyLinkedQueue<Donation> toyQueue) {

        ui.clearScreen();
        System.out.printf("%60s", "Donation Summary\n");
    
        for (int i = 0; i < 90; i++) {
            System.out.print("=");
        }
        System.out.println();
        System.out.printf("     | %-20s | %-8s | %-27s | %-17s |\n", "Donor", "Type", "Donation Date", "Amount");
        for (int i = 0; i < 103; i++) {
            System.out.print("=");
        }
        System.out.println();
    
        // Queue Array and Types Array
        DoublyLinkedQueue<Donation>[] queues = new DoublyLinkedQueue[]{cashQueue, bookQueue, toyQueue};
        String[] types = new String[]{"Cash", "Book", "Toy"};
    
        int count = 0;
        double totalCash = 0, totalBook = 0, totalToy = 0;
        double[] totals = new double[]{totalCash, totalBook, totalToy};
    
        for (int i = 0; i < queues.length; i++) {
            for (Donation donation : queues[i]) {
                System.out.printf("[%2d] | %-20s | %-8s | %-17s | RM%-14.2f |\n",
                    ++count,
                    donation.getDonorName(),
                    types[i],
                    donation.getDonationDate(),
                    (double) donation.getAmount()); // Cast amount to double
    
                totals[i] += donation.getAmount();
            }
        }
    
        for (int i = 0; i < 103; i++) {
            System.out.print("=");
        }
        System.out.println();
    
        System.out.println("Total count: " + count);
        System.out.println("Total Cash donations: RM " + totals[0]);
        System.out.println("Total Book donations: RM " + totals[1]);
        System.out.println("Total Toy donations: RM " + totals[2]);
    
        ui.displayContinue();
        main(null);
    }
    
    
    
    public static void main(String[] args) {
        DoublyLinkedQueue<Donation> cashQueue = RobinHoodOrganisation.cashDonationQueue;
        DoublyLinkedQueue<Donation> bookQueue = RobinHoodOrganisation.bookDonationQueue;
        DoublyLinkedQueue<Donation> toyQueue = RobinHoodOrganisation.toyDonationQueue;

        while (true) {
            int choice = ui.getManagementChoice();
            switch (choice) {
                case 1:
                    addDonation(cashQueue, bookQueue, toyQueue);
                    break;
                case 2:
                    removeDonation(cashQueue, bookQueue, toyQueue);
                    break;
                case 3:
                    searchDonation(cashQueue, bookQueue, toyQueue);
                    break;
                case 4:
                    amendDonation(cashQueue, bookQueue, toyQueue);
                    break;
                case 5:
                    trackDonation(cashQueue, bookQueue, toyQueue);
                    break;
                case 6:
                    listDonations(cashQueue, bookQueue, toyQueue);
                    break;
                case 7:
                    filterDonation(cashQueue, bookQueue, toyQueue);
                    break;
                case 8:
                    displaySummary(cashQueue, bookQueue, toyQueue);
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