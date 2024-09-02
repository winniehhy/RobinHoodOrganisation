package control;

import ADT.*;
import boundaries.VolunteerManagementUI;
import entity.*;
import java.util.Scanner;
import utility.*;

/**
 * Manages volunteer and assign volunteer to events
 * @author Lee Zun Wei
 */

public class VolunteerManagement {
    
    private static VolunteerManagementUI volunteerUI = new VolunteerManagementUI();
    private static Scanner scanner = new Scanner(System.in);


    public static DoublyLinkedQueue<Volunteer> getVolunteer(DoublyLinkedQueue<Volunteer> volunteerQueue){
        
        return volunteerQueue;
    }

    public static DoublyLinkedQueue<Event> getEvent(DoublyLinkedQueue<Event> eventQueue){

        return eventQueue;
    }

    public static DoublyLinkedQueue<EventAssignment> getEventAssignments(DoublyLinkedQueue<EventAssignment> eventAssignmentQueue){

        return eventAssignmentQueue;
    }

    //case 1: Steps to add new volunteer
    public static void addVolunteer(DoublyLinkedQueue<Volunteer> volunteerQueue){

        Scanner scanner = new Scanner(System.in);

        String id;
        while (true) {
            System.out.print("Enter Volunteer ID (National IC Number): ");
            id = scanner.nextLine();
    
            if (id.length() == 12 && id.matches("\\d+")) {
                break;  // Exit loop if ID is exactly 12 digits long
            } else {
                System.out.println("Invalid ID.");
            }
        }

        String name = StringValidation.alphabetValidation("Enter Volunteer Name: ");

        System.out.print("Enter Volunteer Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter Volunteer Age: ");
        int age = scanner.nextInt();


        waitForEnter();

        Volunteer volunteer = new Volunteer();
        volunteer.setVolunteerID(id);
        volunteer.setVolunteerName(name);
        volunteer.setVolunteerAge(age);
        volunteer.setVolunteerPhone(phone);

        volunteerQueue.enqueue(volunteer);

        System.out.println("Volunteer added successfully.");

    }

    //case 2:Steps to remove existing volunteer
    public static void removeVolunteer(DoublyLinkedQueue<Volunteer> volunteerQueue){
        String volunteerName = StringValidation.alphabetValidation("Enter volunteer name to remove: ");

        DoublyLinkedQueue<Volunteer> queue = getVolunteer(volunteerQueue);
        if(queue.isEmpty()){
            System.out.println("No volunteers to remove");
        }

        Volunteer volunteerRemove = null;
        for(Volunteer volunteer: queue){
            if(volunteer.getVolunteerName().equalsIgnoreCase(volunteerName)){
                volunteerRemove = volunteer;
                break;
            }
        }

        if(volunteerRemove != null){
            char confirm = volunteerUI.getConfirmation("Are you sure to remove this volunteer (Y/N)");
            if(confirm == 'Y' | confirm == 'y'){
                queue.remove(volunteerRemove);
                System.out.println("Succesfully Removed");

            } else {
                System.out.println("No Volunteers Removed");
            }
        }else{
            System.out.println("No Volunteer Found");
        }
    }

    //case 6: Steps to list volunteers
    public static void listVolunteer(DoublyLinkedQueue<Volunteer> volunteerQueue){
        volunteerUI.getListFormat();

        if(volunteerQueue.isEmpty()){
            System.out.println("No Volunteers Currently");
        }else{
            for (Volunteer volunteer : volunteerQueue) {
                System.out.println(volunteer.displayVolunteerDetail());
            }
        }
    }

    //case 3: Steps to search volunteers
    public static void searchVolunteerr(DoublyLinkedQueue<Volunteer> volunteerQueue){
        Scanner scanner = new Scanner(System.in);

        DoublyLinkedQueue<Volunteer> queue = getVolunteer(volunteerQueue);
        if(queue.isEmpty()){
            System.out.println("No volunteers to search");
        }
        
        while (true) { 
            int userChoice = volunteerUI.getSearchType();

            switch(userChoice){
                case 1:
                    System.out.print("Enter Volunteer ID to search: "); //input ID to search
                    String volunteerID = scanner.nextLine();

                    boolean foundByID = false;
                    for (Volunteer volunteer : volunteerQueue) {
                        if (volunteer.getVolunteerID().equalsIgnoreCase(volunteerID)) {
                            foundByID = true;
                            volunteerUI.getSearchList();
                            System.out.println(volunteer.displayVolunteerDetail());
                        }
                    }

                    if (!foundByID) {
                        System.out.println("No Volunteer Found with ID: " + volunteerID); //No volunteer ID found
                    }
                    waitForEnter();
                    break;

                case 2:
                    String volunteerName = StringValidation.alphabetValidation("Enter volunteer name to search: "); //input name to search
                    
                    boolean foundByName = false;
                    for (Volunteer volunteer : volunteerQueue) {
                        if (volunteer.getVolunteerName().toLowerCase().contains(volunteerName.toLowerCase())) { //format all to lower case to compare
                            foundByName = true;
                            volunteerUI.getSearchList();
                            System.out.println(volunteer.displayVolunteerDetail());
                        }
                    }

                    if (!foundByName) {
                        System.out.println("No Volunteer Found with Name: " + volunteerName); //No volunteer name found
                    }
                    waitForEnter();
                break;

                case 3:
                    return;
                    
                default:
            }

        }

    }

    //Case 7 Steps to filter volunteers
    public static void filterVolunteer(DoublyLinkedQueue<Volunteer> volunteerQueue) {
        while (true) { 
            int userChoice = volunteerUI.getFilterType();
    
            switch (userChoice) {
                case 1: // Filter by ID
                    System.out.print("Enter Volunteer ID to filter: ");
                    String idFilter = scanner.nextLine();
    
                    System.out.println("Filtered Volunteers by ID:");
                    volunteerUI.getSearchList();
                    for (Volunteer volunteer : volunteerQueue) {
                        if (volunteer.getVolunteerID().equalsIgnoreCase(idFilter)) {
                            System.out.println(volunteer.displayVolunteerDetail());
                        }
                    }
                    break;
    
                case 2: // Filter by Name
                    String nameFilter = StringValidation.alphabetValidation("Enter Volunteer Name to filter: ");
    
                    System.out.println("Filtered Volunteers by Name:");
                    volunteerUI.getSearchList();
                    for (Volunteer volunteer : volunteerQueue) {
                        if (volunteer.getVolunteerName().toLowerCase().contains(nameFilter.toLowerCase())) {
                            System.out.println(volunteer.displayVolunteerDetail());
                        }
                    }
                    break;

    
                case 3: // Filter by Phone
                    System.out.print("Enter Volunteer Phone to filter: ");
                    String phoneFilter = scanner.nextLine();
    
                    System.out.println("Filtered Volunteers by Phone:");
                    volunteerUI.getSearchList();
                    for (Volunteer volunteer : volunteerQueue) {
                        if (volunteer.getVolunteerPhone().contains(phoneFilter)) {
                            System.out.println(volunteer.displayVolunteerDetail());
                        }
                    }
                    break;

                case 4: // Filter by Age
                    System.out.print("Enter Volunteer Age to filter: ");
                    int ageFilter = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline
    
                    System.out.println("Filtered Volunteers by Age:");
                    volunteerUI.getSearchList();
                    for (Volunteer volunteer : volunteerQueue) {
                        if (volunteer.getVolunteerAge() == ageFilter) {
                            System.out.println(volunteer.displayVolunteerDetail());
                        }
                    }
                    break;
    
                case 5: // Exit filtering
                    return;
    
                default:
                    System.out.println("Invalid choice, please try again.");
            }
    
            waitForEnter();
        }
    }

    // Case 4: Steps to assign volunteer to events
    public static void assignVolunteer(DoublyLinkedQueue<Volunteer> volunteerQueue, DoublyLinkedQueue<Event> eventQueue, 
    DoublyLinkedQueue<EventAssignment> eventAssignments) {

        // Get Volunteer ID
        System.out.print("Enter Volunteer ID to assign: ");
        String volunteerID = scanner.nextLine();
    
        // Check if volunteer exists
        boolean volunteerExists = false;
        for (Volunteer volunteer : volunteerQueue) {
            if (volunteer.getVolunteerID().equalsIgnoreCase(volunteerID)) {
                volunteerExists = true;
                break;
            }
        }
    
        if (!volunteerExists) {
            System.out.println("No Volunteer found with ID: " + volunteerID);
            return;
        }
    
        // Get Event ID
        System.out.print("Enter Event ID to assign: ");
        String eventID = scanner.nextLine();
    
        // Check if event exists
        boolean eventExists = false;
        for (Event event : eventQueue) {
            if (event.getEventID().equalsIgnoreCase(eventID)) {
                eventExists = true;
                break;
            }
        }
    
        if (!eventExists) {
            System.out.println("No Event found with ID: " + eventID);
            return;
        }
    
        // Create and add EventAssignment
        EventAssignment eventAssignment = new EventAssignment(eventID, volunteerID);
        eventAssignments.enqueue(eventAssignment);
    
        System.out.println("Volunteer successfully assigned to the event.");
    }
    



    //case 5: Steps to list events assigned to volunteers
    public static void listEventVolunteer(DoublyLinkedQueue<Volunteer> volunteerQueue, DoublyLinkedQueue<Event> eventQueue,
    DoublyLinkedQueue<EventAssignment> eventAssignments){

        // Variables to keep track of the last printed volunteer ID
        String lastPrintedVolunteerID = null;

        volunteerUI.getListEventAssigned();

        // Iterate through EventAssignments to find and print unique volunteers and their events
        for (EventAssignment assignment : eventAssignments) {
            String volunteerID = assignment.getVolunteerID();
            String eventID = assignment.getEventID();

            // Find the volunteer with the matching ID
            Volunteer volunteer = null;
            for (Volunteer v : volunteerQueue) {
                if (v.getVolunteerID().equalsIgnoreCase(volunteerID)) {
                    volunteer = v;
                    break;
                }
            }

            // Find the event with the matching ID
            Event event = null;
            for (Event e : eventQueue) {
                if (e.getEventID().equalsIgnoreCase(eventID)) {
                    event = e;
                    break;
                }
            }

            // If both volunteer and event are found
            if (volunteer != null && event != null) {
                // Print volunteer ID and name only if it's different from the last printed
                if (!volunteerID.equals(lastPrintedVolunteerID)) {
                    System.out.printf("%-12s %-25s ", volunteer.getVolunteerID(), volunteer.getVolunteerName());
                    lastPrintedVolunteerID = volunteerID; // Update the last printed volunteer ID
                } else {
                    // Print spaces for volunteer ID and name if already displayed
                    System.out.printf("%-12s %-25s ", "", "");
                }

                // Print event details
                System.out.printf("%-8s %-25s %-30s%n", event.getEventID(), event.getEventName(), event.getEventDetail());
            }
        }

        // Check if there are no volunteers or events assigned
        if (lastPrintedVolunteerID == null) {
            System.out.println("No volunteers are currently assigned to any events.");
        }
    }

    //case 8 code for summary
    public static void volunteerSummary(DoublyLinkedQueue<Volunteer> volunteerQueue, 
                                        DoublyLinkedQueue<Event> eventQueue, DoublyLinkedQueue<EventAssignment> eventAssignments){

        System.out.print("\033[H\033[2J");
        volunteerUI.getSummary();                                    
    
        // Print total number of volunteers
        int totalVolunteers = volunteerQueue.size();
        System.out.println("Total Number of Volunteers: " + totalVolunteers);
    
        // Print total number of events
        int totalEvents = eventQueue.size();
        System.out.println("Total Number of Events: " + totalEvents);
    
        // Print total number of volunteer assignments
        int totalAssignments = eventAssignments.size();
        System.out.println("Total Number of Volunteer Assignments: " + totalAssignments);
    
        volunteerUI.getSeperator();                                   
    
        // Find the most assigned volunteer
        List<String> volunteerIDs = new ArrayList<>();
        List<Integer> assignmentCounts = new ArrayList<>();
        
        for (EventAssignment assignment : eventAssignments) {
            String volunteerID = assignment.getVolunteerID();
            int index = volunteerIDs.indexOf(volunteerID);
            
            if (index >= 0) {
                // Volunteer ID found, update count
                assignmentCounts.set(index, assignmentCounts.get(index) + 1);
            } else {
                // Volunteer ID not found, add new entry
                volunteerIDs.add(volunteerID);
                assignmentCounts.add(1);
            }
        }
    
        // Determine the volunteer with the maximum assignments
        String mostAssignedVolunteerID = null;
        int maxAssignments = 0;
        
        for (int i = 0; i < volunteerIDs.size(); i++) {
            if (assignmentCounts.get(i) > maxAssignments) {
                maxAssignments = assignmentCounts.get(i);
                mostAssignedVolunteerID = volunteerIDs.get(i);
            }
        }
    
        // Print the most assigned volunteer's details
        if (mostAssignedVolunteerID != null) {
            Volunteer mostAssignedVolunteer = null;
            for (Volunteer volunteer : volunteerQueue) {
                if (volunteer.getVolunteerID().equalsIgnoreCase(mostAssignedVolunteerID)) {
                    mostAssignedVolunteer = volunteer;
                    break;
                }
            }
            
            if (mostAssignedVolunteer != null) {
                System.out.println("\nMost Assigned Volunteer:");
                System.out.printf("ID: %s\nName: %s\nAssignments: %d\n", 
                                mostAssignedVolunteer.getVolunteerID(), 
                                mostAssignedVolunteer.getVolunteerName(), 
                                maxAssignments);
            } else {
                System.out.println("Could not find the most assigned volunteer.");
            }
        } else {
            System.out.println("No assignments found.");
        }
    
        volunteerUI.getSeperator(); 
    }


    public static void waitForEnter() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.println("Press Enter to continue...");
        scanner.nextLine();  // Wait for the user to press Enter
        System.out.print("\033[H\033[2J");
    }

    public static void main(String[] args) {
        DoublyLinkedQueue<Volunteer> volunteerQueue = RobinHoodOrganisation.volunteerQueue;
        DoublyLinkedQueue<Event> eventQueue = RobinHoodOrganisation.eventQueue;
        DoublyLinkedQueue<EventAssignment> assignEvent = RobinHoodOrganisation.eventAssignmentQueue;

        while (true) { 
            int userChoice = volunteerUI.getMenuList();

            switch (userChoice) {
                case 1:
                    addVolunteer(volunteerQueue);
                    break;

                case 2:
                    removeVolunteer(volunteerQueue);
                    waitForEnter();
                    break;

                case 3:
                    searchVolunteerr(volunteerQueue);
                    waitForEnter();
                    break; 

                case 4:
                    assignVolunteer(volunteerQueue, eventQueue, assignEvent);
                    waitForEnter();
                    break;

                case 5:
                    listEventVolunteer(volunteerQueue, eventQueue, assignEvent);
                    waitForEnter();
                    break;

                case 6:
                    listVolunteer(volunteerQueue);
                    waitForEnter();
                    break;

                case 7:
                    filterVolunteer(volunteerQueue);
                    waitForEnter();
                    break;

                case 8:
                    volunteerSummary(volunteerQueue, eventQueue, assignEvent);
                    waitForEnter();
                    break;

                case 9:
                    RobinHoodOrganisation.main(null);

                default:
                    System.out.println("Invalid Input");
                    return;
            }
        }
    }
}
