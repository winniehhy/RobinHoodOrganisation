
package control;

import ADT.DoublyLinkedQueue;
import boundaries.EventManagementUI;
import entity.*;
import java.util.Date;
import java.util.Scanner;
import utility.*;

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
    
    public static void removeEvent(DoublyLinkedQueue<Event> event,DoublyLinkedQueue<EventAssignment> assignments){
        //prompt ui
        UI.DeleteEvent();
        //check event existence
        if(event.isEmpty()){
            UI.EventEmpty();
            UI.toContinue();
        }
        else{
            //print all event
            UI.tableHeader();
            for (Event AllEvent : event){
                System.out.println(AllEvent.toTable());
            }
            //get eventID to remove
            int delEventID = utility.IntValidation.integerValidation("Enter the eventID to be deleted: ", true);
            String eventID = String.valueOf(delEventID);
            //search for eventID
            Event eventToDelete = null;
            for (Event delEvent : event){
                if (delEvent.getEventID().equals(eventID)){
                    //delete event if found
                    eventToDelete = delEvent;
                    event.remove(delEvent);
                    for (EventAssignment delVolunteerEvent : assignments){
                        if (delVolunteerEvent.getEventID().equals(eventID)){
                        //delete event if found
                        assignments.remove(delVolunteerEvent);
                        }
                    }
                    System.err.println(delEvent + "Deleted sucessfully");
                    UI.toContinue();
                    break;
                }
            }
            //check whether its empty or the event cant be find
            if(eventToDelete == null){
                UI.DeleteEventFail();
            } 
        }
    }
    
    public static void searchEvent(DoublyLinkedQueue<Event> event){
        //prompt UI
        UI.SearchEvent();
        //check event existence
        if(event.isEmpty()){
            UI.EventEmpty();
        }
        else{
            //get eventID to search for details
            int SearchEventID = utility.IntValidation.integerValidation("Enter an eventID to search for an event:", true);
            String eventID = String.valueOf(SearchEventID);
            //search for the eventID
            Event eventToSearch = null;
            for (Event SerEvent : event){
                if (SerEvent.getEventID().equals(eventID)){
                    eventToSearch = SerEvent;
                    System.out.println(SerEvent);
                    break;
                }
            }
            //check whether its empty or the event cant be find
            if(eventToSearch == null){
                UI.EventNotFound();
            } 
        }
        UI.toContinue();
    }
    
    public static void AmendEvent(DoublyLinkedQueue<Event> event){
        //prompt UI
        UI.AmendEvent();
        //check event exisrence
        if(event.isEmpty()){
            UI.EventEmpty();
        }else{
            //print all event
            UI.tableHeader();
            for (Event AllEvent : event){
                System.out.println(AllEvent.toTable());
            }
            //get EventID that need to be modified
            int SearchEventID = utility.IntValidation.integerValidation("Enter an eventID to modify:", true);
            String eventID = String.valueOf(SearchEventID);
            //Search for eventID
            System.out.print("\033[H\033[2J");
            Event eventToSearch = null;
            for (Event SerEvent : event){
                if (SerEvent.getEventID().equals(eventID)){
                    eventToSearch = SerEvent;
                    System.out.println(SerEvent);
                    break;
                }
            }
            //check event existence
            if(eventToSearch == null){
                UI.EventNotFound();
            } else{
                //update event detail
                String eventName = eventToSearch.getEventName();
                String eventDetail = eventToSearch.getEventDetail();
                Date eventDate = eventToSearch.getEventDate();
                //get event update option
                int option = UI.AmendEventOption();       
                switch(option){
                    case 1: 
                        System.out.print("Enter new event Name:");
                        eventName = scanner.nextLine();
                        eventToSearch.setEventName(eventName);
                        break;
                    case 2: 
                        System.out.print("Enter new event Detail:");
                        eventDetail = scanner.nextLine();
                        eventToSearch.setEventDetail(eventDetail);
                        break;
                    case 3: 
                        eventDate = StringValidation.dateValidation("Enter event date (dd-mm-yyyy): ");
                        eventToSearch.setEventDate(eventDate);
                        break;
                    case 4:
                        break;
                    default:
                }
                //update event in the queeu by remove previous event from queue 
                //and requeue the altered event 
                Event modEvent = new Event(eventID,eventName,eventDetail,eventDate);
                event.remove(eventToSearch);
                event.enqueue(modEvent);
                UI.AmendSuccessful();
                System.out.println(modEvent);      
            }
        }
        
        UI.toContinue();
    }  
    
    public static void AllEvent(DoublyLinkedQueue<Event> event){
        //UI in table format
        UI.ListAllEvent();
        //check event existence
        if(event.isEmpty()){
            UI.EventEmpty();    
        }
        else{
            //table header
            UI.tableHeader();
            //search for all event and print it as a table
            for (Event AllEvent : event){
                System.out.println(AllEvent.toTable());
            }
        }
        UI.toContinue();
    }
    
    public static void RemoveVolunteerEvent(DoublyLinkedQueue<Event> event,DoublyLinkedQueue<Volunteer> volunteer, DoublyLinkedQueue<EventAssignment> assignments){
        UI.VolunteerEventRemover();
        Boolean eventExist = false;
        Boolean volunteerExist = false;
        //check event existence
        if (event.isEmpty()){
            UI.EventEmpty();
        }
        else{
            //print all event
            UI.tableHeader();
            for (Event AllEvent : event){
            System.out.println(AllEvent.toTable());
            }
            //get eventID to search for details
            int SearchEventID = utility.IntValidation.integerValidation("Enter an eventID to search for an event:", true);
            String eventID = String.valueOf(SearchEventID);
            //search for the eventID
            System.out.print("\033[H\033[2J");
            Event eventToSearch = null;
            for (Event SerEvent : event){
                if (SerEvent.getEventID().equals(eventID)){
                    eventToSearch = SerEvent;
                    System.out.println(SerEvent);
                    eventExist = true;
                    break;
                }
            }
            //check whether its empty or the event cant be find
            if(eventToSearch == null){
                eventExist = false;
                UI.EventNotFound();
            }
            //if event exist, list volunteer in event
            if(eventExist == true){
                System.out.println("Volunteer in event " + eventID);
                //check for volunteer in an event
                UI.volunteerTableHeader();
                for (EventAssignment checkVonlunteerEvent : assignments){
                    if (checkVonlunteerEvent.getEventID().equals(eventID)){
                        String VolunteerEvent = checkVonlunteerEvent.getVolunteerID();
                        volunteerExist = true;
                        //list volunteer
                        for (Volunteer checkVolunteer : volunteer){
                            if (checkVolunteer.getVolunteerID().equals(VolunteerEvent)){
                                System.out.println(checkVolunteer);
                            }
                            
                        }
                    }
                }
                //if volunteer in event,ask volunteerID to delete
                if (volunteerExist == true){
                    System.out.print("Enter volunteer ID to remove(any input to cancel): ");
                    String ID = scanner.nextLine();
                    //check for volunteerID to remove
                    for (EventAssignment DelVolunteer : assignments){
                        if ((DelVolunteer.getVolunteerID().equals(ID)) && (DelVolunteer.getEventID().equals(eventID))){
                            assignments.remove(DelVolunteer);
                            System.err.println("Volunteer removed from event...");
                        }
                    }
                }else{
                    //if there is no volunteer in events
                    System.err.println("There is no volunteer in this event.");
                }       
            }
        }
        UI.toContinue();        
    }
    
    public static void FindVolunteerEvent(DoublyLinkedQueue<Event> event,DoublyLinkedQueue<Volunteer> volunteer, DoublyLinkedQueue<EventAssignment> assignments){
        //prompt banner
        UI.ListVolunteerEvent();
        String eventFound = null;
        //check volunteer existence
        if(volunteer.isEmpty()){
            UI.VolunteerEmpty();
        }
        else{
            //list out volunteer in queue
            UI.volunteerTableHeader();
            for (Volunteer allVolunteer : volunteer){
                System.out.println(allVolunteer);
            }
            //ask for volunteerID
            System.out.print("Enter an volunteerID to search for its event:");
            String SearchVolunteerID = scanner.nextLine();
            //list out volunteer event
            System.out.print("\033[H\033[2J");
            UI.ListVolunteerEvent();
            System.out.println("Event list for VolunteerID: " + SearchVolunteerID);
            UI.tableHeader();
            for (EventAssignment getEvent : assignments){
                //if there is matching event with volunteer, print volunteer event
                if (getEvent.getVolunteerID().equals(SearchVolunteerID)){
                    eventFound = getEvent.getEventID();
                    //print volunteer event
                    for (Event listEvent : event){
                        if(listEvent.getEventID().equals(eventFound))
                        System.out.println(listEvent.toTable());                
                    }
                }
            }
            //if no event found
            if (eventFound == null){
                UI.EventNotFound();
            }
        }
        UI.toContinue();
    }
    
    public static void SummaryReport(DoublyLinkedQueue<Event> event,DoublyLinkedQueue<EventAssignment> assignments){
        //initializer for report analysis
        int totalEvent = 0,totalVolunteer = 0;
        int compareEvent = 0;
        String popularEventID = null,compareEventID = null;
        //report template
        UI.EventReport();
        if (event.isEmpty()){
            UI.EventEmpty();
        }
        else{
            //check for event and print event details
            for(Event allEvent: event){
                System.out.print(allEvent.toTable());
                String eventID = allEvent.getEventID();
                int popularEvent = 0;
                //check for volunteer in event
                for(EventAssignment allVolunteer: assignments){
                    if(allVolunteer.getEventID().equals(eventID)){
                        System.out.printf("%-10s\n",allVolunteer.getVolunteerID());
                        System.out.printf("%-90s","");
                        totalVolunteer ++;
                        popularEvent ++;
                        compareEventID = allVolunteer.getEventID();
                    }
                }
                //check whether event is the most popular
                if(popularEvent>compareEvent){
                    compareEvent = popularEvent;
                    popularEventID = compareEventID;
                }
                //print total volunteer in an event
                System.out.println(popularEvent+" Volunteer");
                System.out.println("");
                totalEvent ++;
            }
            //analysis for total event and total volunteer
            UI.totalEvent();
            System.out.println("Total event: " + totalEvent);
            System.out.println("Total volunteer: " + totalVolunteer);
            //analysis for most popular event
            UI.mostPopularEvent();
            System.out.println("eventID             : " + popularEventID);
            System.out.println("Number of Volunteer : " + compareEvent);
        }
        //report generation end
        UI.toContinue();
        
    }
    
    public static void sortQueue(DoublyLinkedQueue<Event> eventQueue) {
        if (eventQueue.isEmpty()) {
            return; // If the queue is empty, there's nothing to sort.
        }
        //new array to act as sorting buffer
        DoublyLinkedQueue<Event> sortedQueue = new DoublyLinkedQueue<>();
    
        while (!eventQueue.isEmpty()) {
            // Find the event with the lowest ID
            Event lowestEvent = null;
            int lowestID = Integer.MAX_VALUE;

            for (Event currentEvent : eventQueue) {
                int currentID = Integer.parseInt(currentEvent.getEventID());
                if (currentID < lowestID) {
                    lowestID = currentID;
                    lowestEvent = currentEvent;
                }
            }
        
            // Remove the lowest event from the original queue and add it to the sorted queue
            if (lowestEvent != null) {
                eventQueue.remove(lowestEvent);
                sortedQueue.enqueue(lowestEvent);
            }
        }
        // Transfer sorted events back to the original queue
        while (!sortedQueue.isEmpty()) {
            eventQueue.enqueue(sortedQueue.dequeue());
        }
    }
  
    public static void main(String[] args){
        DoublyLinkedQueue<Event> event = RobinHoodOrganisation.eventQueue;
        DoublyLinkedQueue<Volunteer> volunteer = RobinHoodOrganisation.volunteerQueue;
        DoublyLinkedQueue<EventAssignment> assignEvent = RobinHoodOrganisation.eventAssignmentQueue;

        
        int option = UI.getEventMenuChoice();
        switch(option){
            case 1:
                //Create new event
                newEvent(event);
                main(null);
                break;
            case 2:
                //remove an event
                removeEvent(event,assignEvent);
                sortQueue(event);
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
                sortQueue(event);
                main(null);
                break;
            case 5:
                //List all event
                AllEvent(event);
                main(null);
                break;
            case 6:
                //remove an event from volunteer
                RemoveVolunteerEvent(event,volunteer,assignEvent);
                main(null);
                break;
            case 7:
                //List all events for a volunteer
                FindVolunteerEvent(event,volunteer,assignEvent);
                main(null);
                break;
            case 8:
                //generate summary report
                SummaryReport(event,assignEvent);
                main(null);
                break;
            case 9:
                //return to homepage
                RobinHoodOrganisation.main(null);
                break;
            default:
                
            
        }
    }
}
