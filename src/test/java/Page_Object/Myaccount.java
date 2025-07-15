package Page_Object;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestBase.BasePage;

public class Myaccount extends BasePage
{

    public Myaccount(WebDriver driv, WebDriverWait waits) {
        super(driv, waits);
        PageFactory.initElements(driv, this);
    }

    // ===== Page Elements =====
    @FindBy(xpath = "//span[@id='spanIDMyAccount']")
     WebElement myAccountBtn;

    @FindBy(xpath = "//input[@id='MainContent_chkChangePassword']")
     WebElement changePassCheckBox;

    @FindBy(xpath = "//input[@id='MainContent_txtOldPassword']")
     WebElement oldPassField;

    @FindBy(xpath = "//input[@id='MainContent_txtNewPassword']")
     WebElement newPassField;

    @FindBy(xpath = "//input[@id='MainContent_txtConfirmPassword']")
     WebElement confirmPassField;

    @FindBy(xpath = "//input[@id='MainContent_ChangeButton']")
     WebElement submitBtn;

    @FindBy(xpath = "//span[@id='idErrorMsg']")
     WebElement errorMsg;

    @FindBy(xpath = "//span[@id='success']")
     WebElement successMsg;

    @FindBy(xpath = "//label[@id='ConfirmPwdErrorMsg']")
     WebElement newConfPassMismatchMsg;
    
    @FindBy(xpath = "//a[@id='lnkAddTINs']")
    WebElement manageTins;

    // ===== Action Methods =====

    public void openChangePasswordSection() {
        myAccountBtn.click();
        changePassCheckBox.click();
    }

    public void enterOldPassword(String oldPassword) {
        oldPassField.clear();
        oldPassField.sendKeys(oldPassword);
    }

    public void enterNewPassword(String newPassword) {
        newPassField.clear();
        newPassField.sendKeys(newPassword);
    }

    public void enterConfirmPassword(String confirmPassword) {
        confirmPassField.clear();
        confirmPassField.sendKeys(confirmPassword);
    }

    public void clickSubmit() {
        submitBtn.click();
    }

    public void changePassword(String oldPassword, String newPassword, String confirmPassword) {
        openChangePasswordSection();
        enterOldPassword(oldPassword);
        enterNewPassword(newPassword);
        enterConfirmPassword(confirmPassword);
        clickSubmit();
    }

    // ===== Message Checks =====

    public boolean isSuccessMessageDisplayed() 
    {
        try
        {
            return successMsg.isDisplayed();
        } 
        catch (Exception e) 
        {
            return false;
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return errorMsg.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isMismatchMessageDisplayed() {
        try {
            return newConfPassMismatchMsg.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessageText() 
    {
        try 
        {
            return errorMsg.getText().trim();
        }
        catch (Exception e) 
        {
            return "";
        }
    }
    
    public void clickManageTin()
    {
    	manageTins.click();
    }
    
}
