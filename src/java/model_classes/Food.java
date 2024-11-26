package model_classes;

import java.sql.Date;

public class Food {
    
    public enum ItemType {
        BEVERAGE("Beverage"),
        POPCORN("Popcorn"),
        JUICE("Juice"),
        SNACK("Snack");

        private final String dbValue;

        ItemType(String dbValue) {
            this.dbValue = dbValue;
        }

        @Override
        public String toString() {
            return dbValue;
        }

        public static ItemType fromString(String dbValue) {
            for (ItemType type : ItemType.values()) {
                if (type.dbValue.equalsIgnoreCase(dbValue)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Unknown item type: " + dbValue);
        }
    }
    
    private int itemId;
    private String itemName;
    private ItemType itemType;
    private double price;
    private int stock;
    private Date createdAt;
    private Date updatedAt;
    
    // Constructors
    public Food() {}
    
    public Food(String itemName, ItemType itemType, double price, int stock, Date createdAt, Date updatedAt) {
        this.itemName = itemName;
        this.itemType = itemType;
        this.price = price;
        this.stock = stock;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    // Getters and Setters
    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }
    
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    
    public ItemType getItemType() { return itemType; }
    public void setItemType(ItemType itemType) { this.itemType = itemType; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    
    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
}
