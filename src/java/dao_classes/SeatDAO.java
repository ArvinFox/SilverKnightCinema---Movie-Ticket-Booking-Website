package dao_classes;

import utility_classes.DBConnection;
import model_classes.Seat;
import model_classes.Seat.SeatType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeatDAO {
    
    // Method to add a seat
    public void addSeat(Seat seat) {
        String query = "INSERT INTO seats (hallId, showtimeId, seatNumber, seatType, price, isAvailable, isReserved) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, seat.getHallId());
            ps.setInt(2, seat.getShowtimeId());
            ps.setString(3, seat.getSeatNumber());
            ps.setString(4, seat.getSeatType().toString());
            ps.setDouble(5, seat.getPrice());
            ps.setBoolean(6, seat.getIsAvailable());
            ps.setBoolean(7, seat.getIsReserved());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in adding seat: " + e.getMessage());
        }
    }
    
    // Method to get seat by its id
    public Seat getSeatById(int seatId) {
        Seat seat = null;
        String query = "SELECT * FROM seats WHERE seatId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, seatId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    seat = populateSeat(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching seat by its ID: " + e.getMessage());
        }

        return seat;
    }
    
    // Method to get seatId by its name and showtimeId
    public int getSeatIdByName(String seatName, int showtimeId) {
        int seatId = -1; // Default value if not found
        String query = "SELECT seatId FROM seats WHERE showtimeId = ? AND seatNumber = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, showtimeId);
            ps.setString(2, seatName);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    seatId = rs.getInt("seatId"); // Fetch seatId directly from result set
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error fetching seatId by name and showtimeId: " + e.getMessage());
        }

        return seatId; // Return the seatId or -1 if not found
    }
    
    // Method to update seat details
    public void updateSeat(Seat seat) {
        String query = "UPDATE seats SET hallId = ?, showtimeId = ?, seatNumber = ?, seatType = ?, price = ?, isAvailable = ?, isReserved = ? WHERE seatId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, seat.getHallId());
            ps.setInt(2, seat.getShowtimeId());
            ps.setString(3, seat.getSeatNumber());
            ps.setString(4, seat.getSeatType().toString());
            ps.setDouble(5, seat.getPrice());
            ps.setBoolean(6, seat.getIsAvailable());
            ps.setBoolean(7, seat.getIsReserved());
            ps.setInt(8, seat.getSeatId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in updating seat: " + e.getMessage());
        }
    }
    
    // Method to update seat availability
    public void updatSeatAvailability(int seatId, boolean availability) {
        String query = "UPDATE seats SET isAvailable = ? WHERE seatId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setBoolean(1, availability);
            ps.setInt(2, seatId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in updating seat availability: " + e.getMessage());
        }
    }
    
    // Method to update seat reserved
    public void updateSeatReserved(int seatId, boolean isReserved) {
        String query = "UPDATE seats SET isReserved = ?, isAvailable = ? WHERE seatId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setBoolean(1, isReserved);
            ps.setBoolean(2, !isReserved);
            ps.setInt(3, seatId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in updating seat reserved: " + e.getMessage());
        }
    }
    
    // Method to delete a seat
    public void deleteSeat(int seatId) {
        String query = "DELETE FROM seats WHERE seatId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, seatId);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in deleting seat: " + e.getMessage());
        }
    }
    
    // Method to get all seats
    public List<Seat> getAllSeats() {
        List<Seat> seats = new ArrayList<>();
        String query = "SELECT * FROM seats";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Seat seat = populateSeat(rs);
                seats.add(seat);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching seats: " + e.getMessage());
        }
        
        return seats;
    }
    
    // Method to get all seats of a hall
    public List<Seat> getAllSeatsByHall(int hallId) {
        List<Seat> seats = new ArrayList<>();
        String query = "SELECT * FROM seats WHERE hallId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, hallId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Seat seat = populateSeat(rs);
                    seats.add(seat);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching seats of hall: " + e.getMessage());
        }
        
        return seats;
    }
    
    // Method to get all seats of a showtime
    public List<Seat> getAllSeatsByShowtime(int showtimeId) {
        List<Seat> seats = new ArrayList<>();
        String query = "SELECT * FROM seats WHERE showtimeId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, showtimeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Seat seat = populateSeat(rs);
                    seats.add(seat);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching seats of showtime: " + e.getMessage());
        }
        
        return seats;
    }
    
    // Method to get all available seats of a showtime
    public List<Seat> getAvailableSeatsByShowtime(int showtimeId) {
        List<Seat> seats = new ArrayList<>();
        String query = "SELECT * FROM seats WHERE showtimeId = ? AND isAvailable = TRUE";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, showtimeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Seat seat = populateSeat(rs);
                    seats.add(seat);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching available seats of showtime: " + e.getMessage());
        }
        
        return seats;
    }
    
    // Method to get all reserved seats of a showtime
    public List<Seat> getReservedSeatsByShowtime(int showtimeId) {
        List<Seat> seats = new ArrayList<>();
        String query = "SELECT * FROM seats WHERE showtimeId = ? AND isReserved = TRUE";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, showtimeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Seat seat = populateSeat(rs);
                    seats.add(seat);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching reserved seats of showtime: " + e.getMessage());
        }
        
        return seats;
    }
    
    // Method to get all reserved seats of a showtime
    public List<String> getReservedSeatNumbersByShowtime(int showtimeId) {
        List<String> seatNumbers = new ArrayList<>();
        String query = "SELECT seatNumber FROM seats WHERE showtimeId = ? AND isReserved = TRUE";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, showtimeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    seatNumbers.add(rs.getString("seatNumber"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching reserved seat numbers: " + e.getMessage());
        }

        return seatNumbers;
    }

    public void releaseExpiredSeats() {
        String query = "UPDATE seats SET isReserved = FALSE, reservationExpiry = NULL WHERE isReserved = TRUE AND reservationExpiry < NOW()";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error releasing expired seats: " + e.getMessage());
        }
    }
    
    //to release seats when expired
    public void releaseSpecificSeats(String[] seatNumbers, int showtimeId) {
        String query = "UPDATE seats SET isReserved = FALSE WHERE showtimeId = ? AND seatNumber = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            for (String seat : seatNumbers) {
                ps.setInt(1, showtimeId);
                ps.setString(2, seat);
                ps.addBatch();
            }
            ps.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Utility method to populate Seat object from ResultSet
    private Seat populateSeat(ResultSet rs) throws SQLException {
        Seat seat = new Seat();
        seat.setSeatId(rs.getInt("seatId"));
        seat.setHallId(rs.getInt("hallId"));
        seat.setShowtimeId(rs.getInt("showtimeId"));
        seat.setSeatNumber(rs.getString("seatNumber"));
        seat.setSeatType(SeatType.fromString(rs.getString("seatType")));
        seat.setPrice(rs.getDouble("price"));
        seat.setIsAvailable(rs.getBoolean("isAvailable"));
        seat.setIsReserved(rs.getBoolean("isReserved"));
        
        return seat;
    }
}
