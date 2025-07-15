package TestCase;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Page_Object.DeepHomePage;
import Page_Object.LoginPage;
import Page_Object.ManageTinPage;
import TestBase.BaseClass;
import Utilities.MessageReader;

public class TC_006_AddTinNegativeTest extends BaseClass 
{

    LoginPage lp;
    DeepHomePage dp;
    ManageTinPage mtp;

    
    @BeforeClass(alwaysRun = true)
    public void setUp() 
    {
        login();
    }

    
    public void login() 
    {
        lp = new LoginPage(driv, waits);
        lp.putUser(p.getProperty("usname"));
        lp.putPass(p.getProperty("passwo"));
        lp.clickBtn();
        
        
        if (lp.isAuthCodeVisible()) 
        {
            lp.Handleauthcode();
        }

        dp = new DeepHomePage(driv, waits);
        
        if (dp.Welcomemessage().toLowerCase().contains("welcome")) 
        {
            dp.ClickManageTin();
        }

        mtp = new ManageTinPage(driv, waits);
        mtp.ClickHaveRadiobtn();
        mtp.ClickAddTin();
    }

    
    public void logout()
    {
        dp.logout();
        logs.info("Logged out");
    }

    // 
    @Test(priority = 1)
    public void testAllEmptyFields()
    {
    	boolean testPassed = false;
        try 
        {
            mtp.clickAddbtn();

            Assert.assertTrue(mtp.IsAffReqMsgDisp(), "Affiliation message missing");
            Assert.assertTrue(mtp.isTinReqMsgDisp(), "TIN message missing");
            Assert.assertTrue(mtp.isDraftNumReqmsgDis(), "Draft Number message missing");
            Assert.assertTrue(mtp.isDraftAmountReqmsgDis(), "Draft Amount message missing");

            Assert.assertEquals(mtp.getAffReqMsg(), MessageReader.get("affiliation.required"));
            Assert.assertEquals(mtp.getTinReqMsg(), MessageReader.get("tin.required"));
            Assert.assertEquals(mtp.getDraftNumReqMsg(), MessageReader.get("draft.number.required"));
            Assert.assertEquals(mtp.getDraftAmountReq(), MessageReader.get("draft.amount.required"));

            testPassed = true;
            System.out.println(" Test 6.1 Passed");

        } 
        catch (Exception e) 
        {
            Assert.fail("Test 6.1 Failed: " + e.getMessage());
        } 
        finally
        {
            logout(); 
            
            if(testPassed)
            {
            login();  
            }
        }
    }


    @Test(priority = 2)
    public void testOnlyAffiliationSelected()
    {
    	boolean testPassed = false;
        try
        {
            mtp.SelectAffiliatedTin("TIN Owner");
            mtp.clickAddbtn();

            Assert.assertTrue(mtp.isTinReqMsgDisp(), "TIN message missing");
            Assert.assertTrue(mtp.isDraftNumReqmsgDis(), "Draft Number message missing");
            Assert.assertTrue(mtp.isDraftAmountReqmsgDis(), "Draft Amount message missing");

            Assert.assertEquals(mtp.getTinReqMsg(), MessageReader.get("tin.required"));
            Assert.assertEquals(mtp.getDraftNumReqMsg(), MessageReader.get("draft.number.required"));
            Assert.assertEquals(mtp.getDraftAmountReq(), MessageReader.get("draft.amount.required"));

            testPassed = true;
            System.out.println(" Test 6.2 Passed");

        } 
       catch (Exception e)
        {
            Assert.fail("Test 6.2 Failed: " + e.getMessage());
        } 
        finally 
        {
            logout();
            
            if(testPassed)
            {
            login();
            }
        }
    }

    
    @Test(priority = 3)
    public void testOnlyTINEntered() 
    {
    	boolean testPassed = false;
        try 
        {
        	mtp.SelectAffiliatedTin("TIN Owner");
            mtp.putTinId(p.getProperty("TinNumber"));
            mtp.clickAddbtn();

            
            Assert.assertTrue(mtp.isDraftNumReqmsgDis(), "Draft Number message missing");
            Assert.assertTrue(mtp.isDraftAmountReqmsgDis(), "Draft Amount message missing");

            
            Assert.assertEquals(mtp.getDraftNumReqMsg(), MessageReader.get("draft.number.required"));
            Assert.assertEquals(mtp.getDraftAmountReq(), MessageReader.get("draft.amount.required"));

            testPassed = true;
            System.out.println(" Test 6.3 Passed");

        } 
        catch (Exception e) 
        {
            Assert.fail("Test 6.3 Failed: " + e.getMessage());
        } 
        finally
        {
            logout();
            
            if(testPassed)
            {
            login();
            }
        }
    }
}
