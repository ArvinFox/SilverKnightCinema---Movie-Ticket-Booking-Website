package model_classes;

import java.sql.Date;

public class Food {
    
    public enum ItemType {
        Beverage,
        Popcorn,
        Juice,
        Snack
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
    
    public Food(String itemName, ItemType itemType, double price, int stock) {
        this.itemName = itemName;
        this.itemType = itemType;
        this.price = price;
        this.stock = stock;
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
