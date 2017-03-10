Feature: edit existing computer SCAFRA

  Scenario: edit existing computer SCAFRA
    Given I have created a new computer and selected for edit using pre requisite scenario two
    When I edit the computer record
    Then The computer record is is edited