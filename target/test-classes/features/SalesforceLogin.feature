Feature: Salesforce Login Implementation

Background: 
Given the url "https://login.salesforce.com/"
  Scenario: Login with correct Username and no Password
    When I enter Username as "santhik@salesforce.com"
    And I enter Password as ""
    And I click on Login button
    Then Error message should be displayed as "Please enter your password."
    
Scenario: Login with correct Username and correct Password
    When I enter Username as "santhik@salesforce.com"
    And I enter Password as "Welcome@123"
    And I click on Login button
    Then Home Page should be displayed
    
Scenario: Login functionality with Remember Me option
    When I enter Username as "santhik@salesforce.com"
    And I enter Password as "Welcome@123"
    And I click on RememberMe checbox
    And I click on Login button
    Then Home Page should be displayed
    When I click on UserMenu
    And Select Logout
    Then Login Page should be displayed
    And Username should be displayed
    
Scenario: validating Forgot password    
    When I click Forgot Password
    Then Forgot your password page should be displayed
    When I enter forgotusername as "santhik@salesforce.com"
    And I click on submit
   Then Password Reset message should be displayed
   
Scenario: Login with incorrect Username and incorrect Password
   When I enter Username as "123"
    And I enter Password as "22131"
    And I click on Login button
    Then Error message should be displayed as "Please check your username and password. If you still can't log in, contact your Salesforce administrator."
