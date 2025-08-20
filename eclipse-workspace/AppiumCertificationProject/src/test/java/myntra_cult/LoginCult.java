package myntra_cult;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class LoginCult {
	
	public static void login(AndroidDriver driver) {
		
		WebElement login_btn=driver.findElement(By.xpath("//android.view.View[@content-desc='ME PROFILE_ICON']"));
		login_btn.click();
		WebElement phone_number=driver.findElement(By.xpath("//android.view.View[@content-desc='GENERATE_PHONE_OTP_BTN CONTINUE']"));
		phone_number.click();
		phone_number.sendKeys("9*******");
				//open message app
				driver.activateApp("com.google.android.apps.messaging");
				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
				WebElement message = driver.findElement(By.xpath("//android.widget.Textview[@text='firstmsg']"));
				String entire_msg=message.getText();
				String otp = Otp.verify_otp(entire_msg);
				WebElement otp_text= driver.findElement(By.className("android.widget.EditText"));
				otp_text.click();
				otp_text.sendKeys("otp");
				WebElement continue_btn=driver.findElement(By.xpath("//android.view.View[@content-desc='VERIFY_PHONE_OTP_BTN CONTINUE']"));
				continue_btn.click();
				
				
	}

}
