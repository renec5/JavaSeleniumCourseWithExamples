package Tests;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class Tests {
	
	WebDriver driver;
	String baseURL;
	
  
  @BeforeMethod(alwaysRun=true)
  public void beforeMethod() {
	  driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
  }

  @AfterMethod(alwaysRun=true)
  public void afterMethod() {
	  driver.quit();
  }

  @BeforeClass(alwaysRun=true)
  @Parameters({"browser", "platform"})
  public void beforeClass(String browser, String platform) {
	  WebDriverManager.chromedriver().setup();
	  baseURL = "https://www.ulta.com/";
	  System.out.println("Browser: " + browser);
	  System.out.println("Platform: " + platform);
  }

  @AfterClass(alwaysRun=true)
  public void afterClass() {
  }
  
  @Test(groups = {"smoke", "sanity", "regression"})
  public void f() throws InterruptedException {
	  driver.get(baseURL);
	  Thread.sleep(3000);
  }

}
