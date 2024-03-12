package bots;

import org.openqa.selenium.support.PageFactory;
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

    public static void main(String args[]) throws Exception {
        //Initailise Log 4j
        UtilClass.initialiseLog4j();
        //Kill Process
        KillAllProcess kill = new KillAllProcess();
        UtilClass util = new UtilClass();
        //Get Driver
        driver = util.getDriver();

        try{
            //Load Page factory for username page
            userNamePage = PageFactory.initElements(driver, UserNamePage.class);
            UserNamePage.enterusername(driver);
            //Load Page factory for password page
            passwordPage = PageFactory.initElements(driver, PasswordPage.class);
            PasswordPage.enterPassword(driver);
        }
        catch(Exception e){
            strExceptionMessage="Failed to login to IBM application. Exception message: "+e.getMessage()+"\n"+"Exception source: "+e.getCause();
           Log.error(strExceptionMessage);
            throw new RuntimeException(strExceptionMessage);
        }


        //Load Page factory for OTP verification page
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

        BuildCriticalAndWarningData getoutput=new BuildCriticalAndWarningData();
        getoutput.buildCriticalAndWarningData(strarrayoutputdata);
    }
}
