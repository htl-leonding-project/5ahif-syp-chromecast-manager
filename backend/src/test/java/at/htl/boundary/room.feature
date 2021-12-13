Feature: Show rooms

  Background:
    * url baseUrl

  Scenario: find all rooms sorted by roomNo

    Given path 'room'
    And header Content-Type = 'application/json'
    When method GET
    Then status 200

  Scenario: find all rooms

    Given path 'rooms'
    And header Content-Type = 'application/json'
    When method GET
    Then status 200

