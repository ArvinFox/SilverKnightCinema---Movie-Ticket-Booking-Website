package servlet_classes;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model_classes.Inquiry;
import dao_classes.InquiryDAO;
import jakarta.servlet.RequestDispatcher;

/**
 * @author Sanuji
 */

@WebServlet(name = "InquiryServlet", urlPatterns = {"/InquiryServlet","/inquiries"})
public class InquiryServlet extends HttpServlet 
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InquiryServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InquiryServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        request.getRequestDispatcher("contactUs.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String subject = request.getParameter("subject");
            String message = request.getParameter("message");
            
            boolean hasErrors = false;
            StringBuilder errorMessage = new StringBuilder();

            // Check if all field are empty
            if ((name == null || name.trim().isEmpty()) && 
                (email == null || email.trim().isEmpty()) && 
                (subject == null || subject.trim().isEmpty()) && 
                (message == null || message.trim().isEmpty())) {
                errorMessage.append("All fields are required. ");
                hasErrors = true;
            } else {
                // Check if individual fields are empty
                if (name == null || name.trim().isEmpty()) {
                    errorMessage.append("Name is required. ");
                    hasErrors = true;
                }
                if (email == null || email.trim().isEmpty()) {
                    errorMessage.append("Email is required. ");
                    hasErrors = true;
                }
                if (subject == null || subject.trim().isEmpty()) {
                    errorMessage.append("Subject is required. ");
                    hasErrors = true;
                } else if (subject.length() > 80) {
                    errorMessage.append("Subject should be no more than 80 characters. ");
                    hasErrors = true;
                }             
                if (message == null || message.trim().isEmpty()) {
                    errorMessage.append("Message is required. ");
                    hasErrors = true;
                } else if (message.length() > 200) {
                    errorMessage.append("Message should be no more than 200 characters. ");
                    hasErrors = true;
                }
            }

            // Check if there are any errors and handle them
            if (hasErrors) {
                // Retain form field values
                request.setAttribute("errorMessage", errorMessage.toString());
                request.setAttribute("name", name);
                request.setAttribute("email", email);
                request.setAttribute("subject", subject);
                request.setAttribute("message", message);
                RequestDispatcher dispatcher = request.getRequestDispatcher("contactUs.jsp");
                dispatcher.forward(request, response);
            } else {
                Inquiry inquiry = new Inquiry(name, email, subject, message);
                InquiryDAO inquiryDAO = new InquiryDAO();
                boolean isSuccess = inquiryDAO.addInquiry(inquiry);

                if (isSuccess) {
                    request.setAttribute("successMessage", "Submitted successfully!");
                } else {
                    request.setAttribute("errorMessage", "Failed to submit inquiry. Please try again.");
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("contactUs.jsp");
                dispatcher.forward(request, response);
            }
     }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
