package com.sib.salesforce.pageobjects;

import com.sib.salesforce.constant.Constant;
import com.sib.salesforce.exception.CustomException;
import com.sib.salesforce.utils.PlaywrightUtils;

public class CartPage {

	/**
	 * Method to Clear Cart
	 */
	public void clearCart() {
		try {
			PlaywrightUtils.waitForAnElement(PlaywrightUtils.getElement("Cart", Constant.PAGE));
			PlaywrightUtils.waitForMoreSec(4);
			if(PlaywrightUtils.getElement("Cart Count", Constant.PAGE).all().size() == 1) {
				PlaywrightUtils.click(PlaywrightUtils.getElement("Cart", Constant.PAGE));
				PlaywrightUtils.waitForMoreSec(2);
				PlaywrightUtils.waitForAnElement(PlaywrightUtils.getElement("Clear Cart", Constant.PAGE));
				PlaywrightUtils.waitForMoreSec(1);
				PlaywrightUtils.click(PlaywrightUtils.getElement("Clear Cart", Constant.PAGE));
				PlaywrightUtils.waitForMoreSec(2);
				PlaywrightUtils.waitForAnElement(PlaywrightUtils.getElement("Oops, your cart is empty.", Constant.PAGE));
			}
			else
				Constant.result = "Cart is already empty";
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}
}
