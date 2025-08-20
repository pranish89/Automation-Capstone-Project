package myntra_cult;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import junit.framework.Assert;

public class Challenge1 {
	
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
				.setAppPackage("com.myntra.android")
				.setAppActivity("com.myntra.android.activities.react.ReactActivity")
				.setAutoGrantPermissions(true)
				.setNoReset(true)
				.setFullReset(false)
				;

				//point at the running server
				URL server = new URL("http://127.0.0.1:4723/wd/hub");

				//driver creation
				driver = new AndroidDriver(server,option);
					}

 // @Test
  
 /* public void perfomancedata() {
	  List<String> perf_data = driver.getSupportedPerformanceDataTypes();
	  Iterator<String> i = perf_data.iterator();
	  while(i.hasNext()) {
		  System.out.println(i.next());
	  
	  List<List<Object>> data =driver.getPerformanceData("com.myntra.android", "batteryinfo", 5);
	  System.out.println(data);
	  
	  //cpuinfo
	  List<List<Object>> cpu=driver.getPerformanceData("com.android.chrome", "networkinfo", 10);
	  HashMap<String,Integer> readable_data = new HashMap<>();
	  for(int i=0; i < cpu.get(0).size(); i++)
	  { 
		  int val;
		  if(cpu.get(1).get(i) == null) {
			  val =0;
		  }
		 else {
				val = Integer.parseInt((String) cpu.get(1).get(i));
			  }
		  readable_data.put((String) cpu.get(0).get(i),val);
			  }
	  System.out.println(cpu);
		  }*/
	
	@BeforeClass
	public void login() {
		LoginMyntra.login(driver);
	}
	  
  @Test(priority=-1)
  public void AsearchItem() throws InterruptedException
  {
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

	  //Search for lens
	  WebElement search_button = driver.findElement(AppiumBy.xpath("//android.widget.ImageView[@index='1']"));
	  search_button.click();
	  
	
	  //closing the auto popup	  
		Thread.sleep(5000);

	/*try {
	  driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(9)")).click();
	  }
	  catch(Exception e) {
		  System.out.println("ELement not found");
	  }
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));*/
	  
	  //Again clicking on the search icon
	  WebElement search_edit = driver.findElement(AppiumBy.accessibilityId("search_default_search_text_input"));
	  // xpath("//android.widget.EditText[@content-desc='search_default_search_text_input']"));
	  search_edit.click();
	  
	  //Sending the keys "Nike Shoes" through keyboard
	  driver.pressKey(new KeyEvent(AndroidKey.N));
	  driver.pressKey(new KeyEvent(AndroidKey.I));
	  driver.pressKey(new KeyEvent(AndroidKey.K));
	  driver.pressKey(new KeyEvent(AndroidKey.E));
	  driver.pressKey(new KeyEvent(AndroidKey.SPACE));
	  driver.pressKey(new KeyEvent(AndroidKey.S));
	  driver.pressKey(new KeyEvent(AndroidKey.H));
	  driver.pressKey(new KeyEvent(AndroidKey.O));
	  driver.pressKey(new KeyEvent(AndroidKey.E));
	  driver.pressKey(new KeyEvent(AndroidKey.S));
	  driver.pressKey(new KeyEvent(AndroidKey.ENTER));
	  System.out.println("Click on the search button and Search text is entered successfully");

  }
  
  @Test(priority=99, dependsOnMethods = {"AsearchItem"})
  public void selectItem() throws InterruptedException
  {
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	  
	  //select 1st item
	  WebElement item = driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc='plp_product_2']"));
	  //androidUIAutomator("new UiSelector().text(\"Downshifter 12 Men's Shoes\")"));
	  item.click();
	  
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	  System.out.println("First item is selected");
	  
	  //Swiping the item image through create swipe class methods
	  Swipe.action(driver,"RIGHT");
	  Swipe.action(driver,"LEFT");
	  System.out.println("Swiping Right and Left is done");
	  
	  //Tap on image
	  WebElement tap_item= driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc='pdp_image_0']"));
	  tap_item.click();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	  
	  //clicking on the image to view full screen
	  WebElement select_item = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(0)"));
	  
	  //Zoom in & Zoom out
	  Zoom obj=new Zoom();
	  obj.zoomin(driver, select_item);
	  System.out.println("Zoom in & Zoom Out is done");
	  
	  //Clicking on the back button
	  WebElement back_button = driver.findElement(AppiumBy.id("com.myntra.android:id/btn_pdp_fs_back"));
	  back_button.click();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

	  //Tap on wishlist, after logging as myntra_cult user, Add to Wishlist will not be displayed , only icon and add to bag is displayed.
	  WebElement wishlist = driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc='save_button']"));
	  wishlist.click();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	  System.out.println("The Item is wishlisted " +wishlist.isSelected());
	  
	  //Adding to bag
	  WebElement add_to_bag = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Add to Bag']"));
	  add_to_bag.click();
	  
	  //Selecting size
	  WebElement size = driver.findElement(AppiumBy.accessibilityId("size_select_6"));	  
	  size.click();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	  
	  WebElement done =driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc='buy_done_button']"));
	  done.click();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	  System.out.println("Added the item to the bag");
	  
	  //bag icon
	  WebElement bag_icon = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='1']"));
	  String text=bag_icon.getText();
	  Assert.assertEquals("Bag count is not equal", "1", text);
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	  
	  //bag click
	  WebElement bag = driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc='bag']"));
	  bag.click();

	  
	  //move to bag
	  /*driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
     WebElement go_to_bag = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Go to Bag']")) ;
     go_to_bag.click();*/
	  
     }
  
  
  }


