package bots;

import org.openqa.selenium.support.PageFactory;
import pages.*;
import org.openqa.selenium.WebDriver;
import utility.*;


public class HB_OMS_DbQuery_Performer {

    static WebDriver driver;
    static String partialotp;
    static String otp;

    static String[][] strarrayinputdata;
    static String[][] strarrayoutputdata;

    static String[][] strarraywarningdata;

    static String[][] strarraycriticaldata;

    static int warningindex;
    static int criticalindex;

    static UserNamePage userNamePage;
    static PasswordPage passwordPage;


    public static void main(String args[]) throws Exception {

        UtilClass.initialiseLog4j();
        //KillAllProcess kill = new KillAllProcess();
        UtilClass util = new UtilClass();
        driver = util.getDriver();
        userNamePage = PageFactory.initElements(driver, UserNamePage.class);
        UserNamePage.enterusername(driver);

        passwordPage = PageFactory.initElements(driver, PasswordPage.class);
        PasswordPage.enterPassword(driver);


        //Thread.sleep(120000);

        OTPVerificationPage otppage = PageFactory.initElements(driver, OTPVerificationPage.class);
        partialotp = otppage.capturePartialOTP();

        otp = GetEmailFromServiceAccount.GetMailOTP(partialotp);
        System.out.println("OTP: " + otp);
        EnterOTPPage enterOTP = PageFactory.initElements(driver, EnterOTPPage.class);
        enterOTP.enterotp(otp);

        strarrayinputdata = ReadExcelInput.getDataFromSheet();
        ExecuteDbQueryPage executeDbQuery = PageFactory.initElements(driver, ExecuteDbQueryPage.class);
        strarrayoutputdata = executeDbQuery.entersqlquery(strarrayinputdata);
    }
}
