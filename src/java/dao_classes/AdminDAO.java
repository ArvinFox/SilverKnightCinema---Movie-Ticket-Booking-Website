package dao_classes;

import utility_classes.DBConnection;
import model_classes.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
    
    // Method to create an admin
    public void createAdmin(Admin admin) {
        String query = "INSERT INTO admins (username, password, contactNumber) VALUES (?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, admin.getUsername());
            ps.setString(2, admin.getPassword());
            ps.setString(3, admin.getContactNumber());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in creating admin: " + e.getMessage());
        }
    }
    
    // Method to authenticate admin
    public boolean authenticateAdmin(String username, String password) {
        boolean isAdminAuthenticated = false;
        String query = "SELECT COUNT(*) FROM admins WHERE username = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    isAdminAuthenticated = count > 0;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in authenticating admin: " + e.getMessage());
        }

        return isAdminAuthenticated;
    }
    
    // Method to get admin by their id
    public Admin getAdminById(int adminId) {
        Admin admin = null;
        String query = "SELECT * FROM admins WHERE adminId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, adminId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    admin = populateAdmin(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching admin by their ID: " + e.getMessage());
        }

        return admin;
    }
    
    // Method to delete an admin
    public void deleteAdmin(int adminId) {
        String query = "DELETE FROM admins WHERE adminId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, adminId);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in deleting admin: " + e.getMessage());
        }
    }
    
    // Utility method to populate Admin object from ResultSet
    private Admin populateAdmin(ResultSet rs) throws SQLException {
        Admin admin = new Admin();
        admin.setAdminId(rs.getInt("adminId"));
        admin.setUsername(rs.getString("username"));
        admin.setPassword(rs.getString("password"));
        admin.setContactNumber(rs.getString("contactNumber"));
        admin.setCreatedAt(rs.getDate("createdAt"));
        
        return admin;
    }
}
