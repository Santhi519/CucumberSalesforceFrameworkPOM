package com.salesforce.pages.base;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.utilities.Log4JUtility;

public class BasePage {
	protected  WebDriver driver;
	protected  WebDriverWait wait;
	protected Log4JUtility logObject=Log4JUtility.getInstance();
	protected Logger log;
	//protected ExtentReportsUtility report=ExtentReportsUtility.getInstance();
	
	public BasePage(WebDriver driver) {
		this.driver=driver;
		System.out.println("driver in basePage="+driver);
		PageFactory.initElements(driver, this);
		log=logObject.getLogger();
	}	
	
	//Passing the text data to the fields
			public void enterText(WebElement ele, String data, String objectName) {
				if (ele.isDisplayed()) {
					ele.clear();
					ele.sendKeys(data);
					log.info("Data is entered in " + objectName + " textbox");
				} else {
					log.info(objectName + " element is not displayed");
				}
			}
			//To clear the existing data in the fields
			public void clearElement(WebElement ele, String ObjectName) {
				if (ele.isDisplayed()) {
					ele.clear();
					log.info(ObjectName + " is cleared");
				} else {
					log.info(ObjectName + " element is not displayed");
				}
			}
			
		   //Clicking the button element
			public void clickElement(WebElement ele, String objectName) {
				if (ele.isEnabled()) {
					ele.click();
					log.info(objectName +" button is clicked");

				} else {
					log.info(objectName+" element is not enabled");

				}
			}
		   //Extracting the text from element
			public String getTextFromElement(WebElement ele, String objectName) {
				String data = ele.getText();
				log.info("Text is extracted from "+objectName);
				return data;
			}
			// To get the Title of the page
					public String getTitle() {
						String data = driver.getTitle();
						log.info("Title of the current webpage is "+data);
						return data;
					}
			//To switch to alert
					public Alert switchToAlert() {

						Alert alert = driver.switchTo().alert();
						log.info("switched to alert");
						return alert;
					}
			       //To accept the alert
					public void AcceptAlert(Alert alert) {

						log.info("Alert accepted");
						alert.accept();

					}
			      //To get the text from alert message
					public String getAlertText(Alert alert, String objectname) {
						log.info("etracting text in the " + objectname + "alert");
						String text = alert.getText();
						log.info("text is extracted from alert box is==" + text);
						return text;

					}
			    // To dismiss the alert
					public void dismisAlert() {

						Alert alert = switchToAlert();
						alert.dismiss();
						log.info("Alert dismissed");

					}
					//To Perform Mousehover on an element
					public void mouseOverOnElement(WebElement ele, String objName) {
						Actions action = new Actions(driver);
						action.moveToElement(ele).build().perform();
						log.info(" cursor moved to web element " + objName);
					}
			       //To rightclick on an element
					public void ContextClickOnElement(WebElement ele, String objName) {
						Actions action = new Actions(driver);
						action.contextClick(ele).build().perform();
						log.info("right click performed on web element " + objName);
					}
					//To click the element using action class
					public void mouseClickOnElement(WebElement ele, String objName) {
						Actions action = new Actions(driver);
						action.click(ele).build().perform();
						log.info("clicked on web element " + objName);
					}
					//To select the text using selectByVisibleText method
					public void selectByTextData(WebElement element, String text, String objName) {
						Select selectCity = new Select(element);
						selectCity.selectByVisibleText(text);
						log.info(objName + " selected " + text);

					}
					//To select the text using selectByIndex method
					public void selectByIndexData(WebElement element, int index, String objName) {
						waitForVisibility(element, 5, objName);
						Select selectCity = new Select(element);
						selectCity.selectByIndex(index);
						log.info(objName + " selected with index=" + index);

					}
					//To select the text using selectByValue method
					public void selectByValueData(WebElement element, String text, String objName) {
						Select selectCity = new Select(element);
						selectCity.selectByValue(text);
						log.info(objName + " selected ");
					}
					//To select the text from the List
					public WebElement selectFromListUsingText(List<WebElement> list, String text) {
						WebElement element = null;
						for (WebElement i : list) {
							if (i.getText().equalsIgnoreCase(text)) {
								log.info("selected=" + i.getText());
								element = i;
								break;
							}

						}
						return element;

					}
					//Method for pageLoadTimout
					public void waitUntilPageLoads() {
						log.info("waiting until page loads with 30 sec maximum");
						driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
					}
					//Method for fluent wait for the visibility of the element
					public void waitForVisibility(WebElement ele, int time, int pollingtime, String objectName) {
						FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
						wait
						.withTimeout(Duration.ofSeconds(time))
						.pollingEvery(Duration.ofSeconds(pollingtime))
						.ignoring(ElementNotInteractableException.class)
						.withMessage(objectName+" is not visible.fluent wait time expires");

						wait.until(ExpectedConditions.visibilityOf(ele));
						log.info(objectName + " is waited for visibility using fluent wait");
					}
			      //Method for explicit wait until the presence of the element
					public void WaitUntilPresenceOfElementLocatedBy(By locator, String objName) {
						log.info("waiting for an web element " + objName + " for its visibility");
						WebDriverWait wait = new WebDriverWait(driver,30);
						wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

					}
					//Method for explicit wait of the element to be clickable
					public void waitUntilElementToBeClickable(By locator, String objName) {
						log.info("waiting for an web element " + objName + " to be clickable");
						WebDriverWait wait = new WebDriverWait(driver,30);
						wait.until(ExpectedConditions.elementToBeClickable(locator));
					}
					//Method for explicit wait for the element to be visible
					public void waitForVisibility(WebElement ele, int time, String objectName) {
						log.info(objectName + " is waited for visibility ");
						WebDriverWait wait = new WebDriverWait(driver, 30);
						wait.until(ExpectedConditions.visibilityOf(ele));
					}
					//Method for explicit wait for the alert to be present
					public void waitForAlertPresent(int time) {
						log.info( "waited for alert to display ");
						WebDriverWait wait = new WebDriverWait(driver,30);
						wait.until(ExpectedConditions.alertIsPresent());
					}
			        //To switch to new window
					public void switchToNewWindowFrom(String currentWindowHandle) {
						Set<String> allWindowHandles = driver.getWindowHandles();
						log.info(""+allWindowHandles.size());
						for (String handle : allWindowHandles) {
							if (!currentWindowHandle.equalsIgnoreCase(handle))
								driver.switchTo().window(handle);
						}
						log.info("switched to new window");
					}
}
