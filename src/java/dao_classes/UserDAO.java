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
import org.mindrot.jbcrypt.BCrypt;

public class UserDAO {
    
    // Method to register a user
    public void registerUser(User user) {
        String query = "INSERT INTO users (firstName, lastName, email, password, contactNumber) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getContactNumber());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in registering user: " + e.getMessage());
        }
    }
    
    // Method to log in user
    public User loginUser(String email, String password) {
        User user = null;
        String query = "SELECT * FROM users WHERE email = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String hashedPassword = rs.getString("password");
                    if(BCrypt.checkpw(password, hashedPassword))
                    {
                        user = populateUser(rs);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in logging in user: " + e.getMessage());
        }

        return user;
    }
    
    // Method to get searched users
    public List<User> getSearchedUsers(String name, String email, String contactNumber, String status) {
        List<User> users = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM users WHERE 1=1");
        
        List<Object> parameters = new ArrayList<>();
        
        if (name != null && !name.trim().isEmpty()) {
            query.append(" AND (firstName LIKE ? OR lastName LIKE ? OR CONCAT(firstName, ' ', lastName) LIKE ?)");
            parameters.add("%" + name.trim() + "%");
            parameters.add("%" + name.trim() + "%");
            parameters.add("%" + name.trim() + "%");
        }
        
        if (email != null && !email.trim().isEmpty()) {
            query.append(" AND email LIKE ?");
            parameters.add("%" + email.trim() + "%");
        }
        
        if (contactNumber != null && !contactNumber.trim().isEmpty()) {
            query.append(" AND contactNumber LIKE ?");
            parameters.add("%" + contactNumber.trim() + "%");
        }
        
        if (status != null && !status.trim().isEmpty() && !status.equals("any")) {
            query.append(" AND accountStatus = ?");
            parameters.add(status);
        }
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query.toString())) {
            
            for (int i = 0; i < parameters.size(); i++) {
                ps.setObject(i + 1, parameters.get(i));
            }
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User user = populateUser(rs);
                    users.add(user);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching searched users: " + e.getMessage());
        }
        
        return users;
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
                if (rs.next()) {
                    int count = rs.getInt(1);
                    isSuspended = count > 0;
                }
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
            
            ps.setString(1, password);
            ps.setString(2, email);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in resetting user's password: " + e.getMessage());
        }
    }
    
    // Method to store reset token of user
    public void storeResetToken(int userId, String token, long expiryTime) {
        String query = "INSERT INTO passwordResetTokens (userId, token, expiryTime) VALUES (?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, userId);
            ps.setString(2, token);
            ps.setLong(3, expiryTime);
            ps.executeUpdate();
            
        } catch(SQLException e) {
            e.printStackTrace();
            System.err.println("Error in storing user's reset token: " + e.getMessage());
        }
    }
    
    // Method to get user by reset token
    public User getUserByResetToken(String token) {
        User user = null;
        String query = "SELECT u.* FROM users u INNER JOIN passwordResetTokens t ON u.userId = t.userId WHERE t.token = ? AND t.isUsed = 0";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, token);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = populateUser(rs);
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching user by reset token: " + e.getMessage());
        }
        
        return user;
    }
    
    // Method to check if reset token is expired
    public boolean isTokenExpired(String token, long currentTime) {
        boolean isExpired = true;
        String query = "SELECT expiryTime FROM passwordResetTokens WHERE token = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, token);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    long expiryTime = rs.getLong("expiryTime");
                    isExpired = currentTime > expiryTime;
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.err.println("Error in checking if reset token is expired: " + e.getMessage());
        }
        
        return isExpired;
    }
    
    // Method to invalidate reset token
    public void invalidateResetToken(String token) {
        String query = "UPDATE passwordResetTokens SET isUsed = 1 WHERE token = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, token);
            ps.executeUpdate();
            
        } catch(SQLException e) {
            e.printStackTrace();
            System.err.println("Error in invalidating reset token: " + e.getMessage());
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
            ps.setString(6, user.getAccountStatus().toString());
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
        user.setAccountStatus(AccountStatus.fromString(rs.getString("accountStatus")));
        user.setRegistrationDate(rs.getDate("registrationDate"));
        user.setUpdatedAt(rs.getDate("updatedAt"));
        
        return user;
    }
}
