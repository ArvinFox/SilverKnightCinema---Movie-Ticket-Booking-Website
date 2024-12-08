package servlet_classes;

import dao_classes.UserDAO;
import model_classes.User;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
        UserDAO user_dao = new UserDAO();
        String email = request.getParameter("email");
        String newPwd = request.getParameter("new_password");
        String confirmPwd = request.getParameter("verify_password");
        
        try{
            User user = userDao.getUserByEmail(email);
            
            if(user != null)
            {
                String hashedPassword = BCrypt.hashpw(newPwd, BCrypt.gensalt());
                user.setPassword(hashedPassword);

                user_dao.resetPassword(email, hashedPassword);
                
                HttpSession session = request.getSession();
                    Boolean isLinkUsed = (Boolean) session.getAttribute("isLinkUsed");

                    if(isLinkUsed != null && isLinkUsed)
                    {
                        response.setContentType("text/html");
                        PrintWriter writer = response.getWriter();
                        writer.println("<p>This link has been already used. Request a new email again</p>");
                        return;
                    }
                    else
                    {
                        response.sendRedirect("login.jsp");
                    }
                    session.setAttribute("isLinkUsed", true);
                
                long expireTime = System.currentTimeMillis() + (5*60*1000);
                Cookie linkExpireCookie = new Cookie("linkExpire", String.valueOf(expireTime));
                linkExpireCookie.setMaxAge(5*60);
                response.addCookie(linkExpireCookie);
                
                Cookie[] cookie = request.getCookies();
                long currentTime = System.currentTimeMillis();
                boolean linkExpired = true;
                
                if(cookie != null)
                {
                    for(Cookie cookies : cookie)
                    {
                        if(cookies.getName().equals("linkExpire"))
                        {
                            long expirationTime = Long.parseLong(cookies.getValue());
                            
                            if(currentTime < expirationTime)
                            {
                                linkExpired = false;
                            }
                            break;
                        }
                    }
                }
                
                if(linkExpired)
                {
                    response.setContentType("text/html");
                    PrintWriter writer = response.getWriter();
                    writer.println("<p>This link has been expired. Request a new email again</p>");
                }
                else
                {
                    response.sendRedirect("login.jsp");
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
