package TestCase;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;

import Page_Object.DeepHomePage;
import Page_Object.LoginPage;
import Page_Object.ManageTinPage;
import Page_Object.RemoveExistTIN;
import TestBase.BaseClass;

public class TC_007_TINRemoval extends BaseClass
{

	@Test(groups= {"sanity","master"})
	public void TINRemoval() throws InterruptedException
	{
		System.out.println("Started Executing TestCase");
        logs.info("Started TC_007_TinRemoval");
        
     // Login Phase
        
        LoginPage lp = new LoginPage(driv, waits);
        logs.info("LoginPage initialized");
        lp.putUser(p.getProperty("usname"));
        lp.putPass(p.getProperty("passwo"));
        lp.clickBtn();

        if (lp.isAuthCodeVisible()) 
        {
            logs.info("Authentication appeared, validating the code");
            lp.Handleauthcode();
        } 
        else 
        {
            logs.info("No auth field appeared, proceeding...");
        }
        
     // HomePage Phase
        
        DeepHomePage dp = new DeepHomePage(driv, waits);
        String welcomeMsg = dp.Welcomemessage();

        if (welcomeMsg.toLowerCase().contains("welcome")) 
        {
        	dp.ClickManageTin();
        	logs.info("Navigated to ManageTin");
        	
        	ManageTinPage mp = new ManageTinPage(driv,waits);
        	mp.clickRemovetin();
        	logs.info("Navigated to RemoveTIN");
        	
        	
        	RemoveExistTIN rexit = new RemoveExistTIN(driv, waits);
        	
        	rexit.putTIN(p.getProperty("RemoveTIN"));
        	
        	rexit.SelectingCheckBox();
        	
        	rexit.clickrmv();
        	
        	String actualmsg = rexit.PassedMessage().toLowerCase();
        	
        	Assert.assertTrue(
        		    rexit.PassedMessage().toLowerCase().contains("successfully") 
        		    || rexit.PassedMessage().toLowerCase().contains("removed"),
        		    "Expected success message but got: " + rexit.PassedMessage()
        		);


        	
        	
        	
        	
        	
        	
        }
          
        
        
	}
	
	
	
	
}
