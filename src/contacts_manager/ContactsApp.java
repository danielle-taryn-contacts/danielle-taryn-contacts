package contacts_manager;

import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class ContactsApp {

    // =================== VARIABLES =====================

    // Initialize variables for directory (file to hold contact info files) and database (text file holding contacts [names and phone numbers])
    static String contactsDirectory = "database"; // directory name as a variable
    static String contactsData = "contacts.txt"; // file name as a variable

    // Create a path that points to our contactsDirectory
    static Path contactsDirectoryPath = Paths.get("./src/contacts_manager/" + contactsDirectory);

    // Create a path that points to our contacts.txt file
    static Path contactsFilePath = Paths.get("./src/contacts_manager/" + contactsDirectory + "/" + contactsData);

    // Create an ArrayList to hold all contacts
    static List<String> contactsList = new ArrayList<>();

    // Declare a contact variable to be used in addContact method
    static Contact contact;

    // ------- SCANNER INITIALIZE -------
    static Scanner scanner = new Scanner(System.in);




    // =================== METHODS =====================


    // ------------------- ADD CONTACT --------------------
    public static void addContact(String name, String phone) throws IOException {

        contact = new Contact(name, phone);
        contactsList.add(contact.getName() + " | " + contact.getPhoneNumber());

        Files.write(contactsFilePath, contactsList, StandardOpenOption.TRUNCATE_EXISTING);// Put the contacts into the contact.txt file
        // TRUNCATE_EXISTING: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/nio/file/StandardOpenOption.html

    }

    // ------------------- PRINT CONTACTS --------------------
    public static void printContacts(List<String> list) {
        System.out.println("\nName | Phone number");
        System.out.println("---------------");
        for (String contact : list) {
            System.out.println(contact);
        }
    }


    // ------------------- VIEW ALL --------------------
    public static void viewAll() throws IOException {

        // Display all the contacts in the CLI
        contactsList = Files.readAllLines(contactsFilePath);

        // Format the output to match the example
        printContacts(contactsList);

    }

    // ------------------- SEARCH --------------------
    public static List<String> searchContacts(String name) {
        List<String> contactsWithName = new ArrayList<>(); // Make new ArrayList to hold search results

        for (String contact : contactsList) {
            if (contact.toLowerCase().contains(name.toLowerCase())) // Sterilize input and the existing contacts, store anything that contains the user's input into the list
                contactsWithName.add(contact);
        }

        return contactsWithName; // Return the list

    }

    // ------------------- DELETE --------------------
    public static void deleteContact(String name) throws IOException{

        List<String> contactToRemove = searchContacts(name);

        System.out.println("Please confirm the contact to delete (enter number): ");
        for (int i = 0; i < contactToRemove.size(); i++) {
            System.out.println((i + 1) + ": " + contactToRemove.get(i));
        }

        int userSelection = Integer.parseInt(scanner.nextLine());

        contactsList.remove(contactToRemove.get(userSelection-1));
        System.out.println("\n" + contactToRemove.get(userSelection-1) + " was successfully deleted.");

        Files.write(contactsFilePath, contactsList, StandardOpenOption.TRUNCATE_EXISTING);// Put the contacts into the contact.txt file

    }


    // ------------------- MAIN --------------------

    public static void main(String[] args) throws IOException {


        // ------- FILE MANAGEMENT (ADDING NECESSARY FILES) -------
        // If no directory exists at the path to our directory, make a new directory
        if (!Files.exists(contactsDirectoryPath)) {
            Files.createDirectory(contactsDirectoryPath);
        }

        // If no file exists at the path to our file, make a new file
        if (!Files.exists(contactsFilePath)) {
            Files.createFile(contactsFilePath);
        }

        // ------- VARIABLE INITIALIZE -------
        boolean isRunning = true;

        contactsList = Files.readAllLines(contactsFilePath);

        // ------- MAIN MENU LOOP -------
        while(isRunning){

            System.out.println("\n=====================");

            System.out.println("\n1. View contacts.\n" +
                    "2. Add a new contact.\n" +
                    "3. Search a contact by name.\n" +
                    "4. Delete an existing contact.\n" +
                    "5. Exit.\n" +
                    "Enter an option (1, 2, 3, 4 or 5): ");

            int userInput = Integer.parseInt(scanner.nextLine()); // Parse the users input to int for the switch statement use


            switch(userInput){

                case 1: // view all contacts
                    System.out.println("\n*** ALL CONTACTS ***");
                    viewAll();
                    break;

                case 2: // add contact
                    System.out.println("\n*** ADD A CONTACT ***");
                    System.out.print("\nName: ");
                    String contactName = scanner.nextLine(); // get the users desired name

                    System.out.print("Phone Number: ");
                    String contactPhone = scanner.nextLine(); // get the user desired phone number

                    addContact(contactName, contactPhone);
                    System.out.println("\nContact added: " + contactName + " | " + contactPhone); // let the user know that their contact was added
                    break;

                case 3: // search for a contact
                    System.out.println("\n*** SEARCH ***");
                    boolean searchAgain = false; // initialize boolean to run loop if needed
                    do {
                        System.out.print("\nName of contact: "); // Ask for name of contact
                        String nameToSearch = scanner.nextLine();

                        List<String> contacts = searchContacts(nameToSearch); // Create a list of contacts with names containing user input

                        if (!contacts.isEmpty()) { // If the list has 1+ elements, display the contacts
                            printContacts(contacts);
                        } else { // If list is empty, display message below
                            System.out.println("\nI'm sorry, a contact by that name does not exist.");
                        }

                        System.out.print("\nWould you like to search the contacts again? [y/n]: "); // prompt the user if they would like to search again

                        String userConfirm = scanner.nextLine();
                        if (userConfirm.toLowerCase().contains("y")) { // if user response contains 'y' re-run search
                            searchAgain = true;
                        } else { // else, do not re-run the loop
                            searchAgain = false;
                        }
                    } while (searchAgain);
                    break;

                case 4: // delete chosen contact
                    System.out.println("\n*** DELETE A CONTACT ***");
                    System.out.println("\nWho would you like to delete?");
                    String nameToDelete = scanner.nextLine();
                    deleteContact(nameToDelete);
                    break;

                case 5: // exit the contact manager
                    isRunning = false;
                    break;

                default: // If the user enters a number that is out of bounds
                    System.out.println("\nThat number is not in our system. Please enter a number from the menu.");
                    break;

            }

        }


    }


}
