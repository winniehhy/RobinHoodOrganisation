package boundaries;

import utility.IntValidation;
import utility.StringValidation;

/**
 * @author Heng Han Yee
 */
public class DonationManagementUI {

    public int getMainMenuChoice() {
        System.out.print("\033[H\033[2J");
        System.out.println("Donation Management Menu");
        System.out.println("[1] Add New Donation");
        System.out.println("[2] Remove Donation");
        System.out.println("[3] Search Donation Details");
        System.out.println("[4] Amend Donation Details");
        System.out.println("[5] Track Donated Items");
        System.out.println("[6] List All Current Donations");
        System.out.println("[7] Display Summary Report");
        System.out.println("[8] Back");
        int userChoice = IntValidation.inputChoice(8);
        return userChoice;
    }

    public int getDonationType() {
        System.out.print("\033[H\033[2J");
        System.out.println("Donation Types");
        System.out.println("[1] Cash");
        System.out.println("[2] Books");
        System.out.println("[3] Toys");
        System.out.println("[4] Back");
        int userChoice = IntValidation.inputChoice(4);
        return userChoice;
    }

    public char getInsufficientConfirmation() {
        System.out.println("There is an insufficient amount to be donated. Continue?");
        char userChoice = StringValidation.inputYN();
        return userChoice;
    }

    public int getModifyMenuChoice() {
        System.out.print("\033[H\033[2J");
        System.out.println("Modify Donations");
        System.out.println("[1] Undo Donation");
        System.out.println("[2] Edit Donor Name");
        System.out.println("[3] Edit Donation Amount");
        System.out.println("[4] Back");
        int userChoice = IntValidation.inputChoice(4);
        return userChoice;
    }

    public char getConfirmation() {
        System.out.println("This action cannot be undone. Continue?");
        char userChoice = StringValidation.inputYN();
        return userChoice;
    }

    public void displayContinue() {
        System.out.print("\n\nPress Enter to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }
}