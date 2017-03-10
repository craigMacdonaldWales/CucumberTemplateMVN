# this scenario could be redundant as the filter functionality is already covered in delete computer feature, but we'll do it anyway.

Feature: filter by name

  Scenario: cancel computer entry
    Given I have created a new computer
    | Name							| Introduced				| Discontinued			| Company		|
    |	FilterMe 	 				|	1900-01-01				|	1925-01-01 				|	Apple Inc. |
	
    When I search for the computer using the name filter
    Then the computer details are displayed
