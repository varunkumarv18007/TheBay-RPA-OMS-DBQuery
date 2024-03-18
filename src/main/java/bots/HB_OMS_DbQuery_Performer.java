package bots;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pages.*;
import org.openqa.selenium.WebDriver;
import utility.*;


public class HB_OMS_DbQuery_Performer {
    static WebDriver driver;
    static String strPartialotp;
    static String strOtp;
    static String[][] strarrayinputdata;
    static String[][] strarrayoutputdata;
    static String[][] strarraywarningdata;
    static String[][] strarraycriticaldata;
    static int intWarningindex;
    static int intCriticalindex;
    static UserNamePage userNamePage;
    static PasswordPage passwordPage;
    static String strExceptionMessage;

    @Test(retryAnalyzer =testng.RetryAnalyzer.class)
    public static void dbQueryPerformer() throws Exception {
        //Initailise Log 4j
        UtilClass.initialiseLog4j();
        int maxRetries = 3;
        int retryCount = 0;
        boolean success = false;
        while (!success && retryCount < maxRetries) {
            try {
                // Call the actions want to retry
                //Kill Process
                KillAllProcess kill = new KillAllProcess();
                UtilClass util = new UtilClass();
                //Get Driver
                driver = util.getDriver();
                //Load Page factory for username page
                userNamePage = PageFactory.initElements(driver, UserNamePage.class);
                UserNamePage.enterusername(driver);
                driver.quit();
                //Load Page factory for password page
                passwordPage = PageFactory.initElements(driver, PasswordPage.class);
                PasswordPage.enterPassword(driver);
                success = true; // If the method call doesn't throw an exception, mark success
            } catch (Exception e) {
                strExceptionMessage = "Failed to login to IBM application. Exception message: " + e.getMessage() + "\n" + "Exception source: " + e.getCause();
                Log.error(strExceptionMessage);
                retryCount++;
                if (retryCount >= maxRetries && !success) {
                    new RuntimeException(strExceptionMessage);
                }
            }

        }

        OTPVerificationPage otppage = PageFactory.initElements(driver, OTPVerificationPage.class);
        //Capture partial OTP
        strPartialotp = otppage.capturePartialOTP();
        //Get OTP from email service account
        Thread.sleep(120000);
        strOtp = GetEmailFromServiceAccount.GetMailOTP(strPartialotp);
        System.out.println("OTP: " + strOtp);
        //Load Page factory for Enter OTP page
        EnterOTPPage enterOTP = PageFactory.initElements(driver, EnterOTPPage.class);
        //Enter OTP
        enterOTP.enterotp(strOtp);
        //Read input data
        strarrayinputdata = ReadExcelInput.getDataFromSheet();
        //Load page factory for Execute Db query
        ExecuteDbQueryPage executeDbQuery = PageFactory.initElements(driver, ExecuteDbQueryPage.class);
        //Get the output data after executing Db query
        strarrayoutputdata = executeDbQuery.entersqlquery(strarrayinputdata);

        BuildCriticalAndWarningData getoutput = new BuildCriticalAndWarningData();
        getoutput.buildCriticalAndWarningData(strarrayoutputdata);
    }


}
