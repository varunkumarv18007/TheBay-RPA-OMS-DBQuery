package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.BaseFunctions;
import utility.Constants;
import utility.Log;

public class OTPVerificationPage extends BaseFunctions {
    WebDriver driver;
    public String partialotp;
    WebDriverWait wait;
    String strExceptionMessage;
    public OTPVerificationPage(WebDriver driver){
       this.driver=driver;
    }
    @FindBy (xpath = "//*[@id=\"mfa-text-wrap\"]/div/span[1]")
    WebElement otpfield;
    public String capturePartialOTP(){
        try {
            Log.info("Capturing partial OTP from IBM page");
            wait = new WebDriverWait(driver, Constants.WAIT_TIME);
            partialotp = getText(otpfield, wait);
            Log.info("Partial OTP captured: "+partialotp);
            return partialotp;
        }
        catch (Exception e)
        {
            strExceptionMessage="Failure in capturing partial OTP. Exception message: "+e.getMessage()+'\n'+"Exception source: "+e.getCause();
            Log.error(strExceptionMessage);
            throw new RuntimeException(strExceptionMessage);
        }
    }
}
