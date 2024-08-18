package boundaries;

import utility.IntValidation;
import utility.StringValidation;

/**
 * @author Ho Zhi Xuen
 */
public class DonationDistributionUI {

    public int getMainMenuChoice() {
        System.out.print("\033[H\033[2J");
        System.out.println("Donation Distribution Menu");
        System.out.println("[1] Distribute Donations");
        System.out.println("[2] Modify Latest Distribution");
        System.out.println("[3] Remove Past Distributions");
        System.out.println("[4] Track Distributions");
        System.out.println("[5] Display Summary");
        System.out.println("[6] Back");
        int userChoice = IntValidation.inputChoice(6);
        return userChoice;
    }

    public int getDistributionType() {
        System.out.print("\033[H\033[2J");
        System.out.println("Distribution Types");
        System.out.println("[1] Cash");
        System.out.println("[2] Books");
        System.out.println("[3] Toys");
        System.out.println("[4] Back");
        int userChoice = IntValidation.inputChoice(4);
        return userChoice;
    }

    public char getInsifficientConfirmation() {
        System.out.println("There is an insufficient amount to be distributed. Continue?");
        char userChoice = StringValidation.inputYN();
        return userChoice;
    }

    public int getModifyMenuChoice() {
        System.out.print("\033[H\033[2J");
        System.out.println("Modify Lastest Distributions");
        System.out.println("[1] Undo Distribution");
        System.out.println("[2] Edit Donee Name");
        System.out.println("[3] Edit Distribution Date");
        System.out.println("[4] Back");
        int userChoice = IntValidation.inputChoice(4);
        return userChoice;
    }

    public int getTrackDistributionsMenuChoice() {
        System.out.print("\033[H\033[2J");
        System.out.println("Track Distributions");
        System.out.println("[1] Track by Donee Name");
        System.out.println("[2] Track by Donor Name");
        System.out.println("[3] Back");
        int userChoice = IntValidation.inputChoice(3);
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
