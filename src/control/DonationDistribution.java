package control;

import entity.*;
import utility.*;
import ADT.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import boundaries.DonationDistributionUI;

/**
 * Manages the controls of donation distributions.
 * 
 * @author Ho Zhi Xuen
 */
public class DonationDistribution {
    // Formatter to convert date objects to string format
    static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    // Today's date
    static Date today = new Date();

    // UI object to allow interaction with the user
    static DonationDistributionUI distributionUI = new DonationDistributionUI();

    /**
     * Distributes donations from the donationQueue to the distributionQueue based on the amount specified.
     *
     * @param donationQueue      The queue containing the donations to be distributed.
     * @param distributionQueue  The queue where the distributed donations will be stored.
     */
    public static void distributeDonations(DoublyLinkedQueue<Donation> donationQueue, DoublyLinkedQueue<Donation> distributionQueue) {
        String doneeName = StringValidation.alphabetValidation("Enter donee name: ");
        int amount = IntValidation.integerValidation("Enter amount: ", false);

        // Calculate the total amount available in the donationQueue
        int total = 0;
        for (Donation donation : donationQueue) {
            total += donation.getAmount();
        }

        // Check if the requested amount exceeds the total available amount
        //If it exceeds, prompt for confirmation for a reduced requested amount
        if (amount > total){
            char userChoice = distributionUI.getInsifficientConfirmation();
            if (userChoice == 'N') {
                main(null);
                return;
            }
        }

        // Distribute donations until the required amount is fulfilled or the queue is empty
        while (amount > 0 && !donationQueue.isEmpty()) {
            Donation currentDonation = donationQueue.peek();
            int donationBalance = currentDonation.getAmount();
        
            if (donationBalance <= amount) {
                // Use the full amount of the current donation
                currentDonation.setDoneeName(doneeName);
                currentDonation.setDistributionDate(today);
                distributionQueue.enqueue(donationQueue.poll());
                amount -= donationBalance;
            } else {
                // Use only part of the current donation
                currentDonation.setAmount(donationBalance - amount);
                distributionQueue.enqueue(new Donation(currentDonation.getDonorName(), doneeName, amount, currentDonation.getDonationDate(), today));
                amount = 0;
            }
        }

        //Display successful message and return to Distributions menu
        distributionUI.displayShortMessage("Distribution successful!");
        main(null);
    }


    /**
     * Allows modification of the last distribution based on user input.
     *
     * @param donationQueue      The queue containing the donations to be modified.
     * @param distributionQueue  The queue where the distributed donations are stored.
     */
    public static void modifyDistributions(DoublyLinkedQueue<Donation> donationQueue, DoublyLinkedQueue<Donation> distributionQueue) {
        int userChoice = distributionUI.getModifyMenuChoice();

        // Revert the last distribution if they match the last distribution details
        if (distributionQueue.isEmpty()){
            //Display error message and return to the main menu
            distributionUI.displayShortMessage("Error: No distributions to modify.");
            main(null);
        }

        // Retrieve details of the last distribution
        Date lastDistributionDate = distributionQueue.peekLast().getDistributionDate();
        String lastDoneeName = distributionQueue.peekLast().getDoneeName();
        String lastDonorName = distributionQueue.peekLast().getDonorName();

        switch (userChoice) {
            case 1:
                // Revert the last distribution if they match the last distribution details
                do {
                    int currentAmount = distributionQueue.peekLast().getAmount();

                    if (!donationQueue.isEmpty() && lastDonorName.equals(donationQueue.peekLast().getDonorName())) {
                        donationQueue.peek().setAmount(currentAmount + donationQueue.peekLast().getAmount());
                        donationQueue.peek().setDoneeName(null);
                        donationQueue.peek().setDistributionDate(null);
                        distributionQueue.removeLast();
                    } else {
                        distributionQueue.peekLast().setDoneeName(null);
                        distributionQueue.peekLast().setDistributionDate(null);
                        donationQueue.addFirst(distributionQueue.removeLast());
                    }
                } while (!distributionQueue.isEmpty() && distributionQueue.peekLast().getDistributionDate().compareTo(lastDistributionDate) == 0 && lastDoneeName.equals(distributionQueue.peekLast().getDoneeName()));

                //Display successful message and return to the main menu
                distributionUI.displayShortMessage(" Operation successful!");
                main(null);
                break;

            case 2:
                // Update the donee name for the last distribution(s)
                String newDoneeName = StringValidation.alphabetValidation("Enter new donee name: ");
                for (Donation donation : distributionQueue){
                    if (donation.getDistributionDate().compareTo(lastDistributionDate) == 0 && lastDoneeName.equals(donation.getDoneeName())) {
                        donation.setDoneeName(newDoneeName);
                    }
                }

                //Display successful message and return to Distributions menu
                distributionUI.displayShortMessage("Donee name updated!");
                main(null);
                break;

            case 3:
                // Update the distribution date for the last distribution(s)
                Date newDate = StringValidation.dateValidation("Enter new date [dd-MM-yyyy]: ");
                for (Donation donation : distributionQueue){
                    if (donation.getDistributionDate().compareTo(lastDistributionDate) == 0 && lastDoneeName.equals(donation.getDoneeName())) {
                        donation.setDistributionDate(newDate);
                    }
                }

                //Display successful message and return to Distributions menu
                distributionUI.displayShortMessage("Distribution date updated!");
                main(null);
                break;

            case 4:
                // Return to the main menu
                main(null);
                break;
        }
    }

    /**
     * Removes distributions from the distributionQueue up to the specified end date.
     *
     * @param distributionQueue  The queue where the distributed donations are stored.
     */
    public static void removeDistributions(DoublyLinkedQueue<Donation> distributionQueue) {
        Date endDate = StringValidation.dateValidation("Remove past distributions until [dd-MM-yyyy]: ");

        char userChoice = distributionUI.getConfirmation();
        if (userChoice == 'Y') {
            for (Donation donation : distributionQueue){
                if (donation.getDistributionDate().compareTo(endDate) <= 0) {
                    distributionQueue.dequeue();
                }
            }
            distributionUI.displayShortMessage("Removal Successful!");
        }

        // Return to the main menu
        main(null);
    }

    /**
     * Tracks and displays distributions based on donee or donor name.
     *
     * @param distributionQueue  The queue where the distributed donations are stored.
     */
    public static void trackDistributions(DoublyLinkedQueue<Donation> distributionQueue) {
        int userChoice = distributionUI.getTrackDistributionsMenuChoice();
        int count = 0;
        int total = 0;

        switch (userChoice) {
            case 1:
                // Track by donee name
                String doneeName = StringValidation.alphabetValidation("Enter donee name: ");

                //Display all distributions based on donee name
                System.out.print("\033[H\033[2J");
                if(distributionQueue == RobinHoodOrganisation.cashDistributionQueue){
                    System.out.printf("%60s","Cash Distributions\n");
                } else if (distributionQueue == RobinHoodOrganisation.bookDistributionQueue) {
                    System.out.printf("%60s","Book Distributions\n");
                } else {
                    System.out.printf("%60s","Toy Distributions\n");
                }
                for (int i = 0; i < 103; i++) {
                    System.out.print("=");
                }
                System.out.println();
                System.out.printf("     | %-20s | %-20s | %-8s | %-17s | %-17s |\n","Donor","Donee","Amount","Donation Date","Distribution Date");
                for (int i = 0; i < 103; i++) {
                    System.out.print("=");
                }
                System.out.println();
                for (Donation donation : distributionQueue){
                    if (donation.getDoneeName().equals(doneeName)) {
                        System.out.printf("[%2d]", count + 1);
                        System.out.println(donation.displayTable());
                        total += donation.getAmount();
                        count ++;
                    }
                }
                for (int i = 0; i < 103; i++) {
                    System.out.print("=");
                }
                System.out.println();
                System.out.println("Total count: " + count);
                if (distributionQueue == RobinHoodOrganisation.cashDistributionQueue){
                    System.out.println("Total distributed amount: RM " + total);
                } else {
                    System.out.println("Total distributed amount: " + total);
                }

                // Return to the main menu
                distributionUI.promptEnter();
                main(null);
                break;

            case 2:
                // Track by donor name
                String donorName = StringValidation.alphabetValidation("Enter donor name: ");

                //Display all distributions based on donor name
                System.out.print("\033[H\033[2J");
                if(distributionQueue == RobinHoodOrganisation.cashDistributionQueue){
                    System.out.printf("%60s","Cash Distributions\n");
                } else if (distributionQueue == RobinHoodOrganisation.bookDistributionQueue) {
                    System.out.printf("%60s","Book Distributions\n");
                } else {
                    System.out.printf("%60s","Toy Distributions\n");
                }
                for (int i = 0; i < 103; i++) {
                    System.out.print("=");
                }
                System.out.println();
                System.out.printf("     | %-20s | %-20s | %-8s | %-17s | %-17s |\n","Donor","Donee","Amount","Donation Date","Distribution Date");
                for (int i = 0; i < 103; i++) {
                    System.out.print("=");
                }
                System.out.println();
                for (Donation donation : distributionQueue){
                    if (donation.getDonorName().equals(donorName)) {
                        System.out.printf("[%2d]", count + 1);
                        System.out.println(donation.displayTable());
                        total += donation.getAmount();
                        count ++;
                    }
                }
                for (int i = 0; i < 103; i++) {
                    System.out.print("=");
                }
                System.out.println();
                System.out.println("Total count: " + count);
                if (distributionQueue == RobinHoodOrganisation.cashDistributionQueue){
                    System.out.println("Total distributed amount: RM " + total);
                } else {
                    System.out.println("Total distributed amount: " + total);
                }
                
                // Return to the main menu
                distributionUI.promptEnter();
                main(null);
                break;

            case 3:
                // Return to the main menu
                main(null);
                break;
        }
    }

    /**
     * Displays a summary of distributions made between specified start and end dates.
     *
     * @param distributionQueue  The queue where the distributed donations are stored.
     */
    public static void displaySummary(DoublyLinkedQueue<Donation> distributionQueue) {
        Date startDate = StringValidation.dateValidation("Display distributions from [dd-MM-yyyy]: ");
        Date endDate = StringValidation.dateValidation("Display distributions to [dd-MM-yyyy]: ");

        // Check if start date is after end date
        if (startDate.after(endDate)) {
            distributionUI.displayShortMessage("Error: Start date cannot be after end date.");
            main(null);
        }

        int total = 0;
        int count = 0;

        // Display the distributions within the date range
        System.out.print("\033[H\033[2J");
        if(distributionQueue == RobinHoodOrganisation.cashDistributionQueue){
            System.out.printf("%60s","Cash Distributions\n");
        } else if (distributionQueue == RobinHoodOrganisation.bookDistributionQueue) {
            System.out.printf("%60s","Book Distributions\n");
        } else {
            System.out.printf("%60s","Toy Distributions\n");
        }
        for (int i = 0; i < 103; i++) {
            System.out.print("=");
        }
        System.out.println();
        System.out.printf("     | %-20s | %-20s | %-8s | %-17s | %-17s |\n","Donor","Donee","Amount","Donation Date","Distribution Date");
        for (int i = 0; i < 103; i++) {
            System.out.print("=");
        }
        System.out.println();
        for (Donation donation : distributionQueue){
            if (donation.getDistributionDate().compareTo(startDate) >= 0 && donation.getDistributionDate().compareTo(endDate) <= 0) {
                System.out.printf("[%2d]", count + 1);
                System.out.println(donation.displayTable());
                total += donation.getAmount();
                count ++;
            }
        }
        for (int i = 0; i < 103; i++) {
            System.out.print("=");
        }
        System.out.println();
        System.out.println("Total count: " + count);
        if (distributionQueue == RobinHoodOrganisation.cashDistributionQueue){
            System.out.println("Total distributed amount: RM " + total);
        } else {
            System.out.println("Total distributed amount: " + total);
        }

        // Return to the main menu
        distributionUI.promptEnter();
        main(null);
    }

    /**
     * Displays all distributions of different types (cash, books, toys).
     */
    public static void displayAllDistributions(){
        int total = 0;
        int count = 0;

        //Display cash distributions
        System.out.print("\033[H\033[2J");
        System.out.printf("%60s","Cash Distributions\n");
        for (int i = 0; i < 103; i++) {
            System.out.print("=");
        }
        System.out.println();
        System.out.printf("     | %-20s | %-20s | %-8s | %-17s | %-17s |\n","Donor","Donee","RM","Donation Date","Distribution Date");
        for (int i = 0; i < 103; i++) {
            System.out.print("=");
        }
        System.out.println();
        for (Donation donation : RobinHoodOrganisation.cashDistributionQueue){
            System.out.printf("[%2d]", count + 1);
            System.out.println(donation.displayTable());
            total += donation.getAmount();
            count ++;
        }
        for (int i = 0; i < 103; i++) {
            System.out.print("=");
        }
        System.out.println();
        System.out.println("Total count: " + count);
        System.out.println("Total distributed amount: RM " + total);

        total = 0;
        count = 0;

        //Display book distributions
        System.out.printf("\n%60s","Book Distributions\n");
        for (int i = 0; i < 103; i++) {
            System.out.print("=");
        }
        System.out.println();
        System.out.printf("     | %-20s | %-20s | %-8s | %-17s | %-17s |\n","Donor","Donee","Amount","Donation Date","Distribution Date");
        for (int i = 0; i < 103; i++) {
            System.out.print("=");
        }
        System.out.println();
        for (Donation donation : RobinHoodOrganisation.bookDistributionQueue){
            System.out.printf("[%2d]", count + 1);
            System.out.println(donation.displayTable());
            total += donation.getAmount();
            count ++;
        }
        for (int i = 0; i < 103; i++) {
            System.out.print("=");
        }
        System.out.println();
        System.out.println("Total count: " + count);
        System.out.println("Total distributed amount: " + total);

        total = 0;
        count = 0;

        //Display toy distributions
        System.out.printf("\n%60s","Toy Distributions\n");
        for (int i = 0; i < 103; i++) {
            System.out.print("=");
        }
        System.out.println();
        System.out.printf("     | %-20s | %-20s | %-8s | %-17s | %-17s |\n","Donor","Donee","Amount","Donation Date","Distribution Date");
        for (int i = 0; i < 103; i++) {
            System.out.print("=");
        }
        System.out.println();
        for (Donation donation : RobinHoodOrganisation.toyDistributionQueue){
            System.out.printf("[%2d]", count + 1);
            System.out.println(donation.displayTable());
            total += donation.getAmount();
            count ++;
        }
        for (int i = 0; i < 103; i++) {
            System.out.print("=");
        }
        System.out.println();
        System.out.println("Total count: " + count);
        System.out.println("Total distributed amount: " + total);

        // Return to the main menu
        distributionUI.promptEnter();
        main(null);
    }
    
    /**
     * Gets the appropriate donation queue based on the distribution type.
     *
     * @param distributionType  The type of distribution (cash, books, toys).
     * @return                  The corresponding donation queue.
     */
    public static DoublyLinkedQueue<Donation> getDonationQueue(int distributionType){
        switch (distributionType) {
            case 1:
                return RobinHoodOrganisation.cashDonationQueue;
            case 2:
                return RobinHoodOrganisation.bookDonationQueue;
            case 3:
                return RobinHoodOrganisation.toyDonationQueue;
        }
        return null;
    }

    /**
     * Gets the appropriate distribution queue based on the distribution type.
     *
     * @param distributionType  The type of distribution (cash, books, toys).
     * @return                  The corresponding distribution queue.
     */
    public static DoublyLinkedQueue<Donation> getDistributionQueue(int distributionType){
        switch (distributionType) {
            case 1:
                return RobinHoodOrganisation.cashDistributionQueue;
            case 2:
                return RobinHoodOrganisation.bookDistributionQueue;
            case 3:
                return RobinHoodOrganisation.toyDistributionQueue;
        }
        return null;
    }

    public static void main(String[] args) {
        // Display the main menu and get the user's choice
        int userChoice = distributionUI.getMainMenuChoice();
        int distributionType = 0;

        // Initialize queues for donations and distributions
        DoublyLinkedQueue<Donation> donationQueue = null;
        DoublyLinkedQueue<Donation> distributionQueue = null;

        // Handle user choice based on the main menu selection
        switch (userChoice) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                // Prompt the user to select the distribution type
                distributionType = distributionUI.getDistributionType();

                // If the user chooses to go back to the main menu
                if (distributionType == 4) {
                    main(null);
                    break;
                }

                // Retrieve the respective queues based on the distribution type
                donationQueue = getDonationQueue(distributionType);
                distributionQueue = getDistributionQueue(distributionType);

                // Perform actions based on the user's specific choice
                switch (userChoice) {
                    case 1:
                        distributeDonations(donationQueue, distributionQueue);
                        break;
                    case 2:
                        modifyDistributions(donationQueue, distributionQueue);
                        break;
                    case 3:
                        removeDistributions(distributionQueue);
                        break;
                    case 4:
                        trackDistributions(distributionQueue);
                        break;
                    case 5:
                        displaySummary(distributionQueue);
                        break;
                }
                break;

            case 6:
                displayAllDistributions();
                break;

            case 7:
                // Redirects to the Robin Hood Organisation's main method
                RobinHoodOrganisation.main(null);
                break;
        }

    }
}
