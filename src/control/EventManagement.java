
package control;

import boundaries.EventManagementUI;
import entity.Event;
import entity.EventAssignment;
import entity.Volunteer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import ADT.*;
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
        //prompt ui
        UI.DeleteEvent();
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
                System.out.println("Event with "+ delEvent + "is removed");
                UI.toContinue();
                break;
            }
        }
        //check whether its empty or the event cant be find
        if(eventToDelete == null && event.isEmpty()){
            System.err.println("No event available");   
            scanner.nextLine();
        }else if(eventToDelete == null){
            UI.DeleteEventFail();
        } 
    }
    
    public static void searchEvent(DoublyLinkedQueue<Event> event){
        //prompt UI
        UI.SearchEvent();
        //get eventID to search for details
        int SearchEventID = utility.IntValidation.integerValidation("Enter an eventID to search for an event:", true);
        String eventID = String.valueOf(SearchEventID);
        //search for the eventID
        Event eventToSearch = null;
        for (Event SerEvent : event){
            if (SerEvent.getEventID().equals(eventID)){
                eventToSearch = SerEvent;
                System.out.println(SerEvent);
                UI.toContinue();
                break;
            }
        }
        //check whether its empty or the event cant be find
        if(eventToSearch == null && event.isEmpty()){
            System.err.println("No event available"); 
            scanner.nextLine();
        }else if(eventToSearch == null){
            UI.EventNotFound();
        } 
    }
    
    public static void AmendEvent(DoublyLinkedQueue<Event> event){
        //prompt UI
        UI.AmendEvent();
        //get EventID that need to be modified
        int SearchEventID = utility.IntValidation.integerValidation("Enter an eventID to modify:", true);
        String eventID = String.valueOf(SearchEventID);
        //Search for eventID
        Event eventToSearch = null;
        for (Event SerEvent : event){
            if (SerEvent.getEventID().equals(eventID)){
                eventToSearch = SerEvent;
                System.out.println(SerEvent);
                break;
            }
        }
        //check event existence
        if(eventToSearch == null && event.isEmpty()){
            System.err.println("No event available");
            scanner.nextLine();
        }else if(eventToSearch == null){
            UI.EventNotFound();
            scanner.nextLine();
        } else{
            //update event detail
            String eventName = eventToSearch.getEventName();
            String eventDetail = eventToSearch.getEventDetail();
            Date eventDate = eventToSearch.getEventDate();
            //get event update option
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
            //update event in the queeu by remove previous event from queue 
            //and requeue the altered event 
            Event modEvent = new Event(eventID,eventName,eventDetail,eventDate);
            event.remove(eventToSearch);
            event.enqueue(modEvent);
            UI.AmendSuccessful();
            System.out.println(modEvent);
            UI.toContinue();
        }
        
    }  
    
    public static void AllEvent(DoublyLinkedQueue<Event> event){
        //UI in table format
        UI.ListAllEvent();
        //search for all event and print it as a table
        for (Event AllEvent : event){
            System.out.println(AllEvent.toTable());
        }
        //prompt when there is no event in queue
        if(event.isEmpty()){
            System.err.println("No event available");    
        }
        UI.toContinue();
    }
    
    public static void RemoveVolunteerEvent(DoublyLinkedQueue<Event> event,DoublyLinkedQueue<Volunteer> volunteer, DoublyLinkedQueue<EventAssignment> assignments){
        UI.VolunteerEventRemover();
        Boolean eventExist = false;
        Boolean volunteerExist = false;
        //get eventID to search for details
        int SearchEventID = utility.IntValidation.integerValidation("Enter an eventID to search for an event:", true);
        String eventID = String.valueOf(SearchEventID);
        //search for the eventID
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
        if(eventToSearch == null && event.isEmpty()){
            eventExist = false;
            System.err.println("No event available"); 
            scanner.nextLine();
        }else if(eventToSearch == null){
            eventExist = false;
            UI.EventNotFound();
        }
        //if event exist, list volunteer in event
        if(eventExist == true){
            System.out.println("Vounteer in event " + eventID);
            //check for volunteer in an event
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
                            UI.toContinue();
                    }
                }
            }else{
                //if there is no volunteer in events
                System.err.println("There is no volunteer in this event.");
                UI.toContinue();
            }
            
        }
        
    }
    
    public static void FindVolunteerEvent(DoublyLinkedQueue<Event> event,DoublyLinkedQueue<Volunteer> volunteer, DoublyLinkedQueue<EventAssignment> assignments){
        //prompt banner
        UI.ListVolunteerEvent();
        //list out volunteer in queue
        for (Volunteer allVolunteer : volunteer){
            System.out.println(allVolunteer);
        }
        //ask for volunteerID
        System.out.print("Enter an volunteerID to search for its event:");
        String SearchVolunteerID = scanner.nextLine();
        //list out volunteer event
        System.out.println("Event list for VolunteerID: " + SearchVolunteerID);
        for (EventAssignment getEvent : assignments){
            //if there is matching event with volunteer, print volunteer event
            if (getEvent.getVolunteerID().equals(SearchVolunteerID)){
                String eventFound = getEvent.getEventID();
                //print volunteer event
                for (Event listEvent : event){
                    if(listEvent.getEventID().equals(eventFound))
                    System.out.println(listEvent);                
                }
            }
        }
        //searching done
        UI.toContinue();
    }
    
    public static void SummaryReport(DoublyLinkedQueue<Event> event,DoublyLinkedQueue<EventAssignment> assignments){
        //initializer for report analysis
        int totalEvent = 0,totalVolunteer = 0;
        int compareEvent = 0;
        String popularEventID = null,compareEventID = null;
        //report template
        UI.EventReport();
        //check for event and print event details
        for(Event allEvent: event){
            System.out.print(allEvent.toTable());
            String eventID = allEvent.getEventID();
            int popularEvent = 0;
            //check for volunteer in event
            for(EventAssignment allVolunteer: assignments){
                if(allVolunteer.getEventID().equals(eventID)){
                    System.out.printf("%-10s\n",allVolunteer.getVolunteerID());
                    System.out.printf("%-80s","");
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
        //report generation end
        UI.toContinue();
        
    }
    //To generate data if other subsystem not working 
    public static void generateData(DoublyLinkedQueue<Event> event,DoublyLinkedQueue<Volunteer> volunteer, DoublyLinkedQueue<EventAssignment> assignments){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
                Date date = formatter.parse("25-09-2024"); 
                Event event1 = new Event("1000","eventname1","event description1",date);
                event.enqueue(event1);
                date = formatter.parse("20-10-2024");
                Event event2 = new Event("1001","eventname2","event description2",date);
                event.enqueue(event2);
                date = formatter.parse("25-11-2024");
                Event event3 = new Event("1002","eventname3","event description3",date);
                event.enqueue(event3);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in dd-MM-yyyy format.");
            }
        
        Volunteer volunteer1 = new Volunteer("ab121","ali",18,"0165449234");
        volunteer.enqueue(volunteer1);
        Volunteer volunteer2 = new Volunteer("ab122","muthu",21,"0165239234");
        volunteer.enqueue(volunteer2);
        Volunteer volunteer3 = new Volunteer("ab123","ah kau",19,"0165473634");
        volunteer.enqueue(volunteer3);
        
        EventAssignment assignEvent1 = new EventAssignment("1000","ab121");
        assignments.enqueue(assignEvent1);
        EventAssignment assignEvent2 = new EventAssignment("1001","ab121");
        assignments.enqueue(assignEvent2);
        EventAssignment assignEvent3 = new EventAssignment("1000","ab122");
        assignments.enqueue(assignEvent3);
        System.out.println("Success");
        UI.toContinue();
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
            case 10:
                //to generate dummy data
                generateData(event, volunteer,assignEvent);
                main(null);
            default:
                
            
        }
    }
}
