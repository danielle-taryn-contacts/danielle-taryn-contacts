package contacts_manager;

public class Contact {

    // Create instance variables

    protected String name;
    protected String phoneNumber;

    // Create constructor for new Contact object

    public Contact(String name, String phoneNumber) {

        this.name = name;
        this.phoneNumber = phoneNumber;

    }

    // Create getter methods

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString(){
        return name + " | " + phoneNumber;
    }
}
