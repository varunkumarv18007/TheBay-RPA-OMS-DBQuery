package utility;

import utility.email.SendEmail;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
public class BuildCriticalAndWarningData extends GenerateHTMLBody {
    Map<String, String> criticaldata = new HashMap<>();
    Map<String, String> warningdata = new HashMap<>();

    String htmlText;
    String warningThreshold;
    String criticalThreshold;
    String htmlbody;

    public void buildCriticalAndWarningData(String[][] strarrayoutputdata) throws Exception {
        htmlText = Constants.HTML_TABLEHEADER;
        for (int i = 1; i < Arrays.stream(strarrayoutputdata).count(); i++) {

            warningThreshold = strarrayoutputdata[i][5];
            criticalThreshold = strarrayoutputdata[i][6];
            System.out.println("Warning threshold: " + strarrayoutputdata[i][5]);
            System.out.println("Critical threshold: " + strarrayoutputdata[i][6]);
            System.out.println("Result count: " + strarrayoutputdata[i][11]);

            if (strarrayoutputdata[i][11].isBlank() || strarrayoutputdata[i][11].isEmpty()) {
                Log.info("Result value is blank for Alert: " + strarrayoutputdata[i][1]);
                // System.out.println("Result value is blank for Alert: " + strarrayoutputdata[i][1]);
            } else {
                if ((Integer.parseInt(strarrayoutputdata[i][11]) <= Integer.parseInt(strarrayoutputdata[i][5])) && (Integer.parseInt(strarrayoutputdata[i][11]) > Integer.parseInt(strarrayoutputdata[i][6]))) {
                    //Add Alert name to Warning table
                    Log.info("Adding item to warning hashmap: " + strarrayoutputdata[i][0]);
                    warningdata.put(strarrayoutputdata[i][0],
                            strarrayoutputdata[i][11]);
                } else if ((Integer.parseInt(strarrayoutputdata[i][11]) <= Integer.parseInt(strarrayoutputdata[i][6]))) {
                    //Add Alert name to Warning table
                    Log.info("Adding item to critical hashmap: " + strarrayoutputdata[i][0]);
                    criticaldata.put(strarrayoutputdata[i][0],
                            strarrayoutputdata[i][11]);
                } else {
                    System.out.println("No warning or critical values");
                }
            }
        }

        SendEmail email = new SendEmail();
        email.setfrom(Constants.SENDMAIL_FROM);
        email.setTo(Constants.MAIL_TO);
        System.out.println(
                criticaldata.size());
        if (criticaldata!=null) {
            //System.out.println("html body: "+generateHtmlBody(criticaldata));
           htmlbody= String.format(Constants.CRITICAL_MAILBODY,generateHtmlBody(criticaldata));

           email.setSubject(Constants.CRITICAL_MAILSUBJECT);
            email.setBody(htmlbody);
            email.sendEmail();


        }
        if (warningdata!=null) {
            htmlbody= String.format(Constants.WARNING_MAILBODY,generateHtmlBody(warningdata));
            email.setSubject(Constants.WARNING_MAILSUBJECT);
            email.setBody(htmlbody);
            email.sendEmail();
        }

    }

}
