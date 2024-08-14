package WebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Topic_14_Action {
	WebDriver driver;
	Actions actions;

	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();
		// Nó đang giả lập hành vi hover không dùng thiết bị
		actions = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_Hover_github() throws InterruptedException {
	driver.get("https://automationfc.github.io/jquery-tooltip/");

	WebElement ageTextbox =  driver.findElement(By.cssSelector("input#age"));

	actions.moveToElement(ageTextbox).perform();
	Thread.sleep(2000);
		Assert.assertEquals(driver.findElement(By.cssSelector("#div.ui-tooltip-content")).getText(),"We ask for your age only for statistical purposes.");
	}



	@Test
	public void TC_02_Hover_Myntra() throws InterruptedException {
		driver.get("https://www.myntra.com/");
		actions.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Kids']"))).perform();
		Thread.sleep(2000);

		// Web ELement click
		driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Home & Bath']")).click();

		// Action click
		actions.click(driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Home & Bath']"))).perform();
		Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(),"Kids Home Bath");
		Thread.sleep(2000);


	}

	@Test
	public void TC_03_Hover_Fahasa() throws InterruptedException {
		driver.get("https://www.fahasa.com/");
		actions.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
		Thread.sleep(2000);
		actions.moveToElement(driver.findElement(By.xpath("//span[text()='FOREIGN BOOKS']"))).perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='fhs_column_stretch']//a[text()='Romance']")).click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//ol[@class='breadcrumb']//strong[text()='Romance']")).isDisplayed());

	}
	@Test
	public void TC_04_Hover_DMX() throws InterruptedException {
		driver.get("https://www.dienmayxanh.com/");
		actions.moveToElement(driver.findElement(By.xpath("//a[text()='Tất cả danh mục']"))).perform();
		Thread.sleep(2000);
		actions.moveToElement(driver.findElement(By.xpath("//div[@class='bar-top-left']//a[text()='Bếp điện']"))).perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='submenu-7']//aside[2]//a[5]")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//link[@href='https://www.dienmayxanh.com/binh-dung-nuoc']")).isEnabled());

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
