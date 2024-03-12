package utility;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import utility.*;

public class EmailUtil {
    String to;
    String from;

    String username;
    String password;
    String host;
    int port;
    public void main(String args[]) {
    }

    public void sendMail(String subject, String mailbody) {
        // Recipient's email ID needs to be mentioned.
        to = Constants.MAIL_TO;
        // Sender's email ID needs to be mentioned
        from = Constants.SENDMAIL_FROM;
        username = Constants.SENDMAIL_FROM;//change accordingly
       password = Constants.SENDMAIL_PASSWORD;//change accordingly
        // Assuming you are sending email through relay.jangosmtp.net
        host = Constants.SMTP_HOST;
        port = Constants.SMTP_PORT;
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", 25);
        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));
            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            // Set Subject: header field
            message.setSubject(subject);
            // Send the actual HTML message, as big as you like
            message.setContent(mailbody,"text/html");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}



