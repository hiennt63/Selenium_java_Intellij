package WebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class Topic_30_Mix_Implicit_Explicit_Wait {
	WebDriver driver;

	WebDriverWait implicitWait; // khai báo chưa khoi tao
	WebDriverWait explicitWait;
   @BeforeClass // Precondition - khởi tạo dữ liệu/ data test/ page class/ variable

	// Khởi tạo 1 explicit Wait có tổng thời gian là 10s - polling là 0.5s mặc định
	public void beforeClass() 	{
		driver = new FirefoxDriver();
	}

	@Test
	public void TC_01_Only_Implicit_Found() {

	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	   driver.get("https://vi-vn.facebook.com/");

	   // khi vào tim element tìm thay ngay
		// Khong can cho het timeout
	   driver.findElement(By.cssSelector("input#email"));
   }

	@Test
	public void TC_02_Only_Implicit_NotFound() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://vi-vn.facebook.com/");

		// khi vào tim element tìm  ko thay
		// Polling moi nua s tim lai 1 lan
		// Khi het timeout se danh fail testcase va throw exception: NosuchElementException
		// NosuchElementException: Unable to locate element: input#automation
		driver.findElement(By.cssSelector("input#automation"));
	}
	@Test
	public void TC_03_Only_Explicit_Found() {
		explicitWait = new WebDriverWait(driver,Duration.ofSeconds(5));
		driver.get("https://vi-vn.facebook.com/");

		// khi vào tim element tìm thay ngay
		// Khong can cho het timeout
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
	}

	@Test
	public void TC_04_Only_Explicit_NotFound()  {
			explicitWait = new WebDriverWait(driver,Duration.ofSeconds(5));
			driver.get("https://vi-vn.facebook.com/");

		// khi vào tim element tìm  ko thay
		// Polling moi nua s tim lai 1 lan
		// Khi het timeout se danh fail testcase va throw TimeoutException: Expected condition failed
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#automation")));
		}
	@Test
	public void TC_05_Mix_Explicit_Implicit() {
		driver.get("https://vi-vn.facebook.com/");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5)); // ko anh huong den driver.findElement

		// imp chay trc exp do phai tim findele..
		// ney imp > exp => exp xong truoc nhung se cho imp xong moi show
		System.out.println("Start time: " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#automation")));
		} catch (Exception e) {
			System.out.println("End time: " + getDateTimeNow());
			e.printStackTrace();
		}
	}
	@Test
	public void TC_06_Only_Explicit_Not_Found_Param_By() {
		driver.get("https://vi-vn.facebook.com/");
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5)); // ko anh huong den driver.findElement
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#automation")));
		// find element trong khi wait, tìm & wait xem ke cung chay => bao explicit sau cung


	}
	@Test
	public void TC_07_Only_Explicit_Not_Found_Param_WebElement() {
		driver.get("https://vi-vn.facebook.com/");
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5)); // ko anh huong den driver.findElement
		explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#automation"))));
		// phải tim findelement trc khi wait neu find ko co thi danh fail nosuch => anh huong implicit bao dau tien


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
