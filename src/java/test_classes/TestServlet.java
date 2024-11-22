package test_classes;

// import utility_classes.DBConnection;
// import utility_classes.SMSUtil;
// import utility_classes.EmailUtil;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "TestServlet", urlPatterns = {"/TestServlet", "/test"})
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.getWriter().println("Testing...");
        
        // Testing Database Connections
        // Connection connection = DBConnection.getConnection();    // Success
        // DBConnection.closeConnection(connection);    // Success
        
        // Testing SMS Functionality
        //SMSUtil.sendSMS("phone number", "Testing the SMS functionality.");    // Success
        
        // Testing Email Functionality
        // EmailUtil.sendEmail("recipient email", "Testing Purposes", "Testing the Email functionality.");  // Success
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Servlet used to test the features and functionalities of our SilverKnightCinema Project";
    }
}
