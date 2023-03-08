@tag
Feature: Sign-up
  sign up should be more precise and quick

  @Unsuccessful
  Scenario Outline: Unsuccessful Sign-Up

  New user should see a message on screen about this.

    Given I have chosen to sign up
    When I sign up with an email address '<email>' that has already registered
    And I sign up with invalid password '<password>' format
    And I sign up with un match password '<password>' passwordMatch '<passwordMatch>' format
    And I sign up with invalid name '<name>' format
    And I sign up with invalid phone '<phone>' format
    Then I should to told to me a '<message>'
    Examples:
      | email                    | password  | passwordMatch | name      | phone      | message                               |
      | mohammadre1654@gmail.com | 1mohammad | 1mohammad     | mohammad  | 0592787026 | Email Duplicate                       |
      | mohammadre1999@gmail.com | 1mohammad | 1mohammad     | mohammad  | 0592787026 | Password shouldn't start with number  |
      | mohammadre1999@gmail.com | mohamm    | mohamm        | mohammad  | 0592787026 | Password length should be more than 7 |
      | mohammadre1999@gmail.com | mohammad1 | mohammad2     | mohammad  | 0592787026 | Password doesn't match                |
      | mohammadre1999@gmail.com | mohammad1 | mohammad1     | 1mohammad | 0592787026 | Name shouldn't start with number      |
      | mohammadre1999@gmail.com | mohammad1 | mohammad1     | 1mohammad | 1292787026 | Phone should start with 05            |

  @Successful
  Scenario Outline: Successful sign-up

    New user should get a confirmation email and greeted
    personally by the site once signed-in.

    Given I have chosen to sign up
    When I sign up with valid details email '<email>' and password '<password>' and passwordMatch '<passwordMatch>' and name '<name>' and phone '<phone>'
    Then I should see a personalized greeting '<message>'
    Examples:
      | email                    | password    | passwordMatch | name      | phone      | message     |
      | mohammadre1999@gmail.com | mohammad1   | mohammad1     | 1mohammad | 0592787026 | Hello There |