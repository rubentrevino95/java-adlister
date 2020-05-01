package models;

import java.sql.SQLException;

public class DaoFactory {
    private static Ads adsDao;
    private static User userDao;

    public static Ads getAdsDao() throws SQLException {
        Config config = new Config();
        if (adsDao == null) {
            adsDao = new MySQLAdsDao(config);
        }
        return adsDao;
    }
    public static User getUserDao() throws SQLException {
        Config config = new Config();
        if (userDao == null) {
            userDao = new MySQLUsersDao(config);
        }
        return userDao;
    }
}
