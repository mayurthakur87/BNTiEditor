@All @Search
Feature: Search on authors
	@Negative
  Scenario Outline: Search on author fails--Negative Scenario
    Given The User is logged in
    When User selects Authors from search criterion picklist and enters "<Author_Name>"
    And clicks on search
    Then System responds with callout from search box reading Please fill out this field

    Examples: 
      | Author_Name |
      |             |
	@Positive
  Scenario Outline: Search on author --Positive Scenario
    Given The User is logged in
    When User selects Authors from search criterion picklist and enters "<Author_Name>"
    And clicks on search
    Then System responds with results of "<Author_Name>"

    Examples: 
      | Author_Name |
      | Mayur       |

	@Positive
  Scenario Outline: Search on author with WildCard --Positive Scenario
    Given The User is logged in
    When User selects Authors from search criterion picklist and enters "<Author_Name>"
    And clicks on search
    Then System responds with results of all "<Author_Name>"

    Examples: 
      | Author_Name |
      | *           |
	@Negative
  Scenario Outline: Search on References fails--Negative Scenario
    Given The User is logged in
    When User selects References from search criterion picklist and enters "<References_Name>"
    And clicks on search
    Then System responds with callout from search box reading Please fill out this field

    Examples: 
      | References_Name |
      |                 |

	@Positive
  Scenario Outline: Search on References --Positive Scenario
    Given The User is logged in
    When User selects References from search criterion picklist and enters "<References_Name>"
    And clicks on search
    Then System responds with results of "<References_Name>"

    Examples: 
      | References_Name |
      | Mayur           |

	@Positive
  Scenario Outline: Search on References with WildCard --Positive Scenario
    Given The User is logged in
    When User selects References from search criterion picklist and enters "<References_Name>"
    And clicks on search
    Then System responds with results of all "<References_Name>"

    Examples: 
      | References_Name |
      | *               |

	@Negative
  Scenario Outline: Search on Names fails--Negative Scenario
    Given The User is logged in
    When User selects Names from search criterion picklist and enters "<Names_Name>"
    And clicks on search
    Then System responds with callout from search box reading Please fill out this field

    Examples: 
      | Names_Name |
      |            |

	@Positive 
  Scenario Outline: Search on Names --Positive Scenario
    Given The User is logged in
    When User selects Names from search criterion picklist and enters "<Names_Name>"
    And clicks on search
    Then System responds with results of "<Names_Name>"

    Examples: 
      | Names_Name |
      | Mayur      |

	@Positive
  Scenario Outline: Search on Names with WildCard --Positive Scenario
    Given The User is logged in
    When User selects Names from search criterion picklist and enters "<Names_Name>"
    And clicks on search
    Then System responds with results of all "<Names_Name>"

    Examples: 
      | Names_Name |
      | *          |
