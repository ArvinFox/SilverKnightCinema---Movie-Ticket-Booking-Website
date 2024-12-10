package dao_classes;

import utility_classes.DBConnection;
import model_classes.Inquiry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InquiryDAO {
    
    // Method to add an inquiry
    public void addInquiry(Inquiry inquiry) {
        String query = "INSERT INTO inquiries (name, email, subject, message) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, inquiry.getName());
            ps.setString(2, inquiry.getEmail());
            ps.setString(3, inquiry.getSubject());
            ps.setString(4, inquiry.getMessage());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in adding inquiry: " + e.getMessage());
        }
    }
    
    // Method to get inquiry by its id
    public Inquiry getInquiryById(int inquiryId) {
        Inquiry inquiry = null;
        String query = "SELECT * FROM inquiries WHERE inquiryId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, inquiryId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                   inquiry = populateInquiry(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching inquiry by its ID: " + e.getMessage());
        }

        return inquiry;
    }
    
    // Method to delete a inquiry
    public void deleteInquiry(int inquiryId) {
        String query = "DELETE FROM inquiries WHERE inquiryId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, inquiryId);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in deleting inquiry: " + e.getMessage());
        }
    }
    
    // Method to get all inquiries
    public List<Inquiry> getAllInquiries() {
        List<Inquiry> inquiries = new ArrayList<>();
        String query = "SELECT * FROM inquiries";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
                while (rs.next()) {
                    Inquiry inquiry = populateInquiry(rs);
                    inquiries.add(inquiry);
                }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching inquiries: " + e.getMessage());
        }
        
        return inquiries;
    }
    
    // Method to get the total number of inquiries
    public int getTotalInquiries() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM inquiries";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                count = rs.getInt(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching total inquiries count: " + e.getMessage());
        }
        
        return count;
    }
    
    // Utility method to populate Inquiry object from ResultSet
    private Inquiry populateInquiry(ResultSet rs) throws SQLException {
        Inquiry inquiry = new Inquiry();
        inquiry.setInquiryId(rs.getInt("inquiryId"));
        inquiry.setName(rs.getString("name"));
        inquiry.setEmail(rs.getString("email"));
        inquiry.setSubject(rs.getString("subject"));
        inquiry.setMessage(rs.getString("message"));
        inquiry.setCreatedAt(rs.getDate("createdAt"));
        
        return inquiry;
    }
}
