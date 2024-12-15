package admin_servlet_classes;

import model_classes.Inquiry;
import dao_classes.InquiryDAO;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "AdminManageInquiriesServlet", urlPatterns = {"/AdminManageInquiriesServlet", "/admin/manageInquiries"})
public class AdminManageInquiriesServlet extends HttpServlet {
    private InquiryDAO inquiryDAO;
    
    @Override
    public void init() {
        inquiryDAO = new InquiryDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        List<Inquiry> inquiryList;
        
        if (username != null) {
            if ((email != null && !email.trim().isEmpty()) || (subject != null && !subject.trim().isEmpty()) || (startDate != null && !startDate.trim().isEmpty()) || (endDate != null && !endDate.trim().isEmpty())) {
                inquiryList = inquiryDAO.getSearchedInquiries(email, subject, startDate, endDate);
            } else {
                inquiryList = inquiryDAO.getAllInquiries();
            }
                    
            request.setAttribute("inquiryList", inquiryList);            
            request.getRequestDispatcher("../adminView/manageInquiries.jsp").forward(request, response);
            
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
                case "delete" -> {
                    int inquiryId = Integer.parseInt(request.getParameter("inquiryId"));
                    inquiryDAO.deleteInquiry(inquiryId);
                    actionResponse = "Inquiry deleted successfully.";
                }
                
                default -> response.sendRedirect("login");
            }
            
            // Redirect the admin to manage inquiries panel (manageInquiries), and display the action response 
            response.sendRedirect("manageInquiries");
            
        } else {
            response.sendRedirect("login");
        }       
    }

    @Override
    public String getServletInfo() {
        return "Servlet that manages inquiries, including view, and delete functionalities.";
    }
}
