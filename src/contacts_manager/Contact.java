package contacts_manager;

public class Contact {

    // ------------------- INITIALIZE --------------------

    protected String name;
    protected String phoneNumber;


    // ------------------- CLASS CONSTRUCTOR --------------------

    public Contact(String name, String phoneNumber) {

        this.name = name;
        this.phoneNumber = phoneNumber;

    }


    // ------------------- GET (NAME) --------------------

    public String getName() {
        return name;
    }


    // ------------------- GET (PHONE) --------------------

    public String getPhoneNumber() {
        return phoneNumber;
    }

}
