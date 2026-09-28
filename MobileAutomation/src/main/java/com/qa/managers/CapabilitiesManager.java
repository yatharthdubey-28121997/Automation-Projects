package com.qa.managers;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

public class CapabilitiesManager {

    public static DesiredCapabilities getCaps() throws IOException {
        Properties props = new PropertiesManager().getProps();

        try{
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("browserName", props.getProperty("BrowserName"));
    		capabilities.setCapability("platformName", props.getProperty("PlatformName"));
    		capabilities.setCapability("platformVersion", props.getProperty("PlatformVersion"));
    		capabilities.setCapability("automationName", props.getProperty("AutomationName"));
            return capabilities;
        } catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
