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

public class NewScientificNameTest {

	WebDriver driver;
	Scenario scenario;
	
	String username = BeforeAfter.username;
	String password = BeforeAfter.password;
	String URL = BeforeAfter.URL;
	
	@Before("@ScientificName")
	public void Setup(Scenario scenario)
	{	
		
		/*------------------------COMMON------------------------------*/
		System.out.println("---------------------------------------");
		System.out.println("--------Test Environment Setup---------");
		this.scenario= scenario;
		driver= new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		/*------------------------------------------------------------*/
		
		driver.get(URL);
		driver.findElement(By.xpath("//*[@id='sign_in_username']")).sendKeys(username);
		driver.findElement(By.xpath("//*[@id='sign_in_password']")).sendKeys(password);
		driver.findElement(By.xpath("//*[@title='Select this to sign in']")).click();
	}
	@After("@ScientificName")
	public void teardown(Scenario scenario) throws IOException {
		System.out.println("Inside  After " + scenario.getName());
		scenario.write("Finished Scenario");
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
		//driver.quit();
	}
	
	@Given("^The values \"([^\"]*)\",\"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void the_values(String NStype, String NSrank, String NSstatus, String NSparent, String NSname, String arg6)
			throws Throwable {

		Thread.sleep(2000);
		driver.findElement(By.cssSelector("[id= 'new-dropdown-menu-link']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("[title='Add a new scientific name']")).click();

		Thread.sleep(2000);
		WebElement type = driver.findElement(By.cssSelector("[id= 'name_name_type_id']"));
		Select typeDD = new Select(type);
		typeDD.selectByVisibleText(NStype);

		// Clicking Values in Rank
		Thread.sleep(2000);
		WebElement rank = driver.findElement(By.cssSelector("[id= 'name_name_rank_id']"));
		Select rankDD = new Select(rank);
		rankDD.selectByVisibleText(NSrank);

		// Clicking Values in Status
		Thread.sleep(2000);
		WebElement status = driver.findElement(By.cssSelector("[id= 'name_name_status_id']"));
		Select statusDD = new Select(status);
		statusDD.selectByVisibleText(NSstatus);
		System.out.println("half is done");
		// Clicking Values in Parent
		driver.findElement(By.cssSelector("[id= 'name-parent-typeahead']")).sendKeys(NSparent);

		// Clicking Values in Name--new Name for new Taxa
		driver.findElement(By.cssSelector("[id= 'name_name_element']")).sendKeys(NSname);

	}

	@When("^I click on save$")
	public void i_click_on_save() throws Throwable {

		driver.findElement(By.cssSelector("[id= 'save-name']")).click();

	}

	@Then("^New Scientific Name is added to the database$")
	public void new_scientific_name_is_added_to_the_database() throws Throwable {
		
		String TabHeading = "New Scientific Name";
		String testname = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/h4")).getText();
		Assert.assertTrue(testname.toLowerCase().contains(TabHeading.toLowerCase()));

	}

	@Then("^New Scientific Name is \"([^\"]*)\" is added to database$")
	public void new_scientific_name_is_added_to_database(String name) throws Throwable {
			
		
		Thread.sleep(2000);
		String IDAdd= driver.findElement(By.cssSelector("[id= 'tab-heading']")).getText();
		
		System.out.println("name "+name);
		System.out.println("IDAdd "+IDAdd);
		
		//Assert.assertFalse(IDAdd.toLowerCase().contains(name.toLowerCase()));
		Assert.assertTrue(IDAdd.toLowerCase().contains(name.toLowerCase()));
	}

}