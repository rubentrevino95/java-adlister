package controllers;

import models.DaoFactory;
import models.User;
import org.mindrot.jbcrypt.BCrypt;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

@WebServlet(name = "controllers.RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//         TODO: show the registration form
        request.getRequestDispatcher("/WEB-INF/users/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        // TODO: ensure the submitted information is valid
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirm");
        String hash = BCrypt.hashpw(password, BCrypt.gensalt());

        if (!validRegistrationInput(username, email, password)) {
            request.getRequestDispatcher("/WEB-INF/users/register.jsp").forward(request, response);
            return;
        }
        if (!confirm.equals(password)){
            request.getRequestDispatcher("/WEB-INF/users/register.jsp").forward(request, response);
            return;
        }
        User user = new User(username, email, password);
        try {
            DaoFactory.getUserDao().getUsername();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private boolean validRegistrationInput(String username, String email, String password) {
        if (!(isValidUsername(username) && isValidEmail(email))) return false;
        return true;
    }

    private boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    private boolean isValidUsername(String username) {
        if (username.length() > 4 && username.length() < 21) return true;
        Pattern pattern = Pattern.compile("[A-Za-z_][A-Za-z0-9_] +");
        return false;
    }
    // add valid password regex method

}
