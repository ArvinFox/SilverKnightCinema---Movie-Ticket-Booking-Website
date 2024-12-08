package servlet_classes;

import dao_classes.UserDAO;
import model_classes.User;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * @author Udani Indrachapa
 */

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet","/login"})
public class LoginServlet extends HttpServlet 
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
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String email = request.getParameter("email");
        String pwd = request.getParameter("password");
        
        User isValidUser;
        try{
           isValidUser = userDao.loginUser(email, pwd);
           User user = userDao.getUserByEmail(email);
           
           if(isValidUser != null)
            {
                boolean isAccountSuspended = userDao.isAccountSuspended(user.getUserId());

                if(!isAccountSuspended)
                {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    response.sendRedirect("termsAndConditions.jsp");
                }
                else
                {
                    response.setContentType("text/html");
                    PrintWriter writer = response.getWriter();
                    writer.println("<p>Your account is suspended. Please try again.</p>");
                }
            }
            else
            {
                response.setContentType("text/html");
                PrintWriter writer = response.getWriter();
                writer.println("<p>Invalid username or password. Please try again.</p>");
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
