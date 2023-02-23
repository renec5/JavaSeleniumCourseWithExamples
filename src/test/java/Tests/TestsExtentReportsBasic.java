package Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestsExtentReportsBasic {
	
	static WebDriver driver;
	String baseURL;
	ExtentReports HTMLReport;
	static ExtentTest ReportManager;
	
  
  @BeforeMethod
  public void beforeMethod(Method method) {
	  String testName = method.getName();
	  HTMLReport = ReportResult.ReportResult.getReportInstance(testName);
	  ReportManager = HTMLReport.startTest(testName);
	  baseURL = "https://courses.letskodeit.com/login";
	  
		  WebDriverManager.chromedriver().setup();
		  driver = new ChromeDriver();
		  ReportManager.log(LogStatus.INFO, "Driver instance created successfully");
	  
	  
	  
	  
	  driver.manage().window().maximize();
	  ReportManager.log(LogStatus.INFO, "Browser maximized");
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
  }

  @AfterMethod
  public void afterMethod()  {
	  
	  driver.quit();
	  
  }

  @BeforeClass
  public void beforeClass() {
	  
	  
  }

  @AfterClass
  public void afterClass() {
	  HTMLReport.endTest(ReportManager);
	  HTMLReport.flush();
  }
  
  @Test
  public void TestMethod1() throws IOException, InterruptedException {
	  driver.get(baseURL);
	  ReportManager.log(LogStatus.INFO, "Web application opened", baseURL);
	  Log("info", "Web application opened", true);
	  driver.findElement(By.xpath("(//input[@id='email'])[1]")).sendKeys("evilsnake_@hotmail.com");
	  ReportManager.log(LogStatus.INFO, "email sent correctly");
	  driver.findElement(By.xpath("(//input[@id='password'])[1]")).sendKeys("bigboss.0");
	  ReportManager.log(LogStatus.INFO, "Password sent correctly");
	  Log("info", "email and password entered correctly", true);
	  driver.findElement(By.xpath("(//button[@id='login'])[1]")).click();
	  ReportManager.log(LogStatus.INFO, "Login button clicked");
	  WebElement avatar = null;
	  try {
		  avatar = driver.findElement(By.xpath("//button[@id='dropdownMenu1']"));
		  Log("pass", "Login Successfully", true);
	  }catch (Exception e){
		  Log("fail", "Avatar Icon not found " + e.getMessage(), true);
	  }
	  // Thread.sleep(10000);
	  Assert.assertTrue(avatar != null);
  }
  
  
  public static String takeSS() throws IOException {
	  Date date = new Date();
	  String ssName = "Screenshot-" + Long.toString(date.getTime()) + ".png";
	  String directory = System.getProperty("user.dir") + "/Screesnhots/";
	  File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  FileUtils.copyFile(screenshot, new File(directory + ssName));
	  return directory + ssName;
  }
  
  public static void Log(String status, String description, boolean ss) throws IOException {
	  if (ss) {
		  String ssPath = takeSS();
		  String imagePath = ReportManager.addScreenCapture(ssPath);
		  switch (status.toLowerCase()) {
		  case "pass":
			  ReportManager.log(LogStatus.PASS, description, imagePath);
			  break;
		  case "fail":
			  ReportManager.log(LogStatus.FAIL, description, imagePath);
			  break;
		  case "info":
			  ReportManager.log(LogStatus.INFO, description, imagePath);
			  break;
		  }
	  }
	  else {
		  switch (status.toLowerCase()) {
		  case "pass":
			  ReportManager.log(LogStatus.PASS, description);
			  break;
		  case "fail":
			  ReportManager.log(LogStatus.FAIL, description);
			  break;
		  case "info":
			  ReportManager.log(LogStatus.INFO, description);
			  break;
		
		  }
	  }
  }
  
  

}
