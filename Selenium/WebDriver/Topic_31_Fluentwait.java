package WebDriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.function.Function;

public class Topic_31_Fluentwait {

	WebDriver driver;
	FluentWait<WebDriver> fluentDriver;
	FluentWait<WebElement> fluentElement;

   @BeforeClass
	public void beforeClass() 	{

	   driver = new FirefoxDriver();
	   fluentDriver = new FluentWait<WebDriver>(driver);
	}

	@Test
	public void TC_01() {

		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();

		// Cho cho helloworld text hien thi trong vong 10s
		fluentDriver.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(100))
				.ignoring(NoSuchElementException.class);

		// Dieu kien
		/*fluentDriver.until(new Function<WebDriver, Boolean>() {

							   @Override
							   public Boolean apply(WebDriver driver) {
								   return driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed();
							   }
						   }
		);
		*/

		// COndition
		String HelloWorldText = fluentDriver.until(new Function<WebDriver, String>() {
			@Override
			public String apply(WebDriver driver) {
				String text = driver.findElement(By.xpath("//div[@id='finish']/h4")).getText();
				System.out.println("Get text:" + text);
				return text;
			}

		});
		Assert.assertEquals(HelloWorldText, "Hello World!");
	}
	@Test
	public void TC_02(){
	   driver.get("https://automationfc.github.io/fluent-wait/");
	   WebElement countdowntime = driver.findElement(By.cssSelector("div#javascript_countdown_time"));;
	   fluentElement = new FluentWait<WebElement>(countdowntime);
	   fluentElement.withTimeout(Duration.ofSeconds(15))
	  				 .pollingEvery(Duration.ofMillis(100))
			   		.ignoring(NoSuchElementException.class);
	   // Condition
		fluentElement.until(new Function<WebElement, Boolean>() {
			@Override
			public Boolean apply(WebElement webElement) {
				String text = webElement.getText();
				System.out.println(text);
				return text.endsWith("00");
			}
		});
	}

	public WebElement waitAndfindElement(By locator){
	   FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
	   fluentWait.withTimeout(Duration.ofSeconds(30))
			   .pollingEvery(Duration.ofMillis(300))
			   .ignoring(NoSuchElementException.class);
	   return fluentWait.until(new Function<WebDriver, WebElement>() {
		   @Override
		   public WebElement apply(WebDriver webDriver) {
			   return webDriver.findElement(locator);
		   }
	   });

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
