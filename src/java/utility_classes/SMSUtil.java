package utility_classes;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMSUtil {
    private static final String ACCOUNT_SID = "ACCOUNT_SID";
    private static final String AUTH_TOKEN = "AUTH_TOKEN";
    private static final String FROM_PHONE = "FROM_PHONE";
    
    public static void sendSMS(String toPhone, String messageContent) {
        try {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        
            Message message = Message.creator(
                    new PhoneNumber(toPhone), 
                    new PhoneNumber(FROM_PHONE), 
                    messageContent)
                .create();

            System.out.println("SMS sent successfully: " + message.getSid());
            System.out.println("SMS sent to: " + toPhone);
            
        } catch (ApiException e) {
            System.err.println("Failed to send SMS: " + e.getMessage());
            System.err.println("Error Code: " + e.getCode());
            System.err.println("Error Info: " + e.getMoreInfo());
            
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while sending SMS: " + e.getMessage());
        }
    }
}
