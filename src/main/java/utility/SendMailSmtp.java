package utility;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMailSmtp {

    public static void main(String args[]) throws MessagingException {
        Session smtpsession=Session.getDefaultInstance(new Properties());
        Store store=smtpsession.getStore("smtp");
        store.connect("mail.hbc.com",25,"rpa@hbc.com","xnzicmnejdvfxnzx");
        MimeMessage message=new MimeMessage(smtpsession);
    }
}
