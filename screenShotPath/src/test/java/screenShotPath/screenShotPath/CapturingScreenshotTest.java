package screenShotPath.screenShotPath;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.relevantcodes.extentreports.*;

import utilities.GetScreenShot;





public class CapturingScreenshotTest {
	 ExtentReports extent;
	    ExtentTest test;
	    WebDriver driver;
	     
	    @BeforeTest
	    public void init()
	    {
	    	//extent = new ExtentReports(System.getProperty("user.dir") + "/test-Rep/ExtentScreenshot.html", true);
	    	//extent = new ExtentReports(System.getProperty("user.dir") + "/test-        output/ExtentScreenshot.html", true);
	    	extent = new ExtentReports("E:\\Lasantha\\Auto\\Jenkins\\Test\\Rep\\Result.html");
	    }
	     
	    @Test
	    public void captureScreenshot()
	    {
	    	
	    	test = extent.startTest("captureScreenshot");
	    	test.log(LogStatus.INFO, "Starting the Report");
	    	test.assignCategory("Smoke");
	    	
	        //System.setProperty("webdriver.chrome.driver", "E:\\Lasantha\\Auto\\Drivers\\Chrome\\chromedriver_win32\\chromedriver.exe");   
			//driver = new ChromeDriver();
	        System.setProperty("phantomjs.binary.path","E:/Lasantha/Auto/Drivers/phantomjs-2.1/bin/phantomjs.exe");
	        driver = new PhantomJSDriver();
	        test.log(LogStatus.INFO, "Starting the PJS Browser");
	        driver.get("http://www.Google.com");
	        test.log(LogStatus.INFO, "Navigating to Google");
	        String title = driver.getTitle();
	        Assert.assertEquals("Google", title);
	        test.log(LogStatus.PASS, "Test Passed");
	    }
	    
	    @Test
	    public void sampletest()
	    {
	    	test = extent.startTest("Sample test");
	    	test.log(LogStatus.INFO, "Getting the data");
	    
	    	int a = 8;
	    	int b = 5;
	    	
	    	Assert.assertEquals(13, a+b);
	    	test.log(LogStatus.PASS, "Test passed");
	    	
	    }
	     
	    @AfterMethod
	    public void getResult(ITestResult result) throws IOException
	    {
	        if(result.getStatus() == ITestResult.FAILURE)
	        {
	            String screenShotPath = GetScreenShot.capture(driver, "screenShotName");
	           // test.log(LogStatus.FAIL, result.getThrowable());
	            test.log(LogStatus.FAIL, "Snapshot below: " + test.addScreenCapture(screenShotPath));
	        }
	        extent.endTest(test);
	    }
	     
	         
	    @AfterTest
	    public void endreport()
	    {
	        driver.close();
	        extent.flush();
	        extent.close();
	        System.out.println("done");
	    }

}
