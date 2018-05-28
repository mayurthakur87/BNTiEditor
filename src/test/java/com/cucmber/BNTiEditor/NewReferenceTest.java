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

public class NewReferenceTest {

	WebDriver driver;
	Scenario scenario;
	
	String username = BeforeAfter.username;
	String password = BeforeAfter.password;
	String URL = BeforeAfter.URL;

	@Before("@NewReference")
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

	@After("@NewReference")
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
		// driver.quit();
	}

	@Given("^The values \"([^\"]*)\", \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void the_values(String Type, String Title, String Published, String Author, String Role) throws Throwable {

		driver.findElement(By.cssSelector("[id= 'new-dropdown-menu-link']")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/ul/li[2]/ul/li[11]/a")).click();

		// Type
		Thread.sleep(2000);
		WebElement type = driver.findElement(By.cssSelector("[id= 'reference_ref_type_id']"));
		Select typeDD = new Select(type);
		typeDD.selectByVisibleText(Type);

		// Title
		driver.findElement(By.cssSelector("[id= 'reference_title']")).sendKeys(Title);

		// Title
		driver.findElement(By.cssSelector("[id= 'reference-author-typeahead']")).click();

		// Published tag
		driver.findElement(By.cssSelector("[id= 'reference_published']")).click();

		// Author
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("[id= 'reference-author-typeahead']")).sendKeys(Author);

		// Author Role
		driver.findElement(By.cssSelector("[id= 'reference_notes']")).click();
		WebElement role = driver.findElement(By.cssSelector("[id= 'reference_ref_author_role_id']"));
		Select roleDD = new Select(role);
		roleDD.selectByVisibleText(Role);

	}

	@When("^I click on save reference$")
	public void i_click_on_save_reference() throws Throwable {

		// Save
		Thread.sleep(2000);
		// driver.findElement(By.cssSelector("[id='bottom-save-btn']")).click();
		driver.findElement(By.cssSelector("[id='top-save-btn']")).click();
		Thread.sleep(2000);

	}

	@Then("^Reference  \"([^\"]*)\" is added to the database$")
	public void reference_is_added_to_the_database(String Title) throws Throwable {

		Thread.sleep(2000);
		String testname = driver.findElement(By.cssSelector("[id='tab-heading']")).getText();
		Assert.assertTrue(testname.toLowerCase().contains(Title.toLowerCase()));
	}

}
