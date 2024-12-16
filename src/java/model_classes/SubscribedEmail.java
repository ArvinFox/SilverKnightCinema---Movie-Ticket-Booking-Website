package model_classes;

public class SubscribedEmail 
{
    private int emailId;
    private String email;

    public SubscribedEmail() {}
    
    public SubscribedEmail(int emailId, String email) 
    {
        this.emailId = emailId;
        this.email = email;
    }

    public int getEmailId() { return emailId; }

    public void setEmailId(int emailId) {this.emailId = emailId;}

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }
    
}
