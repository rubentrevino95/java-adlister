// DAO interface

public interface Products {
//  This will return the object of type 'Product'
//  searched by 'id'

    Product findById(long id);

//  Method will insert a 'Product' into our table, the return will be said products ID

    long createProduct(Product product);

}
