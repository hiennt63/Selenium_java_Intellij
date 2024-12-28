package WebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.List;

public class Topic_27_Selenium_Wait_II {
	WebDriver driver;

	WebDriverWait explicitWait;
	FluentWait<WebDriver> fluentWait;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.facebook.com/");

	}

	@Test
	public void TC_01_Element() {

		// Case 1 Element được tìm thấy chỉ có 1
		// Sẽ không cần chờ hết timeout
		// Tìm thấy sẽ trả về 1 WebElement
		// Qa step tiếp theo
		// System.out.println("Start step: " + getDateTimeNow());
		// driver.findElement(By.cssSelector("input#email"));
		// System.out.println("End step: " + getDateTimeNow());
		// Case 2 Element được tìm thấy nhưng có nhều hơn 1
		// Sẽ không cần chờ hết timeout
		// Lấy cái element đầu tiên dù có cả n node
		// Qa step tiếp theo
		// System.out.println("Bước bắt đầu: " + getDateTimeNow());
		// driver.findElement(By.cssSelector("input[type='text'], [type='password']")).sendKeys("dam@gmail.com");
		// System.out.println("Kết thúc bước: " + getDateTimeNow());
		// Case 3 Element không được tìm thấy
		// Chờ hết timeout là 10s
		// Trong thời gian 10s này cứ mỗi nửa s sẽ tìm lại 1 lần
		// Nếu tìm lại mà thấy thì cũng trả về element rồi qua step tiếp theo
		// Nếu tìm lại mà ko thấy thì đánh fail testcase và throw exception: NoSuchElementException
		// Các step còn lại ko chạy nữa
			System.out.println("Bước bắt đầu: " + getDateTimeNow());
		    driver.findElement(By.cssSelector("input#không-tìm-thấy"));
		    System.out.println("End step: " + getDateTimeNow());

	}

	@Test
	public void TC_02_Elements() {
		List<WebElement> elementList;
		// Case 1 Element được tìm thấy chỉ có 1
		// Ko cần chờ hết timeout 10s
		// Trả về List Element chứa đúng 1 element
		// System.out.println("Start step: " + getDateTimeNow());
		// elementList = driver.findElements(By.cssSelector("input#email"));
		// System.out.println("List have:" + elementList.size());
		// System.out.println("End step: " + getDateTimeNow());

		// Case 2 Element được tìm thấy nhưng có nhều hơn 1
		// Ko cần chờ hết timeout 10s
		// Trả về List Element chứa n element
		//	System.out.println("Bước bắt đầu: " + getDateTimeNow());
		//	elementList =  driver.findElements(By.cssSelector("input[type='text'], [type='password']")).sendKeys("dam@gmail.com");
		//  System.out.println("List have:" + elementList.size());
		//	System.out.println("Kết thúc bước: " + getDateTimeNow());

		//  Case 3 Element không được tìm thấy
		// Chờ hết timeout là 10s
		// Mỗi nửa s cũng tìm lại 1 lần (polling)
		// Nếu trong thời gian tìm lại mà thấy element thì cũng trả về List chứa các element đó
		// Nếu hết thời gian tìm lại mà ko thấy thì trả về List rỗng (empty) và không đánh fail testcase
		// Qua step tiếp theo
		  System.out.println("Start step: " + getDateTimeNow());
		  elementList = driver.findElements(By.cssSelector("input#không-tìm-thấy"));
		  System.out.println("List have:" + elementList.size());
		  System.out.println("End step: " + getDateTimeNow());
	}

	@Test
	public void TC_02_InVisible_Not_In_Dom() {


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
	public static String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}
}
