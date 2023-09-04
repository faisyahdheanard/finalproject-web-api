@api
Feature: TAG

  Background:
    Given the request header is properly set up

  @get-positive
  Scenario: GET: get list of tags
    When send a "GET" request to the "tag" endpoint
    Then the status code should be 200
    And all response should be match with "get_list_tag.json"
