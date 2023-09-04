@api
Feature: DELETE

  Background:
    Given the request header is properly set up

  @delete-positive
  Scenario Outline: DELETE: delete data user with valid id
    When send a "DELETE" request to the "user" endpoint with id "<userId>"
    Then the status code should be 200
    And all response should be match with "delete_user.json"

    Examples:
      | userId  |
      | userId1 |
      | userId2 |
      | userId3 |
      | userId4 |

  @delete-negative
  Scenario: DELETE: delete data user with invalid id
    When send a "DELETE" request to the "user" endpoint with id "userId5"
    Then the status code should be 400
    And the response body should contain:
      | error | PARAMS_NOT_VALID |