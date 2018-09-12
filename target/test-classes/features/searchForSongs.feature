Feature: requests

  As a user
  I want to execute requests
  So that I can obtain songs depending on the request

  Scenario: Get a response to the request to obtain a top five of songs
    Given I obtain the endpoint from the properties file
    When I search the top five of songs
    Then I should receive 5 songs
    And I can verify that there are 5 songs

  Scenario: Search the song called Chemical Warfare
    Given I can find the Chemical Warfare song
    When I search this song
    Then I can see the song and its characteristics
    And I can verify that this is the desired song

  Scenario Outline: Search a invalid song
    Given There are a few songs with a valid id
    When I write a wrong "<id>"
    Then I can not find a song in the data base
    And I obtain a message of error
    Examples:
      | id |
      | 0  |
      | 13 |

