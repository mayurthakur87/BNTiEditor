@All @NewReference
Feature: New Reference

  @Positive
  Scenario Outline: Successfully add a new  Reference--Positive scenario
    Given The values "<Type>", "<Title>","<Published>","Author","<Role>"
    When I click on save reference
    Then Reference  "<Title>" is added to the database

    Examples: 
      | Type | Title | Published | Author   | Role    |
      | Book | Mayur12 | Yes       | Dombrain | Author  |
      | Book | Mayur14 | No        | Dombrain | Unknown |
