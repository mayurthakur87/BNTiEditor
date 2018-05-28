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
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginNxlEditorTest {

	public WebDriver driver;
	Scenario scenario;

	String URL = BeforeAfter.URL;
	
	@Given("^when I open browser$")
	public void when_I_open_browser() throws Throwable {

		System.out.println("---------------------------------------");
		System.out.println("--------Test Environment Setup---------");
		//this.scenario = scenario;
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(URL);
		
	}

	@When("^put in \"([^\"]*)\" and \"([^\"]*)\"$")
	public void put_in_and(String User, String Pwd) throws Throwable {

		driver.findElement(By.xpath("//*[@id='sign_in_username']")).sendKeys(User);
		driver.findElement(By.xpath("//*[@id='sign_in_password']")).sendKeys(Pwd);
		driver.findElement(By.xpath("//*[@title='Select this to sign in']")).click();
		Thread.sleep(2000);

	}

	@Then("^I should be able to login$")
	public void i_should_be_able_to_login() throws Throwable {

		Assert.assertTrue("PASS", driver.getCurrentUrl().equalsIgnoreCase("http://icn.uat.oztaxa.com/nxl/editor/"));
	}

	@Then("^I should not be able to login$")
	public void i_should_not_be_able_to_login() throws Throwable {

		Assert.assertFalse("FAIL", driver.getCurrentUrl().equalsIgnoreCase("http://icn.uat.oztaxa.com/nxl/editor/"));

	}
	@After("@Login")
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
}
