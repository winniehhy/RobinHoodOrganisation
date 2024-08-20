package control;

import entity.*;
import utility.*;
import boundaries.DonationManagementUI;

import java.util.Date;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Heng Han Yee
 */
public class DonationManagement {
    static Scanner scanner = new Scanner(System.in);
    public static DonationManagementUI managementUI = new DonationManagementUI();

    // Queues for managing donations of different types
    static LinkedQueue<Donation> cashDonationQueue = new LinkedQueue<>();
    static LinkedQueue<Donation> bookDonationQueue = new LinkedQueue<>();
    static LinkedQueue<Donation> toyDonationQueue = new LinkedQueue<>();

    public static void addDonation() {
        // Create a new donation object
        Donation donation = managementUI.createDonation();
        int donationType = managementUI.getDonationType();

        // Add the donation to the appropriate queue based on the type
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
            default:
                managementUI.showErrorMessage("Invalid donation type.");
                return ;
        }

        managementUI.showSuccessMessage();
        managementUI.displayContinue();
    }

    public static void removeDonation(LinkedQueue<Donation> donationQueue) {
        // Check if the queue is empty
        if (donationQueue.isEmpty()) {
            System.out.println("No donations available to remove.");
            return;
        }
        // Prompt user for donor name
        System.out.print("Enter the donor name to remove donations: ");
        String donorNameToRemove = scanner.nextLine();
        // Flag to check if any donation was found for the donor
        boolean found = false;
        // Create a temporary queue to hold donations while filtering
        LinkedQueue<Donation> tempQueue = new LinkedQueue<>();
        // Display all donations for the given donor and copy other donations to tempQueue
        while (!donationQueue.isEmpty()) {
            Donation donation = donationQueue.dequeue();
            if (donation.getDonorName().equalsIgnoreCase(donorNameToRemove)) {
                // If matching donor name, display the donation details
                System.out.println("Donation Found:");
                System.out.println("Donation Type: " + getDonationTypeName(donation));
                System.out.println("Donation Date: " + donation.getDonationDate());
                System.out.println("Donor Name: " + donation.getDonorName());
                System.out.print("Do you want to delete this donation? (Y/N): ");
                char confirmation = scanner.nextLine().toUpperCase().charAt(0);
    
                if (confirmation == 'Y') {
                    System.out.println("Donation item had been successfully removed.");
                    found = true;
                    // Do not enqueue back to the original queue
                    continue; // Skip adding this donation to the tempQueue
                } else {
                    System.out.println("Donation item was not removed.");
                    // Add donation back to the tempQueue for re-processing
                    tempQueue.enqueue(donation);
                }
            } else {
                // Enqueue donations not matching the donor name
                tempQueue.enqueue(donation);
            }
        }
    
        // Refill the original queue with the remaining donations from tempQueue
        while (!tempQueue.isEmpty()) {
            donationQueue.enqueue(tempQueue.dequeue());
        }
    
        if (!found) {
            System.out.println("No donations found for the given donor name.");
        }
    }
    

    public static void searchDonation(LinkedQueue<Donation> donationQueue) {
        System.out.println(" Searching for details.");
    }

    public static void AmendDonation(LinkedQueue<Donation> donationQueue) {
        // Check if there is any donation in the queue
        if (donationQueue.isEmpty()) {
            managementUI.showErrorMessage("No donations available to amend.");
            return;
        }
        // Assuming we amend the first donation in the queue for simplicity
        Donation donation = donationQueue.peek();
        // Display current donation details
        System.out.println("Current Donation Information:");
        System.out.println("Donor Name: " + donation.getDonorName());
        System.out.println("Donation Date: " + donation.getDonationDate());
        System.out.println("Donation Type: " + getDonationTypeName(donation));
    
        // Ask if the user wants to amend the details
        System.out.print("Do you want to update this information? (Y/N): ");
        char confirmation = managementUI.getConfirmation();
    
        if (confirmation == 'Y' || confirmation == 'y') {
            // Remove the old donation (simulate clearing the record)
            donationQueue.dequeue();
            // Get new donation details
            String newDonorName = managementUI.getDonorName();
            Date newDonationDate = managementUI.getDonationDate();
            int newDonationType = managementUI.getDonationType();
            // Create a new donation with updated details
            Donation newDonation = new Donation();
            
            // Add the new donation back to the appropriate queue
            switch (newDonationType) {
                case 1:
                    cashDonationQueue.enqueue(newDonation);
                    break;
                case 2:
                    bookDonationQueue.enqueue(newDonation);
                    break;
                case 3:
                    toyDonationQueue.enqueue(newDonation);
                    break;
                default:
                    managementUI.showErrorMessage("Invalid donation type.");
                    return;
            }
            
            // Display updated donation details
            System.out.println("Updated Donation Information:");
            System.out.println("Donor Name: " + newDonation.getDonorName());
            System.out.println("Donation Date: " + newDonation.getDonationDate());
            System.out.println("Donation Type: " + getDonationTypeName(newDonation));
            managementUI.showSuccessMessage();
        } else {
            System.out.println("No changes were made.");
        }
        managementUI.displayContinue();
    }
    
    public static String getDonationTypeName(Donation donation) {
        if (cashDonationQueue.contains(donation)) {
            return "Cash";
        } else if (bookDonationQueue.contains(donation)) {
            return "Book";
        } else if (toyDonationQueue.contains(donation)) {
            return "Toy";
        }
        return "Unknown";
    }
    
    public static void TrackDonation(LinkedQueue<Donation> donationQueue) {
        System.out.println(" Choose ur option to track .");
    }
    public static void ListDonation(LinkedQueue<Donation> donationQueue) {
        System.out.println(" listed all available donation items .");
    }
    public static void FilterDonation(LinkedQueue<Donation> donationQueue) {
        System.out.println(" pick one to filter .");
    }


    public static void displaySummary(LinkedQueue<Donation> donationQueue) {
        // Logic for displaying a summary of donations in the queue
    }

    public static LinkedQueue<Donation> getDonationQueue(int donationType) {
        switch (donationType) {
            case 1:
                return cashDonationQueue;
            case 2:
                return bookDonationQueue;
            case 3:
                return toyDonationQueue;
        }
        return null;
    }

    public static void main(String[] args) {
        while (true) {
            int userChoice = managementUI.getManagementChoice();
    
            // Only get the donation type when it's necessary
            LinkedQueue<Donation> donationQueue = null;
    
            switch (userChoice) {
                case 1:
    
                    addDonation();
                    break;
                case 2:
                    removeDonation(donationQueue);
                    break;
                case 3:
                    searchDonation(donationQueue);
                    break;
                case 4:
                    AmendDonation(donationQueue);
                    break;
                case 5:
                    TrackDonation(donationQueue);
                    break;
                case 6:
                    ListDonation(donationQueue);
                    break;
                case 7:
                    FilterDonation(donationQueue);
                    break;
                case 8:
                    displaySummary(donationQueue);
                    break;
                case 9:
                    // Exit or return to the main menu of the RobinHoodOrganisation
                    RobinHoodOrganisation.main(null);
                    return; // Exit the loop
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }
    
}
