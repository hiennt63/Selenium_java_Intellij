package WebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;

public class Demo {
	WebDriver driver;
	Actions action;
	String osName = System.getProperty("os.name");
	Keys keys;

	@BeforeClass
	public void initialBrowser() {

		driver = new FirefoxDriver();
		// Nó đang giả lập hành vi hover không dùng thiết bị
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		action= new Actions(driver);
		if(osName.startsWith("Windows")){
			keys = Keys.CONTROL;
		} else {
			keys = Keys.COMMAND;
		}
	}

	@Test
	public void TC_01_Click_Hover_Block() throws InterruptedException {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		Assert.assertEquals(allNumber.size(),20);

		action.clickAndHold(allNumber.get(0)) // click và số 1 vả giữ chuột
				.moveToElement(allNumber.get(19)) // di chuột tới số 4
				.release() //Nhả chuột trái ra- kết thúc cho sự kiện clickAndHold
				.perform(); // Thực thi lệnh trên (ko có ko thực thi)
		Thread.sleep(5000);
		List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(allNumberSelected.size(),20);
	}

	@Test
	public void TC_02_CLick_Select_Random() throws InterruptedException {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		Assert.assertEquals(allNumber.size(),20);

		//Nhấn phím ctrl xuống (chưa nhả ra)
		action.keyDown(keys).perform();
		action.click(allNumber.get(0))// click và số 1 vả giữ chuột
	          .click(allNumber.get(3))
		      .click(allNumber.get(9))
		      .click(allNumber.get(11))
		      .click(allNumber.get(19)).pause(Duration.ofSeconds(2))
				.perform();
		// Nhả phím Ctrl
		action.keyUp(keys).perform();
		List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(allNumberSelected.size(),5);

	}

	@Test
	public void TC_03_Double_CLick() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(),"Hello Automation Guys!");
	}

	@Test
	public void TC_04_Right_CLick() throws InterruptedException {





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
