package WebDriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class Topic_18_Popup_II {
	WebDriver driver;
	Actions action;
	String osName = System.getProperty("os.name");
	Keys keys;
	JavascriptExecutor jsExecutor;
	String projectPath;

	@BeforeClass
	public void initialBrowser() {

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


	}

	@Test
	public void TC_01_Random_In_DOM()  {

		driver.get("https://www.javacodegeeks.com/");
		By newsletterPopup = By.cssSelector("div.lepopup-popup-comtainer>div:not([stle^='display:none'])");

		// Nếu hiển thị thì nhảy vào close nó đi
		// Luôn chạy được vì element luôn có trong html/ DOM
		if (driver.findElements(newsletterPopup).size()>0 && driver.findElements(newsletterPopup).get(0).isDisplayed()){
			System.out.println("Popup hiển thị");
			driver.findElement(By.cssSelector("div.lepopup-popup-comtainer>div:not([stle^='display:none']) div.lepopup-element-html-content>a")).click();
			sleepInSeconds(3);
			}
		else { // Nếu ko hiển thị qua step tiếp theo (Search dữ liệu)
			System.out.println("popup ko hiển thị");
		}
		//Nhập vào field search dữ liêệu
		driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");
		//Click Search
		driver.findElement(By.cssSelector("button#Search-submit")).click();
		sleepInSeconds(3);
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Agile Testing Explained']")).isDisplayed());
	}
	@Test
	public void TC_02_Random_In_DOM(){
		driver.get("https://vnk.edu.vn/");
		sleepInSeconds(10);

		Assert.assertTrue(driver.findElement(By.cssSelector("div#popmake-38634")).isDisplayed());
		driver.findElement(By.cssSelector("button.pum-close.popmake-close")).click();
		sleepInSeconds(3);
		Assert.assertFalse(driver.findElement(By.cssSelector("div#popmake-38634")).isDisplayed());

	}
	@Test
	public void TC_03_Fixed_Popup_Not_In_DOM() {

	}
	public WebElement findElement(By locator)
	{
		// néu popup xuất hiện thì close
		if(driver.findElement(By.cssSelector("div.tve-leads-conversion-object")).isDisplayed()){
			driver.findElement(By.cssSelector("div.tve_ea_thrive_leads_form_close")).click();
			sleepInSeconds(3);
		}
		return driver.findElement(locator);
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSeconds(long timeInSecond) {
		try
		{
			sleep(timeInSecond*1000);
		}
		catch (InterruptedException e)
		{
			throw new RuntimeException(e);
		}
	}
}
