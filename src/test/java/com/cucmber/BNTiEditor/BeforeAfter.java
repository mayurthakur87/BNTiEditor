package com.cucmber.BNTiEditor;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class BeforeAfter {
	/*Setup Config */
	public static String username ="jamie";
	public static String password= "password";
	public static String URL = "http://icn.uat.oztaxa.com/nxl/editor/sign_in";
	
	@Before
	public void GlobalTestSetup(Scenario scenario) {

		System.out.println("---------GLOBAL TEST ENVIRONMENT---------");
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"//BROWSERS//geckodriver.exe");
	}

	@After
	public void GlobalTestTearDown(Scenario scenario) {

		System.out.println("----GLOBAL TEST ENVIRONMENT TEARDOWN-----");

	}

}
