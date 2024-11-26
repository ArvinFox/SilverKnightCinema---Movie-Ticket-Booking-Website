package model_classes;

import java.sql.Date;
import com.google.gson.Gson;

public class Booking {
    private int bookingId;
    private Integer userId;
    private Integer guestId;
    private int showtimeId;
    private Integer promotionId;
    private String[] bookedSeats;
    private Date bookingDate;
    private Date expiryDate;
    private double totalPrice;
    private boolean isPaid;

    // Constructors
    public Booking() {}
    
    public Booking(Integer userId, Integer guestId, int showtimeId, Integer promotionId, String[] bookedSeats, Date bookingDate, Date expiryDate, double totalPrice, boolean isPaid) {
        this.userId = userId;
        this.guestId = guestId;
        this.showtimeId = showtimeId;
        this.promotionId = promotionId;
        this.bookedSeats = bookedSeats;
        this.bookingDate = bookingDate;
        this.expiryDate = expiryDate;
        this.totalPrice = totalPrice;
        this.isPaid = isPaid;
    }

    // Getters and Setters
    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    
    public Integer getGuestId() { return guestId; }
    public void setGuestId(Integer guestId) { this.guestId = guestId; }

    public int getShowtimeId() { return showtimeId; }
    public void setShowtimeId(int showtimeId) { this.showtimeId = showtimeId; }
    
    public Integer getPromotionId() { return promotionId; }
    public void setPromotionId(Integer promotionId) { this.promotionId = promotionId; }
    
    public String[] getBookedSeats() { return bookedSeats; }
    public void setBookedSeats(String[] bookedSeats) { this.bookedSeats = bookedSeats; }

    // Method to convert the bookedSeats array to a JSON string
    public String getBookedSeatsAsJson() {
        Gson gson = new Gson();
        return gson.toJson(bookedSeats);  // Converts the array to a JSON string
    }
    // Method to set bookedSeats from a JSON string
    public void setBookedSeatsFromJson(String json) {
        Gson gson = new Gson();
        this.bookedSeats = gson.fromJson(json, String[].class);  // Converts the JSON string back to an array
    }

    public Date getBookingDate() { return bookingDate; }
    public void setBookingDate(Date bookingDate) { this.bookingDate = bookingDate; }
    
    public Date getExpiryDate() { return expiryDate; }
    public void setExpiryDate(Date expiryDate) { this.expiryDate = expiryDate; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public boolean getIsPaid() { return isPaid; }
    public void setIsPaid(boolean isPaid) { this.isPaid = isPaid; }
}
