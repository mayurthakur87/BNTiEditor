package com.cucmber.BNTiEditor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class NewAuthorTest {

	WebDriver driver;
	Scenario scenario;
	
	String username = BeforeAfter.username;
	String password = BeforeAfter.password;
	String URL = BeforeAfter.URL;

	@Before("@NewAuthor")
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

	@After("@NewAuthor")
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
	@Given("^The values \"([^\"]*)\", \"([^\"]*)\"$")
	public void the_values(String name, String abbr) throws Throwable {


		driver.findElement(By.cssSelector("[id= 'new-dropdown-menu-link']")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/ul/li[2]/ul/li[9]/a")).click();

		// Name
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("[id= 'author_name']")).sendKeys(name);

		// Abbreviation
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("[id= 'author_abbrev']")).sendKeys(abbr);

	}

	@When("^I click on save author$")
	public void i_click_on_save_author() throws Throwable {

		driver.findElement(By.cssSelector("[id= 'author-save']")).click();
	}

	@Then("^Author \"([^\"]*)\" is added to the database$")
	public void author_is_added_to_the_database(String abbr) throws Throwable {

		Thread.sleep(2000);
		String IDAdd = driver.findElement(By.cssSelector("[id='tab-heading']")).getText();

		Assert.assertTrue(IDAdd.toLowerCase().contains(abbr.toLowerCase()));

	}

}
