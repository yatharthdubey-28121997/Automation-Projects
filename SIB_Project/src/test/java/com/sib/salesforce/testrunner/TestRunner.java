package com.sib.salesforce.testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features={"src/test/resources/Feature"},
		glue= {"com/sib/salesforce/steps","com/sib/salesforce/hooks"},
		tags= "@Renewal",
		plugin={"pretty",
				"json:target/cucumber-reports/Cucumber.json",
				"html:test-output",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
				},
		monochrome = true,
		dryRun = false
		)
public class TestRunner {}