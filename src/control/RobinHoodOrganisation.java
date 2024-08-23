package control;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
        // Add donations to the queue directly
        try {
            
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            RobinHoodOrganisation.cashDonationQueue.enqueue(new Donation("Wishwell Organization", null, 5000, formatter.parse("09-08-2024"), null));
            RobinHoodOrganisation.cashDonationQueue.enqueue(new Donation("Mickey Org", null, 2000, formatter.parse("10-08-2024"), null));
            RobinHoodOrganisation.cashDonationQueue.enqueue(new Donation("Simon Lee", null, 7500, formatter.parse("12-08-2024"), null));
            RobinHoodOrganisation.cashDonationQueue.enqueue(new Donation("Alvin Teoh", null, 500, formatter.parse("14-08-2024"), null));
            RobinHoodOrganisation.cashDonationQueue.enqueue(new Donation("Annie Pie Charity", null, 10000, formatter.parse("16-08-2024"), null));

            RobinHoodOrganisation.cashDistributionQueue.enqueue(new Donation("Melly Organization", "Hollo", 1000, formatter.parse("09-08-2024"), formatter.parse("10-08-2024")));
            RobinHoodOrganisation.cashDistributionQueue.enqueue(new Donation("Daisy Org", "Mel", 2500, formatter.parse("10-08-2024"), formatter.parse("11-08-2024")));
            RobinHoodOrganisation.cashDistributionQueue.enqueue(new Donation("Abby Lee", "Chuck", 8000, formatter.parse("12-08-2024"), formatter.parse("12-08-2024")));
            RobinHoodOrganisation.cashDistributionQueue.enqueue(new Donation("Kiro Koo", "Duck", 1500, formatter.parse("14-08-2024"), formatter.parse("16-08-2024")));
            RobinHoodOrganisation.cashDistributionQueue.enqueue(new Donation("Daisy Org", "Euck", 6000, formatter.parse("16-08-2024"), formatter.parse("17-08-2024")));

            RobinHoodOrganisation.bookDistributionQueue.enqueue(new Donation("Melly", "Hollow", 10, formatter.parse("09-08-2024"), formatter.parse("10-08-2024")));
            RobinHoodOrganisation.bookDistributionQueue.enqueue(new Donation("Daisy", "Mela", 25, formatter.parse("10-08-2024"), formatter.parse("11-08-2024")));

            RobinHoodOrganisation.toyDistributionQueue.enqueue(new Donation("ABC", "Free", 20, formatter.parse("09-08-2024"), formatter.parse("10-08-2024")));
            RobinHoodOrganisation.toyDistributionQueue.enqueue(new Donation("Needa", "PC", 75, formatter.parse("10-08-2024"), formatter.parse("11-08-2024")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

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
