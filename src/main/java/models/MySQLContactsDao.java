package models;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLContactsDao implements Contacts{
    // Set up the DB connection
    private Connection conn;

    public MySQLContactsDao() throws SQLException {
        // Instantiate an object
        Config config = new Config();

        // Set up Database Driver and connect
        DriverManager.registerDriver(new Driver());

        // Get a connection to MySQL database
        this.conn = DriverManager.getConnection(
                config.getUrl(),
                config.getUsername(),
                config.getPassword()
        );
    }

    @Override
    public List<Contact> getContacts() throws SQLException {
        List<Contact> output = new ArrayList<>();
        // Query the SQL DB table for all contacts
        String query = "SELECT * FROM contacts";

        try {
            // Create a statement object
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Iterate through our result set and add each contact to our contact Bean
            while(rs.next()) {
                output.add(new Contact(
                        rs.getLong("id"), // id
                        rs.getString("first_name"), // first name
                        rs.getString("last_name"), // last name
                        rs.getString("phone_number") // phone number
                    )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return output;
    }

    @Override
    public long saveContact(Contact contact) {
        long newlyCreatedUserId = 0;
        // a contact pbject sent in with first/last/phone
        String addContactQuery = String.format( "INSERT INTO contacts (first_name, last_name, phone_number) VALUES ('%s', '%s', '%s')",
                contact.getFirstName(),
                contact.getLastName(),
                contact.getPhoneNumber());
        try {
            Statement stmt = conn.createStatement();
            // Execute SQL query to add new contact to database.
            stmt.executeUpdate(addContactQuery, Statement.RETURN_GENERATED_KEYS);
            long insertedRowId = 0;
            ResultSet ks = stmt.getGeneratedKeys();
            if (ks.next()) {
                newlyCreatedUserId = ks.getLong(1); // This will save the MySQL row ID to a variable
                System.out.println("The ID of the newly inserted row is: " + ks.getLong(1));
            }
            if (newlyCreatedUserId != 0) {
                contact.setId(newlyCreatedUserId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newlyCreatedUserId;
    }

    @Override
    public void deleteContactbyId(long id) {

        //SQL equiv for deleting row by id: DELETE FROM contacts WHERE id = sentInId
        String query = String.format("DELETE FROM contacts WHERE id = %d", id);

        // 'id'passed in is the id of the row we want to delete from our db table
        try {
            Statement stmt = conn.createStatement();
            boolean success = stmt.execute(query);
            if (success) {
                System.out.println("Contact has been deleted");
            } else {
                System.out.println("Contact was not deleted");
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Contact getContactbyId(long id) {
        Contact returnContact = new Contact();
        String query = String.format("SELECT FROM contacts WHERE id = %d", id);
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                returnContact.setId(id);
                returnContact.setFirstName(rs.getString("first_name"));
                returnContact.setLastName(rs.getString("last_name"));
                returnContact.setPhoneNumber(rs.getString("phone_number"));
            } else {
                // If there was no match for id
                System.out.println("Supplied user id found no contact matches.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return returnContact;
    }
}
