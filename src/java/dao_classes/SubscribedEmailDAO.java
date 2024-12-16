package dao_classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model_classes.SubscribedEmail;
import utility_classes.DBConnection;

public class SubscribedEmailDAO 
{
    // Method to add a subscribed Emails
    public void addEmail(SubscribedEmail email) {
        String query = "INSERT INTO subscribedEmails (email) VALUES (?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, email.getEmail());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in subscribe our site: " + e.getMessage());
        }
    }
    
    // Method to unsubscribe
    public void unsubscribeEmail(String email) {
        String query = "DELETE FROM subscribedemails WHERE email = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, email);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in unsubscribe our site: " + e.getMessage());
        }
    }
    
    // Method to check if email is already entered
    public boolean isEmailRegistered(String email) {
        boolean isRegistered = false;
        String query = "SELECT COUNT(*) FROM subscribedemails WHERE email = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    isRegistered = count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in checking whether email is registered: " + e.getMessage());
        }

        return isRegistered;
    }
}
