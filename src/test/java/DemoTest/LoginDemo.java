package DemoTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginDemo
{
	WebDriver drivers;
	

	@BeforeClass
	public void setup()
	{
	drivers= new ChromeDriver();
	drivers.manage().deleteAllCookies();
	drivers.manage().window().maximize();
	drivers.get("https://www.providerpayments.com/Login.aspx");
		
	}
	
	
	
	@FindBy(xpath="//input[@id='MainContent_LoginUser_UserName']")
	WebElement usern;
	
	@FindBy(xpath="//input[@id='MainContent_LoginUser_Password']")
	WebElement passw;
	
	@FindBy(xpath="//input[@id='MainContent_LoginUser_LoginButton']")
	WebElement loginbtn;
	
	@Test
	public  void putUser(String username)
	{
		usern.sendKeys("");
	}
	@Test
	public void putPass(String password)
	{
		passw.sendKeys("");
	}
	@Test
	public void clickBtn()
	{
		loginbtn.click();
	}
	
	@AfterClass
	public void teardown()
	{
		drivers.close();
	}
}

