package com.cucmber.BNTiEditor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class NewSearchTest {

	WebDriver driver;
	Scenario scenario;

	String username = BeforeAfter.username;
	String password = BeforeAfter.password;
	String URL = BeforeAfter.URL;
	
	@Before("@Search")
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
	
	@Given("^The User is logged in$")
	public void the_User_is_logged_in() throws Throwable {
		
		System.out.println("User is logged in ");

	}

	@When("^User selects Authors from search criterion picklist and enters \"([^\"]*)\"$")
	public void user_selects_Authors_from_search_criterion_picklist_and_enters(String Author_Name) throws Throwable {

		Thread.sleep(2000);
		driver.findElement(By.cssSelector("[id='search-target-button']")).click();
		;

		driver.findElement(By.cssSelector("[id='search-target-item-authors']")).click();

		driver.findElement(By.cssSelector("[id='query-string-field']")).sendKeys(Author_Name);

	}

	@When("^clicks on search$")
	public void clicks_on_search() throws Throwable {

		driver.findElement(By.cssSelector("[id='query-submit']")).click();

	}

	@Then("^System responds with callout from search box reading Please fill out this field$")
	public void system_responds_with_callout_from_search_box_reading_Please_fill_out_this_field() throws Throwable {

		// Search Pop up Help test
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement field = driver.findElement(By.cssSelector("[id='query-string-field']"));
		// Boolean is_valid = (Boolean)js.executeScript("return
		// arguments[0].checkValidity();", field);
		String message = (String) js.executeScript("return arguments[0].validationMessage;", field);
		String ExpectedMessage = "Please fill out this field.";

		boolean msgDisplayed = message.toLowerCase().contains(ExpectedMessage.toLowerCase());

		Assert.assertTrue(msgDisplayed);
	}

	@Then("^System responds with results of \"([^\"]*)\"$")
	public void system_responds_with_results_of(String Author_Name) throws Throwable {

		Thread.sleep(2000);
		// Tab heading
		String testname = driver.findElement(By.cssSelector("[id='tab-heading']")).getText();

		System.out.println("testname" + testname);
		Assert.assertTrue(testname.toLowerCase().contains(Author_Name.toLowerCase()));
		{
			System.out.println("Test Pass------------------------------" + testname);
		}

	}

	@Then("^System responds with results of all \"([^\"]*)\"$")
	public void system_responds_with_results_of_all(String Author_Name) throws Throwable {
		Thread.sleep(2000);
		String testname = driver.findElement(By.cssSelector("[id='search-results-summary']")).getText();
		System.out.println("testname     " + testname);
		boolean result = (testname.replaceAll("\\s+", "").contains(("No records found").trim().replaceAll("\\s+", "")));
		System.out.println("result" + result);
		Assert.assertFalse(result);
		{
			System.out.println("Test Pass------------------------------" + testname);
		}

	}

	@When("^User selects References from search criterion picklist and enters \"([^\"]*)\"$")
	public void user_selects_References_from_search_criterion_picklist_and_enters(String References_Name)
			throws Throwable {

		Thread.sleep(2000);
		driver.findElement(By.cssSelector("[id='search-target-button']")).click();

		driver.findElement(By.cssSelector("[id='search-target-item-references']")).click();

		driver.findElement(By.cssSelector("[id='query-string-field']")).sendKeys(References_Name);

	}

	@When("^User selects Names from search criterion picklist and enters \"([^\"]*)\"$")
	public void user_selects_Names_from_search_criterion_picklist_and_enters(String Names_Name) throws Throwable {
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("[id='search-target-button']")).click();

		driver.findElement(By.cssSelector("[id='search-target-item-names']")).click();

		driver.findElement(By.cssSelector("[id='query-string-field']")).sendKeys(Names_Name);
	}
	@After("@Search")
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
}

