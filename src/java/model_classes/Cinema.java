package model_classes;

import java.util.Date;

public class Cinema {
    private int cinemaId;
    private String name;
    private String location;
    private Date createdAt;
    
    // Constructors
    public Cinema() {}
    
    public Cinema(String name, String location) {
        this.name = name;
        this.location = location;
    }
    
    // Getters and Setters
    public int getCinemaId() { return cinemaId; }
    public void setCinemaId(int cinemaId) { this.cinemaId = cinemaId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(java.sql.Date createdAt) { this.createdAt = createdAt; }
}
