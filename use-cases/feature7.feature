Feature: Generate statistics
  As a business owner, i want to be able to generates statistics about total delivered items, total cash, total paid and total debts

  Scenario: valid statistics generate
    Given a database with a list of completed orders
    When a request is made to generate statistics for the year of March 2023
    Then the total number of delivered items for 2023 should be displayed
    And the total cash received for 2023 should be displayed
    And the total amount paid for 2023 should be displayed
    And the total amount debts by customers for 2023 should be displayed

  Scenario: No order for the given year
    Given a database with no completed orders for the year of 2022
    When a request is made to generate statistics for the year of 2022
    Then the message "No completed orders found for 2022" should be displayed

  Scenario: Invalid year entered
    Given a request to generate statistics for an invalid year "2020"
    When the request is made
    Then the message "Invalid Year entered" should be displayed

  Scenario: Negative debts
    Given a database with completed orders for 2023
    When a request is made to generate statistics for 2023
    And the total amount owed by customers is negative
    Then the message "Error: Negative debts found" should be displayed


  Scenario:Empty database
    Given an empty database
    When a request is made to generate statistics for any year
    Then the message "No completed orders found" should be displayed