package contacts_manager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContactsApp {

    // VARIABLES

    // Initialize variables for directory (file to hold contact info files) and database (text file holding contacts [names and phone numbers])
    static String contactsDirectory = "database"; // directory name as a variable
    static String contactsData = "contacts.txt"; // file name as a variable

    // Create a path that points to our contactsDirectory
    static Path contactsDirectoryPath = Paths.get("./src/contacts_manager/" + contactsDirectory);

    // Create a path that points to our contacts.txt file
    static Path contactsFilePath = Paths.get("./src/contacts_manager/" + contactsDirectory + "/" + contactsData);

    // Create an ArrayList to hold all contacts
    static List<String> contactsList = new ArrayList<String>();



    // METHODS

    // Method to add a new contact
    public static void addContact(String name, String phone) throws IOException {
        contactsList.add(new Contact(name, phone).toString());
        Files.write(contactsFilePath, contactsList);
    }




    public static void main(String[] args) throws IOException {

        // If no directory exists at the path to our directory, make a new directory
        if (!Files.exists(contactsDirectoryPath)) {
            Files.createDirectory(contactsDirectoryPath);
        }

        // If no file exists at the path to our file, make a new file
        if (!Files.exists(contactsFilePath)) {
            Files.createFile(contactsFilePath);
        }



        addContact("Danielle", "2102322232");
        addContact("Taryn", "2103678024");


    }




//    public class Main {
//
//        public static void main(String[] args) throws IOException {
//            // test to see if a directory exist
//            // Create it if it doesn't
//            // create a file
//            // crate and write to the file
//            // Break groceries into its own file
//            // Append to the file
//            // read the list
//            // Print the list
//            // CREATE_NEW crash
//            // TRUNCATE_EXISTING works
//
//            String dataPathName = "data"; // directory name as a variable
//            String dataFileName = "data.txt"; // file name as a variable
//
//            Path dataPath = Paths.get(dataPathName);
//            if (!Files.exists(dataPath)) {
//                Files.createDirectory(dataPath);
//            }
//            System.out.println(Files.exists(dataPath));
//
//            // Create a file
//            Path dataFilePath = Paths.get(dataPathName, dataFileName);
////        Files.createFile(dataFilePath);
//            String line = "Whose line is it, anyway?";
//            Files.write(dataFilePath, Arrays.asList(line));
//
//            // Break groceries into its own file
//            String groceriesFileName = "groceries.txt"; // file name as a variable
//            Path groceriesPath = Paths.get(dataPathName, groceriesFileName);
//            List<String> groceries = Arrays.asList("eggs", "toilet paper", "sanitizer");
//            Files.write(groceriesPath, groceries);
//
//            // Append to groceries
//            line = "tissues";
//            Files.write(groceriesPath, Arrays.asList(line), StandardOpenOption.APPEND);
//
//            // read the list
//            List<String> readList = Files.readAllLines(groceriesPath);
//            System.out.println(readList);
//
//
//        }
//    }

}
