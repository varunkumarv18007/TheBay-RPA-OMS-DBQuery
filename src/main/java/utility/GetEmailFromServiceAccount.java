package utility;

import javax.mail.*;
import java.io.IOException;
import java.util.*;
import javax.mail.internet.InternetAddress;
import javax.mail.search.*;
import java.util.regex.*;


public class GetEmailFromServiceAccount {

    static String otp;

    public static void ma


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
            //Message messages[]=inbox.search(sender,new SubjectTerm("[EXTERNAL] Test Email from SCS account"));
            System.out.println("Email count: "+messages.length);

//         for (int i = 0; i < messages.length; i++) {
//               Message message = messages[i];
//               message.getContent();
//               System.out.println("Email Body:" + message.getContent());
//           }

            ArrayList<Message> listmessage = new ArrayList<Message>();
            // Reverse the array so that we get latest unread emails
            Collections.reverse(Arrays.asList(messages));

            //Get the top 30 emails
            for(int i=0;i<=30;i++)
            {
                System.out.println(messages[i].getContent());
                System.out.println(i+". "+messages[i].getSubject()+". Date: "+messages[i].getReceivedDate());
                Pattern regexpattern =Pattern.compile(partialotp+"[0-9]{6}");
                Matcher matchregex =regexpattern.matcher(messages[i].getContent().toString());
                if (messages[i].getSubject().contains(Constants.OTP_MAIL_SUBJECT))
                {
                    System.out.println(messages[i].getContent());
                    messages[i].setFlag(Flags.Flag.SEEN, true);
                    listmessage.add(messages[i]);
                }

                if (matchregex.find())
                {
                    System.out.println(matchregex.group(0));
                    otp =matchregex.group(0);
                    break;
                }


            }


//            for ( Message message : messages ) {
//                System.out.println(message.getSubject());
//                if (message.getSubject().contains("[EXTERNAL] Verify your identity"))
//                {
//                      ListMessage.add(message);
//                }
//               System.out.println(
//                       "sendDate: " + message.getSentDate()
//                               + " subject:" + message.getSubject() +" Time: "+message.getSentDate()+" index: ");
//               message.setFlag(Flags.Flag.SEEN, true);
//          }

//            for (Message m : ListMessage)
//            {
//                System.out.println("List Message: "+m.getSubject());
//            }

      }

        catch (Exception e) {
            throw e;
        }
        return otp;
    }

}







