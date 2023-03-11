@register-user
Feature: Register user

  Scenario: Register a valid user
    When I send a POST request to "/register" with body:
    """
    {
      "name": "Juan Rodriguez",
      "email": "juan@dominio.cl",
      "password": "hunter2",
      "phones": [
          {
              "number": "1234567",
              "citycode": "1",
              "contrycode": "57"
          }
      ]
    }
    """
    Then The response status should be 201
    And The response should contain the user info

  Scenario: Register a user with a taken email
    Given There is a user with the email "juan@dominio.cl"
    When I send a POST request to "/register" with body:
    """
    {
      "name": "Juan Rodriguez",
      "email": "juan@dominio.cl",
      "password": "hunter2",
      "phones": [
          {
              "number": "1234567",
              "citycode": "1",
              "contrycode": "57"
          }
      ]
    }
    """
    Then The response status should be 422
    And The response should be "{\"message\":\"El correo ya registrado\"}"