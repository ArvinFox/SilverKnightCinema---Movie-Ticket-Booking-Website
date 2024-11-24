package dao_classes;

import utility_classes.DBConnection;
import model_classes.Payment;
import model_classes.Payment.PaymentMethod;
import model_classes.Payment.PaymentStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDAO {
    
    // Method to add a payment
    public void addPayment(Payment payment) {
        String query = "INSERT INTO payments (bookingId, transactionId, paymentMethod, paymentStatus, amount) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, payment.getBookingId());
            ps.setString(2, payment.getTransactionId());
            ps.setString(3, payment.getPaymentMethod().name());
            ps.setString(4, payment.getPaymentStatus().name());
            ps.setDouble(5, payment.getAmount());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in adding payment: " + e.getMessage());
        }
    }
    
    // Method to get payment by its id
    public Payment getPaymentById(int paymentId) {
        Payment payment = null;
        String query = "SELECT * FROM payments WHERE paymentId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, paymentId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                   payment = populatePayment(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching payment by its ID: " + e.getMessage());
        }

        return payment;
    }
    
    // Method to update payment status
    public void updatePaymentStatus(int paymentId, PaymentStatus paymentStatus) {
        String query = "UPDATE payments SET paymentStatus = ? WHERE paymentId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, paymentStatus.name());
            ps.setInt(2, paymentId);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in updating payment status: " + e.getMessage());
        }
    }
    
    // Method to delete a payment
    public void deletePayment(int paymentId) {
        String query = "DELETE FROM payments WHERE paymentId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, paymentId);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in deleting payment: " + e.getMessage());
        }
    }
    
    // Utility method to populate Payment object from ResultSet
    private Payment populatePayment(ResultSet rs) throws SQLException {
        Payment payment = new Payment();
        payment.setPaymentId(rs.getInt("paymentId"));
        payment.setBookingId(rs.getInt("bookingId"));
        payment.setTransactionId(rs.getString("transactionId"));
        payment.setPaymentMethod(PaymentMethod.valueOf(rs.getString("paymentMethod")));
        payment.setPaymentStatus(PaymentStatus.valueOf(rs.getString("paymentStatus")));
        payment.setAmount(rs.getDouble("amount"));
        payment.setPaymentDate(rs.getDate("paymentDate"));
        
        return payment;
    }
}
