package boundaries;

import java.util.Scanner;


/**
 * 
 * Author: Phoon Chong Seng
 */
public class EventManagementUI{
    Scanner scanner = new Scanner(System.in);
    
    public int getEventMenuChoice(){
        System.out.print("\033[H\033[2J");
        System.out.println("Event Management");
        System.out.println("Please select an option");
        System.out.println("1. Add an Event");
        System.out.println("2. Remove an Event");
        System.out.println("3. Search an Event");
        System.out.println("4. Amend an event details");
        System.out.println("5. List all Event");
        System.out.println("6. Remove an event from a volunteer");
        System.out.println("7. List all event for a volunteer");
        System.out.println("8. Generate Summary report");
        System.out.println("9. Return");
        return utility.IntValidation.inputChoice(9);
    }
    
    public void toContinue(){
        System.out.println("Press enter to continue");
        scanner.nextLine();
    }
    
    public void AddEvent(){
        System.out.print("\033[H\033[2J");
        System.out.println("Event Management");
        System.out.println("Create a new event");
    }
    
    public void DeleteEvent(){
        System.out.print("\033[H\033[2J");
        System.out.println("Event Management");
        System.out.println("Delete an event");
    }
    
    public void DeleteEventFail(){
        System.err.println("Cant find eventID");
        System.out.println("Press any key to return...");
        scanner.nextLine();
    }
    
    public void SearchEvent(){
        System.out.print("\033[H\033[2J");
        System.out.println("Event Management");
        System.out.println("Search an event");
    }
    
    public void EventNotFound(){
        System.err.println("Cant find eventID...");
    }

    public void EventEmpty(){
        System.err.println("No event in queue...");
    }

    public void VolunteerEmpty(){
        System.err.println("No volunteer in queue...");
    }
    
    public void AmendEvent(){
        System.out.print("\033[H\033[2J");
        System.out.println("Event Management");
        System.out.println("Amend an event");
    }
    
    public int AmendEventOption(){
        System.out.println("Amend option");
        System.out.println("1. Change event Name");
        System.out.println("2. Change event Detail");
        System.out.println("3. Change event Date");
        System.out.println("4. Return");
        return utility.IntValidation.inputChoice(4);
    }
    
    public void AmendSuccessful(){
        System.out.println("Amend suceeded, event have reenter the queue");
        System.out.println("New event details as below");
    }
    
    public void ListAllEvent(){
        System.out.print("\033[H\033[2J");
        System.out.printf("%36s%-20s\n","","Event Management");
        System.out.printf("%40s%-20s\n","","All Event");
        for (int i = 0; i < 90; i++) {
            System.out.print("_");
        }
        System.out.println("");       
    }

    public void tableHeader(){
        System.out.printf("%-10s%-20s%-30s%-30s\n","eventID","Event Name",
                "Event Description","Event Date");
    }

    public void volunteerTableHeader(){
        System.out.printf("%-13s%-26s%-8s%-12s\n","VolunteerID","Name",
                "Age","Phone number");
    }
    
    public void VolunteerEventRemover(){
        System.out.print("\033[H\033[2J");
        System.out.println("Event Management");
        System.out.println("Event remover for volunteer");
    }
    
    public void ListVolunteerEvent(){
        System.out.print("\033[H\033[2J");
        System.out.println("Event Management");
        System.out.println("Search Volunteer Event");
        System.out.println("Volunteer list");
    }
    
    public void EventReport(){
        System.out.print("\033[H\033[2J");
        System.out.printf("%30s%30s\n","","Event Summary Report");
        for (int i = 0; i < 105; i++) {
            System.out.print("_");
        }
        System.out.printf("\n%-10s%-20s%-30s%-30s%-10s\n","eventID","Event Name",
                "Event Description","Event Date","VolunteerID");
        for (int i = 0; i < 105; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }
    
    public void totalEvent(){
        System.out.println("\nEvent Analysis");
        for (int i = 0; i < 30; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }
    
    public void mostPopularEvent(){
        System.out.println("\nMost popular event");
        for (int i = 0; i < 30; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }
}
