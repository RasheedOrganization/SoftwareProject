@tag
Feature: Sign-up
  sign up should be more precise and quickly

  @tag1
  Scenario: Successful sign-up

    New user should get a confirmation email and greeted
    personally by the site once signed-in.

    Given I have chosen to sign up
    When I sign up with valid details
    Then I should receive a confirmation email
    And I should see a personalized greeting message

    Scenario: Duplicate email

      New user should see a message on screen about this.

      Given I have chosen to sign up
      When I sign up with an email address that has already registered
      Then I should to told to me that email has already registered
      And I should be offered the option to recover my password.