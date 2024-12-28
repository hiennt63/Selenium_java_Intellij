package WebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_26_Selenium_Wait_I {
	WebDriver driver;

	WebDriverWait explicitWait;
	By reconfirmmailTextbox = By.cssSelector("input[name='reg_email_confirmation__']");
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver,Duration.ofSeconds(30));
		driver.get("https://www.facebook.com/");

	}

	@Test
	public void TC_01_Visible() {

		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		sleepInSecond(2);

		driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("nguyenthihien7958@gmail.com");
		sleepInSecond(2);

		// Tai thoi diem nay confirm mail/textbox dang visible/ dispayed
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(reconfirmmailTextbox));
		// Đieu kien 1: Hiển thị element có xuat hien trong cay HTML
		Assert.assertTrue(driver.findElement(reconfirmmailTextbox).isDisplayed());
	}

	@Test
	public void TC_02_Invisible_In_DOM() {

		// Dieu kien 2: ko xuat hien tren UI ma xuat hien trong cay HTML
		driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
		sleepInSecond(3);

		// Tai thoi diem nay confirm mail/textbox dang visible/ dispayed
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmmailTextbox));

		// Kiem tra dk 1 element khong hien thi
		Assert.assertTrue(driver.findElement(reconfirmmailTextbox).isDisplayed());
	}

	@Test
	public void TC_02_InVisible_Not_In_Dom() {

		// Click vao icon dong de dong popup
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		sleepInSecond(3);

		// Điêu kien 3 - Element ko xuất hien tren UI va cung ko co trong cay HTML
		driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
		sleepInSecond(3);

		// Tai dung thoi diem nay/ step nay thi confirm Email textbox dang invisible
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmmailTextbox));

		// Kiem tra 1 element khong hien thi
		// Chay lau - ket qua nay failed
		Assert.assertTrue(driver.findElement(reconfirmmailTextbox).isDisplayed());
	}
	@Test
	public void TC_03_Presence() {

		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		sleepInSecond(2);

		driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("nguyenthihien7958@gmail.com");
		sleepInSecond(2);

		// Đieu kien 1: Hiển thị element có xuat hien trong cay HTML va tren UI
		// Tai dung thoi diem nay / step nay confirm email textbox presence (co trong HTML)
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(reconfirmmailTextbox));

		// Đieu kien 2: Element ko xuat hien tren UI nhưng van co trong HTML
		driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
		
	}

	@Test
	public void TC_04_Staleness() {

		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		sleepInSecond(2);
		// Tai thoi diem nay element co xuat hien va tim element
		WebElement reconfirmmail = driver.findElement(reconfirmmailTextbox);

		// Click vào icon Close de dong popup
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		sleepInSecond(3);

		// Đieu kien 3: Hiển thị element ko xuat hien trong cay HTML va UI
		explicitWait.until(ExpectedConditions.stalenessOf(reconfirmmail));


	}
	public void sleepInSecond(int timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
