package com.qa.managers;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumManager {
	
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public WebDriver getDriver() {
		return SeleniumManager.driver.get();
	}

	public void setDriver(WebDriver driver2) {
		SeleniumManager.driver.set(driver2);
	}

	@SuppressWarnings("static-access")
	public void initializeDriver() throws Exception {
		ChromeDriver driver = null;
		if (driver == null) {
			ChromeOptions chromeOptions= new ChromeOptions(); 
			chromeOptions.addArguments("--disable-notifications");
			driver = new ChromeDriver(chromeOptions);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.manage().window().maximize();
			this.driver.set(driver);
		}

	}
}
