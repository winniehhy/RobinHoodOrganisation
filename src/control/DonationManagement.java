package control;

import boundaries.DonationManagementUI;

public class DonationManagement {
    public static void main(String[] args) {
    DonationManagementUI managementUI = new DonationManagementUI();

    while (true) {
        int userChoice = managementUI.getMainMenuChoice();
        int donationType = 0;

        if (userChoice != 8) { // 8 is for 'Back' option
            donationType = managementUI.getDonationType();

            if (donationType == 4) { // If user chooses to go back
                continue;
            }
        }

        switch (userChoice) {
            case 1:
                //addDonation(donationType);
                break;
            case 2:
                //removeDonation(donationType);
                break;
            case 3:
                //searchDonation(donationType);
                break;
            case 4:
                //modifyDonation(donationType);
                break;
            case 5:
                //trackDonations(donationType);
                break;
            case 6:
                //listAllDonations();
                break;
            case 7:
                //displaySummary(donationType);
                break;
            case 8:
            RobinHoodOrganisation.main(null);
            break; // Exit the loop and end the program
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        managementUI.displayContinue();
    }
    }
}
