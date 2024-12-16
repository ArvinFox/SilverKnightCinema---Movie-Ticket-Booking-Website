package servlet_classes;

import dao_classes.SubscribedEmailDAO;
import utility_classes.EmailUtil;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model_classes.SubscribedEmail;

@WebServlet(name = "EmailSubscriptionServlet", urlPatterns = {"/EmailSubscriptionServlet","/subscribe"})
public class EmailSubscriptionServlet extends HttpServlet 
{
private SubscribedEmailDAO subscribedEmailDao;
    
    @Override
    public void init()
    {
        subscribedEmailDao = new SubscribedEmailDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String email = request.getParameter("email");
        String unSubscribe = request.getParameter("unSubscribe");
        
        boolean isSubscribed = subscribedEmailDao.isEmailRegistered(email);
        
        if(!isSubscribed)
        {
            SubscribedEmail newEmail = new SubscribedEmail();
            newEmail.setEmail(email);
            subscribedEmailDao.addEmail(newEmail);
            String unsubscribeLink = "http://localhost:8080/SilverKnightCinema/subscribe?email=" +email+ "&unSubscribe=true";
            String subject = "Unsubscribe link - SilverKnight Cinema";
            String message = "Hi , Thank you for subscribing to our Silver Knight Cinema website.\n To unsubscribe our Silver Knight Cinema website click the below link...\n " +unsubscribeLink;
            EmailUtil.sendEmail(email, subject, message);
        }
        else
        {
            if(unSubscribe != null)
            {
                subscribedEmailDao.unsubscribeEmail(email);
                System.out.println("true");
                request.setAttribute("unSubscribe", true);
                request.getRequestDispatcher("termsAndConditions.jsp").forward(request, response);
            }
        }
    }
}
