package WebDriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.regex.Pattern;

public class Topic_28_Selenium_Wait_IV {
	WebDriver driver;

	WebDriverWait explicitWait; // khai báo chưa khoi tao

	String projectPath = System.getProperty("user.dir");

	String hcmName = "hcm.jpg";
	String hlName = "halong.jpg";
	String hnName = "hanoi.jpg";

	String hcmFilePath = projectPath + "\\uploadFiles\\" + hcmName;
	String hlFilePath = projectPath + "\\uploadFiles\\" + hlName;
	String hnFilePath = projectPath + "\\uploadFiles\\" + hnName;



	@BeforeClass // Precondition - khởi tạo dữ liệu/ data test/ page class/ variable

	// Khởi tạo 1 explicit Wait có tổng thời gian là 10s - polling là 0.5s mặc định
	public void beforeClass() 	{
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver,Duration.ofSeconds(30));
	}

	@Test
	public void TC_01_5s_Visible() {
		explicitWait = new WebDriverWait(driver,Duration.ofSeconds(5));
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();

		// wait cho loading bien mat trong x giay
		//explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
	}

	@Test
	public void TC_02_less_5s() {
		explicitWait = new WebDriverWait(driver,Duration.ofSeconds(3));
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();

		// wait cho loading bien mat trong x giay
		//explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
	}

	@Test
	public void TC_03_than_5s() {
		explicitWait = new WebDriverWait(driver,Duration.ofSeconds(100));
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();

		// wait cho loading bien mat trong x giay
		//explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
	}
    @Test
	public void TC_0601_ajaxloading () {
		explicitWait = new WebDriverWait(driver,Duration.ofSeconds(30));
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		WebElement selectdate = driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"));
		Assert.assertEquals(selectdate.getText(),"No Selected Dates to display.");

		driver.findElement(By.xpath("//a[text()='9']")).click();

		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*=RadCalendar1]>div.raDiv")));
		Assert.assertEquals(selectdate.getText(),"Wednesday, October 9, 2024");
	}
	@Test
	public void TC_07_Upload (){
		driver.get("https://gofile.io/welcome");

		// wait + verify spinner bien mat
		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner-border"))));

		// wait + Click
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.ajaxLink>button"))).click();

		// Wait + verify spinner biến mất
		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElement(By.cssSelector("div.spinner-border")))));
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(hcmFilePath + "\n" + hlFilePath + "\n" + hnFilePath);

		// Wait + spinner biến mất
		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElement(By.cssSelector("div.spinner-border")))));

		// wait process bar bien mat
		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElement(By.cssSelector("div.progress")))));

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.mainUploadSuccessLink a.ajaxLink"))).click();

		// Verify  button Play có tại từng hinh duoc upload
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
				("//span[text()='" + hcmName + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Play']"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
				("//span[text()='" + hlName + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Play']"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
				("//span[text()='" + hnName + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Play']"))).isDisplayed());

		// Verify  button Download có tại từng hinh duoc upload
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
				("//span[text()='" + hcmName + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
				("//span[text()='" + hlName + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
				("//span[text()='" + hnName + "']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());

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
