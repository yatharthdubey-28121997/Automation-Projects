package com.sib.salesforce.pageobjects;

import org.json.simple.JSONObject;

import com.sib.salesforce.constant.Constant;
import com.sib.salesforce.constant.SOQLConstant;
import com.sib.salesforce.exception.CustomException;
import com.sib.salesforce.utils.APIUtils;

public class ContractPage {

	/**
	 * Method to update all required fields on New Contact required for Renewal
	 */
	@SuppressWarnings("unchecked")
	public void updateContractFieldsForRenewal(String contractId) {
		try {
			JSONObject json = new JSONObject();
			String trueString = "true";
			json.put("Due_For_Renewal__c", trueString);
			json.put("SBQQ__RenewalQuoted__c", trueString);
			json.put("SBQQ__RenewalForecast__c", trueString);
			json.put("Show_In_Ecommerce_Store__c", trueString);
			APIUtils.updateFieldValueInSObject(json, contractId, "Contract");
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}
	
	/**
	 * Method to get Contract Id using Order Reference Number and Store it in Constant
	 */
	public void getContractIdUsingOrderReferenceNumber() {
		try {
			String query = SOQLConstant.getQuery("Contract Id", Constant.orderReferenceNumber);
			APIUtils.getSoqlResultInJSON(query);
			Constant.contractId = APIUtils.getIdForCurrentSOQLObjectFromJSON();
			Constant.contractNumber = APIUtils.getSOQLFieldValueFromJSONConstant("ContractNumber");
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}
}
