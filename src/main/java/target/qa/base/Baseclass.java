package target.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Baseclass {

	public static WebDriver driver;
	private static Properties prop;
	private static ExtentSparkReporter htmlReports;
	private static ExtentReports extentReports;
	private static ExtentTest extentTest;
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(Baseclass.class);

	public Baseclass() {
		prop = new Properties();

		try {
			FileInputStream fis = new FileInputStream(
					"D:\\Selenium\\TargetProject\\src\\main\\resource\\config.properties");
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@BeforeTest
	public static ExtentReports reports() {
		htmlReports = new ExtentSparkReporter("Target-Ecommerce");
		extentReports = new ExtentReports();
		extentReports.attachReporter(htmlReports);

		extentReports.setSystemInfo("Author", "Ashutosh");
		extentReports.setSystemInfo("Project", "Target-Ecommerce");
		extentReports.setSystemInfo("APP TYPE", "On CLoud");

		htmlReports.config().setDocumentTitle("Ecommerce-Targets");
		htmlReports.config().setReportName("TargetsEcommerceProject");
		htmlReports.config().setTheme(Theme.STANDARD);
		return extentReports;

	}
	
	@BeforeMethod
	public void Initialized() {
		String BrowserName = prop.getProperty("BrowserName");
		if(BrowserName.equals("chrome")) {
			driver = new ChromeDriver();
		}
		else if(BrowserName.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		else if(BrowserName.equals("edge")) {
			driver = new EdgeDriver();
		}
		else {
			System.err.println("Driver Not found");
			Reporter.log("Driver Not Found");
			log.info("Driver Not Founds");
		}
		driver.manage().window().maximize();
		driver.get(prop.getProperty("URL"));
		driver.manage().deleteAllCookies();
	}
	
	@AfterMethod
	public void resultGet(ITestResult result) {
		if(result.getStatus()==ITestResult.SUCCESS) {
		extentTest=extentReports.createTest(result.getName());
		extentTest.log(Status.PASS,MarkupHelper.createLabel("This test case is passed : "+result.getName(), ExtentColor.RED));
		}
		else if(result.getStatus()==ITestResult.SKIP) {
		extentTest = extentReports.createTest(result.getName());
		extentTest.log(Status.SKIP, MarkupHelper.createLabel("This Test Case is skipped : "+result.getName(), ExtentColor.YELLOW));
		extentTest.skip(result.getThrowable());
		}
		else if(result.getStatus()==ITestResult.FAILURE) {
		extentTest=extentReports.createTest(result.getName());
		extentTest.log(Status.FAIL,MarkupHelper.createLabel("This Test Case is Failed : "+result.getName(), ExtentColor.RED));
		extentTest.fail(result.getThrowable());
		}
		driver.close();
	}
	
	@AfterTest
	public void endReports() {
		extentReports.flush();
	}

}
