package com.qa.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderConfirmationPage extends BasePage{

	@FindBy(xpath = "//span[contains(text(),'Your Order Has Been Processed!')]")
	private WebElement orderConfirmationMessage;
	
	public void verifyOrderConfirmatiomMessage() {
		waitForVisibility(orderConfirmationMessage);
		Assert.assertTrue(orderConfirmationMessage.isDisplayed());
	}
}
