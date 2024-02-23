package com.salesforce.pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.salesforce.pages.base.BasePage;

public class HomePage extends BasePage {
	@FindBy(id="userNav")WebElement user_menu;
    @FindBy(xpath="//*[@id=\"userNav-menuItems\"]/a[5]")WebElement logout;
	public HomePage(WebDriver driver) {
		   super(driver);
	}
	public String getTitleofthehome() {
		waitUntilPageLoads();
		return getTitle();
	}
	public void clickusermenu() {
		clickElement(user_menu, "User menu");
	}
	public WebDriver clicklogout() {
		clickElement(logout,"Logout");
		return driver;
		
	}
}
