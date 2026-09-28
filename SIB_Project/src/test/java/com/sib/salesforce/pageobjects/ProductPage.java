package com.sib.salesforce.pageobjects;

import com.sib.salesforce.constant.Constant;
import com.sib.salesforce.constant.SOQLConstant;
import com.sib.salesforce.exception.CustomException;
import com.sib.salesforce.utils.APIUtils;
import com.sib.salesforce.utils.CommonUtils;
import com.sib.salesforce.utils.PlaywrightUtils;

public class ProductPage {

	/**
	 * Method to add Product with given Quantity
	 * @param quantity - Quantity
	 * @param productName - Name of Product
	 */
	public void addProduct(String quantity, String productName) {
		try {
			PlaywrightUtils.waitForAnElement(PlaywrightUtils.getElementByDynamicXpath(Constant.PAGE, 
					"Product on PLP", productName));
			PlaywrightUtils.click(PlaywrightUtils.getElementByDynamicXpath(Constant.PAGE, 
					"Product on PLP", productName));
			PlaywrightUtils.waitForAnElement(PlaywrightUtils.getElementByDynamicXpath(Constant.PAGE, 
					"Product on PDP", productName));
			PlaywrightUtils.clearAndSetValueUsingKeyboard(quantity, PlaywrightUtils.getElement("Quantity", Constant.PAGE));
			PlaywrightUtils.click(PlaywrightUtils.getElement("Add to Cart", Constant.PAGE));
			PlaywrightUtils.click(PlaywrightUtils.getElement("Continue Shopping", Constant.PAGE));
			PlaywrightUtils.waitForSec();
			String unitPrice = CommonUtils.roundOffDoubleToTwoDecPlaceToString(Double.parseDouble(getProductPrice(productName)));
			String totalPrice = CommonUtils.roundOffDoubleToTwoDecPlaceToString(Integer.parseInt(quantity) * Double.parseDouble(unitPrice));
			quantity = CommonUtils.roundOffDoubleToTwoDecPlaceToString(Double.parseDouble(quantity));
			CommonUtils.addKeyValueInGivenMap("UnitPrice", unitPrice, productName, Constant.productSpecs);
		    CommonUtils.addKeyValueInGivenMap("Quantity", quantity, productName, Constant.productSpecs);
		    CommonUtils.addKeyValueInGivenMap("TotalPrice", totalPrice, productName, Constant.productSpecs);
		    Constant.orderProductList.add(productName);
		    Constant.totalAmount = String.valueOf(CommonUtils
					.roundOffDoubleToTwoDecPlace(Double.parseDouble(Constant.totalAmount) + Double.parseDouble(totalPrice)));
		    Constant.totalQuantity = String.valueOf(CommonUtils
					.roundOffDoubleToTwoDecPlace(Double.parseDouble(Constant.totalQuantity) + Double.parseDouble(quantity)));
		    Constant.result = "'" + productName + "' Product is selected with '" + quantity + "' Quantity successfully";
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}
	
	/**
	 * Method to get Product Price through API
	 * @return - Price of the Product
	 */
	public String getProductPrice(String productName) {
		try {
			APIUtils.getSoqlResultInJSON(SOQLConstant.getQuery("Pricebook Entry", productName));
			return APIUtils.getSOQLFieldValueFromJSONConstant("UnitPrice");
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}
}
