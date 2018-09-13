Feature: Search the instances
  As a user
  I want to see all the instances
  So That I can know which are the APIs instances

  Scenario: Search all the instances
    Given I want to search the "instances"
    When I made the search
    Then I should see all the instances


