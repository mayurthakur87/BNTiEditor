@All @ScientificName
Feature: Add New Scientific Name

	@Positive
  Scenario Outline: Successfully add new name--positive scenario
    Given The values "<Type>","<Rank>", "<Status>", "<Parent>", "<Name>", "<Author>"
    When I click on save
   	Then New Scientific Name is "<Name>" is added to database

    Examples: 
      | Type    | Rank  | Status     | Parent | Name        | Author 	|
      | autonym | Regio | legitimate | acacia | testparent2 | Doepel	|
