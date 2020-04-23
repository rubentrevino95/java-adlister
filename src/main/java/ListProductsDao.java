// This is our DAO (Data Access Object, or data access class)
// Class that implements our Products DAO interface
import java.util.ArrayList;
import java.util.List;

public class ListProductsDao implements Products {

//   list all products, find a product by ID, create a product
//   this will list all products in the database table
    private List<Product> products;

//   DAO constructor
    public ListProductsDao() {
        this.products = new ArrayList<>();

//  Dummy data
        Product hammer = new Product();
        hammer.setId(1);
        hammer.setTitle("A Bad Hammer");
        hammer.setPriceInCents(3000);
        hammer.setDescription("A bad hammer");

        products.add(hammer);

    }


    @Override
    public long createProduct(Product product) {

//      create a product and insert to arrayList
//      assign an ID
        product.setId(products.size()+1); // same as auto_increment

//      adds Product to arrayList
        products.add(product);

        return product.getId();
    }

    @Override
    public Product findById(long id) {

        return products.get((int)id - 1);
    }
}
