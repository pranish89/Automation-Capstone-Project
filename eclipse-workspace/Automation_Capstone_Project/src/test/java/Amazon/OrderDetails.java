package Amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import junit.framework.Assert;
import utilities.Helper;

public class OrderDetails {

	WebDriver driver;

	// @BeforeTest

	public void driverInit() throws Exception {
		Helper.setupDriver();
	}

	// @BeforeClass

	public void Login() throws Exception {
		BaseClass.login();
	}

	@Test

	public void Order_Details() {
		driver = Helper.getDriver();

		// clicking on orders link
		WebElement orders = driver.findElement(By.xpath("//span[text()='& Orders']"));
		orders.click();
		String title = Helper.getTitle();
		Assert.assertEquals("Your Orders", title);

	}
	
	//This will run even when one or more test cases failed or skipped.
	
	@AfterClass(alwaysRun=true)

	public void logout() {

		BaseClass.logout();

	}

	@AfterTest(alwaysRun=true)

	public void driverClose() {
		Helper.tearDown();
	}

}
