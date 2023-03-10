Feature: Health endpoint

  @health
  Scenario: Check the api health check
    Given I send a GET request to "/health-check"
    Then I should get a response with "ok" message