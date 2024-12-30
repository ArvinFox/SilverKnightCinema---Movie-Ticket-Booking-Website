package servlet_classes;

import dao_classes.UserDAO;
import model_classes.User;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

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
        boolean isLoggedIn = false;
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            isLoggedIn = true;
        }
        
        Cookie[] cookies = request.getCookies();
        if(cookies != null)
        {
            for(Cookie c : cookies)
            {
                if("email".equals(c.getName()))
                {
                    isLoggedIn = true;
                }
            }
        }
        
        String checkLoggedInStatus = request.getParameter("checkIsLoggedIn");
        if (checkLoggedInStatus != null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            JSONObject jsonResponse = new JSONObject();
        
            if (isLoggedIn) {
                jsonResponse.put("success", true);
            } else {
                jsonResponse.put("success", false);
            }
            
            response.getWriter().write(jsonResponse.toString());
            return;
        }
        
        if (isLoggedIn) {
            response.sendRedirect("home");
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        request.removeAttribute("errorMessage");
        
        String email = request.getParameter("email");
        String pwd = request.getParameter("password");
        boolean rememberMe = request.getParameter("rememberMe") != null;
        
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
                    
                    if(rememberMe)
                    {
                        Cookie c = new Cookie("email", email);
                        c.setMaxAge(3*24*60*60);
                        response.addCookie(c);
                    }
                    response.sendRedirect("home");
                }
                else
                {
                    request.setAttribute("errorMessage", "Your account has been suspended");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }
            else
            {
                request.setAttribute("errorMessage", "Invalid username or password. Please try again.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
