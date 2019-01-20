Feature: user login to account
  @temp
  Scenario: user can login his/her account by using valid credentials
    Given the user is on the home page
    Then the user clicks on sign in button
    Then username and password sections should be visible
    Then user enters valid username and password
    And user clicks on sign in button
    Then title should contain My Account