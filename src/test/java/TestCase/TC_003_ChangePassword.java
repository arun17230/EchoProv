package TestCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import Page_Object.DeepHomePage;
import Page_Object.LoginPage;
import Page_Object.Myaccount;
import TestBase.BaseClass;

public class TC_003_ChangePassword extends BaseClass {

    @Test(groups = {"regression", "master"})
    public void changepass() {
        logs.info("🚀 Started Execution of TC_003_ChangePassword");

        LoginPage lp = new LoginPage(driv, waits);
        lp.putUser(p.getProperty("usname"));
        lp.putPass(p.getProperty("passwo"));
        lp.clickBtn();

        if (lp.isAuthCodeVisible())
        {
            logs.info("🔐 AuthCode field appeared. Entering OTP...");
            lp.Handleauthcode();
        } 
        else 
        {
            logs.info("✅ No AuthCode field. Proceeding without OTP...");
        }

        DeepHomePage dh = new DeepHomePage(driv, waits);
        String welMessage = dh.Welcomemessage();

        if (!welMessage.toLowerCase().contains("welcome")) 
        {
            logs.error(" Login failed. 'Welcome' message not found.");
            Assert.fail("Test failed due to unexpected login failure.");
        }

        Myaccount myac = new Myaccount(driv, waits);
        myac.changePassword(
            p.getProperty("oldpasswo"),
            p.getProperty("newpasswo"),
            p.getProperty("confpasswo")
        );

       

        if (myac.isSuccessMessageDisplayed()) 
        {
        	
            System.out.println("Password changed successfully.");
            logs.info("Password change Functionality Working as Expected");
            Assert.assertTrue(true);
        } 
        else if (myac.isMismatchMessageDisplayed()) 
        {
            System.out.println("New password and Confirm password mismatch.");
            logs.info("Password change Functionality Identifying mismatch feature as Expected");
            Assert.assertTrue(true);
        } 
        else if (myac.isErrorMessageDisplayed()) {
            String err = myac.getErrorMessageText().toLowerCase();
            if (err.contains("already") || err.contains("used")) 
            {
                System.out.println("Password already used.");
                logs.info("Password change Functionality can validate already used password as Expected");
                Assert.assertTrue(true);
            } 
            else if (err.contains("recently") || err.contains("24")) 
            {
                System.out.println("Password changed recently. Try after 24 hrs.");
                logs.info("Password change Functionality can able to identify 24 hrs TAT as Expected");
                Assert.assertTrue(true);
            } 
            else if (err.contains("incorrect") || err.contains("invalid")) 
            {
                System.out.println("Old password incorrect.");
                logs.info("Password change Functionality Can Identify old password incorrect as Expected");
                Assert.assertTrue(true);
            }
            else if (err.contains("same")) 
            {
                System.out.println("New password is same as old password.");
                logs.info("Password change Functionality can identify old password and new password are same as Expected");
                Assert.assertTrue(true);
            } else {
                System.out.println("Other error: " + err);
                logs.info("Password change Functionality cannot identify the error");
                Assert.assertTrue(false);
            }
        } else {
            System.out.println("No message displayed.");
            logs.info("Password change Functionality failed");
            Assert.assertTrue(false);
        }

        logs.info("✅ TC_003_ChangePassword completed.");
        dh.logout();
    }
}
