package dao_classes;

import utility_classes.DBConnection;
import model_classes.Hall;
import model_classes.Hall.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HallDAO {
    
    // Method to add a hall
    public void addHall(Hall hall) {
        String query = "INSERT INTO halls (name, type, capacity, location) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, hall.getName());
            ps.setString(2, hall.getType().toString());
            ps.setInt(3, hall.getCapacity());
            ps.setString(4, hall.getLocation());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in adding hall: " + e.getMessage());
        }
    }
    
    // Method to get hall by its id
    public Hall getHallById(int hallId) {
        Hall hall = null;
        String query = "SELECT * FROM halls WHERE hallId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, hallId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    hall = populateHall(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching hall by its ID: " + e.getMessage());
        }

        return hall;
    }
    
    // Method to update hall details
    public void updateHall(Hall hall) {
        String query = "UPDATE halls SET name = ?, type = ?, capacity = ?, location = ? WHERE hallId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, hall.getName());
            ps.setString(2, hall.getType().toString());
            ps.setInt(3, hall.getCapacity());
            ps.setString(4, hall.getLocation());
            ps.setInt(5, hall.getHallId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in updating hall: " + e.getMessage());
        }
    }
    
    // Method to delete a hall
    public void deleteHall(int hallId) {
        String query = "DELETE FROM halls WHERE hallId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, hallId);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in deleting hall: " + e.getMessage());
        }
    }
    
    // Method to get all halls
    public List<Hall> getAllHalls() {
        List<Hall> halls = new ArrayList<>();
        String query = "SELECT * FROM halls";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Hall hall = populateHall(rs);
                halls.add(hall);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching halls: " + e.getMessage());
        }
        
        return halls;
    }
    
    // Method to get the total number of halls
    public int getTotalHalls() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM halls";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                count = rs.getInt(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching total halls count: " + e.getMessage());
        }
        
        return count;
    }
    
    // Utility method to populate Hall object from ResultSet
    private Hall populateHall(ResultSet rs) throws SQLException {
        Hall hall = new Hall();
        hall.setHallId(rs.getInt("hallId"));
        hall.setName(rs.getString("name"));
        hall.setType(Type.fromString(rs.getString("type")));
        hall.setCapacity(rs.getInt("capacity"));
        hall.setLocation(rs.getString("location"));
        hall.setCreatedAt(rs.getDate("createdAt"));
        hall.setUpdatedAt(rs.getDate("updatedAt"));
        
        return hall;
    }
}
