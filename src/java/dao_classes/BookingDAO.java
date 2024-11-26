package dao_classes;

import utility_classes.DBConnection;
import model_classes.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    
    // Method to create a booking
    public void createBooking(Booking booking) {
        String query = "INSERT INTO bookings (userId, guestId, showtimeId, promotionId, bookedSeats, expiryDate, totalPrice, isPaid) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            if (booking.getUserId() != null && booking.getUserId() != 0) {
                ps.setInt(1, booking.getUserId());
                ps.setNull(2, java.sql.Types.INTEGER);
            } else if (booking.getGuestId() != null && booking.getGuestId() != 0) {
                ps.setNull(1, java.sql.Types.INTEGER);
                ps.setInt(2, booking.getGuestId());
            }
            
            ps.setInt(3, booking.getShowtimeId());
            
            if (booking.getPromotionId() != null && booking.getPromotionId() != 0) {
                ps.setInt(4, booking.getPromotionId());
            } else {
                ps.setNull(4, java.sql.Types.INTEGER);
            }
            
            ps.setString(5, booking.getBookedSeatsAsJson());
            ps.setDate(6, booking.getExpiryDate());
            ps.setDouble(7, booking.getTotalPrice());
            ps.setBoolean(8, booking.getIsPaid());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in creating booking: " + e.getMessage());
        }
    }
    
    // Method to get booking by its id
    public Booking getBookingById(int bookingId) {
        Booking booking = null;
        String query = "SELECT * FROM bookings WHERE bookingId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, bookingId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                   booking = populateBooking(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching booking by its ID: " + e.getMessage());
        }

        return booking;
    }
    
    // Method to update booking details
   public void updateBooking(int showtimeId, int bookingId) {
       String query = "UPDATE bookings SET showtimeId = ? WHERE bookingId = ?";
       
       try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
           
           ps.setInt(1, showtimeId);
           ps.setInt(2, bookingId);
           ps.executeUpdate();
           
       } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in updating booking details: " + e.getMessage());
        }
   }
    
    // Method to delete a booking
    public void deleteBooking(int bookingId) {
        String query = "DELETE FROM bookings WHERE bookingId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, bookingId);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in deleting booking: " + e.getMessage());
        }
    }
    
    // Method to get all bookings of user
    public List<Booking> getAllBookingsOfUser(int userId) {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM bookings WHERE userId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Booking booking = populateBooking(rs);
                    bookings.add(booking);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching bookings of user: " + e.getMessage());
        }
        
        return bookings;
    }
    
    // Method to get the total number of bookings
    public int getTotalBookings() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM bookings";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                count = rs.getInt(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching total bookings count: " + e.getMessage());
        }
        
        return count;
    }
    
    // Utility method to populate Booking object from ResultSet
    private Booking populateBooking(ResultSet rs) throws SQLException {
        Booking booking = new Booking();
        booking.setBookingId(rs.getInt("bookingId"));
        booking.setUserId(rs.getInt("userId"));
        booking.setGuestId(rs.getInt("guestId"));
        booking.setShowtimeId(rs.getInt("showtimeId"));
        booking.setPromotionId(rs.getInt("promotionId"));
        
        String bookedSeatsJson = rs.getString("bookedSeats");
        if (bookedSeatsJson != null) {
            booking.setBookedSeatsFromJson(bookedSeatsJson);
        }
        
        booking.setBookingDate(rs.getDate("bookingDate"));
        booking.setExpiryDate(rs.getDate("expiryDate"));
        booking.setTotalPrice(rs.getDouble("totalPrice"));
        booking.setIsPaid(rs.getBoolean("isPaid"));
        
        return booking;
    }
}
