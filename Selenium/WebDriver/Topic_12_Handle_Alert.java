package WebDriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;



public class Topic_12_Handle_Alert {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectLocation = System.getProperty("user.dir");
	By resultText = By.cssSelector("p#result");

	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);


	}
	@Test
	public void TC_01_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSeconds(2);

		//Alert alert = driver.switchTo().alert();
		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();

		Assert.assertEquals(driver.findElement(resultText).getText(),"You clicked an alert successfully");

	}
@Test
	public void TC_02_Alert_confirm() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals(alert.getText(),"I am a JS Confirm");
	    sleepInSeconds(2);
		alert.dismiss();
		Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked: Cancel");

	}

   @Test
	public void TC_03_Alert_Prompt() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals(alert.getText(),"I am a JS prompt");
		sleepInSeconds(2);

		String text = "Automation Testing";
		alert.sendKeys(text);
		alert.accept();
		Assert.assertEquals(driver.findElement(resultText).getText(), "You entered: " + text);

	}

	@Test
	public void TC_04_Authentication_Alert() throws IOException {
		// Cách 1: Truyền user/ password
		//driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
		//driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed();

		// Cách 2: Chạy trên window (AutoIT)
		// Thực thi đoạn code autoIT

		Runtime.getRuntime().exec(new String[] {projectLocation + "\\AutoIT\\authen_firefox.exe", "admin", "admin"});
		driver.get("https://the-internet.herokuapp.com/basic_auth");
		sleepInSeconds(5);

		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

	}
	public void afterClass()
	{
		driver.quit();
	}


	public void sleepInSeconds(long timeInSecond) {
		try
		{
			Thread.sleep(timeInSecond*1000);
		}
		catch (InterruptedException e)
		{
			throw new RuntimeException(e);
		}
	}

}


