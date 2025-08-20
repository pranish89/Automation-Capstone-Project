package Amazon;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import junit.framework.Assert;
import utilities.Helper;
import utilities.MSAutomationExcel;

public class FilterProducts {

	WebDriver driver;

	// @BeforeTest

	public void driverInit() throws Exception {
		Helper.setupDriver();
	}

	// @BeforeClass

	public void login() throws Exception {

		// Helper.setupDriver();
		BaseClass.login();

	}

	@Test(priority = -1)
	public void searchProducts() throws Exception {
		driver = Helper.getDriver();
		Helper.maxImplicitWait();

		Helper.getTitle();
		
		WebElement search_box = driver
				.findElement(By.xpath("//input[@id = 'twotabsearchtextbox' and @placeholder='Search Amazon.in']"));
		search_box.click();
		search_box.clear();
		search_box.sendKeys(MSAutomationExcel.getExcelData("Sheet2", 2, 0));
		search_box.sendKeys(Keys.ENTER);
		Helper.maxImplicitWait();

	}

	@Test(dependsOnMethods = { "searchProducts" })
	public void filterProducts() {
		// filter by brand "DELL"
		WebElement filter = driver.findElement(By.xpath("//span[text()='Dell']"));
		filter.click();
		Helper.maxImplicitWait();
		System.out.println("Dell Laptops are displayed");
		
		List<WebElement> product = driver.findElements(By.xpath("//span[contains(text(),'Dell Inspiron')]"));
		String laptop_name = product.get(0).getText();
		System.out.println("Laptop Name is" + laptop_name);
		product.get(0).click();
		System.out.println("The product details page is displayed");
		Helper.maxImplicitWait();
		
		/*WebElement selected_product = driver.findElement(By.xpath("//span[contains(text(),'" + laptop_name + "')]"));
		System.out.println(selected_product.getText());
		Helper.maxImplicitWait();

		Assert.assertEquals(laptop_name, selected_product.getText());*/

	}

	// @AfterClass

	public void logout() {

		BaseClass.logout();
		Helper.tearDown();

	}

	// @AfterTest

	public void driverClose() {
		Helper.tearDown();
	}

}
