package servlet_classes;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao_classes.UserDAO;
import model_classes.User;

@WebServlet(name = "ContactNumberResetServlet", urlPatterns = {"/ContactNumberResetServlet"})
public class ContactNumberResetServlet extends HttpServlet {

    private UserDAO userDao;
    private User user;
    
    public void init(){
        userDao = new UserDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
