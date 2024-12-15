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
    
    public boolean addInquiry(Inquiry inquiry) {
    String query = "INSERT INTO inquiries (name, email, subject, message) VALUES (?, ?, ?, ?)";
    boolean isSuccess = false;
    
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {

        ps.setString(1, inquiry.getName());
        ps.setString(2, inquiry.getEmail());
        ps.setString(3, inquiry.getSubject());
        ps.setString(4, inquiry.getMessage());
        ps.executeUpdate();
        isSuccess = true;
        
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error in adding inquiry: " + e.getMessage());
    }
    
    return isSuccess;
}
    // Method to get searched inquiries
    public List<Inquiry> getSearchedInquiries(String email, String subject, String startDate, String endDate) {
        List<Inquiry> inquiries = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM inquiries WHERE 1=1");
        
        List<Object> parameters = new ArrayList<>();
        
        if (email != null && !email.trim().isEmpty()) {
            query.append(" AND email LIKE ?");
            parameters.add("%" + email.trim() + "%");
        }
        
        if (subject != null && !subject.trim().isEmpty()) {
            query.append(" AND subject LIKE ?");
            parameters.add("%" + subject.trim() + "%");
        }
        
        if (startDate != null && !startDate.trim().isEmpty()) {
            query.append(" AND createdAt >= ?");
            parameters.add(startDate.trim());
        }
        
        if (endDate != null && !endDate.trim().isEmpty()) {
            query.append(" AND createdAt <= ?");
            parameters.add(endDate.trim());
        }
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query.toString())) {
            
            for (int i = 0; i < parameters.size(); i++) {
                ps.setObject(i + 1, parameters.get(i));
            }
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Inquiry inquiry = populateInquiry(rs);
                    inquiries.add(inquiry);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching searched inquiries: " + e.getMessage());
        }
        
        return inquiries;
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
