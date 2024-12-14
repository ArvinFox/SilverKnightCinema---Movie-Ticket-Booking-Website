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
        String query = "INSERT INTO halls (name, type, capacity, location, hallUrl) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, hall.getName());
            ps.setString(2, hall.getType().toString());
            ps.setInt(3, hall.getCapacity());
            ps.setString(4, hall.getLocation());
            ps.setString(5, hall.getHallUrl());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in adding hall: " + e.getMessage());
        }
    }
    
    // Method to get searched halls
    public List<Hall> getSearchedHalls(String hallName, String hallType, Integer minCapacity, Integer maxCapacity, String location) {
        List<Hall> halls = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM halls WHERE 1=1");
        
        List<Object> parameters = new ArrayList<>();
        
        if (hallName != null && !hallName.trim().isEmpty()) {
            query.append(" AND name LIKE ?");
            parameters.add("%" + hallName.trim() + "%");
        }
        
        if (hallType != null && !hallType.trim().isEmpty() && !hallType.equals("any")) {
            query.append(" AND type = ?");
            parameters.add(hallType);
        }
        
        if (minCapacity != null) {
            query.append(" AND capacity >= ?");
            parameters.add(minCapacity);
        }
        
        if (maxCapacity != null) {
            query.append(" AND capacity <= ?");
            parameters.add(maxCapacity);
        }
        
        if (location != null && !location.trim().isEmpty()) {
            query.append(" AND location LIKE ?");
            parameters.add("%" + location.trim() + "%");
        }
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query.toString())) {
            
            for (int i = 0; i < parameters.size(); i++) {
                ps.setObject(i + 1, parameters.get(i));
            }
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Hall hall = populateHall(rs);
                    halls.add(hall);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching searched halls: " + e.getMessage());
        }
        
        return halls;
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
        String query = "UPDATE halls SET name = ?, type = ?, capacity = ?, location = ?, hallUrl = ? WHERE hallId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, hall.getName());
            ps.setString(2, hall.getType().toString());
            ps.setInt(3, hall.getCapacity());
            ps.setString(4, hall.getLocation());
            ps.setString(5, hall.getHallUrl());
            ps.setInt(6, hall.getHallId());
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
        hall.setHallUrl(rs.getString("hallUrl"));
        hall.setCreatedAt(rs.getDate("createdAt"));
        hall.setUpdatedAt(rs.getDate("updatedAt"));
        
        return hall;
    }
}
