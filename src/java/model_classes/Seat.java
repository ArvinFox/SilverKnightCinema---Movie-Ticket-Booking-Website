package model_classes;

public class Seat {
    
    public enum SeatType {
        VIP("VIP"),
        STANDARD("Standard");

        private final String dbValue;

        SeatType(String dbValue) {
            this.dbValue = dbValue;
        }

        @Override
        public String toString() {
            return dbValue;
        }

        public static SeatType fromString(String dbValue) {
            for (SeatType type : SeatType.values()) {
                if (type.dbValue.equalsIgnoreCase(dbValue)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Unknown seat type: " + dbValue);
        }
    }
    
    private int seatId;
    private int hallId;
    private int showtimeId;
    private String seatNumber;
    private SeatType seatType;
    private double price;
    private boolean isAvailable;
    private boolean isReserved;
    
    // Constructors
    public Seat() {}
    
    public Seat(int hallId, int showtimeId, String seatNumber, SeatType seatType, double price, boolean isAvailable, boolean isReserved) {
        this.hallId = hallId;
        this.showtimeId = showtimeId;
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
    
    public int getShowtimeId() { return showtimeId; }
    public void setShowtimeId(int showtimeId) { this.showtimeId = showtimeId; }

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
