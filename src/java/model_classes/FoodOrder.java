package model_classes;

import java.sql.Date;

public class FoodOrder {
    private int orderId;
    private int bookingId;
    private int itemId;
    private int quantity;
    private double pricePerItem;
    private double totalPrice;
    private Date createdAt;
    
    // Constructors
    public FoodOrder() {}
    
    public FoodOrder(int bookingId, int itemId, int quantity, double pricePerItem, double totalPrice, Date createdAt) {
        this.bookingId = bookingId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.pricePerItem = pricePerItem;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
    }
    
    // Geters and Setters
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    
    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }
    
    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }
    
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    
    public double getPricePerItem() { return pricePerItem; }
    public void setPricePerItem(double pricePerItem) { this.pricePerItem = pricePerItem; }
    
    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
