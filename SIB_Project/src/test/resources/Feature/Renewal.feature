@SIB
Feature: Renewal Flow with own test data creation

  @SIB @Renewal
  Scenario Outline: Renewal Flow with own test data creation
    Given User login into the Salesforce <Env> Environment
    And User swtiches to <App> App
    When User selects <ContactName> Contact from "Salesforce Home" Page
    And User clicks on the "Log in to Experience as User" button on "Salesforce Contact" page
    And User clicks on the "SIB Store" button on "Salesforce Log in as Site User" page
    Then User waits for "ECOM Home" Page to load
    When User clears the "Cart" from "ECOM Cart" Page
    And User navigates to <Category> Category from "ECOM Home" page
    And User adds <Product> Product with <Quantity> Quantity from "PLP" page
    And User clicks on the "Cart" button on "PDP" page
    And User clicks on the "Checkout" button on "Cart" page
    And User enters "Random PO" in the "Purchase Order" field on "Checkout" page
    And User clicks on the "Agree to Terms and Conditions" button on "Checkout" page
    And User clicks on the "Place Order" button on "Checkout" page
    Then Verify the "Thank You Your order has been placed." message on "Order Confirmation" page
    And Verify "Cart" object is created in "Salesforce" with all required values
    And Verify "Cart Item" object is created in "Salesforce" with all required values
    And Verify "Quote" object is created in "Salesforce" with all required values
    And Verify "Quote Line" object is created in "Salesforce" with all required values
    And Verify "Order" object is created in "Salesforce" with all required values
    And Verify "Order Item" object is created in "Salesforce" with all required values
    And Verify "Contract" object is created in "Salesforce" with all required values
    And Verify "Subscription" object is created in "Salesforce" with all required values
    And User update "Contract" fields to be visible on "ECOM" page
    When User clicks on the "Subscriptions" button on "ECOM Home" page
    And User clicks on the "Renew" button on "Subscriptions" page
    And User clicks on the "Proceed" button on "Subscriptions" page
    Then Verify all the "Products" are added to the Cart on "ECOM Cart" Page
    And User clicks on the "Cart" button on "PDP" page
    And User clicks on the "Checkout" button on "Cart" page
    And User enters "Random PO" in the "Purchase Order" field on "Checkout" page
    And User clicks on the "Agree to Terms and Conditions" button on "Checkout" page
    And User clicks on the "Place Order" button on "Checkout" page
    Then Verify the "Thank You Your order has been placed." message on "Order Confirmation" page
    

    Examples: 
      | Env     | App     | ContactName    | Category              | Product          | Quantity |
      | "SIBQA" | "Sales" | "Shweta Patil" | "Products & Services" | "Sample Product" | "5"      |
