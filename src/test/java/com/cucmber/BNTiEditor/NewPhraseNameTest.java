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

public class NewPhraseNameTest {

	WebDriver driver;
	Scenario scenario;
	
	String username = BeforeAfter.username;
	String password = BeforeAfter.password;
	String URL = BeforeAfter.URL;
	
	@Before("@NewPhraseName")
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
	@After("@NewPhraseName")
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
	
	@Given("^The values at \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void the_values_at(String Type, String Rank, String Status, String Parent, String Pname, String Author) throws Throwable 
	{	
		driver.findElement(By.cssSelector("[id= 'new-dropdown-menu-link']")).click();
		driver.findElement(By.cssSelector("[title='Add a new phrase name']")).click();
				
		//type(Mandatory)
		Thread.sleep(2000);	
		WebElement NPNtype = driver.findElement(By.cssSelector("[id='name_name_type_id']"));
		Select NPNtypeDD = new Select(NPNtype);
		NPNtypeDD.selectByVisibleText(Type);
	
		System.out.println("inside Rank "+ Rank);
		//rank(Mandatory)
		Thread.sleep(2000);	
		WebElement NPNrank = driver.findElement(By.cssSelector("[id='name_name_rank_id']"));
		Select NPNrankDD = new Select(NPNrank);
		NPNrankDD.selectByVisibleText(Rank);
		
		//status(Mandatory)
		Thread.sleep(2000);	
		WebElement NPNstatus = driver.findElement(By.cssSelector("[id='name_name_status_id']"));
		Select NPNstatusDD = new Select(NPNstatus);
		NPNstatusDD.selectByVisibleText(Status);
		
		System.out.println("inside parent "+ Parent);
		//parent(Mandatory)
		driver.findElement(By.cssSelector("[id= 'name-parent-typeahead']")).sendKeys(Parent);
				
		System.out.println(Pname);
		//Name 
		driver.findElement(By.cssSelector("[id= 'name_name_element']")).sendKeys(Pname);
				
	}

	@When("^I click on save phrase Name$")
	public void i_click_on_save_phrase_Name() throws Throwable {
		//Save
		driver.findElement(By.cssSelector("[id= 'save-name']")).click();
	}

	@Then("^Phrase \"([^\"]*)\" is added to database$")
	public void phrase_is_added_to_database(String pname) throws Throwable {
		
		Thread.sleep(2000);
		String IDAdd= driver.findElement(By.cssSelector("[id='tab-heading']")).getText();
		
		Assert.assertTrue(IDAdd.toLowerCase().contains(pname.toLowerCase()));
	
	}

}
