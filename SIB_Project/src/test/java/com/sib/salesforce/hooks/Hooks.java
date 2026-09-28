package com.sib.salesforce.hooks;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.sib.salesforce.constant.Constant;
import com.sib.salesforce.dataprovider.LocatorsFileReader;
import com.sib.salesforce.managers.PlaywrightManager;
import com.sib.salesforce.utils.CommonUtils;
import com.sib.salesforce.utils.ExcelUtils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

	
	@Before
	public void beforeScenario(Scenario scenario) throws Exception {
		
		LocatorsFileReader.readLocatorProperties();
		ExcelUtils.openStream();
		new PlaywrightManager();
		Constant.PAGE = PlaywrightManager.intializePlaywright();
		Constant.productsJsonObj = (JSONObject)new JSONParser()
				.parse(new FileReader(Constant.PRODUCTSJSONPATH));
		CommonUtils.reInitializeTheCpqFieldValues();
		Constant.writingInExcel = false;
	}
	
	@After
	public void afterScenario(Scenario scenario) throws Exception {

		try {
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			if (scenario.isFailed()) {
				scenario.log("Test Case Failed");
				byte[] screenshot = Constant.PAGE.screenshot();
				scenario.attach(screenshot, "image/png", screenshotName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(Constant.writingInExcel)
			ExcelUtils.writeInExcelFileUsingStream();
		PlaywrightManager.closePlaywright();
	}
}
