Feature: Track the orders for customer
  user should be asked for the his order number
  Scenario: empty information
    When user click on insert order and flag is 'true'
    Then the field 'Order Number' should be with error


  Scenario: a successful Information
    When user click on insert order and flag is 'true'
    And he fill in 'Order Number' with '9'
    And he presses 'search' and flag is 'true'
    Then the information has been entered successfully

  Scenario Outline: errors with input
    When user click on insert order and flag is 'true'
    And he fill in 'Name' with '<Order Number>'
    And he presses 'search' and flag is 'true'
    Then the user should see '<message>'

    Examples:
      | Order Number | message                               |
      | -1           | Order Number must be positive integer |
      | abs          | Invalid Order Number, please check it |
      | 1001         | Order Number less than 10000          |
      | 0            | Order Number must be more than 0      |