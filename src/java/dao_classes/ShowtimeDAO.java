package dao_classes;

import utility_classes.DBConnection;
import model_classes.Showtime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowtimeDAO {
    
    // Method to create a showtime
    public void createShowtime(Showtime showtime) {
        String query = "INSERT INTO showtimes (hallId, movieId, showDate, showTime) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, showtime.getHallId());
            ps.setInt(2, showtime.getMovieId());
            ps.setDate(3, showtime.getShowDate());
            ps.setTime(4, showtime.getShowTime());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in creating showtime: " + e.getMessage());
        }
    }
    
    // Method to get filtered showtimes by user
    public List<Showtime> getFilteredShowtimes(int movieId, int cinemaId, String date, String experience) {
        List<Showtime> showtimes = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT s.* FROM showtimes s");
        
        query.append(" JOIN halls h ON s.hallId = h.hallId");
        
        List<Object> parameters = new ArrayList<>();
        
        if (cinemaId != -1) {
            query.append(" JOIN cinemas c ON h.cinemaId = c.cinemaId");
            query.append(" WHERE s.movieId = ?");
            query.append(" AND c.cinemaId = ?");
            parameters.add(movieId);
            parameters.add(cinemaId);
        } else {
            query.append(" WHERE s.movieId = ?");
            parameters.add(movieId);
        }

        if (date != null && !date.trim().isEmpty()) {
            query.append(" AND s.showDate = ?");
            parameters.add(date);
        }

        if (experience != null && !experience.equals("any")) {
            query.append(" AND h.type = ?");
            parameters.add(experience);
        }
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query.toString())) {
            
            for (int i = 0; i < parameters.size(); i++) {
                ps.setObject(i + 1, parameters.get(i));
            }
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Showtime showtime = populateShowtime(rs);
                    showtimes.add(showtime);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching filtered showtimes: " + e.getMessage());
        }
        
        return showtimes;
    }
    
    // Method to get searched showtimes
    public List<Showtime> getSearchedShowtimes(Integer hallId, Integer movieId, String showDate, String showTime) {
        List<Showtime> showtimes = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM showtimes WHERE 1=1");
        
        List<Object> parameters = new ArrayList<>();
        
        if (hallId != null) {
            query.append(" AND hallId = ?");
            parameters.add(hallId);
        }
        
        if (movieId != null) {
            query.append(" AND movieId = ?");
            parameters.add(movieId);
        }
        
        if (showDate != null && !showDate.trim().isEmpty()) {
            query.append(" AND showDate = ?");
            parameters.add(showDate);
        }
        
        if (showTime != null && !showTime.trim().isEmpty()) {
            query.append(" AND showTime = ?");
            parameters.add(showTime + ":00");
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query.toString())) {
            
            for (int i = 0; i < parameters.size(); i++) {
                ps.setObject(i + 1, parameters.get(i));
            }
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Showtime showtime = populateShowtime(rs);
                    showtimes.add(showtime);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching searched showtimes: " + e.getMessage());
        }
        
        return showtimes;
    }
    
    // Method to get showtime by its id
    public Showtime getShowtimeById(int showtimeId) {
        Showtime showtime = null;
        String query = "SELECT * FROM showtimes WHERE showtimeId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, showtimeId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    showtime = populateShowtime(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching showtime by its ID: " + e.getMessage());
        }

        return showtime;
    }
    
    // Method to update showtime details
    public void updateShowtime(Showtime showtime) {
        String query = "UPDATE showtimes SET hallId = ?, movieId = ?, showDate = ?, showTime = ? WHERE showtimeId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, showtime.getHallId());
            ps.setInt(2, showtime.getMovieId());
            ps.setDate(3, showtime.getShowDate());
            ps.setTime(4, showtime.getShowTime());
            ps.setInt(5, showtime.getShowtimeId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in updating showtime: " + e.getMessage());
        }
    }
    
    // Method to delete a showtime
    public void deleteShowtime(int showtimeId) {
        String query = "DELETE FROM showtimes WHERE showtimeId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, showtimeId);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in deleting showtime: " + e.getMessage());
        }
    }
    
    // Method to delete all showtimes of a movie
    public void deleteShowtimesByMovie(int movieId) {
        String query = "DELETE FROM showtimes WHERE movieId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, movieId);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in deleting showtimes by movie: " + e.getMessage());
        }
    }
    
    // Method to get all showtimes
    public List<Showtime> getAllShowtimes() {
        List<Showtime> showtimes = new ArrayList<>();
        String query = "SELECT * FROM showtimes";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
                
                while (rs.next()) {
                    Showtime showtime = populateShowtime(rs);
                    showtimes.add(showtime);
                }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching showtimes: " + e.getMessage());
        }
        
        return showtimes;
    }
    
    // Method to get all showtimes of a specific movie
    public List<Showtime> getShowtimesByMovie(int movieId) {
        List<Showtime> showtimes = new ArrayList<>();
        String query = "SELECT * FROM showtimes WHERE movieId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, movieId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Showtime showtime = populateShowtime(rs);
                    showtimes.add(showtime);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching showtimes of movie: " + e.getMessage());
        }
        
        return showtimes;
    }
    
    // Method to get all showtimes of a specific hall
    public List<Showtime> getShowtimesByHall(int hallId) {
        List<Showtime> showtimes = new ArrayList<>();
        String query = "SELECT * FROM showtimes WHERE hallId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, hallId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Showtime showtime = populateShowtime(rs);
                    showtimes.add(showtime);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching showtimes of hall: " + e.getMessage());
        }
        
        return showtimes;
    }
    
    // Method to get the total number of showtimes
    public int getTotalShowtimes() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM showtimes";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                count = rs.getInt(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching total showtimes count: " + e.getMessage());
        }
        
        return count;
    }
    
    // Utility method to populate Showtime object from ResultSet
    private Showtime populateShowtime(ResultSet rs) throws SQLException {
        Showtime showtime = new Showtime();
        showtime.setShowtimeId(rs.getInt("showtimeId"));
        showtime.setHallId(rs.getInt("hallId"));
        showtime.setMovieId(rs.getInt("movieId"));
        showtime.setShowDate(rs.getDate("showDate"));
        showtime.setShowTime(rs.getTime("showTime"));
        
        return showtime;
    }
}
