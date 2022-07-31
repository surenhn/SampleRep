package pageobjectpackage;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import mainpackage.MainClass;
import reusablepackage.ReusableFunction;

public class CanadaDriveObjects {
	WebDriver driver;
	ExtentTest logger;
	WebDriverWait wait;
	
	ReusableFunction ReusFun = new ReusableFunction(driver, logger);
	@FindBy(xpath = "(//div[contains(@class,'dropdown__province-menu cursor-pointer')])[1]")
	WebElement btn_province;

	@FindBy(xpath = "(//li)[9]")
	WebElement list_Ontario;

	@FindBy(xpath = "//span[text()='Make & Model']")
	WebElement menu_make_model;

	@FindBy(xpath = "//span[text()='Ram']")
	WebElement menu_RAM;

	@FindBy(xpath = "//span[text()='1500']")
	WebElement menu_1500;

	
	
	@FindBy(xpath = "//div[@class='v-select__selections']/div[text()='Featured']")
	WebElement arw_sort_by;

	@FindBy(xpath = "//div[text()='Price: Low to High']")
	WebElement list_price_low_high;

	@FindBy(xpath = "(//span[@class='v-icon notranslate fav-icon__heart fav-icon__heart--gray float-right theme--light'])[1]")
	WebElement heart_favourite_1;

	@FindBy(xpath = "(//span[@class='v-icon notranslate fav-icon__heart fav-icon__heart--gray float-right theme--light'])[2]")
	WebElement heart_favourite_2;

	@FindBy(xpath = "(//span[@class='v-icon notranslate fav-icon__heart fav-icon__heart--gray float-right theme--light'])[3]")
	WebElement heart_favourite_3;

	@FindBy(xpath = "(//div[@class='vehicle-card__title font-weight-medium text-body-1 vehicle-title-row text-h6-card'])[2]")
	WebElement available_vehicle;

	@FindBy(xpath = "//div[@class='desktop-start-purchase-column d-block py-0 pl-0 col col-auto']")
	WebElement btn_start_purchase;

	@FindBy(xpath = "//div[@class='vehicle-price-cards__item d-flex align-center cursor-pointer v-card v-card--link v-sheet v-sheet--outlined theme--light elevation-5 rounded-md']")
	WebElement img_calculate_Delivery;

	@FindBy(xpath = "//input[@id='street_address']")
	WebElement inp_Delivery_address;

	@FindBy(xpath = "//button[@class='d-block mt-7 mx-auto text-none save-button v-btn v-btn--is-elevated v-btn--has-bg theme--light v-size--x-large gradient--blue']")
	WebElement btn_Save_Confirm;

	@FindBy(xpath = "(//div[@class='v-list-item__content']/div[@class='v-list-item__title d-flex align-center justify-space-between'])[3]")
	WebElement inp_warranty_plan;

	@FindBy(xpath = "(//div[@class='d-flex justify-center align-center col col-3'])[3]")
	WebElement rdo_48_months;

	@FindBy(xpath = "//span[@role='status']")
	WebElement heart_3;
	
	public CanadaDriveObjects(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,20);
		
	}
	public CanadaDriveObjects(String CBTC,WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,20);
		
	}
/***
 * Flow to Select Ontario Province, Model as RAM
 */
	public void sampleFlow(String Province, String MakeModel,String RamOption,String WarrantyMonth) throws Exception {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//Title check
		ReusFun.getPageTitle(driver,"Used Cars for Sale in BC | Canada Drives");
		wait.until(ExpectedConditions.visibilityOf(btn_province));
		btn_province.click();
		ReusFun.selectFromMultipleElements(driver,"(//li[@class='province-dropdown__list__item cursor-pointer'])",Province,"Province");
	//	String screenshotPath = MainClass.getScreenshot(driver,"Sample Flow screenshots");
		logger.log(LogStatus.PASS, "ON Province",logger.addScreenCapture(MainClass.getScreenshot(driver,"ON Province")));
		
		//After changing province , title check
		/*wait.until(ExpectedConditions.visibilityOf(btn_province));
		ReusFun.getPageTitle(driver,"Used Cars for Sale in ON | Canada Drives");
		
		//Select a model & RAm option
		wait.until(ExpectedConditions.visibilityOf(menu_make_model));
		menu_make_model.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");
		ReusFun.selectFromMultipleElements(driver,"(//span)",MakeModel,"Make Model");
		
		ReusFun.selectFromMultipleElements(driver,"(//span)",RamOption,"Ram Option");
	//	logger.log(LogStatus.PASS, "Ram:1500",logger.addScreenCapture(MainClass.getScreenshot(driver,"Ram:1500")));
		ReusFun.selectFromMultipleElements(driver,"(//span)","Ram: 1500", "Ram:1500");
		logger.log(LogStatus.PASS, "Ram:1500",logger.addScreenCapture(MainClass.getScreenshot(driver,"1500")));
		wait.until(ExpectedConditions.visibilityOf(arw_sort_by));
		ReusFun.javaScriptEx(driver,arw_sort_by);
		list_price_low_high.click();
		
		//Select 3 favourite Cars
		wait.until(ExpectedConditions.visibilityOf(heart_favourite_1));
		heart_favourite_1.click();
		wait.until(ExpectedConditions.visibilityOf(heart_favourite_2));
		heart_favourite_2.click();
		wait.until(ExpectedConditions.visibilityOf(heart_favourite_3));
		heart_favourite_3.click();
		
		wait.until(ExpectedConditions.visibilityOf(heart_3));
		String heartcount=heart_3.getText();
		System.out.println("Heart count " +heartcount);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(heartcount, "3", "Actual heart is not the same as expected");
		
		Thread.sleep(4000);
		logger.log(LogStatus.PASS, "3 Favourites",logger.addScreenCapture(MainClass.getScreenshot(driver,"3 Favourites")));
		
		//Select availale vehicle
		wait.until(ExpectedConditions.elementToBeClickable(available_vehicle));
		//available_vehicle.click();
		ReusFun.javaScriptEx(driver,available_vehicle);
		wait.until(ExpectedConditions.visibilityOf(btn_start_purchase));
		btn_start_purchase.click();
		wait.until(ExpectedConditions.elementToBeClickable(img_calculate_Delivery));
		
		//Delivery address
		img_calculate_Delivery.click();
		wait.until(ExpectedConditions.elementToBeClickable(inp_Delivery_address));
		inp_Delivery_address.click();
		
		Thread.sleep(5000);
		
		 Actions builder = new Actions(driver);
		builder.moveToElement(inp_Delivery_address).sendKeys("15 Asquith Ave, Toronto ON").sendKeys(Keys.DOWN).sendKeys(Keys.DOWN).sendKeys(Keys.ENTER).sendKeys(Keys.BACK_SPACE).build().perform();
		
		inp_Delivery_address.sendKeys(Keys.DOWN);
		Thread.sleep(1000);
		inp_Delivery_address.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		logger.log(LogStatus.PASS, "Delivery Address",logger.addScreenCapture(MainClass.getScreenshot(driver,"Delivery Address")));
		//wait.until(ExpectedConditions.elementToBeClickable(btn_Save_Confirm));
		//ReusFun.javaScriptEx(driver,btn_Save_Confirm);
		btn_Save_Confirm.click();
		wait.until(ExpectedConditions.visibilityOf(inp_warranty_plan));
		ReusFun.selectFromMultipleElements(driver,"(//span)","Required" ,"Required");
		
		//wait.until(ExpectedConditions.visibilityOf(rdo_48_months));
		//ReusFun.javaScriptEx(driver,rdo_48_months);
		String premium=WarrantyMonth+" | ";
		
		ReusFun.selectFromMultipleElements(driver,"(//div)",premium, "48 months");
		logger.log(LogStatus.PASS, "48 months",logger.addScreenCapture(MainClass.getScreenshot(driver,"48 months"))); */
	}

	
	}


