package WebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class Topic_20_Frame_Iframe {
	WebDriver driver;

	@BeforeClass
	public void initialBrowser() {

		driver = new FirefoxDriver();
		//driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Test
	public void TC_01()  {

	}
	@Test
	public void TC_02(){

		driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
		sleepInSeconds(3);

		// Verify image
		driver.findElement(By.cssSelector("div#imageTemplateContainer>img")).click();
		sleepInSeconds(3);

		// iframe Element
		WebElement fromIframe = driver.findElement(By.cssSelector("div#formTemplateContainer>iframe"));
		Assert.assertTrue(fromIframe.isDisplayed());

		// Switch to iframe
		driver.switchTo().frame(fromIframe);

		// Ko tìm thấy element (element nằm trong iframe)
		new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Junior");
		sleepInSeconds(3);

		// Switch ra lại A
		driver.switchTo().defaultContent();

		//Thao tác vs 1 element bên ngoài ijrame (Trang A)
		driver.findElement(By.cssSelector("nav.header--desktop-floater a.menu-item-login")).click();

		// Verify login
		driver.findElement(By.cssSelector("button#login")).click();
		sleepInSeconds(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(),"Username and password are both required.");
	}

	@Test
	public void TC_03 (){
		driver.get("https://kynaenglish.vn/");
		sleepInSeconds(15);
		driver.switchTo().frame(driver.findElement(By.cssSelector(" div.footer_fanpage__4JklY div.fb-page>span>iframe")));

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='pluginConnectButton']/parent::div/parent::div/parent::div")).getText(),"48K followers");
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
