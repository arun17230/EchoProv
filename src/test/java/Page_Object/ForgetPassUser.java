package Page_Object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestBase.BasePage;

public class ForgetPassUser extends BasePage
{

	public ForgetPassUser(WebDriver driv, WebDriverWait waits) 
	{
		super(driv, waits);
		
	}
	
	//Radio Buttons
	
	@FindBy(xpath ="//label[@for='MainContent_rdoselect_0']")
	WebElement forgetusername;

	@FindBy(xpath ="//label[@for='MainContent_rdoselect_1']")
	WebElement forgetpassword;
	
	@FindBy(xpath = "//input[@id='MainContent_txtUsername']")
	WebElement usernamefield;
	
	@FindBy(xpath = "//input[@id='MainContent_txtEmailID']")
	WebElement emailfield;
	
	@FindBy(xpath = "//input[@id='MainContent_ResetButton']")
	WebElement resetbtn;
	
	@FindBy(xpath = "//li[normalize-space()='Email id registered with is required.']")
	WebElement emailreqmessage;
	
	@FindBy(xpath = "//span[@id='MainContent_ErrorMessage']")
	WebElement errormessage;
	
	@FindBy(xpath = "//span[@id='MainContent_lblmsg']")
	WebElement successmessage;
	
	
	public void selectforgetpassoption()
	{
		forgetpassword.click();
	}
	
	public void enterusername(String uname)
	{
		usernamefield.clear();
		usernamefield.sendKeys(uname);
	}
	
	public void enteremailid(String emaid)
	{
		emailfield.clear();
		emailfield.sendKeys(emaid);
	}
	
	public void clickreset()
	{
		resetbtn.click();
	}
	
	
	public String isSuccessmessageDisplayed()
	{
		try
		{
		if(successmessage.isDisplayed())
		{
			return successmessage.getText();
		}
		}
		catch (Exception e)
		{
			System.out.println("Success message not displayed: " + e.getMessage());
		}
		return "Not Displayed";
			
	}
	
	public String isEmailrequiredMessageDisplay()
	{
		try 
		{
			waits.until(ExpectedConditions.visibilityOf(emailreqmessage));
			if(emailreqmessage.isDisplayed())
			{
				return emailreqmessage.getText();
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return "not_displayed";
	}
	
	public String isUsernamerequiredMessageDisplayed()
	{
		try
		{
			waits.until(ExpectedConditions.visibilityOf(errormessage));
			if(errormessage.isDisplayed())
			{
				return errormessage.getText();
			}
			
		
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return "not_displayed";
		
	}
	
}
