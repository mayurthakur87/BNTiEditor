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

public class NewOtherNameTest {

	WebDriver driver;
	Scenario scenario;
	
	String username = BeforeAfter.username;
	String password = BeforeAfter.password;
	String URL = BeforeAfter.URL;
	
	@Before("@NewOther")
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
	
	@After("@NewOther")
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

	@Given("^The values \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void the_values(String type, String status, String name) throws Throwable {
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("[id= 'new-dropdown-menu-link']")).click();
		driver.findElement(By.cssSelector("[title='Add a new other name']")).click();

		// Type (Mandatory)
		Thread.sleep(2000);
		WebElement NTONtype = driver.findElement(By.cssSelector("[id= 'name_name_type_id']"));
		Select NTONtypeDD = new Select(NTONtype);
		NTONtypeDD.selectByVisibleText(type);

		// Status(Mandatory)
		Thread.sleep(2000);
		WebElement NTONstatus = driver.findElement(By.cssSelector("[id= 'name_name_status_id']"));
		Select NTONstatusDD = new Select(NTONstatus);
		NTONstatusDD.selectByVisibleText(status);

		// Name(Mandatory)
		driver.findElement(By.cssSelector("[id= 'name_name_element']")).sendKeys(name);

	}

	@When("^I click on save Other Name$")
	public void i_click_on_save_Other_Name() throws Throwable {

		// Save
		driver.findElement(By.cssSelector("[id= 'save-name']")).click();
	}

	@Then("^Other \"([^\"]*)\" is added to database$")
	public void other_is_added_to_database(String name) throws Throwable {
		
		Thread.sleep(2000);
		String IDAdd= driver.findElement(By.cssSelector("[id= 'tab-heading']")).getText();
		
		//Assert.assertFalse(IDAdd.toLowerCase().contains(name.toLowerCase()));
		Assert.assertTrue(IDAdd.toLowerCase().contains(name.toLowerCase()));
			
	}
		
}




