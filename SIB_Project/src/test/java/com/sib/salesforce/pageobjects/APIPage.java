package com.sib.salesforce.pageobjects;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sib.salesforce.constant.Constant;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.sib.salesforce.constant.SOQLConstant;
import com.sib.salesforce.exception.CustomException;
import com.sib.salesforce.managers.FileReaderManager;
import com.sib.salesforce.utils.APIUtils;
import com.sib.salesforce.utils.CommonUtils;
import com.sib.salesforce.utils.SOQLUtils;

public class APIPage {
	
	String todaysDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	String oneYearDate = CommonUtils.addDays(CommonUtils.addMonths(todaysDate, 12), -1);
	String defaultTerm = "12";
	
	/**
	 * Method to verify Cart Object Values
	 * @param object - Name of the Salesforce Object
	 */
	public void verifyCartObjectValues(String object) {
		try {
			Constant.baseURL = FileReaderManager.getInstance().getConfigReader().getPayloadURL();
			String query = SOQLConstant.getQuery(object);
			APIUtils.getSoqlResultInJSON(query);
			Constant.cartId = APIUtils.getIdForCurrentSOQLObjectFromJSON();
			String[] fields = SOQLUtils.removedIdFieldFromSOQLSelectFields(SOQLUtils.getSelectFieldsFromSOQLQuery(query));
			String[] values = {Constant.newStatus, Constant.closedStatus, Constant.totalQuantity, Constant.totalAmount, Constant.totalAmount, Constant.totalAmount, Constant.totalAmount};
			APIUtils.validateResponsesThroughJSON(values, fields);
			String expectedFromAPI = Constant.expectedResult;
			String actualFromAPI = Constant.actualResult;
			ExtentCucumberAdapter.addTestStepLog("WebCart Object Values");
			ExtentCucumberAdapter.addTestStepLog("URL: " + Constant.baseURL + "/" + APIUtils.getIdForCurrentSOQLObjectFromJSON());
			ExtentCucumberAdapter.addTestStepLog(expectedFromAPI + Constant.expectedResult);
			ExtentCucumberAdapter.addTestStepLog(actualFromAPI + Constant.actualResult);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}
	
	/**
	 * Method to verify Cart Item Object Values
	 * @param object - Name of the Salesforce Object
	 * @param productName - Name of the Product
	 */
	public void verifyCartItemObjectValues(String object, String productName) {
		try {
			String unitPrice = (String) Constant.productSpecs.get(productName).get("UnitPrice"),
					quantity = (String) Constant.productSpecs.get(productName).get("Quantity");
			String query = SOQLConstant.getQuery(object, Constant.cartId, productName);
			APIUtils.getSoqlResultInJSON(query);
			String[] fields = SOQLUtils.removedIdFieldFromSOQLSelectFields(SOQLUtils.getSelectFieldsFromSOQLQuery(query));
			String[] values = {quantity, unitPrice, Constant.totalAmount, Constant.totalAmount};
			APIUtils.validateResponsesThroughJSON(values, fields);
			String expectedFromAPI = Constant.expectedResult;
			String actualFromAPI = Constant.actualResult;
			ExtentCucumberAdapter.addTestStepLog("Cart Item Object Values for '" + productName + "' product");
			ExtentCucumberAdapter.addTestStepLog("URL: " + Constant.baseURL + "/" + APIUtils.getIdForCurrentSOQLObjectFromJSON());
			ExtentCucumberAdapter.addTestStepLog(expectedFromAPI + Constant.expectedResult);
			ExtentCucumberAdapter.addTestStepLog(actualFromAPI + Constant.actualResult);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}
	
	/**
	 * Method to verify Quote Object Values
	 * @param object - Name of the Salesforce Object
	 * 
	 */
	public void verifyQuoteObjectValues(String object) {
		try {
			String query = SOQLConstant.getQuery(object, Constant.cartId);
			APIUtils.getSoqlResultInJSON(query);
			Constant.quoteId = APIUtils.getIdForCurrentSOQLObjectFromJSON();
			String[] fields = SOQLUtils.removedIdFieldFromSOQLSelectFields(SOQLUtils.getSelectFieldsFromSOQLQuery(query));
			String[] values = {Constant.approvedStatus, todaysDate, oneYearDate, defaultTerm, Constant.totalAmount, Constant.totalAmount, Constant.totalAmount, Constant.totalAmount};
			APIUtils.validateResponsesThroughJSON(values, fields);
			String expectedFromAPI = Constant.expectedResult;
			String actualFromAPI = Constant.actualResult;
			ExtentCucumberAdapter.addTestStepLog("Quote Object Values");
			ExtentCucumberAdapter.addTestStepLog("URL: " + Constant.baseURL + "/" + APIUtils.getIdForCurrentSOQLObjectFromJSON());
			ExtentCucumberAdapter.addTestStepLog(expectedFromAPI + Constant.expectedResult);
			ExtentCucumberAdapter.addTestStepLog(actualFromAPI + Constant.actualResult);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}
	
	/**
	 * Method to verify Quote Line Object Values
	 * @param object - Name of the Salesforce Object
	 * @param productName - Name of the Product
	 */
	public void verifyQuoteLineObjectValues(String object, String productName) {
		try {
			String unitPrice = (String) Constant.productSpecs.get(productName).get("UnitPrice"),
					quantity = (String) Constant.productSpecs.get(productName).get("Quantity");
			String query = SOQLConstant.getQuery(object, Constant.quoteId, productName);
			APIUtils.getSoqlResultInJSON(query);
			String[] fields = SOQLUtils.removedIdFieldFromSOQLSelectFields(SOQLUtils.getSelectFieldsFromSOQLQuery(query));
			String[] values = {defaultTerm, Constant.renewableSubscriptionType, quantity, unitPrice, unitPrice, unitPrice, unitPrice, Constant.totalAmount, Constant.totalAmount, Constant.totalAmount};
			APIUtils.validateResponsesThroughJSON(values, fields);
			String expectedFromAPI = Constant.expectedResult;
			String actualFromAPI = Constant.actualResult;
			ExtentCucumberAdapter.addTestStepLog("Quote Line Object Values for '" + productName + "' product");
			ExtentCucumberAdapter.addTestStepLog("URL: " + Constant.baseURL + "/" + APIUtils.getIdForCurrentSOQLObjectFromJSON());
			ExtentCucumberAdapter.addTestStepLog(expectedFromAPI + Constant.expectedResult);
			ExtentCucumberAdapter.addTestStepLog(actualFromAPI + Constant.actualResult);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}
	
	/**
	 * Method to verify Order Object Values
	 * @param object - Name of the Salesforce Object
	 */
	public void verifyOrderObjectValues(String object) {
		try {
			String query = SOQLConstant.getQuery(object, Constant.orderReferenceNumber);
			APIUtils.getSoqlResultInJSON(query);
			Constant.orderId = APIUtils.getIdForCurrentSOQLObjectFromJSON();
			String[] fields = SOQLUtils.removedIdFieldFromSOQLSelectFields(SOQLUtils.getSelectFieldsFromSOQLQuery(query));
			String[] values = {todaysDate, Constant.totalAmount, Constant.activatedStatus};
			APIUtils.validateResponsesThroughJSON(values, fields);
			String expectedFromAPI = Constant.expectedResult;
			String actualFromAPI = Constant.actualResult;
			ExtentCucumberAdapter.addTestStepLog("Order Object Values");
			ExtentCucumberAdapter.addTestStepLog("URL: " + Constant.baseURL + "/" + APIUtils.getIdForCurrentSOQLObjectFromJSON());
			ExtentCucumberAdapter.addTestStepLog(expectedFromAPI + Constant.expectedResult);
			ExtentCucumberAdapter.addTestStepLog(actualFromAPI + Constant.actualResult);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}
	
	/**
	 * Method to verify Order Item Object Values
	 * @param object - Name of the Salesforce Object
	 * @param productName - Name of the Product
	 */
	public void verifyOrderItemObjectValues(String object, String productName) {
		try {
			String unitPrice = (String) Constant.productSpecs.get(productName).get("UnitPrice"),
					quantity = (String) Constant.productSpecs.get(productName).get("Quantity");
			String query = SOQLConstant.getQuery(object, Constant.orderId, productName);
			APIUtils.getSoqlResultInJSON(query);
			String[] fields = SOQLUtils.removedIdFieldFromSOQLSelectFields(SOQLUtils.getSelectFieldsFromSOQLQuery(query));
			String[] values = {Constant.activatedStatus, todaysDate, oneYearDate, quantity, unitPrice, unitPrice, Constant.totalAmount};
			APIUtils.validateResponsesThroughJSON(values, fields);
			String expectedFromAPI = Constant.expectedResult;
			String actualFromAPI = Constant.actualResult;
			ExtentCucumberAdapter.addTestStepLog("Order Item Object Values  for '" + productName + "' product");
			ExtentCucumberAdapter.addTestStepLog("URL: " + Constant.baseURL + "/" + APIUtils.getIdForCurrentSOQLObjectFromJSON());
			ExtentCucumberAdapter.addTestStepLog(expectedFromAPI + Constant.expectedResult);
			ExtentCucumberAdapter.addTestStepLog(actualFromAPI + Constant.actualResult);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}
	
	/**
	 * Method to verify Contract Object Values
	 * @param object - Name of the Salesforce Object
	 */
	public void verifyContractObjectValues(String object) {
		try {
			String query = SOQLConstant.getQuery(object, Constant.orderId);
			APIUtils.getSoqlResultInJSON(query);
			Constant.contractId = APIUtils.getIdForCurrentSOQLObjectFromJSON();
			String[] fields = SOQLUtils.removedIdFieldFromSOQLSelectFields(SOQLUtils.getSelectFieldsFromSOQLQuery(query));
			String[] values = {todaysDate, oneYearDate, defaultTerm};
			APIUtils.validateResponsesThroughJSON(values, fields);
			String expectedFromAPI = Constant.expectedResult;
			String actualFromAPI = Constant.actualResult;
			ExtentCucumberAdapter.addTestStepLog("Contract Object Values");
			ExtentCucumberAdapter.addTestStepLog("URL: " + Constant.baseURL + "/" + APIUtils.getIdForCurrentSOQLObjectFromJSON());
			ExtentCucumberAdapter.addTestStepLog(expectedFromAPI + Constant.expectedResult);
			ExtentCucumberAdapter.addTestStepLog(actualFromAPI + Constant.actualResult);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}
	
	/**
	 * Method to verify Subscription Object Values
	 * @param object - Name of the Salesforce Object
	 * @param productName - Name of the Product
	 */
	public void verifySubscriptionObjectValues(String object, String productName) {
		try {
			String unitPrice = (String) Constant.productSpecs.get(productName).get("UnitPrice"),
					quantity = (String) Constant.productSpecs.get(productName).get("Quantity");
			String query = SOQLConstant.getQuery(object, Constant.contractId, productName);
			APIUtils.getSoqlResultInJSON(query);
			String[] fields = SOQLUtils.removedIdFieldFromSOQLSelectFields(SOQLUtils.getSelectFieldsFromSOQLQuery(query));
			String[] values = {todaysDate, oneYearDate, quantity, unitPrice, unitPrice, unitPrice, unitPrice, unitPrice};
			APIUtils.validateResponsesThroughJSON(values, fields);
			String expectedFromAPI = Constant.expectedResult;
			String actualFromAPI = Constant.actualResult;
			ExtentCucumberAdapter.addTestStepLog("Subscription Object Values  for '" + productName + "' product");
			ExtentCucumberAdapter.addTestStepLog("URL: " + Constant.baseURL + "/" + APIUtils.getIdForCurrentSOQLObjectFromJSON());
			ExtentCucumberAdapter.addTestStepLog(expectedFromAPI + Constant.expectedResult);
			ExtentCucumberAdapter.addTestStepLog(actualFromAPI + Constant.actualResult);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}
}
