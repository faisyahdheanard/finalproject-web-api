@api
Feature: PUT

  Background:
    Given the request header is properly set up

 @put-positive
  Scenario: PUT: update data user with valid data
    And the request body for "PUT" with "limited fields" is prepared
    When send a "PUT" request to the "user" endpoint with id "userId1"
    Then the status code should be 200
    And all response should be match with "post_put_user.json"
    And the response body should contain important user details
    And the response body should not be empty
    And the response body matches the "completed fields" request body

  @put-positive
  Scenario: PUT: modify data from limited to completed data fields
    And the request body for "PUT" with "complete fields" is prepared
    When send a "PUT" request to the "user" endpoint with id "userId2"
    Then the status code should be 200
    And all response should be match with "post_put_user.json"
    And the response body should contain important user details
    And the response body should not be empty
    And the response body matches the "completed fields" request body

  @put-positive
  #EMAIL BEFORE: fdheana@example.com
  Scenario: PUT: verify email is forbidden to update
    And the request body is set to:
      | firstName | dheana                |
      | lastName  | faisyah               |
      | email     | testing@example.com   |
    When send a "PUT" request to the "user" endpoint with id "userId1"
    Then the status code should be 200
    And all response should be match with "post_put_user.json"
    And the response body should contain important user details
    And the response body should contain:
      | firstName | dheana                |
      | lastName  | faisyah               |
      | email     | fdheana@example.com   |

  @put-negative
  #FAILED
  Scenario: PUT: update data user (firstName and lastName) into null
    And the request body for "PUT" with "null fields" is prepared
    When send a "PUT" request to the "user" endpoint with id "userId1"
    Then the status code should be 400
    And the response body should contain:
      | error          | BODY_NOT_VALID                |
      | data.firstName | Path `firstName` is required. |
      | data.lastName  | Path `lastName` is required.  |

  @put-negative-boudaries
  #FAILED, MIN/MAX CHAR : 2-30
  Scenario Outline: PUT: update user with name(character) length boundaries
    When the request body for "POST/PUT" with "<boundary>" is prepared
    When send a "PUT" request to the "user" endpoint with id "userId1"
    Then the status code should be 400
    And the response body should contain information that "<error>" and "<data.firstName>"

    Examples:
      | boundary   | error          | data.firstName                                                                                           |
      | <MIN CHAR  | BODY_NOT_VALID | Path `firstName` (`a`) is shorter than the minimum allowed length (2).                                   |
      | >MAX CHAR  | BODY_NOT_VALID | Path `firstName` (`aVeryVeryVeryVeryVeryLongFirstName`) is longer than the maximum allowed length (30). |











