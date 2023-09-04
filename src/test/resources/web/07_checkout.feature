@web
Feature: CHECKOUT

  Background:
    Given user is on home page

  @positive-test
  Scenario: CHECKOUT: checkout with valid payment data for one product
    When user already logged in
    And user click add to cart for "one" product
    And user go to the cart page
    And user click Place Order button
    And user should be able to see payment data form
    And user fill the payment data with:
      | Name        | Faisyah Dheana |
      | Country     | Country        |
      | City        | City           |
      | Credit card | 777            |
      | Month       | 12             |
      | Year        | 2002           |
    And click Purchase button
    Then the transaction is successful and there will be a pop up said "Thank you for your purchase!"

  @negative-test
  #FAILED
  Scenario: CHECKOUT: checkout with invalid payment data
    When user already logged in
    And user click add to cart for "one" product
    And user go to the cart page
    And user click Place Order button
    And user should be able to see payment data form
    And user fill the payment data with:
      | Name        | ############## |
      | Country     | Country        |
      | City        | City           |
      | Credit card | 777            |
      | Month       | 12             |
      | Year        | 2002           |
    And click Purchase button
    Then there will be a message said "Username must only contain alphabets"

  @negative-test
  Scenario: CHECKOUT: checkout with blank payment data
    When user already logged in
    And user click add to cart for "one" product
    And user go to the cart page
    And user click Place Order button
    And user should be able to see payment data form
    And user fill the payment data "with blank data"
    And click Purchase button
    Then there will be a message said "Please fill out Name and Creditcard."


  @negative-test
  Scenario: CHECKOUT: checkout without providing a Name
    When user already logged in
    And user click add to cart for "one" product
    And user go to the cart page
    And user click Place Order button
    And user should be able to see payment data form
    And user fill the payment data "without name"
    And click Purchase button
    Then there will be a message said "Please fill out Name and Creditcard."

  @negative-test
  #FAILED
  Scenario: CHECKOUT: checkout without providing City and Country
    When user already logged in
    And user click add to cart for "one" product
    And user go to the cart page
    And user click Place Order button
    And user should be able to see payment data form
    And user fill the payment data "without city and country"
    And click Purchase button
    Then there will be a message said "Please fill out Country and City."

  @negative-test
  Scenario: CHECKOUT: checkout without providing Credit card
    When user already logged in
    And user click add to cart for "one" product
    And user go to the cart page
    And user click Place Order button
    And user should be able to see payment data form
    And user fill the payment data "without credit card"
    And click Purchase button
    Then there will be a message said "Please fill out Name and Creditcard."

  @negative-test
  #FAILED
  Scenario: CHECKOUT: checkout without providing Month and Year
    When user already logged in
    And user click add to cart for "one" product
    And user go to the cart page
    And user click Place Order button
    And user should be able to see payment data form
    And user fill the payment data "without month and year"
    And click Purchase button
    Then there will be a message said "Please fill out Month and Year."


  @positive-test
  Scenario: CHECKOUT: checkout as a guest
    When user click add to cart for "one" product
    And user go to the cart page
    And user click Place Order button
    And user should be able to see payment data form
    And user fill the payment data with:
      | Name        | Faisyah Dheana |
      | Country     | Country        |
      | City        | City           |
      | Credit card | 777            |
      | Month       | 12             |
      | Year        | 2002           |
    And click Purchase button
    Then the transaction is successful and there will be a pop up said "Thank you for your purchase!"

  @positive-test
  Scenario: CHECKOUT: back to the cart page from checkout page
    When user go to the cart page
    And user click Place Order button
    And user click Close button on payment page
    Then user will be directed back to the cart page

  @boundary-test
  #FAILED : FOR EMPTY PRODUCT ON CART TEST
  Scenario Outline: CHECKOUT: checkout with products in cart boundaries
    When user already logged in
    And user click add to cart for "<itemCount>" product
    And user go to the cart page
    And user click Place Order button
    And user should be able to see payment data form
    And user fill the payment data with:
      | Name        | Faisyah Dheana |
      | Country     | Country        |
      | City        | City           |
      | Credit card | 777            |
      | Month       | 12             |
      | Year        | 2002           |
    And click Purchase button
    Then <message>

    Examples:
      | itemCount | message                                                                                       |
      | empty     | there will be a message said "Cannot proceed to checkout as the cart is empty."               |
      | all       | the transaction is successful and there will be a pop up said "Thank you for your purchase!"  |




