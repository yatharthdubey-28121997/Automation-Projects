package com.qa.managers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;


public class BrowserStackManager {
	
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	public WebDriver getDriver() {
		return BrowserStackManager.driver.get();
	}
	
	public void intializeDriver() {
		MutableCapabilities capabilities = new MutableCapabilities();
        HashMap<String, String> bstackOptions = new HashMap<>();
        bstackOptions.putIfAbsent("source", "cucumber-java:sample-master:v1.2");
        capabilities.setCapability("bstack:options", bstackOptions);
        WebDriver driver = null;
        try {
			driver = new RemoteWebDriver(
			        new URL("https://hub.browserstack.com/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
        BrowserStackManager.driver.set(driver);
	}
}
