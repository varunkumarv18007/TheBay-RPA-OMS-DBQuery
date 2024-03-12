package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.BaseFunctions;
import utility.Constants;
import utility.Log;

public class EnterOTPPage extends BaseFunctions {
    WebDriver driver;
    String otp;
    String verificationcode;
    String splitotp[];
    WebDriverWait wait;
    String strExceptionMessage;
    public EnterOTPPage(WebDriver driver)
    {
        this.driver=driver;
    }
    @FindBy (id = "otp")
    WebElement otptextfield;
    @FindBy (xpath = "//button[contains(text(),'Verify')]")
    WebElement verifybtn;
    public void enterotp(String otp) {

        try{
            //Split OTP by -
            Log.info("Split OTP by -");
            splitotp= otp.split("-");
            verificationcode=splitotp[1];
            Log.info("Entering verification code in IBM page....");
            Log.info("Verification code: "+verificationcode);

            wait = new WebDriverWait(driver, Constants.WAIT_TIME);
            type(otptextfield,verificationcode,wait);
            click(verifybtn,wait);
            Log.info("Verification code entered");
        }
        catch(Exception e){
            strExceptionMessage="Failure in entering OTP in IBM page.Exception message: "+e.getMessage()+'\n'+"Exception source: "+e.getCause();
            Log.error(strExceptionMessage);
            throw new RuntimeException(strExceptionMessage);
        }

    }
}
