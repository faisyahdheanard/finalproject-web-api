@web
Feature: HOMEPAGE

  Background:
    Given user is on home page

  @positive-test
  #FAILED, THE PRODUCT FROM THE FIRST PAGE CHANGES AND DECREASES
  Scenario: HOMEPAGE: show all products using Next and Previous button
    Then the first page of all products is displayed
    When user click Next button
    Then the second page of products should be displayed
    When user click Previous button
    Then user will be directed back to the first page of all products

  @positive-test
  Scenario Outline: HOMEPAGE: show only specific category products
    When user click "<category>" category button
    Then only "<category>" products should be displayed

    Examples:
      | category  |
      | Phone     |
      | Laptop    |
      | Monitor   |

  @positive-test
  #FAILED, PRODUCTS DO NOT SHOWED PER-CATEGORY
  Scenario: HOMEPAGE: navigate through category with Next and Previous button
    When user click "Phone" category button
    And user click Next button
    Then only "Laptop" products should be displayed
    When user click Next button
    Then only "Monitor" products should be displayed
    When user click Previous button
    Then only "Laptop" products should be displayed
    When user click Previous button
    Then only "Phone" products should be displayed

  @positive-test
  Scenario: HOMEPAGE: verify match product details between Home page and Product Detail page
    When user click product name
    Then the product details on the homepage match the product details on the product detail page


