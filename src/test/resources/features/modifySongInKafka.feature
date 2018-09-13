Feature: Modify a song in kafka
  As a song producer
  I want to modify a song
  So that all users can see the song changes from the API

  Scenario: Verify that the application allows to modify songs
    Given I have song Uploaded
      | Id | song-album | song-artist | song-name | song-genre |
      | 20 | album      | artist      | name      | rock       |
    And I modify the song
      | Id | song-album | song-artist | song-name | song-genre |
      | 20 | album-mod  | artist-mod  | name-mod  | rock       |
    When I upload the new song
    And I want to search the "song"
    And I search the song in the application
      | Id |
      | 20 |
    Then I should see the song fields
      | song-artist | song-album | song-name |
      | artist-mod  | album-mod  | name-mod  |