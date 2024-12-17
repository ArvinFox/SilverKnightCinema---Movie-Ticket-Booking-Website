package servlet_classes;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "AboutUsServlet", urlPatterns = {"/AboutUsServlet","/AboutUs"})
public class AboutUsServlet extends HttpServlet 
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        request.getRequestDispatcher("aboutUs.jsp").forward(request, response);
    }
}
