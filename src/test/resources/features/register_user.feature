@register-user
Feature: Register user


  Scenario: Register a user with any invalid field
    When I send a POST request to register endpoint with body:
    """
    {
      "email": "juan@dominio.cl",
      "password":"hunter2",
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
    And The response should be "{\"message\":\"required name\"}"

  Scenario: Register a user with a taken email
    Given There is a user with the email "juan@dominio.cl"
    When I send a POST request to register endpoint with body:
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

  Scenario: Register a user with an invalid email
    When I send a POST request to register endpoint with body:
    """
    {
      "name": "Juan Rodriguez",
      "email": "juan@test.cl",
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
    And The response should be "{\"message\":\"invalid email\"}"

  Scenario: Register a user with an invalid clave lada
    Given The regular expression for clave lada is "^[0-9]{5}$"
    When I send a POST request to register endpoint with body:
    """
    {
      "name": "Juan Rodriguez",
      "email": "juan@dominio.cl",
      "password": "hunter2",
      "phones": [
          {
              "number": "1234567",
              "citycode": "1",
              "contrycode": "12"
          }
      ]
    }
    """
    Then The response status should be 422
    And The response should be "{\"message\":\"invalid lada key\"}"

  Scenario: Register a valid user
    When I send a POST request to register endpoint with body:
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
          },
          {
              "number": "1234568",
              "citycode": "1",
              "contrycode": "57"
          }
      ]
    }
    """
    Then The response status should be 201
    And The response should contain the user info
