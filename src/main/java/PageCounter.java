import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "PageCounterServlet", urlPatterns = "/count")

public class PageCounter extends HttpServlet {

    public static int count;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        count++;

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>This page has been viewed: " + count + "</h1>");
    }


}