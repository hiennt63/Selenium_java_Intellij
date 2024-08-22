package WebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_24_Handle_Upload_File {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	String hcmName = "hcm.jpg";
	String hlName = "halong.jpg";
	String hnName = "hanoi.jpg";

	String hcmFilePath = projectPath + "\\uploadFiles\\" + hcmName;
	String hlFilePath = projectPath + "\\uploadFiles\\" + hlName;
	String hnFilePath = projectPath + "\\uploadFiles\\" + hnName;

	@BeforeClass
	public void beforeClass() {
				driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_SingleFile() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		By uploadBy = By.cssSelector("input[name='files[]']");

		driver.findElement(uploadBy).sendKeys(hcmFilePath);
		sleepInSecond(3);
		driver.findElement(uploadBy).sendKeys(hlFilePath);
		sleepInSecond(3);
		driver.findElement(uploadBy).sendKeys(hnFilePath);
		sleepInSecond(3);

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hcmName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hlName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hnName + "']")).isDisplayed());


		List <WebElement> startButtons =  driver.findElements(By.cssSelector("td>button.start"));

		for (int i = 0; i < startButtons.size(); i++){
			startButtons.get(i).click();
			sleepInSecond(3);
		}
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + hcmName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + hlName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + hnName + "']")).isDisplayed());
	}

	@Test
	public void TC_02_MultiFile() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		By uploadBy = By.cssSelector("input[name='files[]']");

		driver.findElement(uploadBy).sendKeys(hcmFilePath + "\n" + hlFilePath + "\n" + hnFilePath);
		sleepInSecond(2);

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hcmName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hlName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hnName + "']")).isDisplayed());


		List <WebElement> startButtons =  driver.findElements(By.cssSelector("td>button.start"));

		for (int i = 0; i < startButtons.size(); i++){
			startButtons.get(i).click();
			sleepInSecond(3);
		}
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + hcmName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + hlName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + hnName + "']")).isDisplayed());
	}

	@Test
	public void TC_03_() {
		
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
