package chat.chickentalk.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class Mailer {

    @Value("#{ systemEnvironment['CHICKEN_EMAIL_ADDRESS'] }")
    private String username;

    @Value("#{ systemEnvironment['CHICKEN_EMAIL_PASSWORD'] }")
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean sendMail(String recipientEmail, String subject, String body) {
        boolean result = false;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            InternetAddress fromAddr = new InternetAddress(username);
            InternetAddress toAddr = new InternetAddress(recipientEmail);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(fromAddr);
            message.addRecipient(Message.RecipientType.TO, toAddr);
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            result = true;
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

        return result;
    }
}
