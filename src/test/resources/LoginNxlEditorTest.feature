@All @Login
Feature: Login feature

 	@Positive
  Scenario Outline: open browser and login-- positive scenario
    Given when I open browser
    When put in "<username>" and "<password>"
    Then I should be able to login

    Examples: 
      | username | password |
      | jamie    | password |

  @Negative  
  Scenario Outline: open browser and login-- Negative scenario
    Given when I open browser
    When put in "<username>" and "<password>"
    Then I should not be able to login

    Examples: 
      | username | password |
      | Jamie    | password |
      | jamie    | Password |
      | Jamie    | Password |
      | Mayur    | Thakur   |
      | Jamie    |          |
      |          | password |
      | *        | *        |