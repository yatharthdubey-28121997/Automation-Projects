package com.qa.hooks;

import java.io.IOException;

import com.qa.managers.AppiumManager;
import com.qa.managers.BrowserStackManager;
import com.qa.managers.PropertiesManager;
import com.qa.managers.SeleniumManager;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

	AppiumManager appiumManager;
	SeleniumManager seleniumManager;
	BrowserStackManager browserStackManager;
	
	@Before
	public void beforeScenario(Scenario scenario) throws Exception {
		String env = null;
		try {
			env = new PropertiesManager().getProps().getProperty("Environment");
		} catch (IOException e) {
			e.printStackTrace();
		}
    	switch (env.toLowerCase()) {
		case "web":
			seleniumManager = new SeleniumManager();
			seleniumManager.initializeDriver();
			break;
		case "mobile":
			appiumManager = new AppiumManager();
			appiumManager.initializeDriver();
			break;
		case "browserstack":
			browserStackManager = new BrowserStackManager();
			browserStackManager.intializeDriver();
			break;
		default:
			throw new RuntimeException("'" + env + "' Environment not supported");
		}
	}

	@After
	public void afterScenario(Scenario scenario) throws IOException {
//		new BasePage().closeBrowser();
	}
}
