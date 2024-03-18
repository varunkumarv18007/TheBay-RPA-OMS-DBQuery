package utility;


import java.time.Duration;
public class Constants {
   public static String LOG4J_XML ="log4j.xml";
   public static String KILL_PROCESS = "taskkill /F /IM chrome.exe /T";

   public static String CHROMEDRIVER_PATH =".\\chromedriver\\chromedriver-win64\\chromedriver-win64\\chromedriver (2).exe";

   public  static String INPUT_FILE="C:\\TheBay-RPA-OMS-DBQuery\\BAY OMoC Monitoring via RPA.xlsx";
   public static String OMS_URL = "https://hudsn-prod-1.oms.supply-chain.ibm.com/smcfs/yfshttpdbi/sterlingdbqueryclient.jsp";
   public static  String USER_NAME = "srvc_oms_automation@hbc.com";
   public static String PASSWORD = "HBCINDIAPassw0rd9";
   public static Duration WAIT_TIME = Duration.ofSeconds(60);
   public static String MAIL_PROTOCOL = "imaps";
   public static String IMAP_SERVER = "imap.gmail.com";
   public static int IMAP_PORT = 993;
   public static String MAILBOX_FOLDER = "Inbox";
   public static String SENDER_MAILADDRESS = "ibmacct@iam.ibm.com";
   public static String OTP_MAIL_SUBJECT =
           "[EXTERNAL] Verify your identity"; //space before and after variable
   public static String IMAP_USERNAME = "srvc_oms_automation@hbc.com";
   public static String IMAP_PASSWORD = "wpdcyaevmtbvgbgi";
   public static String INPUT_SHEET = "ProposedTemplate_30Minutes";
   public static String TIME_ZONE="America/New_York";
   public static String TIME_FORMAT="H:mm";

   public static String CRITICAL_MAILSUBJECT="CRITICAL - OMS Database Query Monitoring- Alert";
   public static String WARNING_MAILSUBJECT="WARNING/ERROR - OMS Database Query Monitoring- Alert";

   public static String CRITICAL_MAILBODY="Hi Team<br/><br/>The BOT has successfully completed it's run. The queries " +
           "listed below have passed the Critical threshold.<br/><br/>Please see below:<br/><br/>{0}";

   public static String WARNING_MAILBODY="Hi Team<br/><br/>The BOT has successfully" +
           " completed it's run. The queries listed below have either passed the warning threshold or triggered an error in IBM.<br/><br/>Please see details below.<br/><br/>{0}";

public static String MAIL_CC="varun.kumarv@sakscloudservices.com";

public static String MAIL_BCC="";

   public  static String SENDMAIL_FROM="rpa@hbc.com";

   public static String SENDMAIL_PASSWORD="xnzicmnejdvfxnzx";

   public static String MAIL_TO="varunkumar.venkatesh@hbc.com";

   public static String SMTP_HOST="mail.hbc.com";

   public static int SMTP_PORT=25;

   public static String HTML_TABLEHEADER=
           "<table width='100%' border='0' align='center'>"
           + "<tr align='center'>"
           + "<td><b>Alert Name<b></td>"
           + "<td><b>Query Result Count<b></td>"
           + "</tr>";




}
