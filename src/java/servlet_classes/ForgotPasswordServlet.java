package servlet_classes;

import dao_classes.UserDAO;
import model_classes.User;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Random;
import utility_classes.EmailUtil;

/**
 *
 * @author Udani Indrachapa
 */
@WebServlet(name = "ForgotPasswordServlet", urlPatterns = {"/ForgotPasswordServlet","/forgotPassword"})
public class ForgotPasswordServlet extends HttpServlet 
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
        request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
       request.removeAttribute("errorMessage");
        
       String email = request.getParameter("email");
        
        try{
           User user = userDao.getUserByEmail(email);
           
           if(user != null)
           {
               boolean isAccountSuspended = userDao.isAccountSuspended(user.getUserId());
               
               if(!isAccountSuspended)
               {
                    //send email
                    String randomToken = generateOTP();
                    String resetLink = "http://localhost:8080/SilverKnightCinema/resetPassword.jsp?email="  +email + "&=" +randomToken;
                    String subject = "Password reset link - SilverKnight Cinema";
                    String message = "Hi " +user.getFirstName()+ "\n\n To reset your password, please follow the link below "
                            + "and enter a new password : \n " +resetLink+ "\n\n Please Note : This link will be valid for only 05 minutes."
                            + "\n If you didn't request this password reset, please ignore this email."
                            + "\n Best regards \n Silver Knight Cinema";
                    EmailUtil.sendEmail(email, subject, message);

                    request.setAttribute("successMessage", "Password reset link has been sent to your email");
                    request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
                }
               else
               {
                   request.setAttribute("errorMessage", "Your account has been suspended.");
                   request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
               }
           }
           else
           {
                request.setAttribute("errorMessage", "Incorrect Email Address. Please try again.");
                request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
           }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    private String generateOTP() 
    {
       Random rand = new Random();
       int token = 100000 + rand.nextInt(900000);
       return String.valueOf(token);
    }
}
