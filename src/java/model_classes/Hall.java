package model_classes;

import java.sql.Date;

public class Hall {
    
    public enum Type {
        IMAX("IMAX"),
        _2D("2D"),
        _3D("3D"),
        VIP("VIP");

        private final String dbValue;

        Type(String dbValue) {
            this.dbValue = dbValue;
        }
        
        public String getDbValue() {
            return dbValue;
        }

        @Override
        public String toString() {
            return dbValue;
        }

        public static Type fromString(String dbValue) {
            for (Type type : Type.values()) {
                if (type.dbValue.equalsIgnoreCase(dbValue) || type.name().equalsIgnoreCase(dbValue)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Unknown hall type: " + dbValue);
        }
    }
    
    private int hallId;
    private String name;
    private Type type;
    private int capacity;
    private String location;
    private String hallUrl;
    private Date createdAt;
    private Date updatedAt;
    
    // Constructors
    public Hall() {}
    
    public Hall(String name, Type type, int capacity, String location, String hallUrl) {
        this.name = name;
        this.type = type;
        this.capacity = capacity;
        this.location = location;
        this.hallUrl = hallUrl;
    }
    
    public Hall(String name, Type type, int capacity, String location, String hallUrl, Date createdAt, Date updatedAt) {
        this.name = name;
        this.type = type;
        this.capacity = capacity;
        this.location = location;
        this.hallUrl = hallUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    // Getters and Setters
    public int getHallId() { return hallId; }
    public void setHallId(int hallId) { this.hallId = hallId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }
    
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public String getHallUrl() { return hallUrl; }
    public void setHallUrl(String hallUrl) { this.hallUrl = hallUrl; }
    
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    
    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
}
