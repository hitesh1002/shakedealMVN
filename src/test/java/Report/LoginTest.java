package Report;

import static org.testng.Assert.assertEquals;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class LoginTest extends BaseTest {
	
	@BeforeTest
	public void StartUp() {
		driver = new FirefoxDriver(); 		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test(alwaysRun=true)
	public void VerifyTitle() {	
		test = reports.createTest("verifyTitle");
		driver.get("https://shakedeal.com");
		String title = driver.getTitle();
		assertEquals("ShakeDeal - Procurement Made Easy | Home of B2B Commerce", title);
	}	
	
	@Test(alwaysRun=true)
	public void Login() {
		test = reports.createTest("Verify Login Panel");
		driver.get("https://shakedeal.com");
		driver.findElement(By.xpath(".//*[@id='sw_dropdown_764']/a/div")).click();
		driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("hitesh.kumar@onjection.com");
		driver.findElement(By.xpath(".//*[@id='password']")).sendKeys("hitesh@123");
		driver.findElement(By.xpath("/div/div/div/div/div/div/div/form/div[2]/div/button")).click();
				
	}
	
	@Test(alwaysRun=true)
	public void SearchProduct() {
		test = reports.createTest("Verify the Product on Home Page");
		driver.get("https://shakedeal.com");
		driver.findElement(By.xpath(".//*[@id='scroll_list_99']/div/div/div[1]/div/div[2]/div/form/div[1]/a")).click();
		
	}
	
	
}
