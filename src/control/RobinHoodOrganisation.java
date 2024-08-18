package control;

import boundaries.*;

/**
 *
 * @author Ho Zhi Xuen
 */
public class RobinHoodOrganisation {

    public static void main(String[] args) {
        RobinHoodOrganisationUI mainUI = new RobinHoodOrganisationUI();

        int userChoice = mainUI.getMainMenuChoice();

        switch (userChoice) {
            case 1:
                
                break;
            case 2:
                DonationDistribution.main(null);
                break;
            case 3:
                
                break;
            case 4:
                
                break;                      
            case 5:
                //exit application
                System.out.print("\033[H\033[2J");
                System.out.println("Goodbye!");
                System.exit(0);
                break;
        }
    }
}
