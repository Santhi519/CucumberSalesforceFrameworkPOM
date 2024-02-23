package com.salesforce.steps;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.salesforce.pages.home.HomePage;
import com.salesforce.pages.login.LoginPage;
import com.salesforce.utilities.Constants;
import com.salesforce.utilities.Log4JUtility;
import com.salesforce.utilities.PropertiesUtility;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesforceLoginStepDef {
	protected static Logger log;
	public  WebDriver driver;
	protected static Log4JUtility logObject=Log4JUtility.getInstance();
	LoginPage login;
	HomePage home;
	
	public  void launchBrowser(String browserName) {
		switch(browserName) {
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			
			break;
		case "chrome":
		 WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			
			break;
		}
		System.out.println(browserName+" browser opened");
	}
	
	public  void goToUrl(String url) {
		driver.get(url);
		log.info(url+ "is entered");
	}

	public  void closeBrowser() {
		driver.close();
		log.info("current browser closed");
	}
	
	@BeforeAll
	public static void setUpBeforeAllScenarios() {
		log=logObject.getLogger();
	}
	@Before
	public void setUpEachScenario() {
		launchBrowser("chrome");
	}
	@After
	public void tearDown() {
		closeBrowser();
	}
	@AfterStep
	public void after_each_step(Scenario sc) {
		if(sc.isFailed()){
			sc.log(sc.getName()+" is "+sc.getStatus());
		}
	}
	
	@Given("the url {string}")
	public void the_url(String string) {
		String url=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "url");
		goToUrl(url);
	}

	@When("I enter Username as {string}")
	public void i_enter_username_as(String username) {
		login=new LoginPage(driver);
	    login.enterUsername(username);
	}

	@When("I enter Password as {string}")
	public void i_enter_password_as(String password) {
	    login.enterPassword(password);
	}

	@When("I click on Login button")
	public void i_click_on_login_button() {
	    driver=login.clickLoginButton();
	}

	@Then("Error message should be displayed as {string}")
	public void error_message_should_be_displayed_as(String string) {
	   String actual=login.geterrorMessage();
//	   System.out.println(login.getusername());
	   if(login.getusername().equalsIgnoreCase("santhik@salesforce.com")) {
		   String expected="Please enter your password.";   
		   Assert.assertEquals(actual, expected);
	   }else if(login.getusername().equalsIgnoreCase("123")){
		   String expected="Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		   Assert.assertEquals(actual, expected);
	   }
//	  Assert.assertEquals(actual, expected);
	   
	}

	@Then("Home Page should be displayed")
	public void home_page_should_be_displayed() {
		home=new HomePage(driver);
		String actual=home.getTitleofthehome();
		String expected="Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(actual, expected);   
	}

	@When("I click on RememberMe checbox")
	public void i_click_on_remember_me_checbox() {
	    login.clickRememberMe();
	}

	@When("I click on UserMenu")
	public void i_click_on_user_menu() {
		home=new HomePage(driver);
		String actual=home.getTitleofthehome();
		String expected="Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(actual, expected);
		home.clickusermenu();
	}

	@When("Select Logout")
	public void select_logout() {
		driver=home.clicklogout();
	}

	@Then("Login Page should be displayed")
	public void login_page_should_be_displayed() throws InterruptedException {
		Thread.sleep(2000);
		String actual=login.getTitleofthelogin();
		String expected="Login | Salesforce";
		Assert.assertEquals(actual, expected);
	}

	@Then("Username should be displayed")
	public void username_should_be_displayed() {
		String actual=login.getusername();
		String expected="santhik@salesforce.com";
		Assert.assertEquals(actual, expected);
	}

	@When("I click Forgot Password")
	public void i_click_forgot_password() {
		login=new LoginPage(driver);
	    login.clickforgotpassword();
	}

	@Then("Forgot your password page should be displayed")
	public void forgot_your_password_page_should_be_displayed() {
		String expected="Forgot Your Password | Salesforce";
		String actual=login.getTitleofthelogin();
		Assert.assertEquals(actual, expected);
	}
	
	@When("I enter forgotusername as {string}")
	public void i_enter_forgotusername_as(String string) {
		login=new LoginPage(driver);
		login.enterforgotusername(string);
	}

	@When("I click on submit")
	public void i_click_on_submit() {
	    login.clicksubmit();
	}

	@Then("Password Reset message should be displayed")
	public void password_reset_message_should_be_displayed() {
		String actual1=login.getpasswordresetMessage();
		log.info("The Forgot password message displayed is: \n"+actual1);
	}
}
