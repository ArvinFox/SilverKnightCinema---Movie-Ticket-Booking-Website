package servlet_classes;

import dao_classes.UserDAO;
import model_classes.User;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Random;
import org.json.JSONObject;
import utility_classes.SMSUtil;

/**
 *
 * @author arvin
 */
@WebServlet(name = "SendOtpServlet", urlPatterns = {"/SendOtpServlet","/otp"})
public class SendOtpServlet extends HttpServlet 
{
    private UserDAO userDao;
    String otp;
    String contact;
    
    User user;
    
    @Override
    public void init()
    {
        userDao = new UserDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {   
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("user");
        
        if (user!=null){
            String action = request.getParameter("action");
            if("send".equals(action))
            {
                sendOTP(request, response);
            }
            else if ("verify".equals(action))
            {
                // Action for verifying OTP
                verifyOTP(request, response);
                
            }
        }
        else
        {
            response.sendRedirect("login");
        }
    }

    private void sendOTP(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        contact = request.getParameter("contact");
        //send OTP
        otp = generateOTP();

        if(contact.startsWith("0"))
        {
            contact = "+94"+contact.substring(1);
        }
        SMSUtil.sendSMS(contact , "YOUR OTP IS : "+otp);
    }
    
    private void verifyOTP(HttpServletRequest request, HttpServletResponse response) throws IOException 
    {
        String enteredOtp = request.getParameter("enteredOtp");
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JSONObject jsonResponse = new JSONObject();
        HttpSession session = request.getSession();
        
        if(otp.equals(enteredOtp))
        {
            jsonResponse.put("success", true);
            if (contact.startsWith("+94")){
                contact = "0"+contact.substring(3);
            }
            userDao.updateContactNumber(contact, user.getUserId());
            user.setContactNumber(contact);
            session.setAttribute("user", user);
            System.out.println("New contact: "+contact+" "+user.getUserId());
            
        }
        else
        {
            jsonResponse.put("success", false);
        }
        response.getWriter().write(jsonResponse.toString());
        System.out.println("response " +jsonResponse);
        System.out.println("response  String" +jsonResponse.toString());
    }
    
    private String generateOTP() 
    {
       Random rand = new Random();
       int otpCode = 100000 + rand.nextInt(900000);
       return String.valueOf(otpCode);
    }
    
}
