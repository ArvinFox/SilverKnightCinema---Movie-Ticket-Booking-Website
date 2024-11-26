package model_classes;

import java.sql.Date;

public class User {
    
    public enum AccountStatus {
        ACTIVE("active"),
        INACTIVE("inactive"),
        SUSPENDED("suspended");

        private final String dbValue;

        AccountStatus(String dbValue) {
            this.dbValue = dbValue;
        }

        @Override
        public String toString() {
            return dbValue;
        }

        public static AccountStatus fromString(String dbValue) {
            for (AccountStatus status : AccountStatus.values()) {
                if (status.dbValue.equalsIgnoreCase(dbValue)) {
                    return status;
                }
            }
            throw new IllegalArgumentException("Unknown account status: " + dbValue);
        }
    }
    
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String contactNumber;
    private AccountStatus accountStatus;
    private Date registrationDate;
    private Date updatedAt;
    
    // Cosntructors
    public User() {}
    
    public User(String firstName, String lastName, String email, String password, String contactNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.contactNumber = contactNumber;
    } 
    
    public User(String firstName, String lastName, String email, String password, String contactNumber, AccountStatus accountStatus, Date registrationDate, Date updatedAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.contactNumber = contactNumber;
        this.accountStatus = accountStatus;
        this.registrationDate = registrationDate;
        this.updatedAt = updatedAt;
    } 

    // Getters and Setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    
    public AccountStatus getAccountStatus() { return accountStatus; }
    public void setAccountStatus(AccountStatus accountStatus) { this.accountStatus = accountStatus; }
    
    public Date getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(Date registrationDate) { this.registrationDate = registrationDate; }
    
    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
}
