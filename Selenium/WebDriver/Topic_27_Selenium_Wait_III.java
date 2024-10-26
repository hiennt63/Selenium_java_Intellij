package WebDriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class Topic_27_Selenium_Wait_III {
	WebDriver driver;

	WebDriverWait explicitWait; // khai báo chưa khoi tao

	@BeforeClass // Precondition - khởi tạo dữ liệu/ data test/ page class/ variable

	// Khởi tạo 1 explicit Wait có tổng thời gian là 10s - polling là 0.5s mặc định
	public void beforeClass() 	{
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10));

	}

	@Test
	public void TC_01_Element() {
		// Chờ cho 1 Alert presence trong HTML/ DOM trước khi thao tác lên
		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		// Chờ cho element ko còn ở trong DOM
		explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector(""))));
		// Chờ cho element có trong DOM ( ko quan tâm có trn UI ko)
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("")));

		// Chờ cho 1 list element có trong DOM
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")));
		explicitWait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.cssSelector("form#search-form"),By.cssSelector("input#live-search-bar")));

		// Chờ cho 1-n element được hiển thị trên UI
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));
		explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(""))));
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector(""))));
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector("")), driver.findElement(By.cssSelector(""))));
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector("")), driver.findElement(By.cssSelector("")),driver.findElement(By.cssSelector(""))));

		// Chờ cho element được phép Click link/button/checkbox/radio...
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));

		// Chờ cho page hiện tại có title như mong đợi
		explicitWait.until(ExpectedConditions.titleIs("Create New Customer Account"));
		driver.getTitle();

		// Kết hợp n điều kiện - 1trong 2 dk đúng
		explicitWait.until(ExpectedConditions.and(
				ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")),
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector(""))));

		// Chờ một element có attribute chứa giá trị mong đợi (tương đối)
		explicitWait.until (ExpectedConditions.attributeContains (By.cssSelector("input#search"), "placeholder","Search entire"));
		explicitWait.until (ExpectedConditions.attributeContains(By.cssSelector("input#search"), "placeholder", "store here..."));
		explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector("input#search"), "placeholder", "Search entire store here..."));

		// Chờ một element có attribute có giá trị mong đợi (tương đối)
		explicitWait.until (ExpectedConditions.attributeToBe (By.cssSelector("input#search"), "placeholder", "Search entire store here..."));

		// Chờ một element có attribute khác null
		explicitWait.until (ExpectedConditions.attributeToBeNotEmpty (driver.findElement(By.cssSelector("input#search")), "placeholder"));

		explicitWait.until (ExpectedConditions.domAttributeToBe (driver.findElement(By.cssSelector("input#search")), "namespaceURI","http://www.w3.org/1999/xhtml"));
		explicitWait.until (ExpectedConditions.domPropertyToBe (driver.findElement(By.cssSelector("input#search")), "namespaceURI",  "http://www.w3.org/1999/xhtml"));

		// Chờ cho 1 element dc slected thah công
		// Checkbox/ Radio/ Dropdown Item (Default)
		explicitWait.until (ExpectedConditions.elementToBeSelected (By.cssSelector("")));

		// Chờ cho 1 element dc slected rồi
		explicitWait.until (ExpectedConditions.elementSelectionStateToBe (By.cssSelector(""),  true));

		// Chờ cho 1 element chưa dc slected
		explicitWait.until (ExpectedConditions.elementSelectionStateToBe (By.cssSelector(""), false));

		// Name or ID
		explicitWait.until (ExpectedConditions.frameToBeAvailableAndSwitchToIt(""));

		// Index
		explicitWait.until (ExpectedConditions.frameToBeAvailableAndSwitchToIt(1));

		// By or Element
		explicitWait.until (ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("")));
		explicitWait.until (ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.cssSelector(""))));

		// Chờ cho 1 element biến mất ( ko hiển thị trên UI)
		explicitWait.until (ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));

		// Chờ cho 1 đoạn code JS cần trả về giá trị

		explicitWait.until (ExpectedConditions.jsReturnsValue ( "document.documentElement.innerText;"));

		// Chờ cho 1 đoạn code JS được thực thi ko ném ra ngoại lệ nào hết
		// Ko ném ra: true
		// Có ngoại lệ: false
		explicitWait.until (ExpectedConditions.javaScriptThrowsNoExceptions("document.documentElement.innerText;"));
        Assert.assertTrue (explicitWait.until(ExpectedConditions.javaScriptThrowsNoExceptions("document.documentElement.innerText;")));

		// Chờ số lượng element bằng 1 con số cố định
		explicitWait.until (ExpectedConditions.numberOfElementsToBe (By.cssSelector("select [title='Sort By']>option"), 6));
		explicitWait.until (ExpectedConditions.numberOfElementsToBeLessThan (By.cssSelector("select [title='Sort By']>option"), 6));
		explicitWait.until (ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("select [title='Sort By']>option"), 6));

		// CHờ cho window/Tab là bao nhiu
		explicitWait.until (ExpectedConditions.numberOfWindowsToBe(3));

		explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div.category-title>h1"),"Mobile"));
		Pattern pattern = Pattern.compile( "This is root of mobile", Pattern.CASE_INSENSITIVE);
		explicitWait.until(ExpectedConditions.textMatches(By.cssSelector("div.category-description"),pattern));

		// Băt buộc cái text naày trong DOM/HTMl
		explicitWait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div-category-description"),"This is root of mobile"));
		explicitWait.until (ExpectedConditions.urlToBe("http://live.techpanda.org/index.php/mobile.html"));
		explicitWait.until (ExpectedConditions.urlContains("/index.php/mobile.html"));
		explicitWait.until (ExpectedConditions.urlMatches ("[^abc]"));

		// Chờ cho 1 dk mà element này bị update trạng thái - load lại HTML
		explicitWait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(By.cssSelector(""))));
	}

	@Test
	public void TC_02_Elements() {

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
