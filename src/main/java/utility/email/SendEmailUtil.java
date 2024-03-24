package utility.email;

import utility.Constants;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;

public class SendEmailUtil {
    String to;
    String cc;
    String bcc;
    String from;

    String username;
    String password;
    String host;
    String[] attachmentFilenames;
    int port;

    public void mailRunner(String args[]) throws Exception {






        from = Constants.SENDMAIL_FROM;
        username = Constants.SENDMAIL_FROM;//change accordingly
        password = Constants.SENDMAIL_PASSWORD;//change accordingly
        // Assuming you are sending email through relay.jangosmtp.net
        host = Constants.SMTP_HOST;
        port = Constants.SMTP_PORT;
        attachmentFilenames = new String[]{"path_to_file1", "path_to_file2", "path_to_file3"}; // Add paths to your files

        sendMail();
    }

    public void sendMail() throws Exception {



        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "mail.hbc.com");
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
            if (!cc.isEmpty()) {
                message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
            }

            if (!bcc.isEmpty()) {
                message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc));
            }
            // Set Subject: header field
            message.setSubject("subject");
            // Send the actual HTML message, as big as you like
            //message.setContent(mailbody,"text/html");
            // Create Multipart object to hold the message text and the attachments

            MimeBodyPart htmlBodyPart = new MimeBodyPart();
            htmlBodyPart.setContent("", "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(htmlBodyPart);

            // Add attachments

            for (String filename : attachmentFilenames) {
                MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(filename);
                attachmentBodyPart.setDataHandler(new DataHandler(source));
                attachmentBodyPart.setFileName(filename);
                multipart.addBodyPart(attachmentBodyPart);
                // Send message
                Transport.send(message);
                System.out.println("Sent message successfully....");
            }

            }
        catch(Exception e){
            new RuntimeException("Send mail failed due to exception:"+e.getMessage());
        }
    }
}



