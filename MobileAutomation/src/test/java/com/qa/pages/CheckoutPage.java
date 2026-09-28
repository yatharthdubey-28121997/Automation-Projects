package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qa.utils.Utils;

public class CheckoutPage extends BasePage{
	
	@FindBy(xpath = "//button[@title='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath = "//form//div[@class='pull-right mb20']//a[@title='Checkout']")
	private WebElement checkoutButton;
	
	@FindBy(xpath = "//input[@id='accountFrm_accountguest']")
	private WebElement guestCheckoutOption;
	
	@FindBy(xpath = "//button[@id='checkout_btn']")
	private WebElement confirmOrderButton;
	
	@FindBy(xpath = "//input[@id='guestFrm_firstname']")
	private WebElement firstName;

	@FindBy(xpath = "//input[@id='guestFrm_lastname']")
	private WebElement lastName;

	@FindBy(xpath = "//input[@id='guestFrm_email']")
	private WebElement email;

	@FindBy(xpath = "//input[@id='guestFrm_address_1']")
	private WebElement address1;

	@FindBy(xpath = "//input[@id='guestFrm_city']")
	private WebElement city;

	@FindBy(xpath = "//select[@id='guestFrm_zone_id']")
	private WebElement region_state;

	@FindBy(xpath = "//input[@id='guestFrm_postcode']")
	private WebElement zip_postalCode;

	@FindBy(xpath = "//select[@id='guestFrm_country_id']")
	private WebElement country;

	public void clickContinueButton() {
		click(continueButton);
	}
	
	public void clickCheckoutButton() {
		waitForSomeSec();
		waitForSomeSec();
		waitForSomeSec();
		click(checkoutButton);
	}
	
	public void clickConfirmOrderButton() {
		click(confirmOrderButton);
	}
	
	public void selectGuestCheckoutOption() {
		click(guestCheckoutOption);
	}
	
	public void fillPersonalDetails() {
		String fName = "FN" + Utils.generateRandomString(5);
		String lName = "LN" + Utils.generateRandomString(5);
		String emailValue = "test" + fName.toLowerCase() + lName.toLowerCase() + "@test.com";
		sendKeys(firstName, fName);
		sendKeys(lastName, fName);
		sendKeys(email, emailValue);
		sendKeys(address1, "111 Wall Street");
		sendKeys(city, "New York");
		sendKeys(zip_postalCode, "10004");
		selectByVisibleTest(country, "United States");
		selectByVisibleTest(region_state, "New York");
	}
}
