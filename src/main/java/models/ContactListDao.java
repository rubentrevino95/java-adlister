package models;// CONTACTS LIST

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class ContactListDao implements Contacts{
    // INITILIZING ARRAYLIST
    private List<Contact> contacts = new ArrayList<>();

    public ContactListDao() {
        contacts.add(new Contact(1, "Billy", "Smith", "2100000000"));
        contacts.add(new Contact(1, "John", "Smith", "2100000000"));
        contacts.add(new Contact(1, "Jane", "Smith", "2100000000"));
        contacts.add(new Contact(1, "Joe", "Smith", "2100000000"));
    }

    //List all contacts (List<models.Contacts>)
    @Override
    public List<Contact> getContacts() {
        return contacts;
    }

    //method for saving contact
    // will get length of List<models.Contact> and +1
    @Override
    public long saveContact(Contact contact) {
        if(contact.getId() == 0) {
            // if setting up first contact
            contact.setId(contacts.size() + 1);
            contacts.add(contact);
        } else {
            contacts.set((int) (contact.getId() - 1), contact);
        }
        return contact.getId();
    }

    //method for deleting contact
    @Override
    public void deleteContactbyId(long id) {
        contacts.remove((int) id - 1);
    }


    //method for get a contact by ID
    @Override
    public Contact getContactbyId(long id) {
        return contacts.get((int) id - 1);
    }


    // Test the DAO to ensure working properly
    public static void main(String[] args) throws SQLException {
        Contacts contactDao = new ContactListDao();

        System.out.println("\n=== Testing getContacts()");
        List<Contact> allContacts = contactDao.getContacts();

        for (Contact contact : allContacts) {
            System.out.println(contact.getFirstName());
        }

    }

}
