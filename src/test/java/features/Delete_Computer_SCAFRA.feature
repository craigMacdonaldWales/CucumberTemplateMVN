Feature: delete computer SCAFRA

  Scenario: delete computer SCAFRA
    Given I have executed pre requisite scenario 1
    And I have recalled the created computer
    When I delete the created computer
    
    Then The computer is deleted