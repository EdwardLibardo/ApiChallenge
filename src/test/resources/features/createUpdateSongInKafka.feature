Feature: Create and update a song in kafka
  As a song producer
  I want to upload or modify a song
  So that all users can see the song changes from the API

  Scenario: Verify that the application allows to upload songs
    Given I have a new song to upload
      | Id | song-album | song-artist | song-name | song-genre |
      | 20 | album      | artist      | name      | rock       |
    When I upload the new song
    And I want to search the "song"
    And I search the song in the application
      | Id |
      | 20 |
    Then I should see the song fields
      | song-artist | song-album | song-name |
      | artist      | album      | name      |

  Scenario: Verify that the application allows to upload empty songs
    Given I have a new song to upload
      | Id | song-album | song-artist | song-name | song-genre |
      | 30 |            |             |           |            |
    When I upload the new song
    And I want to search the "song"
    And I search the song in the application
      | Id |
      | 30 |
    Then I should see the song fields
      | song-artist | song-album | song-name |
      |             |            |           |


