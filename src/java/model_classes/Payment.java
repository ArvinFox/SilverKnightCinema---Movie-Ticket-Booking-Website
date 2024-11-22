package model_classes;

import java.sql.Date;

public class Payment {
    
    public enum PaymentMethod {
        CREDIT_CARD,
        PAYPAL,
        CASH
    }
    
    public enum PaymentStatus {
        COMPLETED,
        FAILED,
        PENDING,
        REFUNDED
    }
    
    private int paymentId;
    private int bookingId;
    private String transactionId;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private double amount;
    private Date paymentDate;
    
    // Constructors
    public Payment() {}
    
    public Payment(int bookingId, String transactionId, PaymentMethod paymentMethod, PaymentStatus paymentStatus, double amount, Date paymentDate) {
        this.bookingId = bookingId;
        this.transactionId = transactionId;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }
    
    // Getters and Setters
    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }

    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }
    
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }

    public PaymentStatus getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(PaymentStatus paymentStatus) { this.paymentStatus = paymentStatus; }
    
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public Date getPaymentDate() { return paymentDate; }
    public void setPaymentDate(Date paymentDate) { this.paymentDate = paymentDate; }
}
