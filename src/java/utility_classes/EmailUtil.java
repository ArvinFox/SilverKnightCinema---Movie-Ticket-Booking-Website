package utility_classes;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class EmailUtil {
    private static final String EMAIL = "EMAIL";
    private static final String PASSWORD = "PASSWORD";
    
    public static void sendEmail(String recipient, String subject, String body) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        Session session = Session.getInstance(properties, 
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(EMAIL, PASSWORD);
                    }
                });
        
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(body);
            
            Transport.send(message);
            System.out.println("Email sent successfully.");
            
        } catch (MessagingException e) {
            System.err.println("Failed to send email.");
            e.printStackTrace();
        }
    }
}
