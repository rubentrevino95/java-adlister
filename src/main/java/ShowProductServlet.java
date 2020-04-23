import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Servlet will be used to listen for users to view all products
// Products - likely by going to page like "/products"

    @WebServlet(name = "ShowProductServlet", urlPatterns = "/products/show")
    public class ShowProductServlet extends HttpServlet {
//      Will require a doGet()

//      This method assumes that there **IS** a product id in the URL parameters
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//          check to see if there is and id passed in URL parameters
            long productId = Long.parseLong(req.getParameter("id"));

            Products productDao = DaoFactory.getProductsDao();
//          will access to all Products Interface methods

//          Get the product by its ID from ListProductsDao
            Product product = productDao.findById(productId);

//          Set the attribute "product" to the object we just created
            req.setAttribute("product", product);
            req.getRequestDispatcher("/products/product-show.jsp").forward(req,resp);
        }
    }
