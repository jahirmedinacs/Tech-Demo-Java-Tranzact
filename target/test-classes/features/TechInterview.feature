Feature: SauceDemo Login Functionality
@Demo

  @Demo-A
  Scenario: Successful Login with Valid Credentials
    Given I am on the SauceDemo login page
    When I enter Username as "standard_user" and Password as "secret_sauce"
    And I click on the Login button
    Then I should be redirected to the home page

  @Demo-B
  Scenario: Failed Login with Locked User
    Given I am on the SauceDemo login page
    When I enter Username as "locked_out_user" and Password as "secret_sauce"
    And I click on the Login button
    Then I should see an error message indicating the user is locked

  @Demo-AB
  Scenario Outline: Login with multiple sets of credentials
    Given I am on the SauceDemo login page
    When I enter Username as "<username>" and Password as "<password>"
    And I click on the Login button
    Then I should see "<expected_result>"

    Examples:
      | username           | password     | expected_result                  |
      | "standard_user"    | "secret_sauce" | "redirected to the home page"   |
      | "locked_out_user"  | "secret_sauce" | "error message indicating the user is locked" |
      | "invalid_user"     | "secret_sauce" | "error message indicating invalid credentials" |

  @Demo-C
  Scenario: Happy Path Workflow
    Given I login to the SauceDemo website with valid credentials
    When I add "Sauce Labs Bike Light" to the cart from the Products page
    And I navigate to the Cart page from the Products page
    And I click on the Checkout button
    And I fill the Checkout Information page with random data
    And I click on the Continue button
    And I click on the Finish button on the Checkout Overview page
    And I click on the Menu icon
    Then I should be able to logout

  @Demo-D
  Scenario: Multiple Scenarios Workflow
    Given I login to the SauceDemo website with valid credentials
    When I change the Product Sort to "Price (low to high)" on the Products page
    Then the selected item on the Product Sort should be "Price (low to high)"
    And the product prices on the page should be in ascending order
    When I add "Sauce Labs Fleece Jacket" and "Sauce Labs Onesie" to the cart
    Then the Remove button should be enabled for "Sauce Labs Fleece Jacket" and "Sauce Labs Onesie"
    And I capture the prices of "Sauce Labs Fleece Jacket" and "Sauce Labs Onesie" from the Products page
    And the value on the Cart Icon should be "2"
    When I navigate to the Cart page
    Then the prices of "Sauce Labs Fleece Jacket" and "Sauce Labs Onesie" on the Cart page should match those captured from the Products page
    When I remove "Sauce Labs Onesie" from the cart
    Then the quantity of "Sauce Labs Fleece Jacket" in the cart and the value on the Cart Icon should be the same
    When I click on the Checkout button
    And I fill the Checkout Information page with random data
    And I click on the Continue button
    Then the Item total on the Checkout Overview page should match the price captured for "Sauce Labs Fleece Jacket"
    When I click on the Finish button on the Checkout Overview page
    Then I should see "Thank you for your order" on the Checkout Complete page
    When I click on the Menu icon
    Then I should be able to logout

