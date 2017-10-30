package persistent;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EmailUtility {

    public static void sendConfirmation(String userEmail, String host, String senderEmail, String senderPassword, String port, int code) throws Exception{

        /*
        Email contents
         */
        String subject = "Account Confirmation - Welcome";
        String messageText = "Hello user, your 4 digit security code is " + code + ". Please input this code to verify your account";

        /*
        Properties to create email request
         */
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");

        /*
        Authenticator to validate credentials
         */
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        };

        /*
        Session to create make a message
         */
        Session session = Session.getInstance(props, authenticator);

        /*
        Populating message parameters
         */
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
        message.setSentDate(new Date());
        message.setSubject(subject);
        message.setText(messageText);

        /*
        Sending message
         */
        Transport.send(message);
    }
}
