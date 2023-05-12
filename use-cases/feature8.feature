Feature: Distribute the orders on the available workers.

  Scenario: Valid order distribution
    Given a list of 20 orders and 5 available workers
    When a request is made to distribute the orders
    Then each worker should receive at least 3 orders
    And the orders should be distributed in a fair and balanced way

  Scenario: Fewer orders than worker
    Given a list of 3 orders and 5 available workers
    When a request is made to distribute the orders
    Then each worker should receive at least 1 order
    And 2 workers should not receive any orders

  Scenario: More orders than worker
    Given a list of 15 orders and 5 available workers
    When a request is made to distribute the orders
    Then each worker should receive at least 3 orders
    And the remaining orders should be distributed in a fair and balanced way

  Scenario: No available worker
    Given a list of 10 orders and no available workers
    When a request is made to distribute the orders
    Then the message "No available workers" should be displayed

  Scenario: Empty order list
    Given an empty order list and 5 available workers
    When a request is made to distribute the orders
    Then the message "No orders to distribute" should be displayed
