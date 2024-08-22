import java.util.Scanner;

class Donor {
    String name;
    String date;
    String donationType;
    int amount; // For cash donations only

    Donor(String name, String date, String donationType, int amount) {
        this.name = name;
        this.date = date;
        this.donationType = donationType;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Donor Name: " + name + ", Donation Date: " + date + ", Donation Type: " + donationType + 
               (donationType.equals("cash") ? ", Amount: $" + amount : "");
    }
}

class Node {
    Donor donor;
    Node prev, next;

    Node(Donor donor) {
        this.donor = donor;
    }
}

class DoublyLinkedQueue {
    Node head, tail;

    public void addDonation(Donor donor) {
        Node newNode = new Node(donor);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    public void displayDonations() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.donor);
            temp = temp.next;
        }
    }

    public void amendDonation(String donorName, Donor newDonorDetails) {
        Node temp = head;
        while (temp != null) {
            if (temp.donor.name.equalsIgnoreCase(donorName)) {
                temp.donor = newDonorDetails;
                break;
            }
            temp = temp.next;
        }
    }
}

public class DonationManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DoublyLinkedQueue queue = new DoublyLinkedQueue();

        // Add 5 donors
        for (int i = 0; i < 5; i++) {
            queue.addDonation(createDonor(scanner));
        }

        // Display current donations
        System.out.println("\nCurrent Donors:");
        queue.displayDonations();

        // Amend a donation
        System.out.print("\nDo you want to amend a donation? (Y/N): ");
        String amend = scanner.nextLine();
        if (amend.equalsIgnoreCase("Y")) {
            System.out.print("Enter the name of the donor you want to amend: ");
            String donorName = scanner.nextLine();
            Donor newDonorDetails = createDonor(scanner);
            queue.amendDonation(donorName, newDonorDetails);

            // Display updated donations
            System.out.println("\nUpdated Donors:");
            queue.displayDonations();
        }

        scanner.close();
    }

    private static Donor createDonor(Scanner scanner) {
        System.out.print("Enter donor name: ");
        String name = scanner.nextLine();
        System.out.print("Enter donation date (DD/MM/YYYY): ");
        String date = scanner.nextLine();
        System.out.print("Choose donation type (cash/books/toys): ");
        String donationType = scanner.nextLine();
        int amount = 0;
        if (donationType.equalsIgnoreCase("cash")) {
            System.out.print("Enter donation amount: ");
            amount = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over
        }
        return new Donor(name, date, donationType, amount);
    }
}
