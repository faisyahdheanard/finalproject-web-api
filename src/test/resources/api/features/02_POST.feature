@api
Feature: POST

  Background:
    Given the request header is properly set up

  @post-positive
  #IMPORTANT! COMPLETE : ALL FIELDS, LIMITED: ONLY REQUIRED FIELDS(EMAIL, FIRSTNAME, LASTNAME)
  Scenario: POST: create a new user with completed fields
    And the request body for "POST" with "complete fields" is prepared
    When send a "POST" request to the "create" endpoint
    Then the status code should be 200
    And all response should be match with "post_put_user.json"
    And the response body should contain important user details
    And the response body should not be empty
    And the response body matches the "completed fields" request body
    And the response body "id" will be named as "userId1" and will be used for the next tests

  @post-positive
  #IMPORTANT! COMPLETE : ALL FIELDS, LIMITED: ONLY REQUIRED FIELDS(EMAIL, FIRSTNAME, LASTNAME)
  Scenario: POST: create a new user with limited fields
    And the request body for "POST" with "limited fields" is prepared
    When send a "POST" request to the "create" endpoint
    Then the status code should be 200
    And all response should be match with "post_user_limited.json"
    And the response body should contain important user details
    And the response body matches the "limited fields" request body
    And the response body "id" will be named as "userId2" and will be used for the next tests

  @post-negative
  #FAILED
  Scenario: POST: create a new user with special characters on name fields
    And the request body is set to:
      | firstName       | #######                  |
      | lastName        | example                  |
      | email           | exampletest@gmail.com    |
    When send a "POST" request to the "create" endpoint
    #FOR CLEAN TEST PURPOSES, THE USER ID WILL BE SAVED TO BE USED FOR DELETION IN THE SUBSEQUENT 'DELETE' METHOD
    And the response body "id" will be named as "userId3" and will be used for the next tests
    #REAL TEST:
    Then the status code should be 400
    And the response body should contain:
      | error           | BODY_NOT_VALID           |
      | data.firstName  | firstName cannot contain special characters, use letters only |

  @post-negative
  #FAILED
  Scenario: POST: create a new user with number on name fields
    And the request body is set to:
      | firstName       | 0000000                  |
      | lastName        | example                  |
      | email           | exampletest2@gmail.com   |
    When send a "POST" request to the "create" endpoint
    #FOR CLEAN TEST PURPOSES, THE USER ID WILL BE SAVED TO BE USED FOR DELETION IN THE SUBSEQUENT 'DELETE' METHOD
    And the response body "id" will be named as "userId4" and will be used for the next tests
    #REAL TEST:
    Then the status code should be 400
    And the response body should contain:
      | error           | BODY_NOT_VALID           |
      | data.firstName  | firstName cannot contain number, use letters only |

  @post-negative
  Scenario: POST: create a new user with existing email
    And the request body is set to:
      | firstName       | faisyah                  |
      | lastName        | dheana                   |
      | email           | fdheana@example.com      |
    When send a "POST" request to the "create" endpoint
    Then the status code should be 400
    And the response body should contain:
      | error           | BODY_NOT_VALID           |
      | data.email      | Email already used       |

  @post-negative
  Scenario: POST: create a new user without '@' on email
    And the request body is set to:
      | firstName       | faisyah                  |
      | lastName        | dheana                   |
      | email           | fdheanagmail.com         |
    When send a "POST" request to the "create" endpoint
    Then the status code should be 400
    And the response body should contain:
      | error           | BODY_NOT_VALID           |
      | data.email      | Path `email` is invalid (fdheanagmail.com).|

  @post-negative
  #WITHOUT EMAIL AND LASTNAME
  Scenario: POST: create a new user with incomplete required field
    And the request body is set to:
      | firstName       | faisyah                      |
    When send a "POST" request to the "create" endpoint
    Then the status code should be 400
    And the response body should contain:
      | error           | BODY_NOT_VALID               |
      | data.email      | Path `email` is required.    |
      | data.lastName   | Path `lastName` is required. |

  @post-negative
  #TITLE MUST BE 'mr', 'ms', 'mrs', 'miss', 'dr', or empty.
  Scenario: POST: create a new user with invalid data (title: drs)
    And the request body for "POST" with "invalid fields" is prepared
    When send a "POST" request to the "create" endpoint
    Then the status code should be 400
    And the response body should contain:
      | error           | BODY_NOT_VALID               |
      | data.title      | `drs` is not a valid enum value for path `title`. |

  @post-negative-boundaries
  #MIN-MAX CHAR : 2-30
  Scenario Outline: POST: create user with name(character) length boundaries
    And the request body for "POST/PUT" with "<boundary>" is prepared
    When send a "POST" request to the "create" endpoint
    Then the status code should be 400
    And the response body should contain information that "<error>" and "<data.firstName>"

    Examples:
      | boundary   | error          | data.firstName                                                                                           |
      | <MIN CHAR  | BODY_NOT_VALID | Path `firstName` (`a`) is shorter than the minimum allowed length (2).                                   |
      | >MAX CHAR  | BODY_NOT_VALID | Path `firstName` (`aVeryVeryVeryVeryVeryLongFirstName`) is longer than the maximum allowed length (30). |









