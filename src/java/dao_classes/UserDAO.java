package dao_classes;

import utility_classes.DBConnection;
import model_classes.User;
import model_classes.User.AccountStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    
    // Method to register a user
    public void registerUser(User user) {
        String query = "INSERT INTO users (firstName, lastName, email, password, contactNumber, accountStatus) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getContactNumber());
            ps.setString(6, user.getAccountStatus().name());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in registering user: " + e.getMessage());
        }
    }
    
    // Method to log in user
    public User loginUser(String email, String password) {
        User user = null;
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = populateUser(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in logging in user: " + e.getMessage());
        }

        return user;
    }
    
    // Method to check if email is already registered
    public boolean isEmailRegistered(String email) {
        boolean isRegistered = false;
        String query = "SELECT COUNT(*) FROM users WHERE email = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    isRegistered = count > 0;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in checking whether email is registered: " + e.getMessage());
        }

        return isRegistered;
    }
    
    // Method to check if contact number is already registered
    public boolean isContactNumberRegistered(String contactNumber) {
        boolean isRegistered = false;
        String query = "SELECT COUNT(*) FROM users WHERE contactNumber = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, contactNumber);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    isRegistered = count > 0;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in checking whether contact number is registered: " + e.getMessage());
        }

        return isRegistered;
    }
    
    // Method to check if account is suspended
    public boolean isAccountSuspended(int userId) {
        boolean isSuspended = false;
        String query = "SELECT COUNT(*) FROM users WHERE accountStatus = ? AND userId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, AccountStatus.SUSPENDED.name());
            ps.setInt(2, userId);
            try (ResultSet rs = ps.executeQuery()) {
                int count = rs.getInt(1);
                isSuspended = count > 0;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in checking whether account is suspended: " + e.getMessage());
        }
        
        return isSuspended;
    }
    
    // Method to get user by their id
    public User getUserById(int userId) {
        User user = null;
        String query = "SELECT * FROM users WHERE userId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = populateUser(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching user by their ID: " + e.getMessage());
        }

        return user;
    }
    
    // Method to get user by their email
    public User getUserByEmail(String email) {
        User user = null;
        String query = "SELECT * FROM users WHERE email = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = populateUser(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching user by their email: " + e.getMessage());
        }

        return user;
    }
    
    // Method to reset user's password
    public void resetPassword(String email, String password) {
        String query = "UPDATE users SET password = ? WHERE email = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, email);
            ps.setString(2, password);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in resetting user's password: " + e.getMessage());
        }
    }
    
    // Method to update details of user
    public void updateUser(User user) {
        String query = "UPDATE users SET firstName = ?, lastName = ?, email = ?, password = ?, contactNumber = ?, accountStatus = ? WHERE userId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getContactNumber());
            ps.setString(6, user.getAccountStatus().name());
            ps.setInt(7, user.getUserId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in updating user: " + e.getMessage());
        }
    }
    
    // Method to update user's contact number
    public void updateContactNumber(String contactNumber, int userId) {
        String query = "UPDATE users SET contactNumber = ? WHERE userId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, contactNumber);
            ps.setInt(2, userId);
            ps.executeUpdate();
                    
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in updating user's contact number: " + e.getMessage());
        }
    }
    
    // Method to delete a user
    public void deleteUser(int userId) {
        String query = "DELETE FROM users WHERE userId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, userId);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in deleting user: " + e.getMessage());
        }
    }
    
    // Method to get all users
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                User user = populateUser(rs);
                users.add(user);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching users: " + e.getMessage());
        }
        
        return users;
    }
    
    // Method to get the total number of users
    public int getTotalUsers() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM users";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                count = rs.getInt(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching total users count: " + e.getMessage());
        }
        
        return count;
    }
    
    // Method to get the total number of active users
    public int getTotalActiveUsers() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM users WHERE accountStatus = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, AccountStatus.ACTIVE.name());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching total active users count: " + e.getMessage());
        }
        
        return count;
    }
    
    // Utility method to populate User object from ResultSet
    private User populateUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("userId"));
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastName"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setContactNumber(rs.getString("contactNumber"));
        user.setAccountStatus(AccountStatus.valueOf(rs.getString("accountStatus")));
        user.setRegistrationDate(rs.getDate("registrationDate"));
        user.setUpdatedAt(rs.getDate("updatedAt"));
        
        return user;
    }
}
