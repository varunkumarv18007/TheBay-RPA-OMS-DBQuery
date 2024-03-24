package bots;

import utility.email.SendEmail;

import javax.mail.MessagingException;
import java.io.IOException;

public class TestMail {

    public static void main(String args[]) throws MessagingException, IOException {

          SendEmail email=new SendEmail();

          email.setBody("Test");
          email.setSubject("Test");
          email.setfrom("rpa@hbc.com");
          email.setTo("varun.kumarv@sakscloudservices.com");
          email.sendEmail();
    }
}
