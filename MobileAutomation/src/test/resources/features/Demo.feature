@Demo
Feature: RafterOne Automation Demo

  @Demo
  Scenario: ECommerce Flow
    Given User navigates to "ECommerce" URL
    And User should be navigated to "Home" page
    When User adds "Skinsheen Bronzer Stick" Product to Cart from "Home" page
    And User navigates to "Cart" page
    And User should be navigated to "Cart" page
    And User clicks on "Checkout" button
    And User selects "Guest Checkout" option
    And User clicks on "Continue" button
    And User fills "Personal Details" and "Address" fields
    And User clicks on "Continue" button
    And User clicks on "Confirm Order" button
    Then Verify "Your Order Has Been Processed!" message displayed