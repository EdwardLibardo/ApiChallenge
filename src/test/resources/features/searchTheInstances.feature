Feature: Search the instances
  As a user
  I want to see all the instances
  So That I can know which are the APIs instances

#  Background:
#    Given I am in the instances API
#
#  Scenario: Search the instances
#
#    When I search for the instances
#    Then I get host, port and storeNames


  Scenario: Search the instances
    Given I have "instances" endpoint
    When I send a request to that endpoint
    Then I should see "host" value
    And I should see "port" value
    And I should see "storeNames" value
    And Response should has a 200 status code

