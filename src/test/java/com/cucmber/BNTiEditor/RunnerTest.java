package com.cucmber.BNTiEditor;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = { 
		 "classpath:NewSearchTest.feature",
		 "classpath:NewScientificNameTest.feature",
		 "classpath:NewReferenceTest.feature",
		 "classpath:NewPhraseNameTest.feature",
		 "classpath:NewAuthorTest.feature",
		 "classpath:LoginNxlEditorTest.feature",
		 "classpath:NewOtherNameTest.feature",
		 "classpath:NewInstanceTest.feature",
		 "classpath:ClearEditorTest.feature"
		
}, 
					tags = { "@All" }, 
					plugin = { "pretty:target/cucumber-html-report", 
								"html:target/cucumber-htmlreport",
								"json:target/cucumber.json", 
								"junit:target/cucumber-results.xml" })
public class RunnerTest {
	// "classpath:LoginNxlEditorTest.feature",
	// "classpath:NewOtherNameTest.feature",
	// "classpath:NewInstanceTest.feature",
	// "classpath:NewSearchTest.feature",
	// "classpath:ClearEditorTest.feature",
	// "classpath:NewScientificNameTest.feature",
	// "classpath:NewReferenceTest.feature",
	// "classpath:NewPhraseNameTest.feature",
	// "classpath:NewAuthorTest.feature",
	
}
