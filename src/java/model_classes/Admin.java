package model_classes;

import java.sql.Date;

public class Admin {
    private int adminId;
    private String username;
    private String password;
    private String contactNumber;
    private Date createdAt;
    
    // Constructors
    public Admin() {}
    
    public Admin(String username, String password, String contactNumber, Date createdAt) {
        this.username = username;
        this.password = password;
        this.contactNumber = contactNumber;
        this.createdAt = createdAt;
    }
    
    // Getters and Setters
    public int getAdminId() { return adminId; }
    public void setAdminId(int adminId) { this.adminId = adminId; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
