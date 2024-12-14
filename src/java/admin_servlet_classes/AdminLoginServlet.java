package admin_servlet_classes;

import dao_classes.AdminDAO;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "AdminLoginServlet", urlPatterns = {"/AdminLoginServlet", "/admin/login"})
public class AdminLoginServlet extends HttpServlet {
    private AdminDAO adminDAO;
    
    @Override
    public void init() {
        adminDAO = new AdminDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        
        if (username == null) {
            request.getRequestDispatcher("../adminView/login.jsp").forward(request, response);
        } else {
            response.sendRedirect("dashboard");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        boolean isAdmin = adminDAO.authenticateAdmin(username, password);
        
        if (isAdmin) {
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(30 * 60);   // Set timeout to 30 minutes 
            session.setAttribute("username", username);
            response.sendRedirect("dashboard");
            
        } else {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("../adminView/login.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet used to authenticate admins and allow only authorized access to the admin panel.";
    }
}
