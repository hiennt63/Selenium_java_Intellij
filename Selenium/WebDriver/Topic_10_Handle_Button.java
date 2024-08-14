package WebDriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class Topic_10_Handle_Button {
	WebDriver driver;

	// Tường minh: trạng thái cụ thể của element
	// Visible/ invisible/presence/ Number / CLickkable/...
	WebDriverWait explicitlyWait;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@Test
	public void TC_01_Egov_Button(){
		driver.get("https://egov.danang.gov.vn/reg");
		WebElement registerButton = driver.findElement(By.cssSelector("input.egov-button"));
		Assert.assertFalse(registerButton.isEnabled());

		driver.findElement(By.cssSelector("input#chinhSach")).click();
		Assert.assertTrue(registerButton.isEnabled());
		sleepInSeconds(2);

		// Lấy ra mã màu nền Button register
		String registerBackgroundRGB = registerButton.getCssValue("background-color");
		System.out.println("Background color RGB = " + registerBackgroundRGB);

		// Convert từ kiểu String (mã RGB) qua color
		Color registerBackgroundColor = Color.fromString(registerBackgroundRGB);

		// Convert qua kiểu màu Hexa
		String registerBackgroundHexa = registerBackgroundColor.asHex();
		System.out.println("Background color Hexa = " + registerBackgroundHexa);

		// Convert qua viết thường
		Assert.assertEquals(registerBackgroundHexa.toLowerCase(), "#ef5a00");

	}

	@Test
	public void TC_02_Fahasa_Button (){
		driver.get("https://www.fahasa.com/customer/account/create");

		// Chuyển qua tab đăng nhập
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		sleepInSeconds(2);

		WebElement loginButton = driver.findElement(By.cssSelector("button.fhs-btn-login"));

		// Verify button login is disabled
		Assert.assertFalse(loginButton.isEnabled());

		//Verify màu nền Button login = Background
		Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toLowerCase(),"#000000");

		// nhập email/ pass
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("hiennt.tgnguyen@gmail.com");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");
		sleepInSeconds(2);

		// Verify button login is enabled
		Assert.assertTrue(loginButton.isEnabled());

		//Verify màu nền Button login = Background
		Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toLowerCase(),"#c92127");

	}


	public void afterClass() {
		driver.quit();
	}


	public void sleepInSeconds(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	// Những dữ liệu truyền vào là tham số





}
	

