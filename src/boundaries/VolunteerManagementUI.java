package boundaries;

import utility.*;

public class VolunteerManagementUI {

    public int getMenuList(){
        System.out.print("\033[H\033[2J");
        System.out.println("Volunteer Management Menu");
        System.out.println("[1] Add new volunteer");
        System.out.println("[2] Remove a volunteer");
        System.out.println("[3] Search for a volunteer");
        System.out.println("[4] Assign volunteer to event");
        System.out.println("[5] List events assigned to a volunteer");
        System.out.println("[6] List all volunteers");
        System.out.println("[7] Filter volunteers");
        System.out.println("[8] Generate summary report");
        System.out.println("[9] Exit");
        int userChoice = IntValidation.inputChoice(9);
        return userChoice;
    }


    public int getSearchType(){
        System.out.print("\033[H\033[2J");
        System.out.println("Select Search Filter");
        System.out.println("[1] ID");
        System.out.println("[2] Name");
        System.out.println("[3] Back");
        int userChoice = IntValidation.inputChoice(3); 
        return userChoice;
    }

    public int getFilterType(){
        System.out.print("\033[H\033[2J");
        System.out.println("Select Filter");
        System.out.println("[1] ID");
        System.out.println("[2] Name");
        System.out.println("[3] Contact");
        System.out.println("[4] Age");
        System.out.println("[5] Back");
        int userChoice = IntValidation.inputChoice(5);
        return userChoice;
    }

    public void getListFormat(){
        System.out.print("\033[H\033[2J");
        System.out.println("------------------------------------------------------------");
        System.out.println("|                 List of all volunteers                   |");
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-12s %-25s %-7s %-12s%n" , "ID" , "Name" ,"Age", "Contact");
        System.out.println("------------------------------------------------------------");
    }

    public void getSearchList(){
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-12s %-25s %-7s %-12s%n" , "ID" , "Name" ,"Age", "Contact");
        System.out.println("------------------------------------------------------------");
    }
    
    public char getConfirmation(String message) {
        System.out.println(message);
        char confirmation = scanner.nextLine().trim().toUpperCase().charAt(0);
        return confirmation;
    }

    public void getSummary(){
        System.out.println("===============================================");
        System.out.println("                 SUMMARY REPORT                ");
        System.out.println("===============================================");
    }

    public void getSeperator(){
        System.out.println("===============================================");
    }
}
