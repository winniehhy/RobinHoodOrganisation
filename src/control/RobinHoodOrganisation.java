package control;

import boundaries.*;
import entity.Donation;
import utility.DoublyLinkedQueue;

/**
 *
 * @author Ho Zhi Xuen
 */
public class RobinHoodOrganisation {
    public static DoublyLinkedQueue<Donation> cashDonationQueue = new DoublyLinkedQueue<>();
    public static DoublyLinkedQueue<Donation> cashDistributionQueue = new DoublyLinkedQueue<>();
    public static DoublyLinkedQueue<Donation> bookDonationQueue = new DoublyLinkedQueue<>();
    public static DoublyLinkedQueue<Donation> bookDistributionQueue = new DoublyLinkedQueue<>();
    public static DoublyLinkedQueue<Donation> toyDonationQueue = new DoublyLinkedQueue<>();
    public static DoublyLinkedQueue<Donation> toyDistributionQueue = new DoublyLinkedQueue<>();

    public static void main(String[] args) {
        RobinHoodOrganisationUI mainUI = new RobinHoodOrganisationUI();

        int userChoice = mainUI.getMainMenuChoice();

        switch (userChoice) {
            case 1:
                DonationManagement.main(null);
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