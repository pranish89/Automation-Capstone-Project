package myntra_cult;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Challenge4 {
	
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
	public void appActivity()
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.runAppInBackground(Duration.ofSeconds(5));
		System.out.println("App is running in background");
		driver.currentActivity();
		System.out.println("App is running foreground");
		System.out.println("App current state " +driver.queryAppState("fit.cure.android"));
		System.out.println("Terminating the app");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.terminateApp("fit.cure.android");

}
	
	
	
	
	
}
