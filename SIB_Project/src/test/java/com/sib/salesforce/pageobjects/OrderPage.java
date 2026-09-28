package com.sib.salesforce.pageobjects;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.sib.salesforce.constant.Constant;
import com.sib.salesforce.constant.SOQLConstant;
import com.sib.salesforce.exception.CustomException;
import com.sib.salesforce.utils.APIUtils;
import com.sib.salesforce.utils.PlaywrightUtils;

public class OrderPage {
	
	/**
	 * Method to wait for Order Activation
	 * @param orderId - Order Id
	 */
	public void waitForOrderActivation() {
		Constant.orderReferenceNumber = PlaywrightUtils.getCurrentURL(Constant.PAGE).split("orderNumber=")[1];
		String orderNumber = null;
		try {
			boolean found = false;
			for(int i=0; i<20; i++) {
				try {
					APIUtils.getSoqlResultInJSON(SOQLConstant.getQuery("Order Activation", Constant.orderReferenceNumber));
					if(!APIUtils.getSOQLFieldValueFromJSONConstant("Status").equals("Activated"))
						throw new CustomException("Status is not Activated");
					orderNumber = APIUtils.getSOQLFieldValueFromJSONConstant("OrderNumber");
					ExtentCucumberAdapter.addTestStepLog("Order Number: '"+ orderNumber + "' is 'Activated'");
					found = true;
					break;
				} catch (Exception e) {
					PlaywrightUtils.waitForMoreSec(5);
				}
			}
			if(!found)
				throw new CustomException("Order Number '"+ orderNumber + "' is not 'Activated'");
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}
}

