package control;

import boundaries.VolunteerManagementUI;
import entity.*;
import java.util.Scanner;
import utility.*;

public class VolunteerManagement {
    
    private static VolunteerManagementUI volunteerUI = new VolunteerManagementUI();
    private static Scanner scanner = new Scanner(System.in);


    public static DoublyLinkedQueue<Volunteer> getVolunteer(DoublyLinkedQueue<Volunteer> volunteerQueue){
        
        return volunteerQueue;
    }

    public static void addVolunteer(DoublyLinkedQueue<Volunteer> volunteerQueue){

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Volunteer ID: ");
        String id = scanner.nextLine();

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

    //case 6: Steps to search volunteers
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
                    System.out.print("Enter Volunteer ID to search: ");
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
                        System.out.println("No Volunteer Found with ID: " + volunteerID);
                    }
                    break;

                case 2:
                    String volunteerName = StringValidation.alphabetValidation("Enter volunteer name to search: ");
                    
                    boolean foundByName = false;
                    for (Volunteer volunteer : volunteerQueue) {
                        if (volunteer.getVolunteerName().toLowerCase().contains(volunteerName.toLowerCase())) {
                            foundByName = true;
                            volunteerUI.getSearchList();
                            System.out.println(volunteer.displayVolunteerDetail());
                        }
                    }

                    if (!foundByName) {
                        System.out.println("No Volunteer Found with Name: " + volunteerName);
                    }
                break;

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
                    for (Volunteer volunteer : volunteerQueue) {
                        if (volunteer.getVolunteerID().equalsIgnoreCase(idFilter)) {
                            volunteerUI.getSearchList();
                            System.out.println(volunteer.displayVolunteerDetail());
                        }
                    }
                    break;
    
                case 2: // Filter by Name
                    String nameFilter = StringValidation.alphabetValidation("Enter Volunteer Name to filter: ");
    
                    System.out.println("Filtered Volunteers by Name:");
                    for (Volunteer volunteer : volunteerQueue) {
                        if (volunteer.getVolunteerName().toLowerCase().contains(nameFilter.toLowerCase())) {
                            volunteerUI.getSearchList();
                            System.out.println(volunteer.displayVolunteerDetail());
                        }
                    }
                    break;
    
                case 3: // Filter by Age
                    System.out.print("Enter Volunteer Age to filter: ");
                    int ageFilter = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline
    
                    System.out.println("Filtered Volunteers by Age:");
                    for (Volunteer volunteer : volunteerQueue) {
                        if (volunteer.getVolunteerAge() == ageFilter) {
                            volunteerUI.getSearchList();
                            System.out.println(volunteer.displayVolunteerDetail());
                        }
                    }
                    break;
    
                case 4: // Filter by Phone
                    System.out.print("Enter Volunteer Phone to filter: ");
                    String phoneFilter = scanner.nextLine();
    
                    System.out.println("Filtered Volunteers by Phone:");
                    for (Volunteer volunteer : volunteerQueue) {
                        if (volunteer.getVolunteerPhone().contains(phoneFilter)) {
                            volunteerUI.getSearchList();
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

    public static void waitForEnter() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.println("Press Enter to continue...");
        scanner.nextLine();  // Wait for the user to press Enter
        System.out.print("\033[H\033[2J");
    }

    public static void main(String[] args) {
        DoublyLinkedQueue<Volunteer> volunteerQueue = RobinHoodOrganisation.volunteerManagementQueue;

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
                case 5:
                case 6:
                    listVolunteer(volunteerQueue);

                    waitForEnter();
                    break;

                case 7:
                    filterVolunteer(volunteerQueue);
                    waitForEnter();
                    break;

                case 8:
                    break;

                case 9:
                    return;

                default:
            }
        }
    }
}

