package control;

import boundaries.VolunteerManagementUI;
import java.util.Scanner;
import utility.StringValidation;

/*
 * @author Lee Zun Wei
 */

class Volunteer {
    String id;
    String name;
    String phone;
    int age;  
    String assignedEvent;

    public Volunteer(String id, String name, int age, String phone) {
        this.id = id;
        this.name = name;
        this.age = age; 
        this.phone = phone; 
    }

    @Override
    public String toString() {
        return String.format("%-12s %-25s %-7d %-12s", id, name, age, phone);
                /*", Assigned Event: " + (assignedEvent != null ? assignedEvent : "None" )*/
    }
}

class Node {
    Volunteer volunteer;
    Node prev;
    Node next;

    String id;
    String name;
    String phone;
    int age;

    public Node(Volunteer volunteer) {
        this.volunteer = volunteer;
        this.prev = null;
        this.next = null;
    }
}

public class VolunteerManagement{

    static VolunteerManagementUI volunteerUI = new VolunteerManagementUI();


    private Node front;
    private Node rear;
    private int size;

    public void VolunteerManagementQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }


    //Add a new volunteer
    public void addVolunteer(Volunteer volunteer) {
        Node newNode = new Node(volunteer);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            newNode.prev = rear;
            rear = newNode;
        }
        size++;
    }
    

    //Remove selected volunteers based on ID
    public boolean removeVolunteerByID(String id) {
        Node current = front;
        while (current != null) {
            if (current.volunteer.id.equals(id)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    front = current.next;
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    rear = current.prev;
                }
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    //Search volunteers using ID
    public Volunteer searchVolunteerByID(String id) {
        Node current = front;
        while (current != null) {
            if (current.volunteer.id.equals(id)) {
                return current.volunteer;
            }
            current = current.next;
        }
        return null;
    }

    //Search volunteers using their name
    public Volunteer searchVolunteerByName(String name) {
        Node current = front;
        while (current != null) {
            if (current.volunteer.name.equals(name)) {
                return current.volunteer;
            }
            current = current.next;
        }
        return null;
    }

    public boolean assignVolunteerToEvent(String id, String event) {
        Volunteer volunteer = searchVolunteerByID(id);
        if (volunteer != null) {
            volunteer.assignedEvent = event;
            return true;
        }
        return false;
    }

    public String searchEventUnderVolunteer(String id) {
        Volunteer volunteer = searchVolunteerByID(id);
        return (volunteer != null && volunteer.assignedEvent != null) ? 
               volunteer.assignedEvent : "No event assigned to this volunteer.";
    }

    //List all volunteers
    public void listAllVolunteers() {
        if (front == null) {
            System.out.println("No volunteers to display.");
            return;
        }

        Node current = front;
        while (current != null) {
            System.out.println(current.volunteer); 
            current = current.next;
        }
    }

    public void filterVolunteersByEvent(String event) {
        Node current = front;
        while (current != null) {
            if (event.equals(current.volunteer.assignedEvent)) {
                System.out.println(current.volunteer);
            }
            current = current.next;
        }
    }



    public void generateSummaryReport() {
        System.out.println("Total Volunteers: " + size);
        int assignedCount = 0;
        Node current = front;
        while (current != null) {
            if (current.volunteer.assignedEvent != null) {
                assignedCount++;
            }
            current = current.next;
        }
        System.out.println("Volunteers assigned to events: " + assignedCount);
        System.out.println("Volunteers not assigned to any event: " + (size - assignedCount));
    }


    //Enter to continue
    public static void waitForEnter() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.println("Press Enter to continue...");
        scanner.nextLine();  // Wait for the user to press Enter
        System.out.print("\033[H\033[2J");
    }


    //Steps to input new volunteer
    public static void newVolunteerInput(VolunteerManagement queue){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Volunteer ID: ");
        String id = scanner.nextLine();

        String name = StringValidation.alphabetValidation("Enter Volunteer Name: ");

        System.out.print("Enter Volunteer Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter Volunteer Age: ");
        int age = scanner.nextInt();



        queue.addVolunteer(new Volunteer(id, name, age, phone));
        System.out.println("Volunteer added successfully.");

        waitForEnter();
    }


    //Steps to remove volunteer
    public static void removeVolunteerInput(VolunteerManagement volunteerManagement) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter ID: ");
        String idToRemove = scanner.nextLine();
        boolean removedByID = volunteerManagement.removeVolunteerByID(idToRemove);
        if (removedByID) {
            System.out.println("Volunteer removed successfully.");
        } else {
            System.out.println("No volunteer found with the given ID.");
        }

        waitForEnter();
        return; // Return to main menu after removing   
        
    }


    //Steps to seaarch volunteer
    public static void searchVolunteerInput(VolunteerManagement volunteerManagement){
        Scanner scanner = new Scanner(System.in);

        int userChoice = volunteerUI.getSearchType();
        switch (userChoice) {
            case 1:
                System.out.print("Enter ID: ");
                String searchId = scanner.nextLine();


                Volunteer foundById = volunteerManagement.searchVolunteerByID(searchId);
                if (foundById != null) {
                    volunteerUI.getSearchList();
                    System.out.println(foundById);
                    
                } else {
                    System.out.println("No volunteer found with the given ID.");
                }
                break;

            case 2:
                System.out.print("Enter Name: ");
                String searchName = scanner.nextLine();

                System.out.println("Searching for Volunteer with ID: " + searchName);

                Volunteer foundByName = volunteerManagement.searchVolunteerByName(searchName);
                if (foundByName != null) {
                    System.out.println("Volunteer found: " + foundByName);
                } else {
                    System.out.println("No volunteer found with the given name.");
                }
                break;

            default:
                System.out.println("Invalid option selected.");
                break;
        }

        waitForEnter();
    }

    // Filter volunteers by ID
    public void filterVolunteersByID(String filterId) {
        Node current = front;
        boolean found = false;
        while (current != null) {
            if (current.volunteer.id.contains(filterId)) {
                System.out.println(current.volunteer);
                found = true;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("No volunteer found with the given ID.");
        }
    }

    // Filter volunteers by Name
    public void filterVolunteersByName(String filterName) {
        Node current = front;
        boolean found = false;
        while (current != null) {
            if (current.volunteer.name.contains(filterName)) {
                System.out.println(current.volunteer);
                found = true;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("No volunteer found with the given name.");
        }
    }

    // Filter volunteers by Contact
    public void filterVolunteersByContact(String filterContact) {
        Node current = front;
        boolean found = false;
        while (current != null) {
            if (current.volunteer.phone.contains(filterContact)) {
                System.out.println(current.volunteer);
                found = true;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("No volunteer found with the given contact.");
        }
    }

    // Filter volunteers by Age
    public void filterVolunteersByAge(int age) {
        Node current = front;
        boolean found = false;
        while (current != null) {
            if (current.volunteer.age == age) {
                System.out.println(current.volunteer);
                found = true;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("No volunteer found with the given age.");
        }
    }



    // Steps to filter volunteer list
    public static void filterVolunteers(VolunteerManagement volunteerManagement) {
        Scanner scanner = new Scanner(System.in);
    
        int userChoice = volunteerUI.getFilterType();
    
        switch (userChoice) {
            case 1:
                System.out.print("Enter ID: ");
                String filterId = scanner.nextLine().trim();

                volunteerUI.getSearchList();
                volunteerManagement.filterVolunteersByID(filterId);
                break;
    
            case 2:
                System.out.print("Enter Name: ");
                String filterName = scanner.nextLine().trim();
    
                volunteerUI.getSearchList();
                volunteerManagement.filterVolunteersByName(filterName);
                break;
    
            case 3:
                System.out.print("Enter Contact: ");
                String filterContact = scanner.nextLine().trim();
    
                volunteerUI.getSearchList();
                volunteerManagement.filterVolunteersByContact(filterContact);
                break;
    
            case 4:
                System.out.print("Enter Age: ");
                int filterAge = scanner.nextInt();
    
                volunteerUI.getSearchList();
                volunteerManagement.filterVolunteersByAge(filterAge);
                break;
    
            default:
                System.out.println("Invalid option selected.");
                break;
        }
    
        waitForEnter();
    }
    

    public static void main(String[] args) {     
        VolunteerManagement volunteerManagement = new VolunteerManagement();
        Scanner scanner = new Scanner(System.in);
        

        while (true) { 
            int userChoice = volunteerUI.getMenuList();

            switch(userChoice){
                case 1:
                    newVolunteerInput(volunteerManagement);
                    break;
    
                case 2:
                    removeVolunteerInput(volunteerManagement);
                    break;
    
                case 3:
                    searchVolunteerInput(volunteerManagement);
                    break;
    
                case 4:
                    break;
    
                case 5:
                    break;
    
                case 6:
                    volunteerUI.getListFormat();
                    volunteerManagement.listAllVolunteers(); 

                    waitForEnter();
                    break;
    
                case 7:
                    filterVolunteers(volunteerManagement);
                    break;
    
                case 8:
                    break;
    
                case 9:
                    RobinHoodOrganisation.main(null);
                    break;
            }
        }
        
    }	

}


