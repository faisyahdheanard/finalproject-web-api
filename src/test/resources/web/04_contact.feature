@web
Feature: CONTACT

  Background:
    Given user is on home page
    When user click contact menu

  @positive-test
  Scenario: CONTACT: submission with valid data
    And user input contact email with "example@gmail.com"
    And user input contact name with "example"
    And user input message with "Is the Samsung galaxy 6 availble in stock?"
    And user click send message button
    Then there will be a message said "Thanks for the message!!"

  @negative-test
  #ALL FAILED
  Scenario Outline: CONTACT: submission with various negative scenarios
  #1. EMPTY DATA
  #2. WITHOUT EMAIL ADRESS
  #3. WITHOUT CONTACT NAME
  #4. WITHOUT MESSAGE
  #5. INVALID EMAIL ADDRESS
    And user input contact email with "<email>"
    And user input contact name with "<contact_name>"
    And user input message with "<message>"
    And user click send message button
    Then there will be a message said "<notification-message>"

    Examples:
      | email             | contact_name | message                                  | notification-message                                                   |
      |                   |              |                                          | Please fill out Contact Email, Contact Name, and Message. |
      |                   | example      | Is the Macbook Air available in stock?   | Please fill out Contact Email                             |
      | example@gmail.com |              | Is the Nexus 6 available in stock?       | Please fill out Contact Name                              |
      | example@gmail.com | example      |                                          | Please fill out message                                   |
      | examplegmail      | example      | Is the Sony vaio i5 available in stock?  | Please enter a valid email address.                       |


  @positive-test
  Scenario: CONTACT: return to the home page from contact page with Close button
    And user click Close button on contact page
    Then user will be directed back to the home page

  @negative-boundaries
  #ALL FAILED
  #EXAMPLE CHAR MESSAGE LENGTH, MIN(5) AND MAX(300)
  Scenario Outline: CONTACT: submission message length boundaries
    And user input contact email with "example@gmail.com"
    And user input contact name with "example"
    And user input message with "<boundary>"
    And user click send message button
    Then there will be a message said "<message_notification>"

    Examples:
      | boundary   | message_notification         |
      | <MIN_CHAR | Message minimum length (5)   |
      | >MAX_CHAR | Message maximum length (300) |


