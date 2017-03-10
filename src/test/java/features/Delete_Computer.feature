Feature: delete computer

  Scenario: delete computer
    Given I am on the CRUD web portal
    And I have clicked the add a new computer button
    And I have entered computer details
    	| Name							| Introduced				| Discontinued			| Company		|
    	|	AA123456 	 				|	1900-01-01				|	1910-01-01 				|	Tandy Corporation |
    
    And I click create this computer
    And I have recalled the created computer
    When I delete the created computer
    
    Then The computer is deleted