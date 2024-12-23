package dao_classes;

import utility_classes.DBConnection;
import model_classes.Cinema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CinemaDAO {
    
    // Method to add a cinema
    public void addCinema(Cinema cinema) {
        String query = "INSERT INTO cinemas (name, location) VALUES (?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, cinema.getName());
            ps.setString(2, cinema.getLocation());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in adding cinema: " + e.getMessage());
        }
    }
    
    // Method to get cinema by its id
    public Cinema getCinemaById(int cinemaId) {
        Cinema cinema = null;
        String query = "SELECT * FROM cinemas WHERE cinemaId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, cinemaId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cinema = populateCinema(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching cinema by its ID: " + e.getMessage());
        }

        return cinema;
    }
    
    // Method to update cinema details
    public void updateCinema(Cinema cinema) {
        String query = "UPDATE cinemas SET name = ?, location = ? WHERE cinemaId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, cinema.getName());
            ps.setString(2, cinema.getLocation());
            ps.setInt(3, cinema.getCinemaId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in updating cinema: " + e.getMessage());
        }
    }
    
    // Method to delete a cinema
    public void deleteCinema(int cinemaId) {
        String query = "DELETE FROM cinemas WHERE cinemaId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, cinemaId);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in deleting cinema: " + e.getMessage());
        }
    }
    
    // Method to get all cinemas
    public List<Cinema> getAllCinemas() {
        List<Cinema> cinemas = new ArrayList<>();
        String query = "SELECT * FROM cinemas";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Cinema cinema = populateCinema(rs);
                cinemas.add(cinema);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching cinemas: " + e.getMessage());
        }
        
        return cinemas;
    }
    
    // Method to get the total number of cinemas
    public int getTotalCinemas() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM cinemas";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                count = rs.getInt(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching total cinemas count: " + e.getMessage());
        }
        
        return count;
    }
    
    // Utility method to populate Cinema object from ResultSet
    private Cinema populateCinema(ResultSet rs) throws SQLException {
        Cinema cinema = new Cinema();
        cinema.setCinemaId(rs.getInt("cinemaId"));
        cinema.setName(rs.getString("name"));
        cinema.setLocation(rs.getString("location"));
        cinema.setCreatedAt(rs.getDate("createdAt"));
        
        return cinema;
    }
}
