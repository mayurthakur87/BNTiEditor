@All @NewPhraseName
Feature: New Phrase name

  @Positive
  Scenario Outline: Successfully add phrase name--Positive scenario
    Given The values at "<Type>", "<Rank>", "<Status>", "<Parent>", "<PName>", "<Author>"
    When I click on save phrase Name
    Then Phrase "<PName>" is added to database

    Examples: 
      | Type        | Rank      | Status | Parent      | PName | Author |
      | phrase name | Subtribus | [n/a]  | Acanthaceae | my4   | mo     |
