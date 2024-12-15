package servlet_classes;

import dao_classes.UserDAO;
import model_classes.User;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.SecureRandom;
import java.util.Base64;
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
                    String token = generateToken();
                    long expiryTime = System.currentTimeMillis() + (5 * 60 * 1000);
                    
                    // Store token and expiry in the database
                    userDao.storeResetToken(user.getUserId(), token, expiryTime);
                    
                    String resetLink = "http://localhost:8080/SilverKnightCinema/resetPassword.jsp?token=" + token;
                    String subject = "Password reset link - SilverKnight Cinema";
                    String message = "Hi " +user.getFirstName()+ "\n\n To reset your password, please follow the link below "
                            + "and enter a new password : \n " +resetLink+ "\n\n Please Note : This link will be valid for only 5 minutes."
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
            request.setAttribute("errorMessage", "An error occurred. Please try again.");
            request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
        }
    }
    
    private String generateToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }
}