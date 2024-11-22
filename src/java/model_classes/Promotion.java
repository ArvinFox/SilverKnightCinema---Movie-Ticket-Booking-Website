package model_classes;

import java.sql.Date;

public class Promotion {
    private int promotionId;
    private String name;
    private String description;
    private String code;
    private double discount;
    private Date startDate;
    private Date endDate;
    private boolean isActive;
    
    // Constructors
    public Promotion() {}
    
    public Promotion(String name, String description, String code, double discount, Date startDate, Date endDate, boolean isActive) {
        this.name = name;
        this.description = description;
        this.code = code;
        this.discount = discount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
    }
    
    // Getters and Setters
    public int getPromotionId() { return promotionId; }
    public void setPromotionId(int promotionId) { this.promotionId = promotionId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    
    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }
    
    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    
    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }
    
    public boolean getIsActive() { return isActive; }
    public void setIsActive(boolean isActive) { this.isActive = isActive; }
}
