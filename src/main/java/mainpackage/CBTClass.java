package mainpackage;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import dataprovider.CSVDataProvider;
import dataprovider.ExcelDataProvider;
import propertyReader.ConfigFileReader;



public class CBTClass {
	public static WebDriver driver=null;
	//private Logger log = Logger.getLogger(MainClass.class);
	public static ExtentReports extent;
	public static ExtentTest logger;
	@Parameters("browser")
	@BeforeTest
	public void launch(String browser)
	{
		ConfigFileReader config=new ConfigFileReader();
		switch(browser) {
		case "chrome":
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(config.getApplicationUrl());
		extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/STMExtentReport.html", true);
		//logger = extent.createTest(getClass().getSimpleName());
		break;
		case "edge":
			System.setProperty("webdriver.edge.driver",System.getProperty("user.dir")+"\\src\\main\\resources\\Drivers\\msedgedriver.exe");
			driver=new EdgeDriver();
			driver.manage().window().maximize();
			driver.get(config.getApplicationUrl());
			extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/STMExtentReport.html", true);
			//logger = extent.createTest(getClass().getSimpleName());
			break;
			
		case "firefox":
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\src\\main\\resources\\Drivers\\geckodriver.exe");
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(config.getApplicationUrl());
			extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/STMExtentReport.html", true);
			//logger = extent.createTest(getClass().getSimpleName());
			break;
		}
	}
	@AfterMethod
	public void getResult(ITestResult result) throws Exception{
		
		if(result.getStatus() == ITestResult.FAILURE){
			
			logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
			String screenshotPath = MainClass.getScreenshot(driver, result.getName());
			//To add it in the extent report 
			logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));
		}else if(result.getStatus() == ITestResult.SKIP){
			logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		}else if(result.getStatus() == ITestResult.SUCCESS){
			logger.log(LogStatus.PASS, "Test Case Passed is "+result.getName());
			//logger.log(LogStatus.PASS, "Test Case Passed is "+result.getThrowable());
			String screenshotPath = MainClass.getScreenshot(driver, result.getName());
			//To add it in the extent report 
			logger.log(LogStatus.PASS, logger.addScreenCapture(screenshotPath));
		}
		extent.endTest(logger);
	}
	@AfterTest
	public void driverclose()
	{
	driver.close();
	 extent.flush();
     extent.close();
	}
	@BeforeSuite
	public void beforeSuite()
	{
	System.out.println("/***********Suite started******************/");
	extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/STMExtentReport.html", true);
	//logger = extent.startReporter(null, null)
	}

	@AfterSuite
	public void afterSuite()
	{
	System.out.println("/****************Suite Finished*************/");

	}
	
	public Object[][] getExcelData(String excelName, String sheetName){
		String excelLocation = System.getProperty("user.dir")+"/src/test/resources/"+excelName;
		//log.info("excel location "+excelLocation);
		ExcelDataProvider excelHelper = new ExcelDataProvider();
		Object[][] data = excelHelper.getExcelData(excelLocation, sheetName);
		return data;
	}
	
	 public static Object[][] csvData() throws IOException 
	    { 
	        String filename =System.getProperty("user.dir")+"/src/test/resources/"+"TestDataCSV.csv";
	        CSVDataProvider csvdata=new CSVDataProvider();
	                 return csvdata.getSearchData(filename);//Get the test data of the CSV file 
	    }

	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
                //after execution, you could see a folder "FailedTestsScreenshots" under src folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

}
