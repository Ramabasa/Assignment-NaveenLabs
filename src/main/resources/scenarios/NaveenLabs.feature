@feature1_nl
Feature: Naveen Labs

  @OrderMAcBookForNewUSer
  Scenario Outline: NL_Register a new user and Place a order for MacBook

    Given I am on Naveen Automation Labs page
    When I read the TestData for the Test ID "<TestCaseID>" from the sheet "<SheetName>"
    Then I Register the Account for New User
    Then I Verify the most expensive laptop is macbook pro
    Then I add products to the cart
    Then I Verify the total cost of Items
    And I Logout the application

    Examples:
      |TestCaseID|SheetName|
      |TestUser1 |Sheet1   |
      |TestUser2 |Sheet1   |
      |TestUser3 |Sheet1   |
      |TestUser4 |Sheet1   |
      |TestUser5 |Sheet1   |


  @VerifyUserDetails
  Scenario Outline: NL_Verify User able to login the application

    Given I am on Naveen Automation Labs page
    When I read the TestData for the Test ID "<TestCaseID>" from the sheet "<SheetName>"
    Then I Verify user able to access for "<TestCaseID>"

    Examples:
      |TestCaseID|SheetName|
      |TestUser1 |UserList   |
      |TestUser2 |UserList   |
      |TestUser3 |UserList   |


