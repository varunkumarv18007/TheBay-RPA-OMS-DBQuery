package utility;

import javax.mail.*;
import java.io.IOException;
import java.util.*;
import javax.mail.internet.InternetAddress;
import javax.mail.search.*;
import java.util.regex.*;
public class GetEmailFromServiceAccount {
    static String otp;
    static String strExceptionMessage;
    public static void main(String args[]) throws Exception {

    }
    public static String GetMailOTP(String partialotp) throws MessagingException,IOException,Exception {
        Session session = Session.getDefaultInstance(new Properties());
        try {
            Store store = session.getStore(Constants.MAIL_PROTOCOL);
            store.connect(Constants.IMAP_SERVER,  Constants.IMAP_PORT, Constants.IMAP_USERNAME,Constants.IMAP_PASSWORD);
            Folder inbox = store.getFolder(Constants.MAILBOX_FOLDER);
            System.out.println(inbox.getUnreadMessageCount());
            inbox.open(Folder.READ_WRITE);
            SearchTerm sender = new FromTerm(new InternetAddress(Constants.SENDER_MAILADDRESS));
            //fetch only unread messages
            Flags seen = new Flags(Flags.Flag.SEEN);
            FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
            //searching sender
            //combine search term
            SearchTerm searchTerm = new AndTerm(unseenFlagTerm, sender);
            Message[] messages = inbox.search(searchTerm);
            Log.info("Getting OTP mail...");
            Log.info("Email count: "+messages.length);

            ArrayList<Message> listmessage = new ArrayList<Message>();
            // Reverse the array so that we get latest unread emails
            Collections.reverse(Arrays.asList(messages));
            //Get the top 30 emails
            for(int i=0;i<=30;i++) {
                Pattern regexpattern =Pattern.compile(partialotp+"[0-9]{6}");
                Matcher matchregex =regexpattern.matcher(messages[i].getContent().toString());
                if (messages[i].getSubject().contains(Constants.OTP_MAIL_SUBJECT)) {
                    Log.info(messages[i].getContent().toString());
                    //System.out.println(messages[i].getContent());
                    messages[i].setFlag(Flags.Flag.SEEN, true);
                    listmessage.add(messages[i]);
                }

                if (matchregex.find()) {

                    otp =matchregex.group(0);
                    Log.info("OTP captured from mail body: "+otp);
                    break;
                }
            }

      }
        catch (Exception e) {
            strExceptionMessage="Failure in getting OTP mail. Exception message: "+e.getMessage()+'\n'+"Exception source: "+e.getCause();
            Log.error(strExceptionMessage);
            throw new RuntimeException(strExceptionMessage);
        }
        return otp;
    }
}







