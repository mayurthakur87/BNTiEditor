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

public class ClearEditorTest {

	WebDriver driver;
	Scenario scenario;

	String username = BeforeAfter.username;
	String password = BeforeAfter.password;
	String URL = BeforeAfter.URL;
	
	@Before("@Clear")
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
	
	
	@Given("^User selects NXL Editor from New Scientific Name screen$")
	public void user_selects_NXL_Editor_from_New_Scientific_Name_screen() throws Throwable {
				
		driver.findElement(By.cssSelector("[id='new-dropdown-menu-link']")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/ul/li[2]/ul/li[1]/a")).click();

		driver.findElement(By.partialLinkText("NXL Editor")).click();

	}

	@Then("^System clears out$")
	public void system_clears_out() throws Throwable {

		System.out.println("System clears out");
		boolean isPresent;

		try {
			driver.findElement(By.cssSelector("[id='save-name-at-top']")).isDisplayed();
			isPresent = true;
		} catch (Exception e) {
			isPresent = false;
		}

		Assert.assertFalse(isPresent);

	}

	@After("@Clear")
	public void teardown(Scenario scenario) throws IOException {

		System.out.println("COMMON AFTER");
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
