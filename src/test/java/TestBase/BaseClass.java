package TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass
{

	
	public  WebDriver driv;
	public WebDriverWait waits;
	public Logger logs;
	public Properties p;

	
	
	@BeforeClass(alwaysRun = true, groups = {"sanity", "master"})
	@Parameters({"os","browser"})
	public void launchpad(@Optional("Windows") String os, @Optional("chrome") String browser) {
		System.out.println("Launchpad Executed");
		System.out.println("Operating System: " + os);  // Add debug line here
	    System.out.println("Browser: " + browser);    // Add debug line here
		try
		{
	        System.out.println("Launching browser... OS: " + os + ", Browser: " + browser);

	        FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
	        p = new Properties();
	        p.load(file);

	        logs = LogManager.getLogger(this.getClass());
	        
	        if(p.getProperty("Execution_Environment").equalsIgnoreCase("remote"))
	        {
	        	DesiredCapabilities dc = new DesiredCapabilities();
	        	//OS setup
	        	if(os.equalsIgnoreCase("Windows"))
	        	{
	        		dc.setPlatform(Platform.WIN11);
	        	}
	        	else if(os.equalsIgnoreCase("linux"))
	        	{
	        		dc.setPlatform(Platform.LINUX);
	        	}
	        	else if(os.equalsIgnoreCase("MAC"))
	        	{
	        		dc.setPlatform(Platform.MAC);
	        	}
	        	else
	        	{
	        		System.out.println("incorrect OS or Not available");
	        		return;
	        	}
	        	//browser
	        	
	        	switch(browser.toLowerCase())
	        	{
	        	case "chrome" : dc.setBrowserName("chrome");break;
	        	case "edge" : dc.setBrowserName("MicrosoftEdge"); break;
	        	default : System.out.println("Incorrect or not available Browser");
	        	}
	        	driv= new RemoteWebDriver(new URL("http://192.168.0.113:4444"),dc);
	        }

	        if(p.getProperty("Execution_Environment").equalsIgnoreCase("local"))
	        {
	       switch (browser.toLowerCase()) {
	            case "chrome":
	            	
	                driv = new ChromeDriver();
	                System.out.println("ChromeDriver setup completed.");
	                break;
	            case "edge":
	            	
	                driv = new EdgeDriver();
	                break;
	            default:
	                System.out.println("Invalid Browser Name"); return;
	        } 
	        
	        }
	        

	        
	        driv.manage().deleteAllCookies();
	        driv.manage().window().maximize();
	        driv.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driv.get(p.getProperty("ApplicationURL"));
	        
		}
	        
	       /* if (driv == null) 
	        {
                System.out.println("❌ WebDriver is null!");
            } 
	        else
	        {
                System.out.println("✅ WebDriver initialized successfully.");
            } */

	     catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	@AfterClass(alwaysRun = true)
	public void dismantle()
	{
		driv.close();
	}

	
	public  String TakefailScreenshot(String tname)
	{
		if (driv == null) 
		{
            System.out.println("⚠️ WebDriver is null");
            return null;
        }
		
		String dateformate = new SimpleDateFormat("yyyyMMdddhhmmss").format(new Date());
		
		TakesScreenshot ts = (TakesScreenshot)driv;
		 File sourcefile = ts.getScreenshotAs(OutputType.FILE);
		 
		 String filepath= System.getProperty( "user.dir")+"\\screenshots\\"+tname+"_"+dateformate+".png";
		 File targetfile= new File(filepath);
		 
		 sourcefile.renameTo(targetfile);
		 
		 return filepath;
		
	}

		
}

