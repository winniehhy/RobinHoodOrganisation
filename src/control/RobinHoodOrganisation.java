package control;

import boundaries.*;
import entity.Donation;
import utility.DoublyLinkedQueue;

/**
 * The main class that handles the operations for managing the charity organisation.
 * 
 * @author ho Zhi Xuen
 */
public class RobinHoodOrganisation {
    
    // Queues for managing cash donations and distributions
    public static DoublyLinkedQueue<Donation> cashDonationQueue = new DoublyLinkedQueue<>();
    public static DoublyLinkedQueue<Donation> cashDistributionQueue = new DoublyLinkedQueue<>();
    
    // Queues for managing book donations and distributions
    public static DoublyLinkedQueue<Donation> bookDonationQueue = new DoublyLinkedQueue<>();
    public static DoublyLinkedQueue<Donation> bookDistributionQueue = new DoublyLinkedQueue<>();
    
    // Queues for managing toy donations and distributions
    public static DoublyLinkedQueue<Donation> toyDonationQueue = new DoublyLinkedQueue<>();
    public static DoublyLinkedQueue<Donation> toyDistributionQueue = new DoublyLinkedQueue<>();

    //Queue for managing Volunteers
    public static DoublyLinkedQueue<Volunteer> volunteerQueue = new DoublyLinkedQueue<>();

    //Queue for managing Events
    public static DoublyLinkedQueue<Event> eventQueue = new DoublyLinkedQueue<>();
    
    /**
     * The main method serves as the entry point for the Robin Hood Organisation application.
     * It presents the main menu to the user and directs them to the appropriate 
     * functionality based on their choice.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        RobinHoodOrganisationUI mainUI = new RobinHoodOrganisationUI(); // Initialize the main UI
        
        int userChoice = mainUI.getMainMenuChoice(); // Get user's choice from the main menu

        switch (userChoice) {
            case 1:
                DonationManagement.main(null);
                break;
            case 2:
                DonationDistribution.main(null);
                break;
            case 3:
                VolunteerManagement.main(null);
                break;
            case 4:
                EventManagement.main(null);
                break;
            case 5:
                // Exit the application
                System.out.print("\033[H\033[2J");
                System.out.println("Goodbye!");
                System.exit(0);
                break;
        }
    }
}
