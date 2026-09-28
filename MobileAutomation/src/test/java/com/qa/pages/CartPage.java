package com.qa.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage{
	
	@FindBy(xpath = "//a[@title='Added to cart']")
	private WebElement cartIconOnHomePage;
	
	@FindBy(xpath = "//span[contains(text(),'Shopping Cart')]")
	private WebElement shoppingCartIcon;
	
	public void navigateToCartFromHomePage() {
		click(cartIconOnHomePage);
	}

	public void waitForCartPageToLoad() {
		waitForVisibility(shoppingCartIcon);
		Assert.assertTrue(shoppingCartIcon.isDisplayed());
	}
}
