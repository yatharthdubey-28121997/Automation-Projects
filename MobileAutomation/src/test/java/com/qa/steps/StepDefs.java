package com.qa.steps;

import com.qa.managers.PropertiesManager;
import com.qa.pages.CartPage;
import com.qa.pages.CheckoutPage;
import com.qa.pages.HomePage;
import com.qa.pages.OrderConfirmationPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs {
	
	HomePage home = new HomePage();
	CheckoutPage checkout = new CheckoutPage();
	CartPage cart = new CartPage();
	OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage();

	@Given("User navigates to {string} URL")
	public void user_navigates_to_url(String application) throws Throwable {
		home.navigate(new PropertiesManager().getProps().getProperty(application));
	}
	
	@Given("User should be navigated to {string} page")
	public void user_should_be_navigated_to_page(String page) {
		switch (page) {
		case "Home":
			home.waitForHomePageToLoad();	
			home.takePercySnapshot("On Home Page");
			break;
		case "Cart":
			cart.refreshBrowser();
			cart.waitForCartPageToLoad();
			break;
		default:
			throw new RuntimeException("Please define '" + page + "' case in StepDefs file");
		}
	}

	@When("User adds {string} Product to Cart from {string} page")
	public void user_adds_product_to_cart_from_page(String product, String page) {
	    home.productSelect(product);
	}

	@When("User navigates to {string} page")
	public void user_navigates_to_page(String string) {
	    cart.navigateToCartFromHomePage();
	    cart.takePercySnapshot("On Cart Page after Selecting Product");
	}

	@When("User clicks on {string} button")
	public void user_clicks_on_button(String button) {
		switch (button) {
		case "Checkout":
			checkout.clickCheckoutButton();
			break;
		case "Continue":
			checkout.clickContinueButton();
			break;
		case "Confirm Order":
			checkout.clickConfirmOrderButton();
			break;
		default:
			throw new RuntimeException("Please define '" + button + "' case in StepDefs file");
		}
	    
	}

	@When("User selects {string} option")
	public void user_selects_option(String string) {
	    checkout.selectGuestCheckoutOption();
	    checkout.takePercySnapshot("Checkout Page");
	}

	@When("User fills {string} and {string} fields")
	public void user_fills_and_fields(String string, String string2) {
	    checkout.fillPersonalDetails();
	    checkout.takePercySnapshot("After filling Personal Details");
	}

	@Then("Verify {string} message displayed")
	public void verify_message_displayed(String string) {
	    orderConfirmationPage.verifyOrderConfirmatiomMessage();
	    checkout.takePercySnapshot("Order Confrimation Page");
	}
}
