package control;

import entity.*;
import utility.*;

import java.util.Date;
import java.text.SimpleDateFormat;
import boundaries.DonationDistributionUI;

/**
 *
 * @author Ho Zhi Xuen
 */
public class DonationDistribution {
    static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    static Date today = new Date();

    static DonationDistributionUI distributionUI = new DonationDistributionUI();

    public static void distributeDonations(DoublyLinkedQueue<Donation> donationQueue, DoublyLinkedQueue<Donation> distributionQueue) {
        String doneeName = StringValidation.alphabetValidation("Enter donee name: ");
        int amount = IntValidation.integerValidation("Enter amount: ", false);

        //check if sufficient amount
        int total = 0;
        
        for (Donation donation : donationQueue) {
            total += donation.getAmount();
        }

        if (amount > total){
            char userChoice = distributionUI.getInsifficientConfirmation();

            switch (userChoice) {
                case 'N':
                    main(null);
                    break;
            }
        }

        while (amount > 0 && !donationQueue.isEmpty()) {
            Donation currentDonation = donationQueue.peek();
            int donationBalance = currentDonation.getAmount();
        
            if (donationBalance <= amount) {
                // Full amount of current donation is used
                donationQueue.peek().setDoneeName(doneeName);
                donationQueue.peek().setDistributionDate(today);
                distributionQueue.enqueue(donationQueue.poll());
                amount -= donationBalance;
            } else {
                // Only part of the current donation's amount is used
                currentDonation.setAmount(donationBalance - amount);
                distributionQueue.enqueue(new Donation(currentDonation.getDonorName(), doneeName, amount, currentDonation.getDonationDate(), today));
                amount = 0;
            }
        }

        distributionUI.displayShortMessage("Distribution successful!");
        main(null);
    }

    public static void modifyDistributions(DoublyLinkedQueue<Donation> donationQueue, DoublyLinkedQueue<Donation> distributionQueue) {
        DonationDistributionUI distributionUI = new DonationDistributionUI();
        int userChoice = distributionUI.getModifyMenuChoice();

        Date lastDistributionDate = distributionQueue.peekLast().getDistributionDate();
        String lastDoneeName = distributionQueue.peekLast().getDoneeName();
        String lastDonorName = distributionQueue.peekLast().getDonorName();

        switch (userChoice) {
            case 1:
                do {
                    int currentAmount = distributionQueue.peekLast().getAmount();

                    if (lastDonorName.equals(donationQueue.peekLast().getDonorName())) {
                        donationQueue.peek().setAmount(currentAmount + donationQueue.peekLast().getAmount());
                        donationQueue.peek().setDoneeName(null);
                        donationQueue.peek().setDistributionDate(null);
                        distributionQueue.removeLast();
                    } else {
                        distributionQueue.peekLast().setDoneeName(null);
                        distributionQueue.peekLast().setDistributionDate(null);
                        donationQueue.addFirst(distributionQueue.removeLast());
                    }
                } while (distributionQueue.peekLast().getDistributionDate().compareTo(lastDistributionDate) == 0 && lastDoneeName.equals(distributionQueue.peekLast().getDoneeName()));

                distributionUI.displayShortMessage("Operation successful!");
                main(null);
                break;
            case 2:
                String newDoneeName = StringValidation.alphabetValidation("Enter new donee name: ");

                for (Donation donation : distributionQueue){
                    if (donation.getDistributionDate().compareTo(lastDistributionDate) == 0 && lastDoneeName.equals(donation.getDoneeName())) {
                        donation.setDoneeName(newDoneeName);
                    }
                }

                distributionUI.displayShortMessage("Donee name updated!");
                main(null);
                break;
            case 3:
                Date newDate = StringValidation.dateValidation("Enter new date [dd-MM-yyyy]: ");

                for (Donation donation : distributionQueue){
                    if (donation.getDistributionDate().compareTo(lastDistributionDate) == 0 && lastDoneeName.equals(donation.getDoneeName())) {
                        donation.setDistributionDate(newDate);
                    }
                }

                distributionUI.displayShortMessage("Distribution date updated!");
                main(null);
                break;
            case 4:
                main(null);
                break;
        }
    }

    public static void removeDistributions(DoublyLinkedQueue<Donation> distributionQueue) {
        Date endDate = StringValidation.dateValidation("Remove past distributions until [dd-MM-yyyy]: ");

        char userChoice = distributionUI.getConfirmation();
        switch (userChoice) {
            case 'Y':
                for (Donation donation : distributionQueue){
                    if (donation.getDistributionDate().compareTo(endDate) <= 0) {
                        distributionQueue.dequeue();
                    }
                }

                distributionUI.displayShortMessage("Removal Successful!");
                main(null);
                break;
        
            case 'N':
                main(null);
                break;
        }
    }

    public static void trackDistributions(DoublyLinkedQueue<Donation> distributionQueue) {
        DonationDistributionUI distributionUI = new DonationDistributionUI();
        int userChoice = distributionUI.getTrackDistributionsMenuChoice();
        int count = 0;
        int total = 0;

        switch (userChoice) {
            case 1:
                String doneeName = StringValidation.alphabetValidation("Enter donee name: ");

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

                distributionUI.promptEnter();
                main(null);
                break;
            case 2:
                String donorName = StringValidation.alphabetValidation("Enter donor name: ");

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
                
                distributionUI.promptEnter();
                main(null);
                break;
            case 3:
                main(null);
                break;
        }
    }

    public static void displaySummary(DoublyLinkedQueue<Donation> distributionQueue) {
        Date startDate = StringValidation.dateValidation("Display distributions from [dd-MM-yyyy]: ");
        Date endDate = StringValidation.dateValidation("Display distributions to [dd-MM-yyyy]: ");
        int total = 0;
        int count = 0;

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

        distributionUI.promptEnter();
        main(null);
    }

    public static void displayAllDistributions(){
        int total = 0;
        int count = 0;
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

        distributionUI.promptEnter();
        main(null);
    }
    
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
        int userChoice = distributionUI.getMainMenuChoice();
        int distributionType = 0;
        DoublyLinkedQueue<Donation> donationQueue = null;
        DoublyLinkedQueue<Donation> distributionQueue = null;

        switch (userChoice) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                distributionType = distributionUI.getDistributionType();

                if (distributionType == 4) {
                    main(null);
                    break;
                }

                donationQueue = getDonationQueue(distributionType);
                distributionQueue = getDistributionQueue(distributionType);

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
                RobinHoodOrganisation.main(null);
                break;
        }

    }
}
