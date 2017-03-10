@addnew

Feature: add a new computer SCAFRA

  Scenario: add new computer
    Given I have executed pre requisite scenario 3
    When I click create this computer
    Then the computer details I have entered are displayed in the main listing correctly
