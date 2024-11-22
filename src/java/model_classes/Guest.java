package model_classes;

import java.sql.Date;

public class Guest {
    private int guestId;
    private String name;
    private String email;
    private String contactNumber;
    private Date createdAt;
    
    // Constructors
    public Guest() {}
    
    public Guest(String name, String email, String contactNumber, Date createdAt) {
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.createdAt = createdAt;
    }
    
    // Getters and Setters
    public int getGuestId() { return guestId; }
    public void setGuestId(int guestId) { this.guestId = guestId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
