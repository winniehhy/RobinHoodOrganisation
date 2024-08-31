package control;

import boundaries.*;
import entity.*;
import ADT.*;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * The main class that handles the operations for managing the charity organisation.
 * 
 * @ author Ho Zhi Xuen
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
    public static DoublyLinkedQueue<EventAssignment> eventAssignmentQueue = new DoublyLinkedQueue<>();
    
    static {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        
        // Adding sample data to cash donation and distribution queues
        try {
            Donation donation1 = new Donation("Alice", "N/A", 100, formatter.parse("01-01-2023"), null);
            donation1.setDonationType(1); // Assuming 1 represents Cash
            cashDonationQueue.enqueue(donation1);

            Donation donation2 = new Donation("Bob", "N/A", 200, formatter.parse("02-01-2023"), null);
            donation2.setDonationType(1); // Assuming 1 represents Cash
            cashDonationQueue.enqueue(donation2);

            Donation donation3 = new Donation("N/A", "Charity A", 150, null, formatter.parse("03-01-2023"));
            donation3.setDonationType(1); // Assuming 1 represents Cash
            cashDistributionQueue.enqueue(donation3);
            
            Donation donation4 = new Donation("Charlie", "N/A", 10, formatter.parse("01-02-2023"), null);
            donation4.setDonationType(2); // Assuming 2 represents Book
            bookDonationQueue.enqueue(donation4);

            Donation donation5 = new Donation("Dave", "N/A", 20, formatter.parse("02-02-2023"), null);
            donation5.setDonationType(2); // Assuming 2 represents Book
            bookDonationQueue.enqueue(donation5);

            Donation donation6 = new Donation("N/A", "Library B", 15, null, formatter.parse("03-02-2023"));
            donation6.setDonationType(2); // Assuming 2 represents Book
            bookDistributionQueue.enqueue(donation6);
            
            Donation donation7 = new Donation("Eve", "N/A", 5, formatter.parse("01-03-2023"), null);
            donation7.setDonationType(3); // Assuming 3 represents Toy
            toyDonationQueue.enqueue(donation7);

            Donation donation8 = new Donation("Frank", "N/A", 10, formatter.parse("02-03-2023"), null);
            donation8.setDonationType(3); // Assuming 3 represents Toy
            toyDonationQueue.enqueue(donation8);

            Donation donation9 = new Donation("N/A", "Orphanage C", 7, null, formatter.parse("03-03-2023"));
            donation9.setDonationType(3); // Assuming 3 represents Toy
            toyDistributionQueue.enqueue(donation9);
            
            volunteerQueue.enqueue(new Volunteer("V001", "John Doe", 30, "123-456-7890"));
            volunteerQueue.enqueue(new Volunteer("V002", "Jane Smith", 25, "098-765-4321"));
            
            eventQueue.enqueue(new Event("E001", "Charity Run", "Annual charity run event", formatter.parse("01-12-2023")));
            eventQueue.enqueue(new Event("E002", "Food Drive", "Community food drive", formatter.parse("15-11-2023")));
            
            eventAssignmentQueue.enqueue(new EventAssignment("John Doe", "Charity Run"));
            eventAssignmentQueue.enqueue(new EventAssignment("Jane Smith", "Food Drive"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
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
                EventManagement.main(null);
                break;
            case 4:
                VolunteerManagement.main(null);
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