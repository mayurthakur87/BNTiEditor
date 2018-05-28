@All @NewInstance
Feature: New instance

	@Positive
  Scenario Outline: Successfully add a New instance name--Positive scenario
    Given Searching  a  new Reference test with "<Name>"
    And The values	"<Reference>","<Type>","<On_Page>"
    When I click on create instance
    Then Instance is created
    And Show instances for name button is displayed

    Examples: 
      | Name          | Reference | Type    | On_Page |
      | Acacia mayur1 | Mayur     | autonym |      19 |
