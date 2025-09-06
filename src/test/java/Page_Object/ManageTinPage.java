package Page_Object;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestBase.BasePage;

public class ManageTinPage extends BasePage
{

	public ManageTinPage(WebDriver driv, WebDriverWait waits) 
	{
		super(driv, waits);
	}
	
	// Locators
	@FindBy(xpath = "//a[@id='addTinLink']")
	WebElement AddTin;
	
	@FindBy(xpath = "//a[@id='removeTinLink']")
	WebElement removeTin;
	
	@FindBy(xpath = "//input[@id='MainContent_rdoPayment_0']")
	WebElement HavePaymentradiobtn;
	
	@FindBy(xpath = "//select[@id='MainContent_ddlTinAffiliationType']")
	WebElement AffiliatedTin;
	
	@FindBy(xpath = "//input[@id='MainContent_txtTIN']")
	WebElement TinNumber;
	
	@FindBy(xpath = "//input[@id='MainContent_txtDraftNumber']")
	WebElement DraftNumber;
	
	@FindBy(xpath = "//input[@id='MainContent_txtDraftAmount']")
	WebElement DraftAmount;
	
	@FindBy(xpath = "//input[@id='MainContent_CreateTINButton']")
	WebElement AddBtn;
	
	@FindBy(xpath = "//input[@id='MainContent_Cancel']")
	WebElement cancelbtnl;
	
	@FindBy(xpath = "//p[normalize-space()='TIN has been added to your account successfully.']")
	WebElement TinAddAccessmsg;
	
	@FindBy(xpath = "//li[normalize-space()='Please select any Affiliation with Tax ID.']")
	WebElement AffiliationReqmsg;
	
	@FindBy(xpath = "//li[normalize-space()='TIN is required.']")
	WebElement TinReqmsg;
	
	@FindBy(xpath = "//li[normalize-space()='Draft Number is required.']")
	WebElement DraftNumReqmsg;
	
	@FindBy(xpath = "//li[normalize-space()='Draft Amount is required.']")
	WebElement DraftAmReqmsg;
	
	
	@FindBy(xpath = "//span[contains(text(),'The information you entered does not match the rec')]")
	WebElement Incorrectmsg;
	
	
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


	// Actions
	public void ClickAddTin()
	{
		waits.until(ExpectedConditions.elementToBeClickable(AddTin));
		AddTin.click();
	}
	
	public void ClickHaveRadiobtn()
	{
		waits.until(ExpectedConditions.elementToBeClickable(HavePaymentradiobtn));
		HavePaymentradiobtn.click();
	}
	
	public void ClickTinAffDropdown()
	{
		
		waits.until(ExpectedConditions.visibilityOf(AffiliatedTin));
		AffiliatedTin.click();
	}
	
	public void SelectAffiliatedTin(String OptionText)
	{
		waits.until(ExpectedConditions.elementToBeClickable(AffiliatedTin));
		Select sc = new Select(AffiliatedTin);
		sc.selectByVisibleText(OptionText);
		waitForSeconds(5);
		
	}
	
	public void putTinId(String TINID)
	{
		waits.until(ExpectedConditions.visibilityOf(TinNumber));
		TinNumber.clear();
		TinNumber.sendKeys(TINID);
		waits.until(ExpectedConditions.textToBePresentInElementValue(TinNumber, TINID));
		waitForSeconds(5);
	}

	public void putDraftNumber(String Dnumber)
	{
		waits.until(ExpectedConditions.visibilityOf(DraftNumber));
		DraftNumber.clear();
		DraftNumber.sendKeys(Dnumber);
		waits.until(ExpectedConditions.textToBePresentInElementValue(DraftNumber, Dnumber));
		waitForSeconds(5);
	}
	
	public void putDraftAmount(String DAmount)
	{
		waits.until(ExpectedConditions.visibilityOf(DraftAmount));
		DraftAmount.clear();
		DraftAmount.sendKeys(DAmount);
		waits.until(ExpectedConditions.textToBePresentInElementValue(DraftAmount, DAmount));
		waitForSeconds(5);
	}
	
	public void clickAddbtn()
	{
		
		waits.until(ExpectedConditions.elementToBeClickable(AddBtn));
		AddBtn.click();
	}
	
	public String DisplaySuccessmessage()
	{
		try 
		{ 
			waitForSeconds(10);
			waits.until(ExpectedConditions.visibilityOf(TinAddAccessmsg));
			return TinAddAccessmsg.getText().trim();
			
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		return "failed";
	}
	
	//Negative TestCase Action Methods
	//Affiliated Message
	public boolean IsAffReqMsgDisp()
	{
		try
		{
			waits.until(ExpectedConditions.visibilityOf(AffiliationReqmsg));
			return AffiliationReqmsg.isDisplayed();
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	public String getAffReqMsg()
	{
		try
		{
			waits.until(ExpectedConditions.visibilityOf(AffiliationReqmsg));
			return AffiliationReqmsg.getText().trim();
		}
		catch(Exception e)
		{
			return "";
		}
	}
	
	
	//TinRequried
	
	public boolean isTinReqMsgDisp()
	{
		try
		{
			waits.until(ExpectedConditions.visibilityOf(TinReqmsg));
			return TinReqmsg.isDisplayed();
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	public String getTinReqMsg()
	{
		try
		{
			waits.until(ExpectedConditions.visibilityOf(TinReqmsg));
			return TinReqmsg.getText().trim();
		}
		catch(Exception e)
		{
			return "";
		}
	}
	
	
	//DraftNumber Req
	
	public boolean isDraftNumReqmsgDis()
	{
		try
		{
			waits.until(ExpectedConditions.visibilityOf(DraftNumReqmsg));
			return DraftNumReqmsg.isDisplayed();
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public String getDraftNumReqMsg()
	{
		try 
		{
			waits.until(ExpectedConditions.visibilityOf(DraftNumReqmsg));
			return DraftNumReqmsg.getText().trim();
					
		}
		catch(Exception e)
		{
			return "";
		}
	}
	
	public boolean isDraftAmountReqmsgDis()
	{
		try
		{
			waits.until(ExpectedConditions.visibilityOf(DraftAmReqmsg));
			return DraftAmReqmsg.isDisplayed();
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public String getDraftAmountReq()
	{
		try 
		{
			waits.until(ExpectedConditions.visibilityOf(DraftAmReqmsg));
			return DraftAmReqmsg.getText().trim();
					
		}
		catch(Exception e)
		{
			return "";
		}
	}
	
	
	public boolean isIncorrectInfoMessageDisplayed() 
	{
	    try 
	    {
	        waits.until(ExpectedConditions.visibilityOf(Incorrectmsg));
	        return Incorrectmsg.isDisplayed();
	    } 
	    catch (Exception e)
	    {
	        return false;
	    }
	}

	public String getIncorrectInfoMessage() 
	{
	    try 
	    {
	        waits.until(ExpectedConditions.visibilityOf(Incorrectmsg));
	        return Incorrectmsg.getText().trim();
	    } 
	    catch (Exception e)
	    {
	        return "";
	    }
	
	}
	
	public void clickRemovetin()
	{
		waits.until(ExpectedConditions.elementToBeClickable(removeTin));
		removeTin.click();
	}
}
