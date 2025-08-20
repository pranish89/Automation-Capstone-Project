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

public class Challenge6 {
	
	
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
	public void CultPass() {
		
		try {
			driver.findElement(AppiumBy.id("com.android.permissioncontroller:id/permission_deny_button"));
		}catch (Exception e)
		{
			System.out.println("Notification pop up is not displayed");
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

		//WebElement cult_elite = driver.findElement(By.xpath("//android.view.View[@content-desc='EXPLORE CULTPASS ELITE EXPLORE CULTPASS ELITE']"));
		//WebElement cult_elite=driver.findElement(AppiumBy.accessibilityId("EXPLORE CULTPASS ELITE\\nEXPLORE CULTPASS ELITE"));
		WebElement cult_elite = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.View\").instance(13)"));
		cult_elite.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		System.out.println("Elite pass is selected");

		WebElement select_elite= driver.findElement(By.xpath("(//android.widget.ImageView[@content-desc='0'])[2]"));
		select_elite.click();
		Scroll.scrollview(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		WebElement buy_pass= driver.findElement(By.xpath("(//android.widget.ImageView[@content-desc='0'])[2]"));
		buy_pass.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		WebElement center = driver.findElement(By.xpath("//android.view.View[@content-desc='Select preferred center']"));
		center.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		WebElement center_name = driver.findElement(By.xpath("//android.view.View[@index='4']"));
		center_name.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		WebElement start = driver.findElement(By.xpath("//android.view.View[@content-desc='Select start date']"));
		start.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		WebElement date = driver.findElement(AppiumBy.accessibilityId("30, Wednesday, July 30, 2025"));
		date.click();
		WebElement ok = driver.findElement(By.xpath("//android.widget.Button[@content-desc='OK']"));
		ok.click();
		System.out.println("Center and Start date choosen");
		Boolean amount = driver.findElement(AppiumBy.xpath("//android.view.View[contains(@content-desc,'CHECKOUT_CTA')]")).isDisplayed();
		//amount.click();
		if(amount)
		{
			System.out.println("Amount is displayed ");
		}
	}
	
	}


