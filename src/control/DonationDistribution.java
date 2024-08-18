package control;

import boundaries.DonationDistributionUI;

/**
 *
 * @author Ho Zhi Xuen
 */
public class DonationDistribution {

    public static void distributeDonations(int distributionType) {

    }

    public static void modifyDistributions(int distributionType) {
    }

    public static void removeDistributions(int distributionType) {
    }

    public static void trackDistributions(int distributionType) {
    }

    public static void displaySummary(int distributionType) {
    }

    public static void main(String[] args) {
        DonationDistributionUI distributionUI = new DonationDistributionUI();

        int userChoice = distributionUI.getMainMenuChoice();
        int distributionType = 0;

        if (userChoice != 6) {
            distributionType = distributionUI.getDistributionType();

            if (distributionType == 4) {
                main(null);
            }
        }

        switch (userChoice) {
            case 1:
                distributeDonations(distributionType);
                break;
            case 2:
                modifyDistributions(distributionType);
                break;
            case 3:
                removeDistributions(distributionType);
                break;
            case 4:
                trackDistributions(distributionType);
                break;
            case 5:
                displaySummary(distributionType);
            case 6:
                RobinHoodOrganisation.main(null);
                break;
        }
    }
}

/*
 System.out.println("Enter Donor Name: ");
     System.out.println("Enter Donee Name: ");
     System.out.println("Enter Distribution Date [DD-MM-YYYY]: ");
     System.out.println("Remove Distributions Until [DD-MM-YYYY]: ");
         System.out.println("Enter Amount: ");
       public String getStartDate
       get end date
 
 */
