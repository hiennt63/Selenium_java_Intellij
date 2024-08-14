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

public class Topic_17_Popup_I {
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
	public void TC_01_Fixed_Popup_In_DOM_01() throws InterruptedException {
		driver.get("https://ngoaingu24h.vn/");
		driver.findElement(By.cssSelector("button.login_.icon-before")).click();
		Thread.sleep(3000);

		By loginPopup = By.cssSelector("div[id='modal-login-v1'][style]>div");
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

		driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input#account-input")).sendKeys( "automationfc");
		driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input#password-input")).sendKeys( "automationfc");
		driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.btn-login-v1")).click();
		Thread.sleep(3000);

		Assert.assertEquals(driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] div.row.error-login-panel")).getText(),"Tài khoản không tồn tại!");
		Thread.sleep(3000);

		driver.findElement(By.cssSelector("div[id='modal-login-v1'][style]>div button.close")).click();
		Thread.sleep(3000);

		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());

	}
	@Test
	public void TC_02_Fixed_Popup_In_DOM_02(){
		driver.get("https://skills.kynaenglish.vn/dang-nhap");

		//By loginPopup = By.cssSelector("a.login-btn");
		By loginPopup = By.cssSelector(" div#k-popup-account-login-mb div.modal-content");
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

		//System.out.println("Popup hiển thị = " + driver.findElement(By.cssSelector(" div#k-popup-account-login-mb div.modal-content")).isDisplayed());



		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automation@gmail.com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleepInSeconds(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(),"Sai tên đăng nhập hoặc mật khẩu");
		sleepInSeconds(3);

		//driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
		//sleepInSeconds(3);

		//Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
	}
	@Test
	public void TC_03_Fixed_Popup_Not_In_DOM() {
		driver.get("https://tiki.vn/");

		driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
		sleepInSeconds(2);

		Assert.assertTrue(driver.findElement(By.cssSelector("div.ReactModal__Content")).isDisplayed());
		sleepInSeconds(2);

		driver.findElement(By.cssSelector("img.styles__StyledImg-sc-p9s3t3-0.hbqSye")).click();
		driver.findElement(By.cssSelector("p.login-with-email")).click();
		sleepInSeconds(2);

		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//input[@type='email']/parent::div/following-sibling::span[1]")).getText(),"Email không được để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@type='email']/parent::div/following-sibling::span[2]")).getText(),"Mật khẩu không được để trống");
		sleepInSeconds(2);

		driver.findElement(By.cssSelector("img.close-img")).click();
		sleepInSeconds(2);

		//popup đóng lại html không còn trong DOM nữa

		//Assert.assertFalse(driver.findElement(By.cssSelector("div.ReactModal__Content")).isDisplayed());
		Assert.assertEquals(driver.findElements(By.cssSelector("div.ReactModal__Content")).size(), 0);

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
