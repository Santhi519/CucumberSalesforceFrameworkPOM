package com.salesforce.pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.salesforce.pages.base.BasePage;

public class LoginPage extends BasePage {

	@FindBy(id="username")WebElement username;
	@FindBy(id="password")WebElement password;
	@FindBy(id="Login")WebElement login;
	@FindBy(id="error")WebElement error;
	@FindBy(id="rememberUn")WebElement remember_check;
	@FindBy(id="forgot_password_link")WebElement forgot_pwd;
	@FindBy(id="un")WebElement username1;
	@FindBy(id="continue")WebElement submit;
	@FindBy(className="message")WebElement message;
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	public void enterUsername(String data) {
		enterText(username, data, "username");
	}
	public void enterPassword(String data) {
		enterText(password, data, "password");
	}
	public WebDriver clickLoginButton() {
		clickElement(login,"Login Button");
		return driver;
	}
	public String geterrorMessage() {
		String error_msg=getTextFromElement(error, "Error Message");
		return error_msg;
	}
	public String getTitleofthelogin() {
		waitUntilPageLoads();
		return getTitle();
	}
	public void clickRememberMe() {
		clickElement(remember_check, "Remember Me");
	}
	public String getusername() {
		String name=username.getAttribute("value");
		return name;
	}
	public void clickforgotpassword() {
		clickElement(forgot_pwd, "Forgot your password?");
	}
	public void enterforgotusername(String data) {
		enterText(username1, data, "username");
	}
	public void clicksubmit() {
		clickElement(submit, "Continue");
	}
	public String getpasswordresetMessage() {
		String msg=getTextFromElement(message,"Password reset Message" );
		return msg;
	}
	
}
