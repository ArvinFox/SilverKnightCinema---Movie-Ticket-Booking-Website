package servlet_classes;

import dao_classes.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model_classes.User;
import org.mindrot.jbcrypt.BCrypt;


@WebServlet(name = "SignupServlet", urlPatterns = {"/SignupServlet", "/signup"})
public class SignupServlet extends HttpServlet {

    private UserDAO userDao;
    
    @Override
    public void init(){
        userDao = new UserDAO();
    }  
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }
    
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO user_dao = new UserDAO();
        String firstname = request.getParameter("fname");
        String lastname = request.getParameter("lname");
        String contactNumber = request.getParameter("contact_no");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        
        User user = new User();
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setContactNumber(contactNumber);
        user.setEmail(email);
        String hashpassword = BCrypt.hashpw(password, BCrypt.gensalt());
        
        user.setPassword(hashpassword);
        
        boolean validEmail; 
        boolean validContact;
        try {
           validEmail = !userDao.isEmailRegistered(email);
           
           validContact = !userDao.isContactNumberRegistered(contactNumber);
           
           if(!validEmail){
                request.setAttribute("errorMessage", "Email is already exist.");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
           }
           else if (!validContact){
                request.setAttribute("errorMessage", "contact is already exist.");
                request.getRequestDispatcher("signup.jsp").forward(request, response);  
           }
           else if (!password.equals(confirmPassword)){
                request.setAttribute("errorMessage", "Password doesn't match.");
                request.getRequestDispatcher("signup.jsp").forward(request, response);  
           }
           else {
                user_dao.registerUser(user);
                response.sendRedirect("login.jsp");
           }
           
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        
        
    }
    
    
 
}
