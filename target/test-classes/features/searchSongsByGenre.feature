Feature: Search by genre
  As a user
  I want to search songs by genre
  So That I can see the songs that belong to that genre


  Scenario Outline: Verify if there are songs by existing genre
    Given I have "genre" endpoint
    When I send a request for a "<genre>"
    Then I see the top 5 song
    Examples:
      | genre   |
      | Hip Hop |
      | Punk    |

  Scenario Outline: Search songs by no existing genre
    Given I have "genre" endpoint
    When I send a request for a "<genres>"
    Then I don't get songs
    Examples:
      | genres    |
      | Vallenato |
      | Salsa     |
      |           |
      | 12345     |
