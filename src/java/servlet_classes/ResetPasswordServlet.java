package servlet_classes;

import dao_classes.UserDAO;
import model_classes.User;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Udani Indrachapa
 */

@WebServlet(name = "ResetPasswordServlet", urlPatterns = {"/ResetPasswordServlet", "/resetPassword"})
public class ResetPasswordServlet extends HttpServlet 
{
    private UserDAO userDao;
    
    @Override
    public void init()
    {
        userDao = new UserDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        request.getRequestDispatcher("resetPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        request.removeAttribute("errorMessage");
        
        String token = request.getParameter("token");
        String newPwd = request.getParameter("new_password");
        
        try{
            User user = userDao.getUserByResetToken(token);
            System.out.println("Token: " + token);
            System.out.println("User: " + user);
            
            if(user != null)
            {
                long currentTime = System.currentTimeMillis();
                if (userDao.isTokenExpired(token, currentTime)) {
                    request.setAttribute("errorMessage", "This link has expired. Please request a new password reset link.");
                    request.getRequestDispatcher("resetPassword.jsp").forward(request, response);
                    return;
                }
                
                // Mark token as used and reset password
                userDao.invalidateResetToken(token);
                String hashedPassword = BCrypt.hashpw(newPwd, BCrypt.gensalt());
                userDao.resetPassword(user.getEmail(), hashedPassword);
                response.sendRedirect("profile");
            }
            else
            {
                request.setAttribute("errorMessage", "Invalid or expired link.");
                request.getRequestDispatcher("resetPassword.jsp").forward(request, response);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred. Please try again.");
            request.getRequestDispatcher("resetPassword.jsp").forward(request, response);
        }
    }
}