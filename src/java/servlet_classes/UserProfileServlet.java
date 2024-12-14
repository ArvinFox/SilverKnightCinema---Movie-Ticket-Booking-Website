package servlet_classes;

import dao_classes.UserDAO;
import model_classes.User;
import model_classes.Booking;
import dao_classes.BookingDAO;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Arvin Premathilake
 */

@WebServlet(name = "UserProfileServlet", urlPatterns = {"/UserProfileServlet","/profile"})
public class UserProfileServlet extends HttpServlet {

    UserDAO userDao;
    BookingDAO bookingDao;

    @Override
    public void init() {
        userDao = new UserDAO();
        bookingDao = new BookingDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        
        if (isLoggedIn){
            User isLoginUser = userDao.getUserByEmail(user.getEmail()); 
            if(isLoginUser != null)
            {
                List<Booking> bookings = bookingDao.getAllBookingsOfUser(user.getUserId());
                session.setAttribute("bookings", bookings);
                request.getRequestDispatcher("userProfile.jsp").forward(request, response);
            }
        }
        else{
            response.sendRedirect("login");
        }   
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
