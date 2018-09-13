Feature: Search by genre
  As a user
  I want to search songs by genre
  So That I can see the songs that belong to that genre


  Scenario: Search songs by existing genre
    Given I want to search the "genre"
    When I made a search for a genre
      | punk |
    Then I can see the top 5 songs

  Scenario: Search songs by no existing genre
    Given I want to search the "genre"
    When I made a search for a genre
      | Vallenato |
    Then I don't get any song
