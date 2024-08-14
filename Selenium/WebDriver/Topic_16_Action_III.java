package WebDriver;

import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.Color;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.time.Duration;

import static java.awt.Color.*;
import static java.lang.Thread.sleep;

public class Topic_16_Action_III {
	WebDriver driver;
	Actions action;
	String osName = System.getProperty("os.name");
	Keys keys;
	JavascriptExecutor jsExecutor;
	String projectPath;

	@BeforeClass
	public void initialBrowser() {

		driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		action = new Actions(driver);
	    jsExecutor = (JavascriptExecutor) driver;


	}

	@Test
	public void TC_01_Drag_Drop_HTML4() throws InterruptedException {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");

		WebElement sourceCircle = driver.findElement(By.cssSelector("div#draggable"));
		WebElement targetCircle = driver.findElement(By.cssSelector("div#droptarget"));

		action.dragAndDrop(sourceCircle, targetCircle).perform();
		Thread.sleep(3000);

		Assert.assertEquals(targetCircle.getText(),"You did great!");
		Assert.assertEquals(Color.fromString(targetCircle.getCssValue("background-color")).asHex().toLowerCase(),"#03a9f4");

	}
	@Test
	public void TC_02_Drag_Drop_HTML5() throws InterruptedException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");

		WebElement columnA = driver.findElement(By.cssSelector("div#column-a"));
		WebElement columnB = driver.findElement(By.cssSelector("div#column-b"));

		action.dragAndDrop(columnA, columnB).perform();
		Thread.sleep(3000);

		action.clickAndHold(columnA).moveToElement(columnB).release().perform();
		Thread.sleep(3000);
	}
	@Test
	public void TC_02_Drag_Drop_HTML5_JS() throws InterruptedException, IOException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");

		
		String jqueryDragDropContent = getContentFile(projectPath + "\\DragDrop\\dragAndDrop.js");
		//Drag A qua B
		jsExecutor.executeScript(jqueryDragDropContent);
		Thread.sleep(3000);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"B");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"A");

		//Drag A qua B
		jsExecutor.executeScript(jqueryDragDropContent);
		Thread.sleep(3000);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"A");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"B");
    }

	@Test
	public void TC_02_Drag_Drop_JQuery() throws InterruptedException, IOException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");

		// Site trong support jQuery

		String jqueryLibraries = getContentFile(projectPath + "\\DragDrop\\jQueryLib.js");
		//Drag A qua B
		jsExecutor.executeScript(jqueryLibraries);
		Thread.sleep(3000);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"B");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"A");

		//Drag A qua B
		jsExecutor.executeScript(jqueryLibraries);
		Thread.sleep(3000);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"A");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"B");
	}

	@Test
	public void TC_03_Drag_Drop_Java_Robot() throws AWTException, InterruptedException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		dragAndDropHTML5ByXpath("div#column-a","div#column-b");
		Thread.sleep(3000);

		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"B");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"A");

		dragAndDropHTML5ByXpath("div#column-a>","div#column-b");
		Thread.sleep(3000);

		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"A");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"B");
	}
	public String getContentFile(String filePath) {
		return filePath;
	}
	public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.cssSelector(sourceLocator));
		WebElement target = driver.findElement(By.cssSelector(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	@Test
	public void TC_03_Scroll_to_Element() throws InterruptedException {
		driver.get("http://live.techpanda.org/index.php/about-magento-demo-store/");
		Thread.sleep(3000);
		action.scrollToElement(driver.findElement(By.cssSelector("input#newsletter"))).perform();
		Thread.sleep(3000);
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
