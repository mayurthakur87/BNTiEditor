@All @NewAuthor
Feature: New Author

	@Positive
  Scenario Outline: Successfully add an Author--positive scenario
    Given The values "<Name>", "<Abbreviation>"
    When I click on save author
    Then Author "<Abbreviation>" is added to the database

    Examples: 
      | Name          | Abbreviation |
      | Mayur  Thakur | MT21         |
