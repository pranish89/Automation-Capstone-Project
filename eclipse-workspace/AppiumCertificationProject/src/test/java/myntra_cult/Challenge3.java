package myntra_cult;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Challenge3 {

	AndroidDriver driver;

	@SuppressWarnings("deprecation")
	@BeforeTest
	public void setup() throws MalformedURLException {
		UiAutomator2Options option = new UiAutomator2Options()
				.setChromedriverExecutable("D:\\Appium\\chromedriver-win64_138\\chromedriver-win64\\chromedriver.exe")
				.setDeviceName("HA23P8P0")
				.setPlatformName("Android")
				.setPlatformVersion("15.0")
				.setAutomationName("UiAutomator2")
				.setAppPackage("com.android.chrome")
				.setAppActivity("com.google.android.apps.chrome.Main")
				.setChromedriverDisableBuildCheck(true)

				.setAutoGrantPermissions(true).setNoReset(true).setFullReset(false);

		// point at the running server
		URL server = new URL("http://127.0.0.1:4723/wd/hub");

		// driver creation
		driver = new AndroidDriver(server, option);
	}

	//@BeforeClass
	public void login() {
		LoginMyntra.login(driver);
	}
	
	@Test(priority = -1)
	public void getUrl() {

		// WebElement search =
		// driver.findElement(By.id("com.android.chrome:id/search_box_text"));
		// WebElement search =
		// driver.findElement(By.xpath("//android.widget.EditText[@text = 'Search or
		// type URL']"));
		// search.click();
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		// Finding the Search box, clears and click on it
		WebElement edit_box = driver.findElement(By.id("com.android.chrome:id/url_bar"));
		edit_box.clear();
		edit_box.click();
		edit_box.sendKeys("myntra_cult.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		System.out.println("Opened myntra_cult from the chrome browser");

	}

	@Test(priority = 2, dependsOnMethods = { "getUrl" })
	public void searchItem() {
		Set<String> windows = driver.getContextHandles();
		System.out.println("Window handles in browser " + windows);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.context("WEBVIEW_chrome");
		System.out.println("Switched to WebView");
		WebElement search_box = driver
				.findElement(By.xpath("//input[@placeholder='Search for products, brands and more']"));
		search_box.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		search_box.sendKeys("Nike Shoes");
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		System.out.println("Search operation is performed");

	}

	@Test(priority = 3, dependsOnMethods = { "searchItem" })
	public void selectItem() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		// select the first item
		WebElement select_item = driver.findElement(
				By.xpath("//img[contains(@src,'Nike-Run-Defy-Mens-Road-Running-Shoes-8891750693265022-1.jpg')]"));
		select_item.click(); // opens the item in another window
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		
		Set<String> handles = driver.getWindowHandles();//get all the window handles
		String curr=driver.getWindowHandle(); //get the current window handle
		for(String win:handles) {
			if(!win.equals(curr))
			{
				driver.switchTo().window(win);
				break;
			}
		}
		System.out.println("Switched to the child window");
		

		WebElement size = driver.findElement(By.xpath("//p[text()='7']"));
		size.click();
		// add to bag
		WebElement add_to_bag = driver.findElement(By.xpath("//*[text()= 'ADD TO BAG']"));
		add_to_bag.click();
		// Go to bag
		WebElement go_to_bag = driver.findElement(By.xpath("//span[contains(text(),'GO TO BAG')]"));
		go_to_bag.click();

	}

	@Test(priority = 99, dependsOnMethods = { "selectItem" })
	public void placeOrder() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		WebElement place_order = driver.findElement(By.xpath("//*[contains(text(),'PLACE ORDER')]"));
		place_order.click();

		// Address check
		if( driver.findElement(By.className("addressDetails-base-name")).isDisplayed()) {
			System.out.println("Address is displayed");
		}
		
		WebElement order = driver.findElement(By.xpath("//div[text()='continue']"));
		order.click();

		// Net Banking
		WebElement netbanking = driver.findElement(By.id("netbanking"));
		netbanking.click();

		// Axis bank
		WebElement axis_bank = driver.findElement(By.xpath("//*[contains(text(), 'Axis Bank')]"));
		axis_bank.click();
		driver.context("NATIVE_APP");
	}

	// @AfterClass
	public void close_drive() {
		driver.close();
	}
}
