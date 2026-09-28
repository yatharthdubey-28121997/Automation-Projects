package com.sib.salesforce.constant;

import com.sib.salesforce.exception.CustomException;

public class SOQLConstant {

	private SOQLConstant() {}
	
	/**
	 * Method to get Query by given Object Name
	 * @param objName - Object Name
	 * @param params - Param used in where clause
	 * @return - Query
	 */
	public static String getQuery(String objName, Object... params) {
		switch (objName) {
		case "Cart":
			return "SELECT Cart_Type__c, Status, TotalProductCount, TotalListAmount, TotalProductAmount, TotalAmount, GrandTotalAmount, Id FROM WebCart WHERE Name = 'Default_Cart_Name' order by CreatedDate desc limit 1";
		case "Cart Item":
			return String.format("SELECT Quantity, SalesPrice, TotalLineNetAmount, TotalAmount, Id FROM CartItem WHERE CartId = '%s' and Product2.Name = '%s'", params);
		case "Quote":
			return String.format("SELECT SBQQ__Status__c, SBQQ__StartDate__c, SBQQ__ExpirationDate__c, SBQQ__SubscriptionTerm__c, SBQQ__ListAmount__c, SBQQ__RegularAmount__c, SBQQ__CustomerAmount__c, SBQQ__NetAmount__c,  Id FROM SBQQ__Quote__c WHERE Cart__c = '%s'", params);
		case "Quote Line":
			return String.format("SELECT SBQQ__DefaultSubscriptionTerm__c, SBQQ__SubscriptionType__c, SBQQ__Quantity__c, SBQQ__ListPrice__c, SBQQ__CustomerPrice__c, SBQQ__NetPrice__c, SBQQ__RegularPrice__c, SBQQ__ListTotal__c, SBQQ__CustomerTotal__c, SBQQ__NetTotal__c, Id FROM SBQQ__QuoteLine__c WHERE SBQQ__Quote__c = '%s' and SBQQ__Product__r.Name = '%s'", params);
		case "Order":
			return String.format("SELECT EffectiveDate, TotalAmount, Status, Id FROM Order WHERE OrderReferenceNumber = '%s'", params);
		case "Order Activation":
			return String.format("SELECT Status, OrderNumber, Id FROM Order WHERE OrderReferenceNumber = '%s'", params);
		case "Order Item":
			return String.format("SELECT SBQQ__Status__c, ServiceDate, EndDate, Quantity, ListPrice, UnitPrice, TotalPrice, Id FROM OrderItem WHERE OrderId = '%s' and Product2.Name = '%s'", params);
		case "Contract":
			return String.format("Select StartDate, EndDate, ContractTerm, Id FROM Contract WHERE SBQQ__Order__c = '%s'", params);
		case "Subscription":
			return String.format("Select SBQQ__StartDate__c, SBQQ__EndDate__c, SBQQ__Quantity__c, SBQQ__ListPrice__c, SBQQ__SpecialPrice__c, SBQQ__CustomerPrice__c, SBQQ__RegularPrice__c, SBQQ__NetPrice__c, Id FROM SBQQ__Subscription__c WHERE SBQQ__Contract__c = '%s' and SBQQ__Product__r.Name = '%s'", params);
		case "Pricebook Entry":
			return String.format("SELECT UnitPrice FROM PricebookEntry where Product2.Name = '%s' and Pricebook2.Name = 'Standard Price Book' and CurrencyIsoCode = 'USD'", params);
		case "Contract Id":
			return String.format("Select ContractNumber, Id from Contract where SBQQ__Order__r.OrderReferenceNumber = '%s'", params);
		case "Order Id":
			return String.format("Select Id from Order where OrderReferenceNumber = '%s'", params);
		default:
			throw new CustomException("Query not defined");
		}
	}
}
