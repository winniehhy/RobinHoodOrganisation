
package control;

import boundaries.EventManagementUI;
import entity.Event;
import java.util.Date;
import java.util.Scanner;
import utility.DoublyLinkedQueue;
import utility.StringValidation;

/**
 *
 * @author Phoon Chong Seng
 */
public class EventManagement {
    //scanner tool
    private static Scanner scanner = new Scanner(System.in);
    //global identifier for UI in control
    private static EventManagementUI UI = new EventManagementUI();
    
    public static void newEvent(DoublyLinkedQueue<Event> event){
        //initialize variable
        String eventID = "0";
        Event newEvent = new Event();
        //Welcome page
        UI.AddEvent();
        //Get event detail
        System.out.print("Enter event name: ");
        String eventName = scanner.nextLine();
        System.out.print("Enter event detail: ");
        String eventDetail = scanner.nextLine();
        Date eventDate = StringValidation.dateValidation("Enter event date (dd-mm-yyyy): ");
        //check event existence in entity
        if (event.isEmpty() == true){
            eventID = "1000";
        }
        else{
            //check previous event ID
            Event lastEvent = event.peekLast();
            int prevEventID = Integer.parseInt(lastEvent.getEventID()) + 1;
            eventID = String.valueOf(prevEventID);
        }
        // save event detail in entity
        newEvent.setEventID(eventID);
        newEvent.setEventName(eventName);
        newEvent.setEventDetail(eventDetail);
        newEvent.setEventDate(eventDate);
        //queue event detail into entity Event
        event.enqueue(newEvent);
        Event eventStatus = null;
        //Check event status
        for (Event chkEvent : event){
            if (chkEvent.getEventID().equals(eventID)){
                eventStatus = chkEvent;
                System.out.println(newEvent + "Event created sucessfully!!!");
                UI.toContinue();
                break;
            }
        }
        if(eventStatus == null){
            System.err.println("Event creation fail, please try again...");
            UI.toContinue();
        }
    }
    
    public static void removeEvent(DoublyLinkedQueue<Event> event){
        UI.DeleteEvent();
        int delEventID = utility.IntValidation.integerValidation("Enter the eventID to be deleted: ", true);
        String eventID = String.valueOf(delEventID);
        
        Event eventToDelete = null;
        for (Event delEvent : event){
            if (delEvent.getEventID().equals(eventID)){
                eventToDelete = delEvent;
                event.remove(delEvent);
                System.out.println("Event with "+ delEvent + "is removed");
                UI.toContinue();
                break;
            }
        }
        if(eventToDelete == null && event.isEmpty()){
            System.err.println("No event available");   
            scanner.nextLine();
        }else if(eventToDelete == null){
            UI.DeleteEventFail();
        } 
    }
    
    public static void searchEvent(DoublyLinkedQueue<Event> event){
        UI.SearchEvent();
        int SearchEventID = utility.IntValidation.integerValidation("Enter an eventID to search for an event:", true);
        String eventID = String.valueOf(SearchEventID);
        if (event.isEmpty()){
            System.err.println("No event available");
        }
        Event eventToSearch = null;
        for (Event SerEvent : event){
            if (SerEvent.getEventID().equals(eventID)){
                eventToSearch = SerEvent;
                System.out.println(SerEvent);
                UI.toContinue();
                break;
            }
        }
        if(eventToSearch == null && event.isEmpty()){
            System.err.println("No event available"); 
            scanner.nextLine();
        }else if(eventToSearch == null){
            UI.EventNotFound();
        } 
    }
    
    public static void AmendEvent(DoublyLinkedQueue<Event> event){
        UI.AmendEvent();
        int SearchEventID = utility.IntValidation.integerValidation("Enter an eventID to modify:", true);
        String eventID = String.valueOf(SearchEventID);
        if (event.isEmpty()){
            System.err.println("No event available");
        }
        Event eventToSearch = null;
        for (Event SerEvent : event){
            if (SerEvent.getEventID().equals(eventID)){
                eventToSearch = SerEvent;
                System.out.println(SerEvent);
                break;
            }
        }
        if(eventToSearch == null && event.isEmpty()){
            System.err.println("No event available");
            scanner.nextLine();
        }else if(eventToSearch == null){
            UI.EventNotFound();
            scanner.nextLine();
        } else{
            String eventName = eventToSearch.getEventName();
            String eventDetail = eventToSearch.getEventDetail();
            Date eventDate = eventToSearch.getEventDate();
            int option = UI.AmendEventOption();       
            switch(option){
                case 1: 
                    System.out.println("Enter new event Name:");
                    eventName = scanner.nextLine();
                    eventToSearch.setEventName(eventName);
                    break;
                case 2: 
                    System.out.println("Enter new event Detail:");
                    eventDetail = scanner.nextLine();
                    eventToSearch.setEventDetail(eventDetail);
                    break;
                case 3: 
                    System.out.println("Enter new event Detail:");
                    eventDate = StringValidation.dateValidation("Enter event date (dd-mm-yyyy): ");
                    eventToSearch.setEventDate(eventDate);
                    break;
                case 4:
                    break;
                default:
            }
            Event modEvent = new Event(eventName,eventDetail,eventID,eventDate);
            event.remove(eventToSearch);
            event.enqueue(modEvent);
            UI.AmendSuccessful();
            System.out.println(modEvent);
            UI.toContinue();
        }
        
    }  
    
    public static void AllEvent(DoublyLinkedQueue<Event> event){
        UI.ListAllEvent();
        for (Event AllEvent : event){
            System.out.println(AllEvent.toTable());
            
        }
        if(event.isEmpty()){
            System.err.println("No event available");    
        }
        UI.toContinue();
    }
    
    public static void main(String[] args) {
        DoublyLinkedQueue<Event> event = RobinHoodOrganisation.eventQueue;

        int option = UI.getEventMenuChoice();
        
        switch(option){
            case 1:
                //Create new event
                newEvent(event);
                main(null);
                break;
            case 2:
                //remove an event
                removeEvent(event);
                main(null);
                break;
            case 3:
                //Search an event
                searchEvent(event);
                main(null);
                break;
            case 4:
                //Amend an event details
                AmendEvent(event);
                main(null);
                break;
            case 5:
                //List all event
                AllEvent(event);
                main(null);
                break;
            case 6:
                //remove an event from volunteer
                break;
            case 7:
                //List all events for a volunteer
                break;
            case 8:
                //generate summary report
                break;
            case 9:
                RobinHoodOrganisation.main(null);
                break;
            default:
                
            
        }
    }
}
