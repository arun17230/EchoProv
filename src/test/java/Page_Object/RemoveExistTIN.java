package Page_Object;



import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestBase.BasePage;

public class RemoveExistTIN extends BasePage
{

	//Constructor
	
	public RemoveExistTIN(WebDriver driv, WebDriverWait waits) 
	{
		super(driv, waits);
		
	}
	
	// Locator 
	
	@FindBy(xpath = "//button[@id='MainContent_ddlTIN_ddlTIN_Button']")
	WebElement dropdownbtn;
	
	@FindBy(xpath = "//input[@id='MainContent_ddlTIN_ddlTIN_TextBox']")
	WebElement TINinputbox;
	
	@FindBy(xpath = "//a[@id='MainContent_refreshLink']")
	WebElement refreshbtn;
	
	@FindBy(xpath = "//input[@id='MainContent_gvTIN_checkboxTin_0']")
	WebElement TINSelectCheckBox;
	
	@FindBy(xpath = "//input[@id='MainContent_RemoveTinBtn']")
	WebElement Removebtn;
	
	@FindBy(xpath = "//p[@class='SuccessMessage']")
	WebElement successmsg;
	
	@FindBy(xpath = "//span[@id='lblProfile']")
	WebElement emptyclk;
	
	
	//Using the Method Becuase after selecting from DropDown Page AJAX elements are refreshing Up
		private void waitForSeconds(int seconds) 
		{
		    try 
		    {
		        new WebDriverWait(driv, Duration.ofSeconds(seconds)).until(driver -> false);
		    } 
		    catch (Exception ignored) 
		    {
		        // Acts like Thread.sleep but cleaner
		    }
		}
	
	
	
	//Action Methods
	
	//Delete this Method and Rewrite again as the clicking on Dropdown list button
		public void putTIN(String TINNumber) 
		{
		    waits.until(ExpectedConditions.elementToBeClickable(dropdownbtn));
		    dropdownbtn.click();

		    waits.until(ExpectedConditions.visibilityOf(TINinputbox));

		    // Best practice: use CTRL+A + overwrite (no clear())
		    TINinputbox.sendKeys(Keys.chord(Keys.CONTROL, "a"), TINNumber);

		    waits.until(ExpectedConditions.elementToBeClickable(emptyclk));
		    emptyclk.click();  // confirm selection
		}

	
	public void SelectingCheckBox()
	{
		waitForSeconds(5);
		
		TINSelectCheckBox.click();
	}
	
	public String PassedMessage()
	{
		waits.until(ExpectedConditions.visibilityOf(successmsg));
		waitForSeconds(5);
		return successmsg.getText().trim();
	}
	
	public void clickrmv()
	{
		
		Removebtn.click();
		// Handle confirmation alert after clicking Remove
	    try 
	    {
	        waits.until(ExpectedConditions.alertIsPresent()); // wait for alert
	        driv.switchTo().alert().accept(); // click OK
	    } 
	    catch (Exception e) 
	    {
	        System.out.println("No alert appeared after clicking Remove.");
	    }
	}
	
	
	public void clickrefresh()
	{
		waits.until(ExpectedConditions.elementToBeClickable(refreshbtn));
		refreshbtn.click();
	}

	
	
}
	
	
	
	


