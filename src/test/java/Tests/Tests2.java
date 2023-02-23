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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Tests2 {
	
	WebDriver driver;
	String baseURL;
	
  
  @BeforeMethod(alwaysRun=true)
  public void beforeMethod() {
	  
	  //driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
  }

  @AfterMethod(alwaysRun=true)
  public void afterMethod() {
	  driver.quit();
  }

  @BeforeClass(alwaysRun=true)
  @Parameters({"browser", "platform"})
  public void beforeClass(String browser, String platform) {
	  baseURL = "https://www.ulta.com/";
	  if (browser.equalsIgnoreCase("chrome")) {
		  WebDriverManager.chromedriver().setup();
		  driver = new ChromeDriver();
	  } else if (browser.equalsIgnoreCase("firefox")) {
		  System.setProperty("webdriver.gecko.driver", "/Users/rene.cortes/Desktop/UltaBeauty/ultabeauty/geckodriver");
		  driver = new FirefoxDriver();
	  }
	  
	  
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
