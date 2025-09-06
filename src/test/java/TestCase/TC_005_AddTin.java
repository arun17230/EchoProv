package TestCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import Page_Object.DeepHomePage;
import Page_Object.LoginPage;
import Page_Object.ManageTinPage;
import TestBase.BaseClass;

public class TC_005_AddTin extends BaseClass {

    @Test (groups= {"sanity","master"})
    public void TinAddition() 
    {
        System.out.println("Started Executing TestCase");
        logs.info("Started TC_005_AddTin");

        // Login Phase
        LoginPage lp = new LoginPage(driv, waits);
        logs.info("LoginPage initialized");
        lp.putUser(p.getProperty("usname"));
        lp.putPass(p.getProperty("passwo"));
        lp.clickBtn();

        if (lp.isAuthCodeVisible()) {
            logs.info("Authentication appeared, validating the code");
            lp.Handleauthcode();
        } else {
            logs.info("No auth field appeared, proceeding...");
        }

        // HomePage Phase
        DeepHomePage dp = new DeepHomePage(driv, waits);
        String welcomeMsg = dp.Welcomemessage();

        if (welcomeMsg.toLowerCase().contains("welcome")) 
        {
            dp.ClickManageTin();
            logs.info("Navigated to ManageTin");

            // ManageTin Phase
            ManageTinPage manTin = new ManageTinPage(driv, waits);
            manTin.ClickHaveRadiobtn();
            manTin.ClickTinAffDropdown();
            manTin.SelectAffiliatedTin("TIN Owner");

            // Fill Tin Details
            manTin.putTinId(p.getProperty("TinNumber"));
            manTin.putDraftNumber(p.getProperty("DraftNumber"));
            manTin.putDraftAmount(p.getProperty("DraftAmount"));

            // Submit and Validate
            manTin.clickAddbtn();
            String successMsg = manTin.DisplaySuccessmessage();
            System.out.println(successMsg);

            if (successMsg.toLowerCase().contains("successfully.")) 
            {
                logs.info("TIN addition successful, test passed");
                dp.logout();
                logs.info("Logged Out successfully");
                Assert.assertTrue(true);
            } 
            else 
            {
                logs.error("TIN addition failed, success message not found");
                dp.logout();
                logs.info("Logged Out successfully");
                Assert.fail("TIN addition unsuccessful or success message not found.");
            }

           
        } 
        else 
        {
        	dp.logout();
            logs.info("Logged out successfully");
            logs.error("Welcome message not found. Login might have failed.");
            Assert.fail("Login failed, welcome message not detected.");
        }
    }
}
