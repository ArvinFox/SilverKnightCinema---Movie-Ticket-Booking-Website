package admin_servlet_classes;

import model_classes.User;
import model_classes.User.AccountStatus;
import dao_classes.UserDAO;
import model_classes.Guest;
import dao_classes.GuestDAO;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "AdminManageUsersServlet", urlPatterns = {"/AdminManageUsersServlet", "/admin/manageUsers"})
public class AdminManageUsersServlet extends HttpServlet {
    private UserDAO userDAO;
    private GuestDAO guestDAO;
    
    @Override
    public void init() {
        userDAO = new UserDAO();
        guestDAO = new GuestDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        
        String type = request.getParameter("type");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String contactNumber = request.getParameter("contactNumber");
        String status = request.getParameter("status");
        List<User> userList = null;
        List<Guest> guestList = null;
        
        if (username != null) {
            if ((type != null && !type.equals("any")) || (name != null && !name.trim().isEmpty()) || (email != null && !email.trim().isEmpty()) || (contactNumber != null && !contactNumber.trim().isEmpty()) || (status != null && !status.equals("any"))) {
                if ("Registered Users".equals(type)) {
                    userList = userDAO.getSearchedUsers(name, email, contactNumber, status);
                } else if ("Guests".equals(type)) {
                    guestList = guestDAO.getSearchedGuests(name, email, contactNumber);
                } else {
                    userList = userDAO.getSearchedUsers(name, email, contactNumber, status);
                    guestList = guestDAO.getSearchedGuests(name, email, contactNumber);
                }
                
            } else {
                userList = userDAO.getAllUsers();
                guestList = guestDAO.getAllGuests();
            }
            
            request.setAttribute("userList", userList);
            request.setAttribute("guestList", guestList);
            request.getRequestDispatcher("../adminView/manageUsers.jsp").forward(request, response);
            
        } else {
            response.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        
        if (username != null) {
            String action = request.getParameter("action");
            String actionResponse = "Failed to perform operation.";
            
            if (null != action) switch (action) {
                
                case "status" -> {
                    int userId = Integer.parseInt(request.getParameter("userId"));
                    User user = userDAO.getUserById(userId);
                    
                    if (userDAO.isAccountSuspended(userId)) {
                        user.setAccountStatus(AccountStatus.ACTIVE);
                    } else {
                        user.setAccountStatus(AccountStatus.SUSPENDED);
                    }
                    
                    userDAO.updateUser(user);
                    actionResponse = "Account status updated successfully.";
                }
                
                case "delete" -> {
                    String type = request.getParameter("type");
                    
                    if ("user".equals(type)) {
                        int userId = Integer.parseInt(request.getParameter("userId"));
                        userDAO.deleteUser(userId);
                        actionResponse = "User deleted successfully.";
                    } else if ("guest".equals(type)) {
                        int guestId = Integer.parseInt(request.getParameter("guestId"));
                        guestDAO.deleteGuest(guestId);
                        actionResponse = "Guest deleted successfully.";
                    }
                }
                
                default -> response.sendRedirect("login");
            }
            
            // Redirect the admin to manage users panel (manageUsers), and display the action response 
            response.sendRedirect("manageUsers");
            
        } else {
            response.sendRedirect("login");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet that manages users (registered users and guests), including view, update status, and delete functionalities.";
    }
}
