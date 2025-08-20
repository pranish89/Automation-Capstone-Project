package myntra_cult;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.touch.offset.PointOption;
import junit.framework.Assert;

public class Challenge2 {

	AndroidDriver driver;

	@SuppressWarnings("deprecation")
	@BeforeTest
	public void setup() throws MalformedURLException {
		UiAutomator2Options option = new UiAutomator2Options()
				.setChromedriverExecutable("D:\\Appium\\chromedriver-win64 (4)\\chromedriver-win64\\chromedriver.exe")
				.setDeviceName("HA23P8P0").setPlatformName("Android").setPlatformVersion("15.0")
				.setAutomationName("UiAutomator2").setAppPackage("com.myntra.android")
				.setAppActivity("com.myntra.android.activities.react.ReactActivity").setAutoGrantPermissions(true)
				.setNoReset(true).setFullReset(false);

		// point at the running server
		URL server = new URL("http://127.0.0.1:4723/wd/hub");

		// driver creation
		driver = new AndroidDriver(server, option);
	}
	
	@BeforeClass
	public void login() {
		LoginMyntra.login(driver);
	}
	
	@Test
	public void imageSearch() throws IOException, InterruptedException {
		// copying an image to device
		driver.pushFile("/sdcard/Downloads/appium.jpg", new File("C:\\Users\\mages\\Downloads\\appium.jpg"));
		System.out.println("File upload is done");
		// finding search image
		WebElement search_button = driver.findElement(AppiumBy.xpath("//android.widget.ImageView[@index='1']"));
		System.out.println("clicked on search button");
		search_button.click();

		Thread.sleep(5000);

		// driver.findElement(AppiumBy.androidUIAutomator("new
		// UiSelector().className(\"android.view.ViewGroup\").instance(9)")).click();

		// finding camera icon
		driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc='PHOTO_SEARCH_1_2']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		// existung photo
		/*
		 * WebElement existing_photo=driver.findElement(AppiumBy.
		 * xpath("//android.widget.TextView[@text='Choose an existing photo']"));
		 * existing_photo.click();
		 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		 */

		// select photo
		WebElement select_photo = driver.findElement(
				AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.View\").instance(16)"));
		select_photo.click();
		
		// search button click
		WebElement search_click = driver.findElement(AppiumBy.id("com.myntra.android:id/Search"));
		search_click.click();
		System.out.println("Image has been selected and searching operation is complete");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

		TouchAction touch = new TouchAction(driver);
		touch.tap(PointOption.point(246, 1618)).perform();
		System.out.println("tapping done on search results");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));


		/*
		 * Scroll.scrollview(driver);
		 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		 * 
		 * Boolean present = driver.findElement(By.
		 * xpath("//android.widget.TextView[@content-desc='text_brand' and @text='RAPL ']"
		 * )).isDisplayed();
		 * 
		 * Assert.assertSame("The match is not found", true, present);
		 */

		driver.terminateApp("com.myntra.android");
	}

}
