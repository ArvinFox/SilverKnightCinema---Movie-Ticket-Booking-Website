package model_classes;

public class Seat {
    
    public enum SeatType {
        VIP,
        Standard
    }
    
    private int seatId;
    private int hallId;
    private String seatNumber;
    private SeatType seatType;
    private double price;
    private boolean isAvailable;
    private boolean isReserved;
    
    // Constructors
    public Seat() {}
    
    public Seat(int hallId, String seatNumber, SeatType seatType, double price, boolean isAvailable, boolean isReserved) {
        this.hallId = hallId;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.price = price;
        this.isAvailable = isAvailable;
        this.isReserved = isReserved;
    }

    // Getters and Setters
    public int getSeatId() { return seatId; }
    public void setSeatId(int seatId) { this.seatId = seatId; }
    
    public int getHallId() { return hallId; }
    public void setHallId(int hallId) { this.hallId = hallId; }

    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }

    public SeatType getSeatType() { return seatType; }
    public void setSeatType(SeatType seatType) { this.seatType = seatType; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    public boolean getIsAvailable() { return isAvailable; }
    public void setIsAvailable(boolean isAvailable) { this.isAvailable = isAvailable; }
    
    public boolean getIsReserved() { return isReserved; }
    public void setIsReserved(boolean isReserved) { this.isReserved = isReserved; }
}
