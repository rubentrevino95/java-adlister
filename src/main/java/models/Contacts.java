package models;//INTERFACE FOR OUR CONTACT BEAN (implemented by DAO)

import java.sql.SQLException;
import java.util.List;
public interface Contacts {
    //List all contacts (List<models.Contacts>)
    List<Contact> getContacts() throws SQLException;

    //method for saving contact
    // will get length of List<models.Contact> and +1
    long saveContact(Contact contact);

    //method for deleting contact
    void deleteContactbyId(long id);

    //method for get a contact by ID
    Contact getContactbyId(long id);

}
