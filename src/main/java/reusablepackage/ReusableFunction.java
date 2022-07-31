package reusablepackage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;

public class ReusableFunction {
	WebDriver driver;
	ExtentTest logger;
	
	public ReusableFunction(WebDriver driver,ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		PageFactory.initElements(driver, this);
		}

	
	public void SELECTBYVISIBLETEXT1(String selectname,String visibleText)
	{
		try {
			WebElement selectElement=driver.findElement(By.xpath(selectname));
			WebDriverWait wait = new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(selectname)));
		Select selElement=new Select(selectElement);
		selElement.selectByVisibleText(visibleText);
		}
		catch(Exception e)
		{
			System.out.println("Select element not found - SELECTBYVISIBLETEXT");
		}
	}
	
	
	public void SELECTBYVISIBLETEXT(WebElement element,String visibleText)
	{
		try {
			//WebDriverWait wait = new WebDriverWait(driver,30);
			//wait.until(ExpectedConditions.visibilityOf(element));
		Select selElement=new Select(element);
		selElement.selectByVisibleText(visibleText);
		}
		catch(Exception e)
		{
			System.out.println("Select element not found - SELECTBYVISIBLETEXT");
		}
	}
	public void SELECTBYVALUE(WebElement element,String value)
	{
		try {
			//WebDriverWait wait = new WebDriverWait(driver,30);
			//wait.until(ExpectedConditions.visibilityOf(element));
		Select selElement=new Select(element);
		selElement.selectByValue(value);
		}
		catch(Exception e)
		{
			
			e.printStackTrace();			//System.out.println("Select element not found - SELECTBYVALUE");
		}
	}

	
	public void SENDKEYS(WebElement element,String sendText)
	{
		try {
			element.click();
			element.sendKeys(sendText);
		}
		catch(Exception e)
		{
			System.out.println("Element not found - SendKeys");
		}
	}
	public void ISDISPLAYED(WebElement element)
	{
		try {
			if(element.isDisplayed())
			System.out.println("Element displayed - ISDISPLAYED");
		}
		catch(Exception e)
		{
			System.out.println("Element not found - ISDISPLAYED");
		}
	}
	
	public void javaScriptEx(WebDriver driver,WebElement element) {
		try {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("window.scrollBy(0,350)", "");
		js.executeScript("arguments[0].scrollIntoView();", element);
		js.executeScript("arguments[0].click();", element);
		}
		catch(Exception e)
		{
			System.out.println("Element not found - javaScriptEx");
		}
	}
	
	public void selectFromMultipleElements(WebDriver driver,String xpath,String value,String elementname)
	{
		try {
		List<WebElement> makemodel = driver.findElements(By.xpath(xpath));
		//System.out.println("Number of elements:" + makemodel.size());

		for (int i = 1; i < makemodel.size(); i++) {
			// System.out.println("(//li[@class='province-dropdown__list__item
			// cursor-pointer'])["+i+"]");
			String element = driver.findElement(By.xpath(xpath+"[" + i + "]")).getText();
			//System.out.println(element);
			
			if (element.contains(value)) {
				WebElement x = driver.findElement(By.xpath(xpath+"[" + i + "]"));
				if(x.isDisplayed())
				x.click();
				break;
			}
		}
		}
		catch(Exception e)
		{
			System.out.println("Element not found - selectFromMultipleElements "+elementname);
		}
	}
	
	public void getPageTitle(WebDriver driver,String pageValue)
	{
		try {
		String pageTitle=driver.getTitle();
		Assert.assertEquals(pageTitle, pageValue);
		}
		catch(Exception e)
		{
			System.out.println("Page not found - getPageTitle");
		}
	}
}

