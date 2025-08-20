package myntra_cult;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import myntra_cult.Otp;

public class LoginMyntra {


public static void login(AndroidDriver driver) {
	
	//click the profile/login icon @ top right corner of the app
	WebElement login_icon = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='ic_actionbar_profile']/android.view.ViewGroup"));
	login_icon.click();
	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	
	//Click on the actual login btn
	WebElement login_btn = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='LOG IN/SIGN UP']"));
	System.out.println("Login pop-up is displayed");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
	
	//Enter the mobile number
	WebElement phone_number = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@content-desc='mobile']"));
	phone_number.clear();
	phone_number.click();
	phone_number.sendKeys("99*****");
	
	//Click on the check box
	WebElement check = driver.findElement(AppiumBy.xpath("//android.widget.TextView[contains(text(),'you confirm that you are above 18 years of age')]"));
	check.click();
	
	//Click on login btn
	WebElement login_otp = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Login using OTP']"));
	login_otp.click();
	
	//open message app
	driver.activateApp("com.google.android.apps.messaging");
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
	WebElement message = driver.findElement(By.xpath("//android.widget.Textview[@text='firstmsg']"));
	String entire_msg=message.getText();
	String otp = Otp.verify_otp(entire_msg);
	
	
	
}
}