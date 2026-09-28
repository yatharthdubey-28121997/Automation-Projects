package com.qa.managers;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;

import com.qa.global.GlobalParams;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class AppiumManager {
	
	private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

	public WebDriver getDriver() {
		return AppiumManager.driver.get();
	}

	public void setDriver(AppiumDriver driver2) {
		AppiumManager.driver.set(driver2);
	}

	@SuppressWarnings("static-access")
	public void initializeDriver() throws Exception {
		AppiumDriver driver = null;
		if (driver == null) {
			try {
				driver = new AndroidDriver(new URL(GlobalParams.hubURL), 
						CapabilitiesManager.getCaps());
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				this.driver.set(driver);
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
		}

	}
}
