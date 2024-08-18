package boundaries;

import utility.IntValidation;

/**
 *
 * @author Ho Zhi Xuen
 */
public class RobinHoodOrganisationUI {
  public int getMainMenuChoice(){
    System.out.print("\033[H\033[2J");
    System.out.println("Robin Hood Organisation");
    System.out.println("[1] Donation Management");
    System.out.println("[2] Donation Distribution");
    System.out.println("[3] Event Management");
    System.out.println("[4] Volunteer Management");
    System.out.println("[5] Quit");
    int userChoice = IntValidation.inputChoice(5);
    return userChoice;
  }
}
