package Tests;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;

public class TestsITestResult {
	
	static WebDriver driver;
	String baseURL;
	
  
  @BeforeMethod(alwaysRun=true)
  @Parameters({"browser", "platform"})
  public void beforeMethod(String browser, String platform) {
	  baseURL = "https://courses.letskodeit.com/login";
	  if (browser.equalsIgnoreCase("chrome")) {
		  WebDriverManager.chromedriver().setup();
		  driver = new ChromeDriver();
	  } else if (browser.equalsIgnoreCase("firefox")) {
		  System.setProperty("webdriver.gecko.driver", "/Users/rene.cortes/Desktop/UltaBeauty/ultabeauty/geckodriver");
		  driver = new FirefoxDriver();
	  }
	  
	  
	  System.out.println("Browser: " + browser);
	  System.out.println("Platform: " + platform);
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
  }

  @AfterMethod(alwaysRun=true)
  public void afterMethod(ITestResult testResult) throws IOException {
	  if (testResult.getStatus() == ITestResult.FAILURE) {
		  System.out.println("TestCase: {" + testResult.getMethod().getMethodName() + " } Failed");
		  takeSS();
	  }
	  if (testResult.getStatus() == ITestResult.SUCCESS) {
		  System.out.println("TestCase: { " + testResult.getMethod().getMethodName() +  " } Passed");
	  }
	  driver.quit();
	  
  }

  @BeforeClass(alwaysRun=true)
  public void beforeClass() {
	  
  }

  @AfterClass(alwaysRun=true)
  public void afterClass() {
	  
  }
  
  @Test(groups = {"smoke", "sanity", "regression"})
  public void TestMethod1() {
	  driver.get(baseURL);
	  driver.findElement(By.xpath("(//input[@id='email'])[1]")).sendKeys("test@gamil.com");
	  driver.findElement(By.xpath("(//input[@id='password'])[1]")).sendKeys("test@gamil.com");
	  driver.findElement(By.xpath("(//button[@id='Login'])[1]")).click();
	  //Thread.sleep(3000);
	  System.out.println("Running TestMethod1");
	  Assert.assertTrue(true);
  }
  
  @Test(groups = {"smoke", "sanity", "regression"})
  public void TestMethod2() {
	  //driver.get(baseURL);
	  //Thread.sleep(3000);
	  System.out.println("Running TestMethod2");
	  Assert.assertTrue(false);
  }
  
  public static String takeSS() throws IOException {
	  Date date = new Date();
	  String ssName = "Screenshot-" + Long.toString(date.getTime()) + ".png";
	  String directory = System.getProperty("user.dir") + "/Screesnhots/";
	  File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  FileUtils.copyFile(screenshot, new File(directory + ssName));
	  return directory + ssName;
  }

}
