package ReportResult;

import java.util.Date;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;

public class ReportResult {
	
	WebDriver driver;
	

	public ReportResult(WebDriver driver) {
		this.driver = driver;
	}
	
	public static ExtentReports getReportInstance(String testName) {
		Date date = new Date();
		ExtentReports HTMLReport;
		HTMLReport = new ExtentReports("/Users/rene.cortes/Desktop/reports/" + testName + "-" + Long.toString(date.getTime()) + ".html", false);
		return HTMLReport;
	}
}
