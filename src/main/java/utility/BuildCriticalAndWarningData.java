package utility;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
public class BuildCriticalAndWarningData extends SendMailAlerts
{
    Map<String, String> criticaldata = new HashMap<>();
    Map<String, String> warningdata = new HashMap<>();

    String htmlText;

    public void buildCriticalAndWarningData(String[][] strarrayoutputdata) {
        htmlText=Constants.HTML_TABLEHEADER;
        for (int i = 1; i < Arrays.stream(strarrayoutputdata).count(); i++) {
            System.out.println("Warning threshold: "+strarrayoutputdata[i][5]);
            System.out.println("Critical threshold: "+strarrayoutputdata[i][6]);
            System.out.println("Result count: "+strarrayoutputdata[i][11]);

                if (strarrayoutputdata[i][11].isBlank() || strarrayoutputdata[i][11].isEmpty()) {
                    System.out.println("Result value is blank for Alert: " + strarrayoutputdata[i][1]);
                }
                else {
                    if ((Integer.parseInt(strarrayoutputdata[i][11]) <= Integer.parseInt(strarrayoutputdata[i][5])) && (Integer.parseInt(strarrayoutputdata[i][11]) > Integer.parseInt(strarrayoutputdata[i][6]))) {
                        //Add Alert name to Warning table
                        warningdata.put(strarrayoutputdata[i][0],
                                strarrayoutputdata[i][11]);
                    }
                    else if ((Integer.parseInt(strarrayoutputdata[i][11]) <= Integer.parseInt(strarrayoutputdata[i][6]))) {
                        //Add Alert name to Warning table
                        criticaldata.put(strarrayoutputdata[i][0],
                                strarrayoutputdata[i][11]);
                    }
                    else {
                        System.out.println("No warning or critical values");
                    }
                }
            }
        sendMailAlert(criticaldata);
        sendMailAlert(warningdata);
//        htmlText=Constants.HTML_TABLEHEADER;
//            for (Map.Entry<String,String> map : criticaldata.entrySet())
//            {
//                System.out.println("Printing critical data");
//                System.out.println("Key:"+map.getKey());
//                System.out.println("Value:"+map.getValue());
//                htmlText=htmlText+"<tr align='center' border ='2'>"+"<td>" + map.getKey() + "</td>"
//                        + "<td>" + map.getValue() + "</td>"+"</tr>";
//            }
//            if (!criticaldata.isEmpty()) {
//                sendMail(Constants.CRITICAL_MAILSUBJECT, Constants.CRITICAL_MAILBODY.replace("{0}",htmlText));
//            }

//        htmlText=Constants.HTML_TABLEHEADER;
//            for (Map.Entry<String,String> map : warningdata.entrySet())
//            {
//                htmlText=htmlText+"<tr align='center'>"+"<td>" + map.getKey() + "</td>"
//                        + "<td>" + map.getValue() + "</td>"+"</tr>";
//
//            }
//        if (!warningdata.isEmpty()) {
//            sendMail(Constants.WARNING_MAILSUBJECT, Constants.WARNING_MAILBODY.replace("{0}",htmlText));
//        }
        }

}


