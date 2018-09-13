Feature: Delete a song in kafka
  As a song producer
  I want to delete a song
  So that all users can not see the song from the API

  Scenario: Verify that the application allows to delete songs
    Given I have a new song to upload
      | Id | song-album | song-artist | song-name | song-genre |
      | 20 | album      | artist      | name      | rock       |
    When I upload the new song
    And I delete the song
      | Id |
      | 20 |
    And I want to search the "song"
    And I search the song in the application
      | Id |
      | 20 |
    Then I should get a response with no songs