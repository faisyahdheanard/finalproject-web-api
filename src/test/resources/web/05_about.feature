@web
Feature: ABOUT

  @positive-test
  Scenario: ABOUT: view about us information
    Given user is on home page
    When user click About us menu
    Then user should be able to see information about the company's

  @positive-test
  Scenario: ABOUT: return to the home page from About us page with Close button
    Given user is on home page
    When user click About us menu
    And user click Close button on About us page
    Then user will be directed back to the home page
