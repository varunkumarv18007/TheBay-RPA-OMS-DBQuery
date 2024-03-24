package bots;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pages.*;
import org.openqa.selenium.WebDriver;
import utility.*;
import utility.queueitems.QueueItemUtils;

import java.util.Queue;


public class HBOMSDbQueryPerformer {
    static WebDriver driver;
    static String strPartialotp;
    static String strOtp;
    static String[][] strarrayinputdata;
    static String[][] strarrayoutputdata;
    static String[][] strarraywarningdata;
    static String[][] strarraycriticaldata;
    static int intWarningindex;
    static int intCriticalindex;

    static PasswordPage passwordPage;
    static String strExceptionMessage;

    static String queueStatus;



        @Test
        public static void dbQueryPerformer () throws Exception {
            try {
                //Initailise Log 4j
                UtilClass.initialiseLog4j();

                // Call the actions want to retry
                //Kill Process
                KillAllProcess kill = new KillAllProcess();
                UtilClass util = new UtilClass();
                //Get Driver
                driver = util.getDriver();


                LoginPage loginPage = new LoginPage(driver);
                loginPage.login(driver);
                OTPVerificationPage otppage = new OTPVerificationPage(driver);
                //Capture partial OTP
                strPartialotp = otppage.capturePartialOTP();
                //Get OTP from email service account
                Thread.sleep(Constants.EMAIL_DELAY);

                GetEmailFromServiceAccount emailFromServiceAccount = new GetEmailFromServiceAccount();
                strOtp = emailFromServiceAccount.GetMailOTP(strPartialotp);

                //Load Page factory for Enter OTP page
                EnterOTPPage otpPage = new EnterOTPPage(driver);
                //Enter OTP
                otpPage.enterOtp(strOtp);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            try {
                //Read input data

                QueueItemUtils queueItemUtils = new QueueItemUtils();
                queueItemUtils.addQueueItem();
                queueItemUtils.getQueueItem();

                ReadExcelInput excelInput = new ReadExcelInput();
                strarrayinputdata = excelInput.getDataFromSheet();
                //Load page factory for Execute Db query

                ExecuteDbQueryPage executeDbQuery = new ExecuteDbQueryPage(driver);
                //Get the output data after executing Db query
                strarrayoutputdata = executeDbQuery.entersqlquery(strarrayinputdata);

                BuildCriticalAndWarningData getoutput = new BuildCriticalAndWarningData();
                getoutput.buildCriticalAndWarningData(strarrayoutputdata);
                 queueStatus = "Successful";
            }
        catch(Exception e){
                queueStatus="Failed";


        }finally {

                System.out.println("Setting Transactiion status");
                QueueItemUtils setStatus=new QueueItemUtils();
                setStatus.setQueueStatus(queueStatus);

    }
    }

}
