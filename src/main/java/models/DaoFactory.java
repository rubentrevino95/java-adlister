package models;

import java.sql.SQLException;

public class DaoFactory {
    private static Ads adsDao;
    private static User userDao;
    private static Config config = new Config();

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
