@web
Feature: LOGIN

  Background:
    Given user is on home page
    When user click login menu

  @positive-test
  Scenario: LOGIN: login with valid data
    And user login with username "existing_account"
    And user login with password "thisispassword"
    And user click login button
    Then user will be directed back to the home page
    And user successfully logged in and there will be an element Welcome "existing_account"

  @negative-test
  Scenario Outline: LOGIN: login with various negative test scenario
  #1. INVALID PASSWORD
  #2. INVALID USERNAME
  #3. INVALID DATA
  #4. VALID DATA WITH SPACE
  #5. BLANK DATA
    And user login with username "<username>"
    And user login with password "<password>"
    And user click login button
    Then there will be a message said "<message>"

    Examples:
      | username                    | password        | message                                 |
      | existing_account            | invalidpassword | Wrong password.                         |
      | invalidusername             | thisispassword  | User does not exist.                    |
      | invalidusername             | invalidpassword | User does not exist.                    |
      | existing_account_with_space | thisispassword  | User does not exist.                    |
      |                             |                 | Please fill out Username and Password.  |

  @positive-test
  Scenario: LOGIN: return to the home page from login page with Close button
    And user click close button on login page
    Then user will be directed back to the home page
