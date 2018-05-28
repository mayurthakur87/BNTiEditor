package com.cucmber.BNTiEditor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class NewInstanceTest {

	WebDriver driver;
	Scenario scenario;

	String username = BeforeAfter.username;
	String password = BeforeAfter.password;
	String URL = BeforeAfter.URL;

	@Before("@NewInstance")
	public void Setup(Scenario scenario) {

		/*------------------------COMMON------------------------------*/
		System.out.println("---------------------------------------");
		System.out.println("--------Test Environment Setup---------");
		this.scenario = scenario;
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		/*------------------------------------------------------------*/

		driver.get(URL);
		driver.findElement(By.xpath("//*[@id='sign_in_username']")).sendKeys(username);
		driver.findElement(By.xpath("//*[@id='sign_in_password']")).sendKeys(password);
		driver.findElement(By.xpath("//*[@title='Select this to sign in']")).click();
	}

	@After("@NewInstance")
	public void teardown(Scenario scenario) throws IOException {

		System.out.println("Inside  After " );
		scenario.write("Finished Scenario "+ scenario.getName());
		if (scenario.isFailed()) {
			// take ScreenShot
			{
				System.out.println("------Test has Failed---");
				final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png"); // stick it in the report
				System.out.println("inside Report");
			}
		}
		System.out.println("--------Test Environment Closed--------");
		System.out.println("---------------------------------------");

		driver.close();
		// driver.quit();
	}

	@Given("^Searching  a  new Reference test with \"([^\"]*)\"$")
	public void searching_a_new_Reference_test_with(String Name) throws Throwable {

		// Search for reference
		driver.findElement(By.cssSelector("[id='query-string-field']")).sendKeys(Name);
		driver.findElement(By.cssSelector("[id='query-submit']")).click();

		// Click new Instance
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("[id='name-instances-tab']")).click();

	}

	@Given("^The values	\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void the_values(String Reference, String Type, String On_Page) throws Throwable {
	
		// As an instance type
		Thread.sleep(2000);
		WebElement type = driver.findElement(By.cssSelector("[id='instance_instance_type_id']"));
		Select typeDD = new Select(type);
		typeDD.selectByVisibleText(Type);

		// Instance Page
		driver.findElement(By.cssSelector("[id='instance_page']")).sendKeys(On_Page);

		// Appears in Reference
		driver.findElement(By.cssSelector("[id='instance-reference-typeahead']")).sendKeys(Reference);
		
		Thread.sleep(2000);
		WebElement ref = driver.findElement(By.cssSelector("[id='instance_instance_type_id']"));
		Select refDD = new Select(ref);
		refDD.selectByIndex(1);
		driver.findElement(By.cssSelector("[id='new_instance']")).click();

	}

	@When("^I click on create instance$")
	public void i_click_on_create_instance() throws Throwable {

		// Click on Create instance
		driver.findElement(By.cssSelector("[id='save-new-instance-btn']")).click();

	}

	@Then("^Instance is created$")
	public void instance_is_created() throws Throwable {
		
		Thread.sleep(2000);
		String message = driver.findElement(By.cssSelector("[id='search-result-details-info-message-container']")).getText();
		
		String messageExpected = "Record created. (Refresh tab to create another.)";
		
		Assert.assertTrue(message.toLowerCase().contains(messageExpected.toLowerCase()));

	}

	@Then("^Show instances for name button is displayed$")
	public void show_instances_for_name_button_is_displayed() throws Throwable {

		Assert.assertTrue(driver.findElement(By.cssSelector("[id='show-instances-for-name-btn']")).isDisplayed());

	}

}
