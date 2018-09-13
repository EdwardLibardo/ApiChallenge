Feature: requests
  As a user
  I want to search the top five of the songs and a unique song
  So that I can obtain the desired songs

  Scenario: Obtain the top five of songs
    Given I want to see the top five of songs with their characteristics
    When I search the "top-five" of songs
    Then I can verify that there are 5 songs

  Scenario: Search the song called Chemical Warfare
    Given I can find a "song"
    When I search this song by id
      | id |
      | 1  |
    Then I can verify that this is the desired song
      | artist        | album                              | name             |
      | Dead Kennedys | Fresh Fruit For Rotting Vegetables | Chemical Warfare |

  Scenario: Search a non existent song
    Given I can find a "song"
    When I try to search a song with invalid id
      | id |
      | 0  |
    Then I can not find a the song and i receive a error message


