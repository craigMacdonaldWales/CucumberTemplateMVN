Feature: add a new computer

  Scenario: add new computer
    Given I am on the CRUD web portal
    And I have clicked the add a new computer button
    And I have entered computer details
    	| Name							| Introduced				| Discontinued			| Company		|
    	|	AA123456 	 				|	1900-01-01				|	1910-01-01 				|	Tandy Corporation |
	
    	
    When I click create this computer
    Then the computer details I have entered are displayed in the main listing correctly
