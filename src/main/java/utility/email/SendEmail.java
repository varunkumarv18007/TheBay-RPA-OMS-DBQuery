package utility.email;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SendEmail {
    private String from;
    private String to;
    private String subject;
    private String body;
    private String cc; //optional
    private String bcc; //optional
    private String[] attachments; //optional

    // Getters and setters for email attributes
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String[] getAttachments() {
        return attachments;
    }

    public void setAttachments(String[] attachments) {
        this.attachments = attachments;
    }

    // Method to send the email
    public void sendEmail() throws MessagingException, IOException {
        Properties props = new Properties();
        //set property file path
        InputStream inputStream=new FileInputStream(".\\propertyfiles\\email.properties");
        props.load(inputStream);

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", props.getProperty("mailServer")); // Set your SMTP host here
        props.put("mail.smtp.port", props.getProperty("mailPort")); // Set your SMTP port here

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(props.getProperty("mailUsername"), props.getProperty("mailPassword"));
            }
        });

        // Create a multipart message
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(this.from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.to));

        if (this.cc != null && !this.cc.isEmpty()) {
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(this.cc));
        }

        if (this.bcc != null && !this.bcc.isEmpty()) {
            message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(this.bcc));
        }

        message.setSubject(this.subject);

        // Create the message body
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(this.body, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        // Add attachments if any
        if (this.attachments != null) {
            for (String attachment : this.attachments) {
                MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(attachment);
                attachmentBodyPart.setDataHandler(new DataHandler(source));
                attachmentBodyPart.setFileName(source.getName());
                multipart.addBodyPart(attachmentBodyPart);
            }
        }

        message.setContent(multipart);

        // Send the email
        Transport.send(message);
        System.out.println("Email sent successfully.");
    }

    public static void setEmailProps(String[] args) {
        SendEmail email = new SendEmail();
        email.setFrom("rpa@hbc.com");
        email.setTo("varun.kumarv@sakscloudservices.com");
        email.setSubject("Test Subject");
        email.setBody("<html><body><h1>Hello!</h1><p>This is a test email with HTML body.</p></body></html>");

//     email.setCc("cc@example.com");

//      email.setBcc("bcc@example.com");

//       email.setAttachments(new String[]{"path_to_attachment1", "path_to_attachment2"}); // Provide paths to attachments if any

        try {
            email.sendEmail();
        } catch (Exception e){
            throw new RuntimeException("Send email failed. Exception message: "+e.getMessage()+". "+"Exception cause: "+e.getCause());
        }
    }
}
