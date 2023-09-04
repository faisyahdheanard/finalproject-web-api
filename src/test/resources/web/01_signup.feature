@web
Feature: SIGNUP

  Background:
    Given user is on home page
    When user click signup menu

  @positive-test
  Scenario Outline: SIGNUP: signup with various positive test scenario
  #1. VALID DATA
  #2. NUMERIC USERNAME
    And user input username with "<username>"
    And user input password with "<password>"
    And user click signup button
    Then there will be a message said "Sign up successful."
    And user will be directed back to the home page

    Examples:
      | username        | password       |
      | random_username | thisispassword |
      | random_num      | thisispassword |

  @negative-test
  #FAILED:  SPECIAL CHARACTERS USERNAME TEST, USERNAME LEADING AND TRAILING SPACES TEST
  Scenario Outline: SIGNUP: signup with various negative test scenario
  #1. AN EXISTING ACCOUNT USING THE USERNAME 'random_username'
  #2. BLANK DATA
  #3. WITHOUT PROVIDING A PASSWORD
  #4. WITHOUT PROVIDING A USERNAME
  #5. SPECIAL CHARACTERS USERNAME
  #6. USERNAME LEADING AND TRAILING SPACES
    And user input username with "<username>"
    And user input password with "<password>"
    And user click signup button
    Then there will be a message said "<message>"

    Examples:
      | username                   | password       | message                                           |
      | existing_account           | thisispassword | This user already exist.                          |
      |                            |                | Please fill out Username and Password.            |
      | example                    |                | Please fill out Username and Password.            |
      |                            | example        | Please fill out Username and Password.            |
      | random_specialChar         | thisispassword | Username must only contain alphabets and numbers. |
      | random_username_with_space | thisispassword | Error: Username cannot contain spaces             |

  @negative-boundaries
  #FAILED
  #EXAMPLE CHAR USERNAME LENGTH, MAX(30) AND MIN(3)
  Scenario Outline: SIGNUP: signup with name and password length boundaries
    And user input username with "<boundary>"
    And user input password with "thisispassword"
    And user click signup button
    Then there will be a message said "<error>"

    Examples:
      | boundary  | error                        |
      | >MAX_CHAR | Username maximum length (30) |
      | <MIN_CHAR | Username minimum length (3)  |

  @positive-test
  Scenario: SIGNUP: return to the home page from signup page with Close button
    And user click close button on signup page
    Then user will be directed back to the home page














