// Class for accessing the DAO
// Will reference interface
public class DaoFactory {
    private static Products productsDao; // Interface

    public static Products  getProductsDao() {
        if(productsDao == null) {
            productsDao = new ListProductsDao();
        }
        return productsDao;
    }
}
