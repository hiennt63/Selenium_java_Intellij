package WebDriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;

public class Topic_19_Shadow_DOM {
	WebDriver driver;

	@BeforeClass
	public void initialBrowser() {

		driver = new FirefoxDriver();
		//driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Test
	public void TC_01()  {
		driver.get("https://automationfc.github.io/shadow-dom/");

		WebElement shadowHostElement = driver.findElement(By.cssSelector("div#shadow_host"));
		SearchContext shadowRootContext = shadowHostElement.getShadowRoot();

		String someText = shadowRootContext.findElement(By.cssSelector("span#shadow_content>span")).getText();
		System.out.println(someText);
		Assert.assertEquals(someText,"some text");

		WebElement shadowboxShadow = shadowRootContext.findElement(By.cssSelector("input[type='checkbox']"));
		Assert.assertFalse(shadowboxShadow.isSelected());

		List<WebElement> allInput = shadowRootContext.findElements(By.cssSelector("input"));
		System.out.println(allInput.size());

		WebElement nestedShadowHostElement = shadowRootContext.findElement(By.cssSelector("div#nested_shadow_host"));
		SearchContext nestedShadowRootContent = nestedShadowHostElement.getShadowRoot();

		String nestedtext = nestedShadowRootContent.findElement(By.cssSelector("div#nested_shadow_content>div")).getText();
		System.out.println(nestedtext);
		Assert.assertEquals(nestedtext,"nested text");
	}
	@Test
	public void TC_02(){
		driver.get("https://shopee.vn/");

		sleepInSeconds(5);
		WebElement shadowHostElement = driver.findElement(By.cssSelector("shopee-banner-popup-stateful"));
		SearchContext shadowRootContext = shadowHostElement.getShadowRoot();

	   //Verify popup hiển thị
		if (shadowRootContext.findElements(By.cssSelector("div.home-popup__content")).size()>0 &&
				shadowRootContext.findElements(By.cssSelector("div.home-popup__content")).get(0).isDisplayed() ){
		// CLick để close popup đi
			shadowRootContext.findElement(By.cssSelector("div.shopee-popup__close-btn")).click();
			sleepInSeconds(3);
		}
		// Ko hiển thị/ đã bị đóng rồi qua step Search dữ liệu
		driver.findElement(By.cssSelector("div.shopee-searchbar-input__input")).sendKeys("iPhone 15 Pro Max");
		sleepInSeconds(3);

		driver.findElement(By.cssSelector("button.shopee-searchbar__search-button")).click();
		sleepInSeconds(3);


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
