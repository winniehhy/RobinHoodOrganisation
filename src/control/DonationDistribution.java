package control;

import entity.*;
import utility.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import boundaries.DonationDistributionUI;

/**
 *
 * @author Ho Zhi Xuen
 */
public class DonationDistribution {
    static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    static DonationDistributionUI distributionUI = new DonationDistributionUI();

    //remove later
    static LinkedQueue<Donation> cashDonationQueue = new LinkedQueue<>();
    static LinkedQueue<Donation> cashDistributionQueue = new LinkedQueue<>();
    static LinkedQueue<Donation> bookDonationQueue = new LinkedQueue<>();
    static LinkedQueue<Donation> bookDistributionQueue = new LinkedQueue<>();
    static LinkedQueue<Donation> toyDonationQueue = new LinkedQueue<>();
    static LinkedQueue<Donation> toyDistributionQueue = new LinkedQueue<>();








    public static void distributeDonations(LinkedQueue<Donation> donationQueue, LinkedQueue<Donation> distributionQueue) {
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
                distributionQueue.enqueue(donationQueue.poll());
                amount -= donationBalance;
            } else {
                // Only part of the current donation's amount is used
                currentDonation.setAmount(donationBalance - amount);
                distributionQueue.enqueue(new Donation(currentDonation.getDonorName(), null, amount, null, null)); // Adjust as necessary for Donation attributes
                amount = 0;
            }
        }
    }

    public static void modifyDistributions(LinkedQueue<Donation> donationQueue, LinkedQueue<Donation> distributionQueue) {
        DonationDistributionUI distributionUI = new DonationDistributionUI();
        int userChoice = distributionUI.getModifyMenuChoice();

        switch (userChoice) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
        }
    }

    public static void removeDistributions(LinkedQueue<Donation> distributionQueue) {
}

    public static void trackDistributions(LinkedQueue<Donation> distributionQueue) {
        DonationDistributionUI distributionUI = new DonationDistributionUI();
        int userChoice = distributionUI.getTrackDistributionsMenuChoice();

        switch (userChoice) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
        }
    }

    public static void displaySummary(LinkedQueue<Donation> distributionQueue) {
    }

    public static LinkedQueue<Donation> getDonationQueue(int distributionType){
        switch (distributionType) {
            case 1:
                return cashDonationQueue;
            case 2:
                return bookDonationQueue;
            case 3:
                return toyDonationQueue;
        }
        return null;
    }

    public static LinkedQueue<Donation> getDistributionQueue(int distributionType){
        switch (distributionType) {
            case 1:
                return cashDistributionQueue;
            case 2:
                return bookDistributionQueue;
            case 3:
                return toyDistributionQueue;
        }
        return null;
    }

    public static void main(String[] args) {
        
        // Add donations to the queue directly
        try {
            cashDonationQueue.enqueue(new Donation("Wishwell Organization", null, 5000, formatter.parse("09-08-2024"), null));
            cashDonationQueue.enqueue(new Donation("Mickey Org", null, 2000, formatter.parse("10-08-2024"), null));
            cashDonationQueue.enqueue(new Donation("Simon Lee", null, 7500, formatter.parse("12-08-2024"), null));
            cashDonationQueue.enqueue(new Donation("Alvin Teoh", null, 500, formatter.parse("14-08-2024"), null));
            cashDonationQueue.enqueue(new Donation("Annie Pie Charity", null, 10000, formatter.parse("16-08-2024"), null));
        } catch (ParseException e) {
            e.printStackTrace();
        }



        

        int userChoice = distributionUI.getMainMenuChoice();
        int distributionType = 0;

        if (userChoice != 6) {
            distributionType = distributionUI.getDistributionType();
            
            if (distributionType == 4) {
                main(null);
            }
        }

        LinkedQueue<Donation> donationQueue = getDonationQueue(distributionType);
        LinkedQueue<Donation> distributionQueue = getDistributionQueue(distributionType);

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
            case 6:
                RobinHoodOrganisation.main(null);
                break;
        }
    }
}

/*
 * System.out.println("Enter Donor Name: ");
 * System.out.println("Enter Donee Name: ");
 * System.out.println("Enter Distribution Date [DD-MM-YYYY]: ");
 * System.out.println("Remove Distributions Until [DD-MM-YYYY]: ");
 * System.out.println("Enter Amount: ");
 * public String getStartDate
 * get end date
 * 
 */
