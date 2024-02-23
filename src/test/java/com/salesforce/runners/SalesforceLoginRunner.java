package com.salesforce.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/features/SalesforceLogin.feature"},
		glue= {"com.salesforce.steps"},
		monochrome = true,
		dryRun = false,
		plugin = {"pretty","html:target/cucumber-pom-selenium.html"}
		//tags = "@cal and @sub"
		
		)
public class SalesforceLoginRunner extends AbstractTestNGCucumberTests {

}
