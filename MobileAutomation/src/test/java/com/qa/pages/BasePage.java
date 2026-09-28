package com.qa.pages;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.browserstack.PercySDK;
import com.qa.managers.AppiumManager;
import com.qa.managers.BrowserStackManager;
import com.qa.managers.PropertiesManager;
import com.qa.managers.SeleniumManager;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BasePage {
	
	private WebDriver driver;

    public BasePage(){
    	String env = null;
		try {
			env = new PropertiesManager().getProps().getProperty("Environment");
		} catch (IOException e) {
			e.printStackTrace();
		}
    	switch (env.toLowerCase()) {
		case "web":
			this.driver = new SeleniumManager().getDriver();
			PageFactory.initElements(this.driver, this);
			break;
		case "mobile":
			this.driver = new AppiumManager().getDriver();
			PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
			break;
		case "browserstack":
			this.driver = new BrowserStackManager().getDriver();
			PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
			break;
		default:
			throw new RuntimeException("'" + env + "' Environment not supported");
		}
    }
    
    public void navigate(String url) {
    	driver.get(url);
    }

    public WebElement productByName(String product) {
    	return driver.findElement(By.xpath(String.format(
    			"(//a[text()='%s'])[1]/ancestor::div[@class='thumbnails list-inline']/div[1]//a[@title='Add to Cart']",
    			product)));
    }
    
    public void waitForVisibility(WebElement e) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void waitForVisibility(By e) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(e));
    }

    public void clear(WebElement e) {
        waitForVisibility(e);
        e.clear();
    }

    public void click(WebElement e) {
        waitForVisibility(e);
        e.click();
    }

    public void click(WebElement e, String msg) {
        waitForVisibility(e);
        e.click();
    }

    public void click(By e, String msg) {
        waitForVisibility(e);
        driver.findElement(e).click();
    }

    public void sendKeys(WebElement e, String txt) {
        waitForVisibility(e);
        e.sendKeys(txt);
    }

    public void sendKeys(WebElement e, String txt, String msg) {
        waitForVisibility(e);
        e.sendKeys(txt);
    }

    public String getAttribute(WebElement e, String attribute) {
        waitForVisibility(e);
        return e.getAttribute(attribute);
    }

    public String getAttribute(By e, String attribute) {
        waitForVisibility(e);
        return driver.findElement(e).getAttribute(attribute);
    }
    
    public String getText(WebElement e, String msg) {
        String txt;
        txt = getAttribute(e, "text");
        return txt;
    }

    public String getText(By e, String msg) {
        String txt;
        txt = getAttribute(e, "text");
        return txt;
    }
    
    public void selectByValue(WebElement e, String value) {
    	Select dropdown = new Select(e);
    	dropdown.selectByValue(value);
    }
    
    public void selectByVisibleTest(WebElement e, String value) {
    	Select dropdown = new Select(e);
    	dropdown.selectByVisibleText(value);
    }

    public void closeBrowser() {
        driver.close();
    }
    
    public void scrollDown() {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("window.scrollBy(0,350)", "");
    }
    
    public void takePercySnapshot(String screenshotName) {
    	PercySDK.screenshot(driver, screenshotName);
    }
    
    public void refreshBrowser() {
    	waitForSomeSec();
    	driver.navigate().refresh();
    }
    
    public void waitForSomeSec() {
    	try {
			TimeUnit.MILLISECONDS.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}
