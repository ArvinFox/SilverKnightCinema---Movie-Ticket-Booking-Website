package dao_classes;

import utility_classes.DBConnection;
import model_classes.Guest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuestDAO {
    
    // Method to add a guest
    public void addGuest(Guest guest) {
        String query = "INSERT INTO guests (name, email, contactNumber) VALUES (?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, guest.getName());
            ps.setString(2, guest.getEmail());
            ps.setString(3, guest.getContactNumber());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in adding guest: " + e.getMessage());
        }
    }
    
    // Method to get guest by their id
    public Guest getGuestById(int guestId) {
        Guest guest = null;
        String query = "SELECT * FROM guests WHERE guestId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, guestId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    guest = populateGuest(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching guest by their ID: " + e.getMessage());
        }

        return guest;
    }
    
    // Method to get guest by their email
    public Guest getGuestByEmail(String email) {
        Guest guest = null;
        String query = "SELECT * FROM guests WHERE email = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    guest = populateGuest(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching guest by their email: " + e.getMessage());
        }

        return guest;
    }
    
    // Method to get guest by their contact number
    public Guest getGuestByContactNumber(String contactNumber) {
        Guest guest = null;
        String query = "SELECT * FROM guests WHERE contactNumber = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, contactNumber);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    guest = populateGuest(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching guest by their contact number: " + e.getMessage());
        }

        return guest;
    }
    
    // Method to delete a guest
    public void deleteGuest(int guestId) {
        String query = "DELETE FROM guests WHERE guestId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, guestId);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in deleting guest: " + e.getMessage());
        }
    }
    
    // Method to get all guests
    public List<Guest> getAllGuests() {
        List<Guest> guests = new ArrayList<>();
        String query = "SELECT * FROM guests";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Guest guest = populateGuest(rs);
                guests.add(guest);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching guests: " + e.getMessage());
        }
        
        return guests;
    }
    
    // Method to get the total number of guests
    public int getTotalGuests() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM guests";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                count = rs.getInt(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching total guests count: " + e.getMessage());
        }
        
        return count;
    }
    
    // Utility method to populate Guest object from ResultSet
    private Guest populateGuest(ResultSet rs) throws SQLException {
        Guest guest = new Guest();
        guest.setGuestId(rs.getInt("guestId"));
        guest.setName(rs.getString("name"));
        guest.setEmail(rs.getString("email"));
        guest.setContactNumber(rs.getString("contactNumber"));
        guest.setCreatedAt(rs.getDate("createdAt"));
        
        return guest;
    }
}
