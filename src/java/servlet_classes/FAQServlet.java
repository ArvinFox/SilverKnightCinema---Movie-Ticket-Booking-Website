package servlet_classes;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Udani Indrachapa
 */
@WebServlet(name = "FAQServlet", urlPatterns = {"/FAQServlet","/FAQ"})
public class FAQServlet extends HttpServlet 
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        HttpSession session = request.getSession(false); 
        boolean isLoggedIn = false;
        
        if (session != null && session.getAttribute("user") != null)
        {
            isLoggedIn = true; 
        }
        
        request.setAttribute("isLoggedIn", isLoggedIn);
        request.getRequestDispatcher("faq.jsp").forward(request, response);
    }
}