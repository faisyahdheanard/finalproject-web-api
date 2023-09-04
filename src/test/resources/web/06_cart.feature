@web
Feature: CART

  Background:
    Given user is on home page

  @positive-test
  Scenario: CART: add product to the cart
    When user click add to cart button for these product:
      | Samsung galaxy s6 |
    And user go to the cart page
    Then the cart should contain:
      | Samsung galaxy s6 |
    And the calculated total price is accurate

  @positive-test
  Scenario: CART: add the quantity of the product
    When user click add to cart button 2 times for these product:
      | Samsung galaxy s6 |
    And user go to the cart page
    Then the cart's item count for these product should be 2
      | Samsung galaxy s6 |
    And the calculated total price is accurate

  @posistive-test
  Scenario: CART: delete product from the cart
    When user click add to cart button for these product:
      | Samsung galaxy s6 |
      | Samsung galaxy s7 |
    And user go to the cart page
    And user click delete button to these product:
      | Samsung galaxy s7 |
    Then the cart should contain:
      | Samsung galaxy s6 |
    And the calculated total price is accurate

  @boundary-test
  Scenario: CART: add all products to the cart
    When user click add to cart button for all product
    And user go to the cart page
    Then the cart should contain all product
    And the calculated total price is accurate

  @positive-test
  Scenario: CART: return to the home page from cart page with Home menu
    And user click home menu
    Then user will be directed back to the home page







