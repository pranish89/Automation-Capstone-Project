package myntra_cult;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Challenge5 {
	
	
	AndroidDriver driver;
	@SuppressWarnings("deprecation")
	@BeforeTest
	public void setup()throws MalformedURLException {
		UiAutomator2Options option = new UiAutomator2Options()
				.setChromedriverExecutable("D:\\Appium\\chromedriver-win64 (4)\\chromedriver-win64\\chromedriver.exe")
				.setDeviceName("HA23P8P0")
				.setPlatformName("Android")
				.setPlatformVersion("15.0")
				.setAutomationName("UiAutomator2")
				.setAppPackage("fit.cure.android")
				.setAppActivity("fit.cure.android.MainActivity")
				.setAutoGrantPermissions(true)
				.setNoReset(true)
				.setFullReset(false)
				;

				//point at the running server
				URL server = new URL("http://127.0.0.1:4723/wd/hub");

				//driver creation
				driver = new AndroidDriver(server,option);
					}
	
	@BeforeClass
	public void login() {
		LoginCult.login(driver);
	}


	@Test
	public void Wifi_Off() {
		//notification pop up
		try {
			driver.findElement(AppiumBy.id("com.android.permissioncontroller:id/permission_deny_button"));
		}catch (Exception e)
		{
			System.out.println("Notification pop up is not displayed");
		}
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	Swipe.action(driver, "RIGHT");
	Scroll.scrollview(driver);

	//home workout
	WebElement work_from_home = driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='3']"));
	work_from_home.click();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	Scroll.scrollview(driver);
	System.out.println("Work From option is clicked");

	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	WebElement program = driver.findElement(By.xpath("//android.view.View[@content-desc='Programs']"));
	program.click();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

	WebElement explore = driver.findElement(By.xpath("//android.widget.TextView[@text='EXPLORE']"));
	explore.click();
	
	WebElement play = driver.findElement(By.xpath("//android.widget.TextView[@text='']"));
	play.click();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

	driver.toggleWifi(); 


	System.out.println("The video is not playing");
	driver.terminateApp("fit.cure.android");
	driver.toggleWifi(); 

	}
	
	

}
