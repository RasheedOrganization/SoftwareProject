@tag
Feature: Sign-In
  sign in should be more precise and quick

  @Unsuccessful
  Scenario Outline: Unsuccessful Sign-In

  New user should see a message on screen about this.

    Given I have chosen to sign in
    When I sign in with an email address does not contain '<email>'
    And I sign in with invalid password '<password>' and valid email '<email>'
    Then I should to told to me a '<message>'
    Examples:
      | email                    | password    | message           |
      | mohammadre1999@gmail.com | 12345678   | Email Not Found    |
      | mohammadre1654@gmail.com | mohammad   | Password not match |

  @Successful
  Scenario Outline: Successful sign-in

  user should get a greeted personally by the site once signed-in.

    Given I have chosen to sign in
    When I sign in with valid details email '<email>' and password '<password>'
    Then I should see a personalized greeting '<message>'
    Examples:
      | email                    | password    | message    |
      | mohammadre1654@gmail.com | 12345678   | Hello There |