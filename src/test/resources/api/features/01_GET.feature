@api
Feature: GET

  Background:
    Given the request header is properly set up

  @get-positive
  Scenario: GET: get list of users
    When send a "GET" request to the "list" endpoint
    Then the status code should be 200
    And all response should be match with "get_list_user.json"
    And the limit and the page should be set to their default values

  @get-positive
  #DATA SHOULDN'T NULL(IMPORTANT DETAILS) : FIRSTNAME, LASTNAME, EMAIL
  Scenario: GET: get user full data by id
    When send a "GET" request to the "single" endpoint with id "60d0fe4f5311236168a109ca"
    Then the status code should be 200
    And all response should be match with "get_single_user.json"
    And the response body should contain important user details

  @get-negative
  Scenario: GET: get user full data with wrong id
    When send a "GET" request to the "single" endpoint with id "0123"
    Then the status code should be 400
    And the response body should contain:
      | error | PARAMS_NOT_VALID |

  @get-negative
  Scenario: GET: get user full data with id containing "-"
    When send a "GET" request to the "single" endpoint with id "-60d0fe4f5311236168a109ca"
    Then the status code should be 400
    And the response body should contain:
      | error | PARAMS_NOT_VALID |


  @get-positive-boundaries
  #MIN-MAX INFP : LIMIT(5-50) AND PAGE(0-999)
  Scenario Outline: GET: get list of users with valid 'limit' and 'page' boundary values
    When the request paramaters set to:
      | limit | <limit> |
      | page  | <page>  |
    And send a "GET" request to the "list" endpoint
    Then the status code should be 200
    And all response should be match with "get_list_user.json"
    And the response body should contain:
      | limit | <limit> |
      | page  | <page>  |

    Examples:
      | limit | page |
      |5      |0     |
      |50     |999   |

  @get-negative-boundaries
  #MIN-MAX INFP : LIMIT(5-50) AND PAGE(0-999)
  #FAILED
  Scenario: GET: get list of users with invalid 'limit' and 'page' boundary values
    When the request paramaters set to:
      | limit | 51    |
      | page  | 1000  |
    And send a "GET" request to the "list" endpoint
    Then the status code should be 400
    And the response body should contain:
      | error | PARAMS_NOT_VALID |






