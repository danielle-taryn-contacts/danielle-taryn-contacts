package contacts_manager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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


    // =================== METHODS =====================


    // ------------------- ADD CONTACT --------------------
    public static void addContact(String name, String phone) throws IOException {

        contact = new Contact(name, phone);
        contactsList.add((contact.getName() + " | " + contact.getPhoneNumber()));
        Files.write(contactsFilePath, contactsList, StandardOpenOption.TRUNCATE_EXISTING);// Put the contacts into the contact.txt file
        // TRUNCATE_EXISTING: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/nio/file/StandardOpenOption.html

    }

    // ------------------- VIEW ALL --------------------
    public static void viewAll() throws IOException {

        // Display all the contacts in the CLI
        contactsList = Files.readAllLines(contactsFilePath);

        // Format the output to match the example
        System.out.println("\nname | Phone number");
        System.out.println("---------------");
        for(String contact : contactsList) {
            System.out.println(contact);
        }

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


        // ------- SCANNER INITIALIZE -------
        Scanner scanner = new Scanner(System.in);

        // ------- VARIABLE INITIALIZE -------
        boolean isRunning = true;


        // ------- MAIN MENU LOOP -------
        while(isRunning){

            System.out.println("\n1. View contacts.\n" +
                    "2. Add a new contact.\n" +
                    "3. Search a contact by name.\n" +
                    "4. Delete an existing contact.\n" +
                    "5. Exit.\n" +
                    "Enter an option (1, 2, 3, 4 or 5):");

            int userInput = Integer.parseInt(scanner.nextLine()); // Parse the users input to int for the switch statement use

            switch(userInput){
                case 1: // view all contacts
                    viewAll();
                    break;
                case 2: // add contact
                    System.out.print("\nName: ");
                    String contactName = scanner.nextLine(); // get the users desired name

                    System.out.print("Phone Number: ");
                    String contactPhone = scanner.nextLine(); // get the user desired phone number

                    addContact(contactName, contactPhone);
                    System.out.println("Contact added: " + contactName + " | " + contactPhone); // let the user know that their contact was added
                    break;
                case 3: // search for a contact
                case 4: // delete chosen contact
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
