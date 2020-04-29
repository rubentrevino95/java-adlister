package models;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;
import java.sql.DriverManager;
import com.mysql.cj.jdbc.Driver;

public class JDBCLecture {

    public static void main(String[] args) throws Exception {
        // Set up Database Driver and connect
        DriverManager.registerDriver(new Driver());

        // Instantiate an object
        Config config = new Config();

        // Get a connection to MySQL database
        Connection conn = DriverManager.getConnection(
                config.getUrl(),
                config.getUsername(),
                config.getPassword()
        );
        // Create statement object
        Statement stmt = conn.createStatement();

        //Create queries
        String contactsQuery = "SELECT * FROM contacts";

        ResultSet rs = stmt.executeQuery(contactsQuery);

        // Display the result set (rs)
        while(rs.next()) {
            System.out.println(rs.getString("first_name") + " " + rs.getString("last_name")
             + " " + rs.getString("phone_number"));
        }

        // To add a row to database:
        // 1: Create a contact object (Bean)
        // 2: Use DAO to add our new contact using the saveContact() method, returns ID
        // 3: Create an SQL Query to insert that Contact object into our database, as a new row, using the ID we previously retrieved.

        // Will allow us to use the methods defined in the DAO
        // Obtains from Contacts INTERFACE
        Contacts clDao = DaoFactory.getContactsDao();

        Contact ruben = new Contact(
                "Ruben",
                "Trevino",
                "2100000000");

        long newContactId = clDao.saveContact(ruben);
        Contact newlyCreatedContact = clDao.getContactbyId(newContactId);

        // INSERT INTO contacts (first_name, last_name, phone_number) VALUE ('Ruben', 'Trevino', '2100000000')

        String addContactQuery = String.format( "INSERT INTO contacts (first_name, last_name, phone_number) VALUES ('%s', '%s', '%s')",
                newlyCreatedContact.getFirstName(),
                newlyCreatedContact.getLastName(),
                newlyCreatedContact.getPhoneNumber());

        // Execute SQL query to add new contact to database.
        stmt.executeUpdate(addContactQuery, Statement.RETURN_GENERATED_KEYS);

        // If we add Statement.RETURN_GENERATED_KEYS, we can work with the actual MySQL  DB table row ID's
        // and reassign those ID's to our Contact objects here in out Java code.
        long insertedRowId = 0;
        ResultSet ks = stmt.getGeneratedKeys();
        if (ks.next()) {
            insertedRowId = ks.getLong(1); // This will save the MySQL row ID to a variable
            System.out.println("The ID of the newly inserted row is: " + ks.getLong(1));
        }
        System.out.println("Before doing the MySQL id check," + newlyCreatedContact.getFirstName() + "'s id was: " + newlyCreatedContact.getId());

        // Check to see if the id was returned, or if insertedRowId is still at its default of '0'
        if (insertedRowId != 0) {
            newlyCreatedContact.setId(insertedRowId);
        }
        System.out.println("After doing the MySQL id check," + newlyCreatedContact.getFirstName() + "'s id was: " + newlyCreatedContact.getId());

    }
}
