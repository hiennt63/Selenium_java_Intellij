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
import java.util.Date;

public class Topic_29_Selenium_Wait_V {
	WebDriver driver;

	WebDriverWait explicitWait; // khai báo chưa khoi tao
   @BeforeClass // Precondition - khởi tạo dữ liệu/ data test/ page class/ variable

	// Khởi tạo 1 explicit Wait có tổng thời gian là 10s - polling là 0.5s mặc định
	public void beforeClass() 	{
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver,Duration.ofSeconds(30));
	}

	@Test
	public void TC_01() {
}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public static String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}
}
