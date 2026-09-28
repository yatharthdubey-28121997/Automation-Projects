package com.sib.salesforce.steps;

import org.junit.Assert;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.sib.salesforce.constant.Constant;
import com.sib.salesforce.exception.CustomException;
import com.sib.salesforce.pageobjects.APIPage;
import com.sib.salesforce.pageobjects.AccountPage;
import com.sib.salesforce.pageobjects.CartPage;
import com.sib.salesforce.pageobjects.ContractPage;
import com.sib.salesforce.pageobjects.OrderPage;
import com.sib.salesforce.pageobjects.ProductPage;
import com.sib.salesforce.pageobjects.QuotePage;
import com.sib.salesforce.utils.CommonUtils;
import com.sib.salesforce.utils.PlaywrightUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepFile {

	AccountPage accountPage = new AccountPage();
	QuotePage quotePage = new QuotePage();
	APIPage apiPage = new APIPage();
	OrderPage orderPage = new OrderPage();
	CartPage cartPage = new CartPage();
	ProductPage productPage = new ProductPage();
	ContractPage contractPage = new ContractPage();
	
	@Given("User login into the Salesforce {string} Environment")
	public void user_login_into_the(String value) throws Throwable {
		Constant.env = value;
		CommonUtils.testConnection();
		CommonUtils.salesforcelogin();
		PlaywrightUtils.waitForMoreSec(5);
		ExtentCucumberAdapter.addTestStepLog(Constant.result);
	}

	@Given("User swtiches to {string} App")
	public void user_switches_to_app(String appName) throws Throwable {
		CommonUtils.switchToApp(appName);
		ExtentCucumberAdapter.addTestStepLog(Constant.result);
	}

	@Given("User switches to {string} user in {string}")
	public void user_switches_to_user_in(String userName, String page) throws Throwable {
		CommonUtils.switchToUserByAPI(userName);
		ExtentCucumberAdapter.addTestStepLog(Constant.result);
	}

	@When("User clicks on the {string} button on {string} page")
	public void user_clicks_on_the_something_button(String button, String page) throws Throwable {
		switch (button) {
		case "Log in to Experience as User": 
			PlaywrightUtils.click(PlaywrightUtils.getElement("Log in to Experience as User Down Arrow", 
					Constant.PAGE));
			PlaywrightUtils.waitForMoreSec(1);
			PlaywrightUtils.click(PlaywrightUtils.getElementByDynamicXpath(Constant.PAGE, 
					"Get Span Tag Locator by Exact Text", "Log in to Experience as User"));
			break;
		case "Subscriptions":
			PlaywrightUtils.click(PlaywrightUtils.getElement("User Menu", Constant.PAGE));
			PlaywrightUtils.click(PlaywrightUtils.getElement(button, Constant.PAGE));
			break;
		case "Renew":
			contractPage.getContractIdUsingOrderReferenceNumber();
			PlaywrightUtils.click(PlaywrightUtils.getElementByDynamicXpath(Constant.PAGE, "Get Renew Button for Contract", Constant.contractNumber));
			break;
		default:
			PlaywrightUtils.waitForAnElement(PlaywrightUtils.getElement(button, Constant.PAGE));
			PlaywrightUtils.click(PlaywrightUtils.getElement(button, Constant.PAGE));
			ExtentCucumberAdapter.addTestStepLog("'" + button + "' Button clicked successfully");
			break;
		}
	}

	@Then("User enters {string} in the {string} field on {string} page")
	public void user_enters_in_the_field_on_Page(String value, String field, String page) throws Throwable {
		switch (field) {
		case "Purchase Order":
			PlaywrightUtils.waitForMoreSec(2);
			PlaywrightUtils.waitForAnElement(PlaywrightUtils.getElement("Purchase Order", Constant.PAGE));
			String poNumber = "PO-" + CommonUtils.generateRandomNumber(7);
			PlaywrightUtils.setValueUsingKeyboard(poNumber, PlaywrightUtils.getElement("Purchase Order", Constant.PAGE));
			ExtentCucumberAdapter.addTestStepLog("'" + poNumber + "' value entered in '" + field + "' field successfully");
			break;
		default:
			PlaywrightUtils.waitForAnElement(PlaywrightUtils.getElement(field, Constant.PAGE));
			PlaywrightUtils.setValueUsingKeyboard(value, PlaywrightUtils.getElement(field, Constant.PAGE));
			ExtentCucumberAdapter.addTestStepLog("'" + value + "' value entered in '" + field + "' field successfully");
			break;
		}
	}

	@Then("User selects {string} in the {string} dropdown on {string} page")
	public void user_selects_in_the_dropdown(String value, String field, String page) throws Throwable {
		switch (field) {
		default:
			PlaywrightUtils.waitForAnElement(PlaywrightUtils.getElement(field, Constant.PAGE));
			PlaywrightUtils.selectByLabelFromDropdown(value, PlaywrightUtils.getElement(field, Constant.PAGE));
			break;
		}
	}

	@Given("User refreshes the browser")
	public void user_refreshes_the_browser() throws Throwable {
		PlaywrightUtils.refreshBrowser(Constant.PAGE);
	}
	
	@Then("Verify the {string} message on {string} page")
	public void verify_the_message(String element, String page) throws Throwable {
		switch (element) {
		default:
			PlaywrightUtils.waitForAnElement(PlaywrightUtils.getElement(element, Constant.PAGE));
			ExtentCucumberAdapter.addTestStepLog("'" + element + "' displayed successfully");
			break;
		}
	}

	@Then("Verify {string} field value is {string} on {string} page")
	public void verify_field_value_is_on_page(String fieldName, String value, String page) throws Throwable {
		switch (fieldName+"_"+value) {
		default:
			PlaywrightUtils.waitForMoreSec(4);
			CommonUtils.verifyFieldValue(value, fieldName, Constant.PAGE);
			break;
		}
		ExtentCucumberAdapter.addTestStepLog(Constant.result);
	}

	@Then("User compares copied values {string} from {string} and {string} page")
	public void user_compares_the_addresses_from_and_page(String condition, String page1, String page2) throws Throwable {
		CommonUtils.compareMaps(condition);
	}

	@Then("User updates {string} field value to {string} on {string} page")
	public void user_updtaes_field_value_to_on_page(String fieldName, String value, String page) throws Throwable {
		switch (fieldName) {
		default:
			throw new CustomException("'" + fieldName + "' case is not defined in respective Stepdef");
		}
	}

	@When("User checks {string} checkbox on {string} page")
	public void user_checks_checkbox_on_page(String field, String page) throws Throwable {
		switch (field) {
		default:
			PlaywrightUtils.checkCheckbox(PlaywrightUtils.getElement(field, Constant.PAGE));
			break;
		}
	}

	@Then("User waits for element {string} to load")
	public void user_waits_for_element_to_load(String locator) throws Throwable{
		PlaywrightUtils.waitForAnElement(PlaywrightUtils.getElement(locator,Constant.PAGE));
	}

	@Given("User logouts from {string} user in {string}")
	public void user_logouts_from_user_in(String userName, String page) throws Throwable {
		CommonUtils.userLogout(userName);
		ExtentCucumberAdapter.addTestStepLog("Successfully logout from '" + userName + "'");
	}

	@When("User updates the {string} field on {string} as {string}")
	public void user_updates_the_field_on_account(String field, String object, String value) throws Throwable {
		switch (object) {
		default:
			throw new CustomException("'" + object + "' case is not defined in respective Stepdef");
		}
	}

	@Given("User login into the ECommerce {string} Environment")
	public void user_login_into_the_ecommerce(String value) throws Throwable {
		Constant.env = value;
		CommonUtils.storelogin();
	}
	
	@Then("Verify {string} is created on {string} page")
	public void verify_is_created(String value, String page) throws Throwable {
		switch (value) {
		default:
			throw new CustomException("'" + value + "' case is not defined in respective Stepdef");
		}
	}
	
	@When("User navigates to newly created {string} object")
	public void user_navigates_to_created_object(String object) throws Throwable {
		switch (object) {
		default:
			throw new CustomException("'" + object + "' case is not defined in respective Stepdef");
		}
	}
	
	@When("Verify {string} having {string} populated correctly on {string} page")
	public void verify_are_having_populated_correctly_on_page(String object, String field, String page) throws Throwable {
		switch (object+"_"+field) {
		default:
			throw new CustomException("'" + object +"_"+ field + "' case is not defined in respective Stepdef");
		}
	}
	
	@Then("User updates {string} field value to {string} on {string} object in {string} through API")
	public void user_updtaes_field_value_to_object_in_through_api(String fieldName, String value, String object, String app) throws Throwable {
		switch (object) {
		default:
			throw new CustomException("'" + object + "' case is not defined in respective Stepdef");
		}
	}
	
	@Given("User waits for {string} to be created in {string}")
	public void user_waits_for_to_be_created_in(String object, String app) throws Throwable{
	    switch (object) {
	    default:
			throw new CustomException("'" + object + "' case is not defined in respective Stepdef");
		}
	}
	
	@Given("User selects {string} Contact from {string} Page")
	public void user_selects_contact_from_page(String contactName, String page) {
	    CommonUtils.searchContactInSalesforce(contactName);
	    ExtentCucumberAdapter.addTestStepLog(Constant.result);
	}
	
	@Then("User waits for {string} Page to load")
	public void user_waits_for_page_to_load(String page) {
	    PlaywrightUtils.waitForAnElement(PlaywrightUtils.getElement(page, Constant.PAGE));
	}
	
	@When("User clears the {string} from {string} Page")
	public void user_clears_the_from_page(String string, String string2) {
	    cartPage.clearCart();
	    ExtentCucumberAdapter.addTestStepLog(Constant.result);
	}
	
	@When("User navigates to {string} Category from {string} page")
	public void user_navigates_to_category_from_page(String category, String page) throws Throwable {
		switch (category) {
		case "Products & Services":
			PlaywrightUtils.waitForAnElement(PlaywrightUtils.getElementByDynamicXpath(Constant.PAGE, "Category", category));
			PlaywrightUtils.click(PlaywrightUtils.getElementByDynamicXpath(Constant.PAGE, "Category", category));
			ExtentCucumberAdapter.addTestStepLog("Navigated to '" + category + "' Category successfully");
			break;
		default:
			throw new CustomException("Case not defined in stepdef for '" + category +"' Category");
		}
	}
	
	@Given("User adds {string} Product with {string} Quantity from {string} page")
	public void user_adds_product_with_quantity_from_page(String productName, String quantity, String page) {
	    productPage.addProduct(quantity, productName);
	    ExtentCucumberAdapter.addTestStepLog(Constant.result);
	}
	
	@Given("Verify {string} object is created in {string} with all required values")
	public void verify_object_is_created_in_with_all_required_values(String object, String page) {
	    switch (object) {
		case "Order":
			orderPage.waitForOrderActivation();
			apiPage.verifyOrderObjectValues(object);
			break;
		case "Order Item":
			for(String product : Constant.orderProductList)
				apiPage.verifyOrderItemObjectValues(object, product);
			break;
		case "Contract":
			apiPage.verifyContractObjectValues(object);
			break;
		case "Subscription":
			for(String product : Constant.orderProductList)
				apiPage.verifySubscriptionObjectValues(object, product);
			break;
		case "Quote":
			apiPage.verifyQuoteObjectValues(object);
			break;
		case "Quote Line":
			for(String product : Constant.orderProductList)
				apiPage.verifyQuoteLineObjectValues(object, product);
			break;
		case "Cart":
			apiPage.verifyCartObjectValues(object);
			break;
		case "Cart Item":
			for(String product : Constant.orderProductList)
				apiPage.verifyCartItemObjectValues(object, product);
			break;
		default:
			throw new CustomException("Case not defined in stepdef for '" + object +"' Category");
		}
	}
	
	@Given("User adds all the Products defined in {string} file")
	public void user_adds_all_the_products_defined_in_file(String string) {
	    throw new io.cucumber.java.PendingException();
	}
	
	@Given("User update {string} fields to be visible on {string} page")
	public void user_update_fields_to_be_visible_on_page(String string, String string2) {
	    contractPage.updateContractFieldsForRenewal(Constant.contractId);
	}
	
	@Then("Verify all the {string} are added to the Cart on {string} Page")
	public void verify_all_the_are_added_to_the_cart_on_page(String string, String string2) {
		PlaywrightUtils.waitForMoreSec(2);
	    Assert.assertEquals(PlaywrightUtils.getElement("Cart Count", Constant.PAGE).all().size() > 0, true);
	}
}
