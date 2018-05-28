@All @NewOther
Feature: New Other name

	@Positive
  Scenario Outline: Successfully add other name--Positive scenario
    Given The values "<Type>","<Status>","<Name>"
    When I click on save Other Name
    Then Other "<Name>" is added to database

    Examples: 
      | Type   | Status | Name      |
      | common | [n/a]  | MAYURTEST30 |