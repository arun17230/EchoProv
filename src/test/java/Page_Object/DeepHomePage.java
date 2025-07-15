package Page_Object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestBase.BasePage;

public class DeepHomePage extends BasePage
{

	//Constructor 
	
	public DeepHomePage(WebDriver driv, WebDriverWait waits)
	{
		super(driv, waits);
		// TODO Auto-generated constructor stub
	}
	
	
	//Locators
	//Welcome Text
	@FindBy(xpath="//span[@id='lblProfile']")
	WebElement textwel;
	
	@FindBy(xpath="//a[@id='btnLogout']")
	WebElement logoutbtn;
	
	@FindBy(xpath = "//a[@id='lnkAddTINs']")
	WebElement ManageTins;
	

	public String Welcomemessage()
	{
		return textwel.getText();
	}
	
	public boolean ValidateWelcomemessage()
	{
		try 
	    {
	        String msg = textwel.getText();  // ✅ capture the text
	        
	        // ✅ You had .contains("Welcome") — but maybe it’s case-sensitive or word is different
	        return textwel.isDisplayed() && msg.toLowerCase().contains("welcome");
	    }
	    catch (Exception e)
	    {
	        System.out.println("❌ Exception in ValidateWelcomemessage(): " + e.getMessage());
	        
	        return false;
	    }
	}
	
	
	public void logout()
	{
	
		waits.until(ExpectedConditions.visibilityOf(logoutbtn));
		logoutbtn.click();
		
	}
	
	
	public void ClickManageTin()
	{
		waits.until(ExpectedConditions.visibilityOf(ManageTins));
		ManageTins.click();
	}
	
	
	
}
