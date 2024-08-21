package WebDriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;
import java.util.Set;

import static java.lang.Thread.sleep;

public class Topic_23_Javascript_Executor {
	WebDriver driver;
	///WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;


	@BeforeClass
	public void initialBrowser() {


		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
	}

	@Test
	public void TC_01_JE()  {
		executeForBrowser("window.location = 'http://live.techpanda.org/'");
		sleepInSecond(3);
		String techpadaDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(techpadaDomain,"live.techpanda.org");

		String techpadaURL = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(techpadaURL,"http://live.techpanda.org/");

		hightlightElement("//a[text()='Mobile']");
		// thao tác js ko phai hanh vi cua enduser
		driver.findElement(By.xpath("//a[text()='Mobile']"));
		sleepInSecond(3);

		hightlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");

		Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));

		hightlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		String customerTitle = (String) executeForBrowser("return document.title");
		Assert.assertEquals(customerTitle,"Customer Service");

		scrollToBottomPage();
		hightlightElement("//input[@id='newsletter']");
		clickToElementByJS("//input[@id='newsletter']");
		sendkeyToElementByJS("//input[@id='newsletter']", "nguyenthihien7958@gmail.com");

		hightlightElement("//button[@title='Subscribe']");
		clickToElementByJS("//button[@title='Subscribe']");

		Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));
		Assert.assertTrue(isExpectedTextInInnerText("Thank you for your subscription."));

		navigateToUrlByJS("https://www.facebook.com/");
		String fbDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(fbDomain,"facebook.com");

	}

	@Test
	public void TC_02_HTML5(){
		driver.get("https://sieuthimaymocthietbi.com/account/register");
		sleepInSecond(3);

		driver.findElement(By.xpath(	"//button[text()='Đăng ký']")).click();
		sleepInSecond(3);
		Assert.assertEquals(getElementValidationMessage("//input[@id='lastName']"),"Please fill out this field.");

		driver.findElement(By.xpath(	"//input[@id='lastName']")).sendKeys("Nguyễn");
		driver.findElement(By.xpath(	"//button[text()='Đăng ký']")).click();
		sleepInSecond(3);
		Assert.assertEquals(getElementValidationMessage("//input[@id='firstName']"),"Please fill out this field.");

		driver.findElement(By.xpath(	"//input[@id='lastName']")).sendKeys("Nguyễn");
		driver.findElement(By.xpath(	"//input[@id='firstName']")).sendKeys("Hiền");
		driver.findElement(By.xpath(	"//button[text()='Đăng ký']")).click();
		sleepInSecond(3);
		Assert.assertEquals(getElementValidationMessage("//input[@id='email']"),"Please fill out this field.");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void switchToWindowByID (String expectedID){
		Set<String> allIDs = driver.getWindowHandles();
		for(String id:allIDs){
			// Nếu ID nào khác ID parentID thì switch to
			if(!id.equals(expectedID)){
				driver.switchTo().window(id);

				// Ngược lại thì thoát khỏi vòng lặp ko cần kiểm tra tiếp
				break;
			}
		}
	}

	public void switchToWindowByTitle(String TitleExpected){
		//Lấy all ID của Tab
		Set<String> allIDs = driver.getWindowHandles();
		// Dùng vòng lặp duyệt qua ID trên
		for(String id: allIDs){
			// switch vào từng ID trước
			driver.switchTo().window(id);
			sleepInSeconds(2);

			// Lấy ra title của từng window/ tab trên
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(TitleExpected)){
				break;
			}
		}

	}

	public void CloseAllWindowsWithoutPearent(String ParentID){
		//Lấy all ID của Tab
		Set<String> allIDs = driver.getWindowHandles();
		// Dùng vòng lặp duyệt qua ID trên
		for(String id: allIDs){
			if (!id.equals(ParentID)){
				driver.switchTo().window(id);
				driver.close();
			}
		} driver.switchTo().window(ParentID);

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
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean isExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void sleepInSecond(int timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
		sleepInSecond(3);
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
		sleepInSecond(3);
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
		jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public String getAttributeInDOM(String locator, String attributeName) {
		return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
}
