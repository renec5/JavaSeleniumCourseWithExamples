package Tests;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class TestsDataProvider {
	
	WebDriver driver;
	String baseURL;
	
	/*
	 * When @DataProvider is in the same class we only have to declare it 
	 * on the @Test(dataProvider="inputs") but when we have too much data to 
	 * work with we can create this object in a separate class and add all 
	 * data required on it and declare it on @Test(dataProvider="inputs", dataProviderClass=TestData.class)
	 * passing the path to the class that contains the data. 
	 */
	
	/*
	@DataProvider(name="inputs")
	public Object[][] getData(){
		return new Object[][] {
			{"bmw", "m3"},
			{"audi", "a6"},
			{"benz", "c300"}
		};
	}
	*/
	
  
  @BeforeMethod(alwaysRun=true)
  public void beforeMethod() {
	  
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
  
  @Test(dataProvider="inputs", dataProviderClass=TestData.class)
  public void f(String input1, String input2) throws InterruptedException {
	  driver.get(baseURL);
	  System.out.println("Input1: " + input1);
	  System.out.println("Input2: " + input2);
	  Thread.sleep(3000);
  }

}
