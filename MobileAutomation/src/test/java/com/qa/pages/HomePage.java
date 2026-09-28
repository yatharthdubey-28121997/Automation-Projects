package com.qa.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	@FindBy(xpath = "//a[@class='logo']")
	private WebElement homePageLogo;

	public void waitForHomePageToLoad() {
		waitForVisibility(homePageLogo);
		Assert.assertTrue(homePageLogo.isDisplayed());
	}
	
	public void productSelect(String product) {
		click(productByName(product));
	}
}
