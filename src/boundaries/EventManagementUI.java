package boundaries;

import java.util.Scanner;


/**
 * 
 * Author: Phoon Chong Seng
 */
public class EventManagementUI{
    Scanner scanner = new Scanner(System.in);
    
    public int getEventMenuChoice(){
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
        System.out.println("Press any key to continue");
        scanner.nextLine();
    }
    
    public void AddEvent(){
        System.out.println("Event Management");
        System.out.println("Create a new event");
    }
    
    public void DeleteEvent(){
        System.out.println("Event Management");
        System.out.println("Delete an event");
    }
    
    public void DeleteEventFail(){
        System.err.println("Cant find eventID");
        System.out.println("Press any key to return...");
        scanner.nextLine();
    }
    
    public void SearchEvent(){
        System.out.println("Event Management");
        System.out.println("Search an event");
    }
    
    public void EventNotFound(){
        System.err.println("Cant find eventID...");
        scanner.nextLine();
    }
    
    public void AmendEvent(){
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
        System.out.printf("%25s%20s\n","","All Event");
        for (int i = 0; i < 80; i++) {
            System.out.print("_");
        }
        System.out.println("");
        System.out.printf("%-10s%-20s%-20s%-30s\n","eventID","Event Name",
                "Event Description","Event Date");
    }
}
