Feature: Notify the customer by sending email when the order is complete.
  we need to notify the customer by sending email when the order is complete.

  Scenario: Successful email notification
    Given a customer with a valid email address
    And an order that has been completed
    When a request is made to send an email notification to the customer
    Then the email should be sent successfully
    And the customer should receive an email confirming that their order is complete

  Scenario: Invalid email address
    Given a customer with an invalid email address
    And an order that has been completed
    When a request is made to send an email notification to the customer
    Then the message "Invalid email address" should be displayed
    And the email should not be sent

  Scenario: Order not complete
    Given an order that is not yet complete
    When a request is made to send an email notification to the customer
    Then the message "Order not yet complete" should be displayed
    And the email should not be sent

  Scenario: Email service not available
    Given a customer with a valid email address
    And an order that has been completed
    And the email service is not available
    When a request is made to send an email notification to the customer
    Then the message "Email service not available" should be displayed
    And the email should not be sent



  Scenario:Empty customer email address
    Given a customer with an empty email address
    And an order that has been completed
    When a request is made to send an email notification to the customer
    Then the message "Customer email address is empty" should be displayed
    And the email should not be sent