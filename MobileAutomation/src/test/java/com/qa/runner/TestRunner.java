package com.qa.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features={"src/test/resources/features"},
		glue= {"com/qa/steps", "com/qa/hooks"},
		tags= "@Demo",
		dryRun = false,
		monochrome=true,
		plugin={"pretty"
		}
)
public class TestRunner {

}
