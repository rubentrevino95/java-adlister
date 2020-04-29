// USED TO GENERATE AN OBJECT
package models;

import java.sql.SQLException;

public class DaoFactory {
    // properties
    private static Ads adsDao;
    private static Contacts contactsDao;

    // getter
    public static Ads getAdsDao() {
        if (adsDao == null) {
            adsDao = new ListAdsDao();
        }
        return adsDao;
    }
    public static Contacts getContactsDao() throws SQLException {
        if (contactsDao == null) {
            contactsDao = new MySQLContactsDao();
        }
        return contactsDao;
    }
}
