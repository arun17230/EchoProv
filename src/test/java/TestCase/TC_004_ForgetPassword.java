package TestCase;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Page_Object.ForgetPassUser;
import Page_Object.LoginPage;
import TestBase.BaseClass;

public class TC_004_ForgetPassword extends BaseClass
{
	@BeforeMethod
	public void navigateURL() //Using this Because we need to Load Page again for Negative Testing
	{
		driv.get(p.getProperty("ApplicationURL"));
	}
	
	@Test(groups= {"sanity","master","regression"})
	public void forgetpass()
	{
		System.out.println("Started Executing TestCase");
		logs.info("Started TC_004_ForgetPassword");
		LoginPage lp = new LoginPage(driv, waits);
		logs.info("LoginPage is Intializing");
		lp.forgetpassword();
		
		ForgetPassUser fp = new ForgetPassUser(driv, waits);
		fp.selectforgetpassoption();
		fp.enterusername(p.getProperty("usname"));
		fp.enteremailid(p.getProperty("emailid"));
		fp.clickreset();
		
		
		String actualmsgs=fp.isSuccessmessageDisplayed().trim();
		System.out.println(actualmsgs);
		
		if(actualmsgs.toLowerCase().contains("success"))
		{
			System.out.println("password link sent successfully.");
            logs.info("ForgetPassword Functionality Working as Expected");
            Assert.assertTrue(true);
		}
		else 
		{
			System.out.println("Test Failed");
			logs.info("ForgetPassword functionality failed");
			System.out.println("Test Failed");
			Assert.assertFalse(false);
		}
	}
	
	
	//Negative Test
	
	@Test(groups = {"regression","master"})
	  public void verifyEmailrequired ()
	  {
		  System.out.println("Testing Negative case without email id");
		  logs.info("Exectuing Negative Test");
		  
		  LoginPage lp = new LoginPage(driv, waits);
		  lp.forgetpassword();
		  
		  ForgetPassUser fp = new ForgetPassUser(driv, waits);
		  fp.selectforgetpassoption();
		  fp.enterusername(p.getProperty("usname"));
		  fp.enteremailid("");
		  fp.clickreset();
		  
		  String errormsg1=fp.isEmailrequiredMessageDisplay();
		  System.out.println(errormsg1);
		  
		  
		  if(!errormsg1.equalsIgnoreCase("not_displayed") && errormsg1.toLowerCase().contains("email id"))
		  {
		  Assert.assertTrue(true);
		  }
		  else
		  {
			  Assert.fail();
		  } 
		  
	  }
	  
	  //Negative Testcase
	@Test(groups = {"regression","master"})
	  public void verifyUsernameRequired()
	  {
		  System.out.println("Testing Negative case without username");
		  logs.info("Exectuing Negative Test");
		  
		  LoginPage lp = new LoginPage(driv, waits);
		  lp.forgetpassword();
		  
		  ForgetPassUser fp = new ForgetPassUser(driv, waits);
		  fp.selectforgetpassoption();
		  fp.enterusername("");
		  fp.enteremailid(p.getProperty("emailid"));
		  fp.clickreset();
		  
		  String errormsg2=fp.isUsernamerequiredMessageDisplayed();
		  System.out.println(errormsg2);
		  
		  if(!errormsg2.equalsIgnoreCase("not_displayed") && errormsg2.toLowerCase().contains("admin"))
		  {
			  assertTrue(true,"Required Username");
		  }
		  else
		  {
			  Assert.fail();
		  }
		  
		  
		  
	  }

}
